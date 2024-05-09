public class Producto {
    private String nombreDelproduco;
    private String descripcionDelProducto;
    private double precioDelProducto;
    private int cantidad;
    private String materialDelProducto;
    private String ejemploDeUso;
    private String HerramientaParaUsar;
    
    public Producto(String nombreDelproduco, String descripcionDelProducto, double precioDelProducto, int cantidad,
        String materialDelProducto, String ejemploDeUso, String herramientaParaUsar) {
        
        this.nombreDelproduco = nombreDelproduco;
        this.descripcionDelProducto = descripcionDelProducto;
        this.precioDelProducto = precioDelProducto;
        this.cantidad = cantidad;
        this.materialDelProducto = materialDelProducto;
        this.ejemploDeUso = ejemploDeUso;
        this.HerramientaParaUsar = herramientaParaUsar;

    }

    public String getNombreDelproduco() {
        return nombreDelproduco;
    }

    public void setNombreDelproduco(String nombreDelproduco) {
        this.nombreDelproduco = nombreDelproduco;
    }

    public String getDescripcionDelProducto() {
        return descripcionDelProducto;
    }

    public void setDescripcionDelProducto(String descripcionDelProducto) {
        this.descripcionDelProducto = descripcionDelProducto;
    }

    public double getPrecioDelProducto() {
        return precioDelProducto;
    }

    public void setPrecioDelProducto(double precioDelProducto) {
        this.precioDelProducto = precioDelProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMaterialDelProducto() {
        return materialDelProducto;
    }

    public void setMaterialDelProducto(String materialDelProducto) {
        this.materialDelProducto = materialDelProducto;
    }

    public String getEjemploDeUso() {
        return ejemploDeUso;
    }

    public void setEjemploDeUso(String ejemploDeUso) {
        this.ejemploDeUso = ejemploDeUso;
    }

    public String getHerramientaParaUsar() {
        return HerramientaParaUsar;
    }

    public void setHerramientaParaUsar(String herramientaParaUsar) {
        HerramientaParaUsar = herramientaParaUsar;
    }
    

    
}