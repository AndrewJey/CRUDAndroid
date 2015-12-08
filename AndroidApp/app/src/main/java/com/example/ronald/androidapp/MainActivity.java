package com.example.ronald.androidapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import Estructuras.Producto;
import Http.Metodos;
import Http.direccionUrl;
import Modelos.ModeloProductos;

public class MainActivity extends AppCompatActivity {
    public static TextView text;
    public static ListView Listaproductos;
    public static ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.HelloText);
        //new GetProductsAsync().execute(direccionUrl.getInstance().url+"productos");
        new GetProductsAsyncMetodos().execute(direccionUrl.getInstance().url + "productos");
    }
}

class GetProductsAsyncMetodos extends AsyncTask<String,Void, ArrayList<Producto>> {
    protected ArrayList<Producto> doInBackground(String... urls) {
        Metodos metodos=new Metodos();
        String resultado;
        resultado= metodos.GetRequest(urls[0],"");
        ModeloProductos productosModel=new ModeloProductos();
        ArrayList<Producto> productos=productosModel.obtProductos(resultado);
        return productos;
    }
    protected void onPostExecute(ArrayList<Producto> productos) {
        String datos="";
        for (int i=0 ; i < productos.size() ; i++){
            datos=datos+productos.get(i).getId() +"-"+
                    productos.get(i).getNombre() +"-"+
                    productos.get(i).getCantidad() +"-"+
                    productos.get(i).getPrecio()+"\n";
        }

        MainActivity.text.setText(datos);
    }
}

/*
class GetProductsAsync extends AsyncTask<String,String, String> {
    @Override
    protected String doInBackground(String... urls) {
        InputStream Input = null;
        HttpURLConnection urlconection = null;
        StringBuffer buffer= new StringBuffer();
        BufferedReader reader = null;
        try {
            URL urlconeect = new URL(urls[0]);
            urlconection = (HttpURLConnection) urlconeect.openConnection();
            urlconection.connect();
            Input = urlconection.getInputStream();

             reader = new BufferedReader(new InputStreamReader(Input));
            String line = "";
            try {
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
            }catch(Exception error){
                direccionUrl.getInstance().error=true;
                direccionUrl.getInstance().ErrorDescripcion=error.getMessage();
            }
        } catch (MalformedURLException e) {
            direccionUrl.getInstance().error=true;
            direccionUrl.getInstance().ErrorDescripcion=e.getMessage();
        } catch (IOException e) {
            direccionUrl.getInstance().error=true;
            direccionUrl.getInstance().ErrorDescripcion=e.getMessage();
        }
        finally {
            if(urlconection != null){
                urlconection.disconnect();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String resultado="";

            resultado=buffer.toString();
       
        return resultado;
    }

    @Override
    protected void onPostExecute(String result) {
       MainActivity.text.setText(result);
    }
}
*/
