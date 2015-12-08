package Modelos;

import org.json.JSONArray;

import java.util.ArrayList;

import Estructuras.Producto;

/**
 * Created by USUARIO-STN-01 on 20/11/2015.
 */
public class ModeloProductos {
    public ArrayList<Producto> obtProductos(String response){
        ArrayList<Producto> productos= new ArrayList<Producto>();
        try {
            JSONArray json= new JSONArray(response);
            String texto="";
            for (int i=0; i<json.length();i++){
                Producto  producto= new Producto(
                        Integer.parseInt(json.getJSONObject(i).getString("id")),
                        json.getJSONObject(i).getString("nombre"),
                        Integer.parseInt(json.getJSONObject(i).getString("cantidad")),
                        Integer.parseInt(json.getJSONObject(i).getString("precio"))
                        );
                productos.add(producto);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return productos;
    }
}
