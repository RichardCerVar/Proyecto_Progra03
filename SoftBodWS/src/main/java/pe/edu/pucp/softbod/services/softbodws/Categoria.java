package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.almacen.CategoriaBO;
import pe.edu.pucp.softbod.model.almacen.CategoriaDTO;

@WebService(serviceName = "Categoria")
public class Categoria {
   
    private CategoriaBO categoriaBO;
    
    public Categoria(){
        this.categoriaBO = new CategoriaBO();
    }
    
    @WebMethod(operationName = "insertarCategoria")
    public Integer insertarCategoria(@WebParam(name = "categoria")CategoriaDTO categoria){
        return this.categoriaBO.insertar(categoria);
    }
    
    @WebMethod(operationName = "eliminarCategoria")
    public Integer eliminarCategoria(@WebParam(name = "categoria")CategoriaDTO categoria){
        return this.categoriaBO.eliminar(categoria);
    }
    
    @WebMethod(operationName = "obtenerCategoriaPorId")
    public CategoriaDTO obtenerCategoriaPorId (@WebParam(name = "categoriaId")Integer categoriaId){
        return this.categoriaBO.obtenerPorId(categoriaId);
    }
    
    @WebMethod(operationName = "listarTodasCategorias")
    public ArrayList<CategoriaDTO> listarTodasCategorias (){
        return this.categoriaBO.listarTodos();
    }
    
    @WebMethod(operationName = "categoriaContieneProductos")
    public Boolean categoriaContieneProductos(@WebParam(name = "categoriaId")Integer categoriaId) {
        return this.categoriaBO.categoriaContieneProductos(categoriaId);
    }
    
    @WebMethod(operationName = "puedeEliminarCategoria")
    public Boolean puedeEliminarCategoria(@WebParam(name = "categoriaId")Integer categoriaId) {
        return this.categoriaBO.puedeEliminarCategoria(categoriaId);
    }
    
    @WebMethod(operationName = "nombreCategoriaYaExiste")
    public Boolean nombreCategoriaYaExiste(@WebParam(name = "descripcion")String descripcion) {
        return this.categoriaBO.nombreCategoriaYaExiste(descripcion);
    }
    
}