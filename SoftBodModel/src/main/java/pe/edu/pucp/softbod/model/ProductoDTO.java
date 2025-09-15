package pe.edu.pucp.softbod.model;

public class ProductoDTO {
    private Integer productoId;
    private Unidad_Medida unidad_medida;
    private Categoria categoria;
    private String nombre;
    private Double precio_unitario;
    private Double stock;
    private Double stockMinimo;

    public ProductoDTO() {
        productoId = null;
        nombre = null;
        precio_unitario = null;
        stock = null;
        stockMinimo = null;
    }

    public ProductoDTO(Integer productoId, Unidad_Medida unidad_Medida, Categoria categoria, String nombre, Double precio_unitario, Double stock, Double stockMinimo) {
        this.productoId = productoId;
        this.unidad_medida = unidad_Medida;
        this.categoria = categoria;
        this.nombre = nombre;
        this.precio_unitario = precio_unitario;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Unidad_Medida getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(Unidad_Medida unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
