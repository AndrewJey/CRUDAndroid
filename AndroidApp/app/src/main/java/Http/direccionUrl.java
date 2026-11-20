package Http;

/**
 * Created by ronald on 14/11/15.
 */
public class direccionUrl {
    public String url = "http://10.0.2.2:8000/";
    public Boolean error = false;
    public String ErrorDescripcion = "";

    private static final direccionUrl INSTANCE = new direccionUrl();

    private direccionUrl() {
    }

    public static direccionUrl getInstance() {
        return INSTANCE;
    }

    public void setBaseUrl(String baseUrl) {
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            return;
        }
        String trimmed = baseUrl.trim();
        this.url = trimmed.endsWith("/") ? trimmed : (trimmed + "/");
    }

    public void Limpiar() {
        this.error = false;
        this.ErrorDescripcion = "";
    }
}
