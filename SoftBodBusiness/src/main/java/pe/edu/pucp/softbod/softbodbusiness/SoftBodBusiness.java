package pe.edu.pucp.softbod.softbodbusiness;

import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.*;
import pe.edu.pucp.softbod.model.*;
import pe.edu.pucp.softbod.model.util.Unidad_Medida;

public class SoftBodBusiness {
    
    private static ProductoBO productoBO;
    private static Integer productoIdInsertado = null;
    
    public static void main(String[] args) {
        productoBO = new ProductoBO();
        
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║        PRUEBAS COMPLETAS DEL ProductoBO - BACKEND ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        
        // CRUD básico
        probarInsertar();
        probarModificar();
        probarObtenerPorId();
        
        // Listar todos
        probarListarTodos();
        probarListarTodosActivos();
        probarListarTodosInactivos();
        
        // Búsquedas por nombre
        probarListarTodosPorNombre();
        probarListarTodosPorNombreParcialActivo();
        probarListarTodosPorNombreParcialInactivo();
        
        // Búsquedas por categoría
        probarListarTodosPorCategoria();
        probarListarTodosActivosPorCategoria();
        probarListarTodosInactivosPorCategoria();
        
        // Búsquedas combinadas (categoría + nombre)
        probarListarTodosPorNombreParcialYcategoria();
        probarListarTodosPorNombreParcialYcategoriaActivo();
        probarListarTodosPorNombreParcialYcategoriaInactivo();
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║           PRUEBAS FINALIZADAS EXITOSAMENTE        ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
    
    // ==================== MÉTODOS DE PRUEBA ====================
    
    private static void probarInsertar() {
        imprimirEncabezado("PRUEBA: insertar(ProductoDTO)");
        
        try {
            // Crear categoría DTO (usando categoría existente ID=1 "Bebidas")
            CategoriaDTO categoria = new CategoriaDTO();
            categoria.setCategoriaId(1);
            categoria.setDescripcion("Bebidas");
            
            // Crear producto nuevo
            ProductoDTO nuevoProducto = new ProductoDTO();
            nuevoProducto.setCategoria(categoria);
            nuevoProducto.setNombre("Producto Prueba Test");
            nuevoProducto.setPrecioUnitario(15.50);
            nuevoProducto.setUnidadMedida(Unidad_Medida.UNIDAD);
            nuevoProducto.setStock(100);
            nuevoProducto.setStockMinimo(10);
            nuevoProducto.setActivo(true);
            
            productoIdInsertado = productoBO.insertar(nuevoProducto);
            
            if (productoIdInsertado != null && productoIdInsertado > 0) {
                System.out.println("✓ Producto insertado exitosamente");
                System.out.println("  ID generado: " + productoIdInsertado);
                System.out.println("  Nombre: " + nuevoProducto.getNombre());
                System.out.println("  Precio: S/ " + nuevoProducto.getPrecioUnitario());
                System.out.println("  Stock: " + nuevoProducto.getStock());
            } else {
                System.out.println("✗ Error al insertar producto");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al insertar: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarModificar() {
        imprimirEncabezado("PRUEBA: modificar(ProductoDTO)");
        
        try {
            // Modificar el producto ID=1 (existente en BD)
            Integer productoIdAModificar = 1;
            ProductoDTO productoExistente = productoBO.obtenerPorId(productoIdAModificar);
            
            if (productoExistente != null) {
                System.out.println("Producto ANTES de modificar:");
                imprimirProducto(productoExistente);
                
                // Modificar valores
                productoExistente.setPrecioUnitario(productoExistente.getPrecioUnitario() + 1.00);
                productoExistente.setStock(productoExistente.getStock() + 5);
                
                Integer resultado = productoBO.modificar(productoExistente);
                
                if (resultado > 0) {
                    System.out.println("\n✓ Producto modificado exitosamente");
                    ProductoDTO productoModificado = productoBO.obtenerPorId(productoIdAModificar);
                    System.out.println("\nProducto DESPUÉS de modificar:");
                    imprimirProducto(productoModificado);
                } else {
                    System.out.println("✗ Error al modificar producto");
                }
            } else {
                System.out.println("✗ No se encontró el producto con ID=" + productoIdAModificar);
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al modificar: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarObtenerPorId() {
        imprimirEncabezado("PRUEBA: obtenerPorId(Integer)");
        
        try {
            Integer productoId = 3; // "Agua Mineral 1L"
            ProductoDTO producto = productoBO.obtenerPorId(productoId);
            
            if (producto != null) {
                System.out.println("✓ Producto encontrado:");
                imprimirProducto(producto);
            } else {
                System.out.println("✗ No se encontró el producto con ID=" + productoId);
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al obtener por ID: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodos() {
        imprimirEncabezado("PRUEBA: listarTodos()");
        
        try {
            ArrayList<ProductoDTO> productos = productoBO.listarTodos();
            
            System.out.println("✓ Total de productos encontrados: " + productos.size());
            System.out.println("\nPrimeros 5 productos:");
            
            int contador = 0;
            for (ProductoDTO p : productos) {
                if (contador >= 5) break;
                System.out.println("\n  Producto #" + (contador + 1) + ":");
                imprimirProductoResumido(p);
                contador++;
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar todos: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosActivos() {
        imprimirEncabezado("PRUEBA: listarTodosActivos()");
        
        try {
            ArrayList<ProductoDTO> productos = productoBO.listarTodosActivos();
            
            System.out.println("✓ Total de productos ACTIVOS: " + productos.size());
            System.out.println("\nPrimeros 3 productos activos:");
            
            int contador = 0;
            for (ProductoDTO p : productos) {
                if (contador >= 3) break;
                System.out.println("\n  Producto #" + (contador + 1) + ":");
                imprimirProductoResumido(p);
                contador++;
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar activos: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosInactivos() {
        imprimirEncabezado("PRUEBA: listarTodosInactivos()");
        
        try {
            ArrayList<ProductoDTO> productos = productoBO.listarTodosInactivos();
            
            System.out.println("✓ Total de productos INACTIVOS: " + productos.size());
            
            if (!productos.isEmpty()) {
                System.out.println("\nProductos inactivos:");
                for (ProductoDTO p : productos) {
                    System.out.println("\n  - " + p.getNombre());
                    System.out.println("    ID: " + p.getProductoId() + " | Activo: " + p.getActivo());
                }
            } else {
                System.out.println("\nNo hay productos inactivos en el sistema");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar inactivos: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosPorNombre() {
        imprimirEncabezado("PRUEBA: listarTodosPorNombre(String)");
        
        try {
            String nombreBuscar = "ol"; // Búsqueda parcial
            ArrayList<ProductoDTO> productos = productoBO.listarTodosPorNombre(nombreBuscar);
            
            System.out.println("✓ Búsqueda: productos con '" + nombreBuscar + "' en el nombre");
            System.out.println("  Total encontrados: " + productos.size());
            
            if (!productos.isEmpty()) {
                System.out.println("\nResultados:");
                for (ProductoDTO p : productos) {
                    System.out.println("\n  - " + p.getNombre());
                    System.out.println("    ID: " + p.getProductoId() + " | Activo: " + p.getActivo());
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar por nombre: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosPorNombreParcialActivo() {
        imprimirEncabezado("PRUEBA: listarTodosPorNombreParcialActivo(String)");
        
        try {
            String nombreBuscar = "a"; // Búsqueda genérica
            ArrayList<ProductoDTO> productos = productoBO.listarTodosPorNombreParcialActivo(nombreBuscar);
            
            System.out.println("✓ Búsqueda: productos ACTIVOS con '" + nombreBuscar + "' en el nombre");
            System.out.println("  Total encontrados: " + productos.size());
            
            if (!productos.isEmpty()) {
                System.out.println("\nPrimeros 5 resultados:");
                int contador = 0;
                for (ProductoDTO p : productos) {
                    if (contador >= 5) break;
                    System.out.println("\n  - " + p.getNombre());
                    System.out.println("    ID: " + p.getProductoId() + " | Precio: S/ " + p.getPrecioUnitario());
                    contador++;
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar por nombre parcial activo: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosPorNombreParcialInactivo() {
        imprimirEncabezado("PRUEBA: listarTodosPorNombreParcialInactivo(String)");
        
        try {
            String nombreBuscar = "i"; // Búsqueda genérica
            ArrayList<ProductoDTO> productos = productoBO.listarTodosPorNombreParcialInactivo(nombreBuscar);
            
            System.out.println("✓ Búsqueda: productos INACTIVOS con '" + nombreBuscar + "' en el nombre");
            System.out.println("  Total encontrados: " + productos.size());
            
            if (!productos.isEmpty()) {
                System.out.println("\nResultados:");
                for (ProductoDTO p : productos) {
                    System.out.println("\n  - " + p.getNombre());
                    System.out.println("    ID: " + p.getProductoId() + " | Activo: " + p.getActivo());
                }
            } else {
                System.out.println("\nNo hay productos inactivos con ese criterio");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar por nombre parcial inactivo: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosPorCategoria() {
        imprimirEncabezado("PRUEBA: listarTodosPorCategoria(String)");
        
        try {
            String nombreCategoria = "Bebidas";
            ArrayList<ProductoDTO> productos = productoBO.listarTodosPorCategoria(nombreCategoria);
            
            System.out.println("✓ Búsqueda: productos de categoría '" + nombreCategoria + "'");
            System.out.println("  Total encontrados: " + productos.size());
            
            if (!productos.isEmpty()) {
                System.out.println("\nPrimeros 5 productos:");
                int contador = 0;
                for (ProductoDTO p : productos) {
                    if (contador >= 5) break;
                    System.out.println("\n  - " + p.getNombre());
                    System.out.println("    Categoría: " + p.getCategoria().getDescripcion());
                    System.out.println("    Activo: " + p.getActivo());
                    contador++;
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar por categoría: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosActivosPorCategoria() {
        imprimirEncabezado("PRUEBA: listarTodosActivosPorCategoria(String)");
        
        try {
            String nombreCategoria = "Snacks";
            ArrayList<ProductoDTO> productos = productoBO.listarTodosActivosPorCategoria(nombreCategoria);
            
            System.out.println("✓ Búsqueda: productos ACTIVOS de categoría '" + nombreCategoria + "'");
            System.out.println("  Total encontrados: " + productos.size());
            
            if (!productos.isEmpty()) {
                System.out.println("\nResultados:");
                for (ProductoDTO p : productos) {
                    System.out.println("\n  - " + p.getNombre());
                    System.out.println("    ID: " + p.getProductoId() + " | Stock: " + p.getStock());
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar activos por categoría: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosInactivosPorCategoria() {
        imprimirEncabezado("PRUEBA: listarTodosInactivosPorCategoria(String)");
        
        try {
            String nombreCategoria = "Limpieza";
            ArrayList<ProductoDTO> productos = productoBO.listarTodosInactivosPorCategoria(nombreCategoria);
            
            System.out.println("✓ Búsqueda: productos INACTIVOS de categoría '" + nombreCategoria + "'");
            System.out.println("  Total encontrados: " + productos.size());
            
            if (!productos.isEmpty()) {
                System.out.println("\nResultados:");
                for (ProductoDTO p : productos) {
                    System.out.println("\n  - " + p.getNombre());
                    System.out.println("    ID: " + p.getProductoId() + " | Activo: " + p.getActivo());
                }
            } else {
                System.out.println("\nNo hay productos inactivos en esta categoría");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar inactivos por categoría: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosPorNombreParcialYcategoria() {
        imprimirEncabezado("PRUEBA: listarTodosPorNombreParcialYcategoria(String, String)");
        
        try {
            String nombreCategoria = "Bebidas";
            String nombreProducto = "a";
            ArrayList<ProductoDTO> productos = productoBO.listarTodosPorNombreParcialYcategoria(nombreCategoria, nombreProducto);
            
            System.out.println("✓ Búsqueda combinada:");
            System.out.println("  Categoría: '" + nombreCategoria + "'");
            System.out.println("  Nombre contiene: '" + nombreProducto + "'");
            System.out.println("  Total encontrados: " + productos.size());
            
            if (!productos.isEmpty()) {
                System.out.println("\nPrimeros 3 resultados:");
                int contador = 0;
                for (ProductoDTO p : productos) {
                    if (contador >= 3) break;
                    System.out.println("\n  - " + p.getNombre());
                    System.out.println("    Categoría: " + p.getCategoria().getDescripcion());
                    System.out.println("    Activo: " + p.getActivo());
                    contador++;
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar por nombre y categoría: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosPorNombreParcialYcategoriaActivo() {
        imprimirEncabezado("PRUEBA: listarTodosPorNombreParcialYcategoriaActivo(String, String)");
        
        try {
            String nombreCategoria = "Lácteos";
            String nombreProducto = "e";
            ArrayList<ProductoDTO> productos = productoBO.listarTodosPorNombreParcialYcategoriaActivo(nombreCategoria, nombreProducto);
            
            System.out.println("✓ Búsqueda combinada (solo ACTIVOS):");
            System.out.println("  Categoría: '" + nombreCategoria + "'");
            System.out.println("  Nombre contiene: '" + nombreProducto + "'");
            System.out.println("  Total encontrados: " + productos.size());
            
            if (!productos.isEmpty()) {
                System.out.println("\nResultados:");
                for (ProductoDTO p : productos) {
                    System.out.println("\n  - " + p.getNombre());
                    System.out.println("    Precio: S/ " + p.getPrecioUnitario() + " | Stock: " + p.getStock());
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar activos por nombre y categoría: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarListarTodosPorNombreParcialYcategoriaInactivo() {
        imprimirEncabezado("PRUEBA: listarTodosPorNombreParcialYcategoriaInactivo(String, String)");
        
        try {
            String nombreCategoria = "Ferretería";
            String nombreProducto = "Clavos";
            ArrayList<ProductoDTO> productos = productoBO.listarTodosPorNombreParcialYcategoriaInactivo(nombreCategoria, nombreProducto);
            
            System.out.println("✓ Búsqueda combinada (solo INACTIVOS):");
            System.out.println("  Categoría: '" + nombreCategoria + "'");
            System.out.println("  Nombre contiene: '" + nombreProducto + "'");
            System.out.println("  Total encontrados: " + productos.size());
            
            if (!productos.isEmpty()) {
                System.out.println("\nResultados:");
                for (ProductoDTO p : productos) {
                    System.out.println("\n  - " + p.getNombre());
                    System.out.println("    ID: " + p.getProductoId() + " | Activo: " + p.getActivo());
                }
            } else {
                System.out.println("\nNo hay productos inactivos con ese criterio");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar inactivos por nombre y categoría: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    // ==================== MÉTODOS AUXILIARES ====================
    
    private static void imprimirEncabezado(String titulo) {
        System.out.println("\n┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│ " + String.format("%-62s", titulo) + " │");
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
    
    private static void imprimirSeparador() {
        System.out.println("\n" + "═".repeat(64) + "\n");
    }
    
    private static void imprimirProducto(ProductoDTO p) {
        System.out.println("  ID: " + p.getProductoId());
        System.out.println("  Nombre: " + p.getNombre());
        System.out.println("  Categoría: " + (p.getCategoria() != null ? p.getCategoria().getDescripcion() : "N/A"));
        System.out.println("  Precio: S/ " + p.getPrecioUnitario());
        System.out.println("  Unidad: " + p.getUnidadMedida());
        System.out.println("  Stock: " + p.getStock() + " (Mínimo: " + p.getStockMinimo() + ")");
        System.out.println("  Activo: " + p.getActivo());
    }
    
    private static void imprimirProductoResumido(ProductoDTO p) {
        System.out.println("    ID: " + p.getProductoId() + " | " + p.getNombre());
        System.out.println("    Precio: S/ " + p.getPrecioUnitario() + " | Stock: " + p.getStock() + " | Activo: " + p.getActivo());
    }
}