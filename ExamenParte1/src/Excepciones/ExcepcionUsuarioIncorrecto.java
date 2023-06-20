package Excepciones;

public class ExcepcionUsuarioIncorrecto extends RuntimeException{
    public ExcepcionUsuarioIncorrecto(String message) {
        super(message);
    }
}
