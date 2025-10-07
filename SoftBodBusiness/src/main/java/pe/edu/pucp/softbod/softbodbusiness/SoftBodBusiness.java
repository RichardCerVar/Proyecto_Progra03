package pe.edu.pucp.softbod.softbodbusiness;

import pe.edu.pucp.softbod.bo.*;
import pe.edu.pucp.softbod.model.*;
import pe.edu.pucp.softbod.model.util.Unidad_Medida;

        
public class SoftBodBusiness  {
    public static void main(String[] args) {
        ////////////////////////////7////
        //PRODUCTOS:
        /////////////////////////////////
        ProductoBO prodBO = new ProductoBO();
        //INSERTAR:
//        ProductoDTO prodDto = new ProductoDTO();
//        prodDto.setActivo(Boolean.TRUE);
//        prodDto.setNombre("Milanesas de Pollo (6u)");
//        prodDto.setPrecioUnitario(10.50);
//        prodDto.setStock(10);
//        prodDto.setStockMinimo(2);
//        prodDto.setUnidadMedida(Unidad_Medida.UNIDAD);
//        CategoriaDTO categDto = new CategoriaDTO();
//        categDto.setCategoriaId(7);
//        prodDto.setCategoria(categDto);
//        Integer idProdGenerado = prodBO.insertar(prodDto);
//        if((int)idProdGenerado!=0){
//            System.out.println("Se inserto el nuevo producto, su id es: "+ idProdGenerado);
//        }else{
//            System.out.println("No se inserto el producto");
//            
//        }
        
        //OBTENER POR ID Y MODIFICAR:
//        ProductoDTO prodDto2 = prodBO.obtenerPorId(41);
//        if(prodDto2 != null){
//            System.out.println("PRODUCTO ANTES:");
//            imprimirDatosProducto(prodDto2);
//        }else{
//            System.out.println("No se inserto el producto");
//            
//        }
//        prodDto2.setNombre("Milanesas De Carne (3u)");//cambio
//        Integer update = prodBO.modificar(prodDto2);
//        if((int)update!=0){
//            System.out.println("PRODUCTO DESPUES:");
//            imprimirDatosProducto(prodDto2);
//        }else{
//            System.out.println("Hubo un error al modificar");
//        }
        

    
        
        
        
        
        
    }
    
    private static void imprimirDatosProducto(ProductoDTO producto) {
        System.out.println("=== PRODUCTO ===");
            System.out.println("ID: " + producto.getProductoId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Precio Unitario: S/ " + producto.getPrecioUnitario());
            System.out.println("Unidad de Medida: " + producto.getUnidadMedida());
            System.out.println("Stock Actual: " + producto.getStock());
            System.out.println("Stock Mínimo: " + producto.getStockMinimo());
            System.out.println("Activo: " + (producto.getActivo() ? "Sí" : "No"));
            System.out.println("------------------------------");
    }
    
}
