package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.almacen.ProductoBO;
import pe.edu.pucp.softbod.model.almacen.CategoriaDTO;
import pe.edu.pucp.softbod.model.almacen.ProductoDTO;
import pe.edu.pucp.softbod.reporte.util.ReporteUtil;


@WebService(serviceName = "Producto")
public class Producto {

    private ProductoBO productoBO;
    
    public Producto(){
        productoBO = new ProductoBO();
    }

    @WebMethod(operationName = "insertarProducto")
    public Integer insertarProducto(@WebParam(name = "categoria") CategoriaDTO categoria,
            @WebParam(name = "nombre") String nombre, @WebParam(name = "precioUnitario") 
            Double precioUnitario,@WebParam(name = "unidadMedida")  String unidadMedida, 
            @WebParam(name = "stock")  Integer stock,@WebParam(name = "stockMinimo")  Integer stockMinimo,
            @WebParam(name = "activo")  Boolean activo) {
        return this.productoBO.insertar(categoria, nombre, precioUnitario, unidadMedida, stock, stockMinimo, activo);
    }

    @WebMethod(operationName = "modificarProducto")
    public Integer modificarProducto(@WebParam(name = "producto") ProductoDTO producto) {
        return this.productoBO.modificar(producto);
    }
    
    @WebMethod(operationName = "eliminarProducto")
    public Integer eliminarProducto(@WebParam(name = "productoId") Integer Idproducto) {
        return this.productoBO.eliminar(Idproducto);
    }

    @WebMethod(operationName = "obtenerProductoPorId")
    public ProductoDTO obtenerProductoPorId(@WebParam(name = "productoId")Integer productoId) {
        return this.productoBO.obtenerPorId(productoId);
    }
    
    @WebMethod(operationName = "listarTodosProductos")
    public ArrayList<ProductoDTO> listarTodosProductos() {
        return this.productoBO.listarTodos();
    }

    @WebMethod(operationName = "listarTodosConFiltroProductos")
    public ArrayList<ProductoDTO> listarTodosProductosActivos(
            @WebParam(name = "activo")Boolean activo,
            @WebParam(name = "categoria")String categoria,
            @WebParam(name = "nombreProducto") String nombreProducto) {
        return this.productoBO.listarProductosConFiltro(activo,categoria,nombreProducto);
    }
    
    @WebMethod(operationName = "reporteDeInventario")
    public byte[] reporteDeInventario(){
        return ReporteUtil.reporteDeInventario();
    }
}