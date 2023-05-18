package BancoAV.Excepciones;

public class ExcepcionContraseñaIncorrecta extends RuntimeException{
    public ExcepcionContraseñaIncorrecta(String message) {
        super(message);
    }
}
