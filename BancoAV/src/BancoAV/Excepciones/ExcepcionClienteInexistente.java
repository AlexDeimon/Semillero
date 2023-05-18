package BancoAV.Excepciones;

public class ExcepcionClienteInexistente extends RuntimeException{
    public ExcepcionClienteInexistente(String message) {
        super(message);
    }
}
