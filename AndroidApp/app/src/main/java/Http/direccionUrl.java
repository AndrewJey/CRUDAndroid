package Http;

/**
 * Created by ronald on 14/11/15.
 */
public class direccionUrl {
    public String url="http://192.168.1.3:8000/";
    public Boolean error;
    public String ErrorDescripcion;

    private static direccionUrl INSTANCE = new direccionUrl();//Se utiliza para asignar la conexion a la base de datos de manera global

    private direccionUrl() {
    }

    public static direccionUrl getInstance() {//Se obtiene la conexion global
        return INSTANCE;
    }

    public void Limpiar(){
        this.error=false;
        this.ErrorDescripcion="";
    }
}
