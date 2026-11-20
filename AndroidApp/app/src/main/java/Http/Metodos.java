package Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by USUARIO-STN-01 on 20/11/2015.
 */
public class Metodos {
    public String GetRequest(String url, String parametros){
        InputStream Input = null;
        HttpURLConnection urlconection = null;
        BufferedReader reader = null;
        String result = "";
        StringBuilder buffer = new StringBuilder();
        try {
            String fullUrl = (parametros != null && !parametros.trim().isEmpty())
                    ? (url + parametros.trim())
                    : url;
            URL urlconect = new URL(fullUrl);
            urlconection = (HttpURLConnection) urlconect.openConnection();
            urlconection.setConnectTimeout(10000);
            urlconection.setReadTimeout(10000);
            urlconection.connect();
            Input = urlconection.getInputStream();
            if (Input != null) {
                reader = new BufferedReader(new InputStreamReader(Input, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                result = buffer.toString();
            }
        } catch (Exception error) {
            direccionUrl.getInstance().error = true;
            direccionUrl.getInstance().ErrorDescripcion = error.getMessage();
            result = error.toString();
        } finally {
            try { if (reader != null) reader.close(); } catch (IOException ignored) {}
            try { if (Input != null) Input.close(); } catch (IOException ignored) {}
            if (urlconection != null) urlconection.disconnect();
        }
        return result;
    }
}
