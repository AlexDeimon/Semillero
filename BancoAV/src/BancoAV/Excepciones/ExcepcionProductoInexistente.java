package BancoAV.Excepciones;

public class ExcepcionProductoInexistente extends RuntimeException{
    public ExcepcionProductoInexistente(String message) {
        super(message);
    }
}
