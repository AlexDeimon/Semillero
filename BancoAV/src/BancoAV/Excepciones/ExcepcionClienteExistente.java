package BancoAV.Excepciones;

public class ExcepcionClienteExistente extends RuntimeException{
    public ExcepcionClienteExistente(String message) {
        super(message);
    }
}
