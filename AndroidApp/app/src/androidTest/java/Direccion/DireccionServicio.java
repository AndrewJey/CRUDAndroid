package Direccion;

/**
 * Created by ronald on 14/11/15.
 */
public class DireccionServicio {
    private static DireccionServicio INSTANCE = new DireccionServicio();//Se utiliza para asignar la conexion a la base de datos de manera global

    private DireccionServicio() {
    }

    public static DireccionServicio getInstance() {//Se obtiene la conexion global
        return INSTANCE;
    }
}
