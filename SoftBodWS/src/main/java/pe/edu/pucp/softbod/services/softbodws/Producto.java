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

    @WebMethod(operationName = "insertar")
    public Integer insertar(@WebParam(name = "producto")ProductoDTO producto) {
        return this.productoBO.insertar(producto);
    }

    @WebMethod(operationName = "modificar")
    public Integer modificar(@WebParam(name = "producto")ProductoDTO producto) {
        return this.productoBO.modificar(producto);
    }

    @WebMethod(operationName = "obtenerPorId")
    public ProductoDTO obtenerPorId(@WebParam(name = "productoId")Integer productoId) {
        return this.productoBO.obtenerPorId(productoId);
    }
    
    @WebMethod(operationName = "listarTodos")
    public ArrayList<ProductoDTO> listarTodos() {
        return this.productoBO.listarTodos();
    }

    @WebMethod(operationName = "listarTodosActivos")
    public ArrayList<ProductoDTO> listarTodosActivos() {
        return this.productoBO.listarTodosActivos();
    }

    @WebMethod(operationName = "listarTodosInactivos")
    public ArrayList<ProductoDTO> listarTodosInactivos() {
        return this.productoBO.listarTodosInactivos();
    }

    @WebMethod(operationName = "listarTodosPorNombre")
    public ArrayList<ProductoDTO> listarTodosPorNombre(@WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombre(nombreProd);
    }

    @WebMethod(operationName = "listarTodosPorNombreParcialActivo")
    public ArrayList<ProductoDTO> listarTodosPorNombreParcialActivo(@WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombreParcialActivo(nombreProd);
    }

    @WebMethod(operationName = "listarTodosPorNombreParcialInactivo")
    public ArrayList<ProductoDTO> listarTodosPorNombreParcialInactivo(@WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombreParcialInactivo(nombreProd);
    }

    @WebMethod(operationName = "listarTodosPorCategoria")
    public ArrayList<ProductoDTO> listarTodosPorCategoria(@WebParam(name = "nameCategoria")String nameCategoria) {
        return this.productoBO.listarTodosPorCategoria(nameCategoria);
    }

    @WebMethod(operationName = "listarTodosActivosPorCategoria")
    public ArrayList<ProductoDTO> listarTodosActivosPorCategoria(@WebParam(name = "nameCategoria")String nameCategoria) {
        return this.productoBO.listarTodosActivosPorCategoria(nameCategoria);
    }
    
    @WebMethod(operationName = "listarTodosInactivosPorCategoria")
    public ArrayList<ProductoDTO> listarTodosInactivosPorCategoria(@WebParam(name = "nameCategoria")String nameCategoria) {
        return this.productoBO.listarTodosInactivosPorCategoria(nameCategoria);
    }
    
    @WebMethod(operationName = "listarTodosPorNombreParcialYcategoria")
    public ArrayList<ProductoDTO> listarTodosPorNombreParcialYcategoria(@WebParam(name = "nameCategoria")String nameCategoria, @WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombreParcialYcategoria(nameCategoria, nombreProd);
    }
    
    @WebMethod(operationName = "listarTodosPorNombreParcialYcategoriaActivo")
    public ArrayList<ProductoDTO> listarTodosPorNombreParcialYcategoriaActivo(@WebParam(name = "nameCategoria")String nameCategoria, @WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombreParcialYcategoriaActivo(nameCategoria, nombreProd);
    }

    @WebMethod(operationName = "listarTodosPorNombreParcialYcategoriaInactivo")
    public ArrayList<ProductoDTO> listarTodosPorNombreParcialYcategoriaInactivo(@WebParam(name = "nameCategoria")String nameCategoria, @WebParam(name = "nombreProd")String nombreProd) {
        return this.productoBO.listarTodosPorNombreParcialYcategoriaInactivo(nameCategoria, nombreProd);
    }

    @WebMethod(operationName = "verificarStockDisponible")
    public Boolean verificarStockDisponible(@WebParam(name = "productoId")Integer productoId, @WebParam(name = "cantidadRequerida")Integer cantidadRequerida) {
        return this.productoBO.verificarStockDisponible(productoId, cantidadRequerida);
    }
    
    @WebMethod(operationName = "calcularValorTotalInventarioActivo")
    public Double calcularValorTotalInventarioActivo() {
        return this.productoBO.calcularValorTotalInventarioActivo();
    }
    
    @WebMethod(operationName = "calcularValorInventarioActivoPorCategoria")
    public Double calcularValorInventarioActivoPorCategoria(@WebParam(name = "nombreCategoria")String nombreCategoria) {
        return this.productoBO.calcularValorInventarioActivoPorCategoria(nombreCategoria);
    }
}
