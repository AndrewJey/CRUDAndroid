package Http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO-STN-01 on 20/11/2015.
 */
public class HttpProductos {
    private static String encodeParam(String value) {
        try {
            return URLEncoder.encode(value == null ? "" : value, "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    private static String safeResponseString(HttpResponse response) {
        if (response == null) return "";
        try {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity, "UTF-8") : response.getStatusLine().toString();
        } catch (Exception e) {
            return response.toString();
        }
    }

    public String RegistrarUsuarioPost(String nombre, int precio, int cantidad) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost(direccionUrl.getInstance().url + "productos");
        HttpResponse response = null;
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>(3);
            params.add(new BasicNameValuePair("nombre", nombre == null ? "" : nombre.trim()));
            params.add(new BasicNameValuePair("cantidad", String.valueOf(cantidad)));
            params.add(new BasicNameValuePair("precio", String.valueOf(precio)));
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            response = httpClient.execute(httpPost, localContext);
        } catch (Exception e) {
            direccionUrl.getInstance().error = true;
            direccionUrl.getInstance().ErrorDescripcion = e.getMessage();
        }
        return safeResponseString(response);
    }

    public String RegistrarUsuarioGet(String nombre, String apellido, String correo_electronico, String contrasena) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpResponse response = null;
        String parametros = "?nombre=" + encodeParam(nombre)
                + "&apellido=" + encodeParam(apellido)
                + "&correo_electronico=" + encodeParam(correo_electronico)
                + "&contrasena=" + encodeParam(contrasena);

        HttpGet httpget = new HttpGet(direccionUrl.getInstance().url + "productos" + parametros);
        try {
            response = httpClient.execute(httpget, localContext);
        } catch (Exception e) {
            direccionUrl.getInstance().error = true;
            direccionUrl.getInstance().ErrorDescripcion = e.getMessage();
        }
        return safeResponseString(response);
    }

    public String ModificarUsuarioGet(int id, String nombre, String apellido, String correo_electronico, String contrasena) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpResponse response = null;
        String parametros = "?id=" + id
                + "&nombre=" + encodeParam(nombre)
                + "&apellido=" + encodeParam(apellido)
                + "&correo_electronico=" + encodeParam(correo_electronico)
                + "&contrasena=" + encodeParam(contrasena);

        HttpGet httpget = new HttpGet(direccionUrl.getInstance().url + "productos" + parametros);
        try {
            response = httpClient.execute(httpget, localContext);
        } catch (Exception e) {
            direccionUrl.getInstance().error = true;
            direccionUrl.getInstance().ErrorDescripcion = e.getMessage();
        }
        return safeResponseString(response);
    }

    public String BorrarUsuarioGet(int id) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpResponse response = null;
        String parametros = "?id=" + id;

        HttpGet httpget = new HttpGet(direccionUrl.getInstance().url + "productos" + parametros);
        try {
            response = httpClient.execute(httpget, localContext);
        } catch (Exception e) {
            direccionUrl.getInstance().error = true;
            direccionUrl.getInstance().ErrorDescripcion = e.getMessage();
        }
        return safeResponseString(response);
    }

    public String leer() {
        HttpClient cliente = new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        HttpGet httpget = new HttpGet(direccionUrl.getInstance().url + "productos");
        String resultado = "";
        try {
            HttpResponse response = cliente.execute(httpget, contexto);
            HttpEntity entity = response != null ? response.getEntity() : null;
            resultado = entity != null ? EntityUtils.toString(entity, "UTF-8") : "";
        } catch (Exception e) {
            direccionUrl.getInstance().error = true;
            direccionUrl.getInstance().ErrorDescripcion = e.getMessage();
        }
        return resultado;
    }

    public ArrayList<String> obtDatosJSON(String response) {
        ArrayList<String> listado = new ArrayList<String>();
        if (response == null || response.trim().isEmpty()) {
            return listado;
        }
        try {
            JSONArray json = new JSONArray(response);
            for (int i = 0; i < json.length(); i++) {
                JSONObject item = json.getJSONObject(i);
                String id = item.optString("id", String.valueOf(i + 1));
                String nombre = item.optString("nombre", "Sin nombre");
                if (item.has("precio") || item.has("cantidad")) {
                    listado.add(id + " - " + nombre
                            + " | Precio: " + item.optInt("precio", 0)
                            + " | Stock: " + item.optInt("cantidad", 0));
                } else {
                    listado.add(id + " - " + nombre
                            + " - " + item.optString("apellido", "")
                            + " - " + item.optString("correo_electronico", ""));
                }
            }
        } catch (Exception e) {
            direccionUrl.getInstance().error = true;
            direccionUrl.getInstance().ErrorDescripcion = e.getMessage();
        }
        return listado;
    }
}
