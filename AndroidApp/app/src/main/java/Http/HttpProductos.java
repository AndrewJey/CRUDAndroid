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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO-STN-01 on 20/11/2015.
 */
public class HttpProductos {
    public String RegistrarUsuarioPost(String nombre, int  precio, int cantidad) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost(direccionUrl.getInstance().url+"productos");
        HttpResponse response = null;
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>(3);
            params.add(new BasicNameValuePair("nombre", nombre));
            params.add(new BasicNameValuePair("cantidad",String.valueOf(cantidad)));
            params.add(new BasicNameValuePair("precio",String.valueOf(precio)));
            params.add(new BasicNameValuePair("METHOD", "POST"));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            response = httpClient.execute(httpPost, localContext);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return response.toString();

    }

    public String RegistrarUsuarioGet(String nombre, String apellido, String correo_electronico,String contrasena) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpResponse response = null;
        String parametros = "?nombre=" + nombre + "&apellido=" + apellido
                + "&correo_electronico="+correo_electronico+"&contrasena="+contrasena;

        HttpGet httpget = new HttpGet(direccionUrl.getInstance().url+"productos");
        try {
            response = httpClient.execute(httpget, localContext);

        } catch (Exception e) {

        }
        return response.toString();
    }
    public String ModificarUsuarioGet(int id,String nombre, String apellido, String correo_electronico,String contrasena) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpResponse response = null;
        String parametros = "?id="+id+"&nombre="+nombre+"&apellido=" + apellido
                + "&correo_electronico="+correo_electronico+"&contrasena="+contrasena;

        HttpGet httpget = new HttpGet(direccionUrl.getInstance().url+"productos");
        try {
            response = httpClient.execute(httpget, localContext);

        } catch (Exception e) {

        }
        return response.toString();
    }
    public String BorrarUsuarioGet(int id) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpResponse response = null;
        String parametros = "?id="+id;

        HttpGet httpget = new HttpGet(direccionUrl.getInstance().url+"productos");
        try {
            response = httpClient.execute(httpget, localContext);

        } catch (Exception e) {

        }
        return response.toString();
    }


    public String leer(){
        HttpClient cliente =new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        HttpGet httpget = new HttpGet(direccionUrl.getInstance().url+"productos");
        String resultado=null;
        try {
            HttpResponse response = cliente.execute(httpget,contexto);
            HttpEntity entity = response.getEntity();
            resultado = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return resultado;
    }

    public ArrayList<String> obtDatosJSON(String response){
        ArrayList<String> listado= new ArrayList<String>();
        try {
            JSONArray json= new JSONArray(response);
            String texto="";
            for (int i=0; i<json.length();i++){
                texto = json.getJSONObject(i).getString("id")+"-"+
                        json.getJSONObject(i).getString("nombre") +" - "+
                        json.getJSONObject(i).getString("apellido") +" - "+
                        json.getJSONObject(i).getString("correo_electronico") +" - "+
                        json.getJSONObject(i).getString("contrasena");
                listado.add(texto);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listado;
    }
}
