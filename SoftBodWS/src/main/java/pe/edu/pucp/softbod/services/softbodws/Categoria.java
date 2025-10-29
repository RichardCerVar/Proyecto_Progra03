/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
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
    
    @WebMethod(operationName = "insertar")
    public Integer insertar(@WebParam(name = "categoria")CategoriaDTO categoria){
        return this.categoriaBO.insertar(categoria);
    }
    
    @WebMethod(operationName = "eliminar")
    public Integer eliminar(CategoriaDTO categoria){
        return this.categoriaBO.eliminar(categoria);
    }
    
    @WebMethod(operationName = "obtenerPorId")
    public CategoriaDTO obtenerPorId (@WebParam(name = "categoriaId")Integer categoriaId){
        return this.categoriaBO.obtenerPorId(categoriaId);
    
    }
    @WebMethod(operationName = "listarTodos")
    public ArrayList<CategoriaDTO> listarTodos (){
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
