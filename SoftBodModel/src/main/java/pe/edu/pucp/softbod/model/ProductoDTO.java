package pe.edu.pucp.softbod.model;

import pe.edu.pucp.softbod.model.util.Unidad_Medida;

public class ProductoDTO {
    private Integer productoId;
    private CategoriaDTO categoria;
    private String nombre;
    private Double precioUnitario;
    private Unidad_Medida unidadMedida;
    private Integer stock;
    private Integer stockMinimo;
    private Boolean activo;

    public ProductoDTO() {
        this.categoria = null;
        this.nombre = null;
        this.precioUnitario = null;
        this.unidadMedida = null;
        this.stock = null;
        this.stockMinimo = null;
        this.activo = null;
    }

    public ProductoDTO(Integer productoId, CategoriaDTO categoria, String nombre, 
                       Double precioUnitario, Unidad_Medida unidadMedida, 
                       Integer stock, Integer stockMinimo, Boolean activo) {
        this.productoId = productoId;
        this.categoria = categoria;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.unidadMedida = unidadMedida;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.activo = activo;
    }

    public Integer getProductoId() { return productoId; }
    public void setProductoId(Integer productoId) { this.productoId = productoId; }
    public CategoriaDTO getCategoria() { return categoria; }
    public void setCategoria(CategoriaDTO categoria) { this.categoria = categoria; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { 
        this.precioUnitario = precioUnitario; 
    }
    public Unidad_Medida getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(Unidad_Medida unidadMedida) { this.unidadMedida = unidadMedida; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
