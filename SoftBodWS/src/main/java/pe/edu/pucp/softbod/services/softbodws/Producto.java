package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.almacen.ProductoBO;
import pe.edu.pucp.softbod.model.almacen.ProductoDTO;


@WebService(serviceName = "Producto")
public class Producto {

    private ProductoBO productoBO;
    
    public Producto(){
        productoBO = new ProductoBO();
    }

    @WebMethod(operationName = "insertarProducto")
    public Integer insertarProducto(@WebParam(name = "producto")ProductoDTO producto) {
        return this.productoBO.insertar(producto);
    }

    @WebMethod(operationName = "modificarProducto")
    public Integer modificarProducto(@WebParam(name = "producto")ProductoDTO producto) {
        return this.productoBO.modificar(producto);
    }

    @WebMethod(operationName = "obtenerProductoPorId")
    public ProductoDTO obtenerProductoPorId(@WebParam(name = "productoId")Integer productoId) {
        return this.productoBO.obtenerPorId(productoId);
    }
    
    @WebMethod(operationName = "listarTodosProductos")
    public ArrayList<ProductoDTO> listarTodosProductos() {
        return this.productoBO.listarTodos();
    }

    @WebMethod(operationName = "listarTodosProductosActivos")
    public ArrayList<ProductoDTO> listarTodosProductosActivos() {
        return this.productoBO.listarTodosActivos();
    }

    @WebMethod(operationName = "listarTodosProductosInactivos")
    public ArrayList<ProductoDTO> listarTodosProductosInactivos() {
        return this.productoBO.listarTodosInactivos();
    }

    @WebMethod(operationName = "listarTodosProductosPorNombre")
    public ArrayList<ProductoDTO> listarTodosProductosPorNombre(@WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombre(nombreProd);
    }

    @WebMethod(operationName = "listarTodosProductosPorNombreParcialActivo")
    public ArrayList<ProductoDTO> listarTodosProductosPorNombreParcialActivo(@WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombreParcialActivo(nombreProd);
    }

    @WebMethod(operationName = "listarTodosProductosPorNombreParcialInactivo")
    public ArrayList<ProductoDTO> listarTodosProductosPorNombreParcialInactivo(@WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombreParcialInactivo(nombreProd);
    }

    @WebMethod(operationName = "listarTodosProductosPorCategoria")
    public ArrayList<ProductoDTO> listarTodosProductosPorCategoria(@WebParam(name = "nameCategoria")String nameCategoria) {
        return this.productoBO.listarTodosPorCategoria(nameCategoria);
    }

    @WebMethod(operationName = "listarTodosProductosActivosPorCategoria")
    public ArrayList<ProductoDTO> listarTodosProductosActivosPorCategoria(@WebParam(name = "nameCategoria")String nameCategoria) {
        return this.productoBO.listarTodosActivosPorCategoria(nameCategoria);
    }
    
    @WebMethod(operationName = "listarTodosProductosInactivosPorCategoria")
    public ArrayList<ProductoDTO> listarTodosProductosInactivosPorCategoria(@WebParam(name = "nameCategoria")String nameCategoria) {
        return this.productoBO.listarTodosInactivosPorCategoria(nameCategoria);
    }
    
    @WebMethod(operationName = "listarTodosProductosPorNombreParcialYcategoria")
    public ArrayList<ProductoDTO> listarTodosProductosPorNombreParcialYcategoria(@WebParam(name = "nameCategoria")String nameCategoria, @WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombreParcialYcategoria(nameCategoria, nombreProd);
    }
    
    @WebMethod(operationName = "listarTodosProductosPorNombreParcialYcategoriaActivo")
    public ArrayList<ProductoDTO> listarTodosProductosPorNombreParcialYcategoriaActivo(@WebParam(name = "nameCategoria")String nameCategoria, @WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombreParcialYcategoriaActivo(nameCategoria, nombreProd);
    }

    @WebMethod(operationName = "listarTodosProductosPorNombreParcialYcategoriaInactivo")
    public ArrayList<ProductoDTO> listarTodosProductosPorNombreParcialYcategoriaInactivo(@WebParam(name = "nameCategoria")String nameCategoria, @WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombreParcialYcategoriaInactivo(nameCategoria, nombreProd);
    }
}