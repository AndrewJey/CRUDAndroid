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
        String result="";
        StringBuffer buffer= new StringBuffer();
        try {
            URL urlconect = new URL(url);
            urlconection = (HttpURLConnection) urlconect.openConnection();
            urlconection.connect();
            Input = urlconection.getInputStream();
            if(Input != null) {
                reader = new BufferedReader(new InputStreamReader(Input));
                String line = "";
                try {
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    result = buffer.toString();
                } catch (Exception error) {
                    result = error.toString();
                }
            }
        }catch (Exception error){
            result = error.toString();
        }
        return result;
    }
}
