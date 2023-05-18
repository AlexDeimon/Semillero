package BancoAV.Modelos;

public abstract class Producto {
    private String productoId;
    private String clienteId;
    private String producto;

    public Producto(String productoId, String clienteId, String producto) {
        this.productoId = productoId;
        this.clienteId = clienteId;
        this.producto = producto;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {return productoId + ',' + clienteId + ',' + producto;}
}
