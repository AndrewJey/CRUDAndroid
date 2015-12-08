package Estructuras;

/**
 * Created by ronald on 14/11/15.
 */
public class Productos {
    public int id;
    public String Nombre;
    public int precio;
    public int cantidad;

    public Productos(int id,String Nombre,int precio,int cantidad) {
        this.id=id;
        this.Nombre=Nombre;
        this.precio=precio;
        this.cantidad=cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
