package pe.edu.pucp.softbod.softbodbusiness;

import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.*;
import pe.edu.pucp.softbod.model.*;
import pe.edu.pucp.softbod.model.util.Unidad_Medida;
import pe.edu.pucp.softbod.model.util.Tipo_Usuario;
import java.sql.Date;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;

public class SoftBodBusiness {
    
    private static ProductoBO productoBO;
    private static Integer productoIdInsertado = null;
    private static RazonDevolucionBO razonDevolucionBO;
    private static Integer razonDevolucionIdInsertado = null;
    private static UsuarioBO usuarioBO;
    private static Integer usuarioIdInsertado = null;
    private static VentaBO ventaBO;
    private static DetalleVentaBO detalleVentaBO;
    private static Integer ventaIdInsertado = null;
    
    public static void main(String[] args) {
        productoBO = new ProductoBO();
        
//        System.out.println("╔════════════════════════════════════════════════════════════════╗");
//        System.out.println("║        PRUEBAS COMPLETAS DEL ProductoBO - BACKEND ║");
//        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
//        
//        // CRUD básico
//        probarInsertar();
//        probarModificar();
//        probarObtenerPorId();
//        
//        // Listar todos
//        probarListarTodos();
//        probarListarTodosActivos();
//        probarListarTodosInactivos();
//        
//        // Búsquedas por nombre
//        probarListarTodosPorNombre();
//        probarListarTodosPorNombreParcialActivo();
//        probarListarTodosPorNombreParcialInactivo();
//        
//        // Búsquedas por categoría
//        probarListarTodosPorCategoria();
//        probarListarTodosActivosPorCategoria();
//        probarListarTodosInactivosPorCategoria();
//        
//        // Búsquedas combinadas (categoría + nombre)
//        probarListarTodosPorNombreParcialYcategoria();
//        probarListarTodosPorNombreParcialYcategoriaActivo();
//        probarListarTodosPorNombreParcialYcategoriaInactivo();
//        imprimirSeparador();
//        System.out.println("╔════════════════════════════════════════════════════════════════╗");
//        System.out.println("║ PRUEBAS COMPLETAS DEL RazonDevolucionBO - BACKEND ║");
//        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
//        
//        razonDevolucionBO = new RazonDevolucionBO();
//        // Pruebas RazonDevolucion
//        probarRazonDevolucionInsertar();
//        probarRazonDevolucionEliminar();
//        probarRazonDevolucionObtenerPorId();
//        probarRazonDevolucionListarTodos();
//        probarRazonDevolucionListarTodosPorNombreParcial();
        
//        imprimirSeparador();
//        System.out.println("╔════════════════════════════════════════════════════════════════╗");
//        System.out.println("║ PRUEBAS COMPLETAS DEL UsuarioBO - BACKEND         ║");
//        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
//        
//        usuarioBO = new UsuarioBO();
//        // Pruebas Usuario
//        probarUsuarioInsertar();
//        probarUsuarioModificar();
//        probarUsuarioEliminarLogico();
//        probarUsuarioObtenerPorId();
//        probarUsuarioObtenerCuenta();
//        probarUsuarioObtenerPorCorreo();
//        probarUsuarioListarTodos();
//        probarUsuarioListarActivos();
//        probarUsuarioListarInactivos();
//        probarUsuarioListarPorNombreParcial();
        
        imprimirSeparador();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║ PRUEBAS COMPLETAS DE VentaBO - BACKEND            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

        ventaBO = new VentaBO();
        detalleVentaBO = new DetalleVentaBO();
        //PRUEBAS VENTA
        probarVentaInsertar();
        probarVentaObtenerPorId();
        probarVentaListarTodos();
        probarVentaListarTodosPorFecha();
        
        
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
    
    //RAZON DEVOLUCION:
    private static void probarRazonDevolucionInsertar() {
        imprimirEncabezado("PRUEBA: RazonDevolucionBO - insertar(RazonDevolucionDTO)");

        try {
            // Crear nueva razón de devolución
            RazonDevolucionDTO nuevaRazon = new RazonDevolucionDTO();
            nuevaRazon.setDescripcion("Razon de Prueba Test Backend");

            razonDevolucionIdInsertado = razonDevolucionBO.insertar(nuevaRazon);

            if (razonDevolucionIdInsertado != null && razonDevolucionIdInsertado > 0) {
                System.out.println("✓ Razón de devolución insertada exitosamente");
                System.out.println("  ID generado: " + razonDevolucionIdInsertado);
                System.out.println("  Descripción: " + nuevaRazon.getDescripcion());
            } else {
                System.out.println("✗ Error al insertar razón de devolución");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al insertar: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarRazonDevolucionEliminar() {
        imprimirEncabezado("PRUEBA: RazonDevolucionBO - eliminar(RazonDevolucionDTO)");

        try {
            // Verificar si se insertó una razón previamente
            if (razonDevolucionIdInsertado != null && razonDevolucionIdInsertado > 0) {
                // Obtener la razón insertada
                RazonDevolucionDTO razonAEliminar = razonDevolucionBO.obtenerPorId(razonDevolucionIdInsertado);

                if (razonAEliminar != null && razonAEliminar.getRazonDevolucionId() != null) {
                    System.out.println("Razón a eliminar:");
                    System.out.println("  ID: " + razonAEliminar.getRazonDevolucionId());
                    System.out.println("  Descripción: " + razonAEliminar.getDescripcion());

                    // Eliminar
                    Integer resultado = razonDevolucionBO.eliminar(razonAEliminar);

                    if (resultado > 0) {
                        System.out.println("\n✓ Razón de devolución eliminada exitosamente");

                        // Verificar que ya no existe
                        RazonDevolucionDTO verificacion = razonDevolucionBO.obtenerPorId(razonDevolucionIdInsertado);
                        if (verificacion == null || verificacion.getRazonDevolucionId() == null) {
                            System.out.println("✓ Verificación: La razón ya no existe en la BD");
                        }
                    } else {
                        System.out.println("✗ Error al eliminar razón de devolución");
                    }
                } else {
                    System.out.println("✗ No se pudo obtener la razón para eliminar");
                }
            } else {
                System.out.println("⚠ No hay razón insertada para eliminar");
                System.out.println("  Se procederá a eliminar la razón con ID=1 como alternativa");

                // Eliminar razón con ID=1 (Producto dañado)
                RazonDevolucionDTO razonAlternativa = new RazonDevolucionDTO();
                razonAlternativa.setRazonDevolucionId(1);
                razonAlternativa.setDescripcion("Producto dañado");

                Integer resultado = razonDevolucionBO.eliminar(razonAlternativa);

                if (resultado > 0) {
                    System.out.println("✓ Razón con ID=1 eliminada exitosamente (prueba alternativa)");
                } else {
                    System.out.println("✗ Error al eliminar razón con ID=1");
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al eliminar: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarRazonDevolucionObtenerPorId() {
        imprimirEncabezado("PRUEBA: RazonDevolucionBO - obtenerPorId(Integer)");

        try {
            // Obtener razón con ID=2 ("Fecha de caducidad vencida")
            Integer razonId = 2;
            RazonDevolucionDTO razon = razonDevolucionBO.obtenerPorId(razonId);

            if (razon != null && razon.getRazonDevolucionId() != null) {
                System.out.println("✓ Razón de devolución encontrada:");
                System.out.println("  ID: " + razon.getRazonDevolucionId());
                System.out.println("  Descripción: " + razon.getDescripcion());
            } else {
                System.out.println("✗ No se encontró la razón con ID=" + razonId);
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al obtener por ID: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarRazonDevolucionListarTodos() {
        imprimirEncabezado("PRUEBA: RazonDevolucionBO - listarTodos()");

        try {
            ArrayList<RazonDevolucionDTO> razones = razonDevolucionBO.listarTodos();

            System.out.println("✓ Total de razones de devolución encontradas: " + razones.size());

            if (!razones.isEmpty()) {
                System.out.println("\nTodas las razones de devolución:");
                System.out.println("─".repeat(60));

                int contador = 0;
                for (RazonDevolucionDTO r : razones) {
                    contador++;
                    System.out.println("\n  " + contador + ". ID: " + r.getRazonDevolucionId());
                    System.out.println("     Descripción: " + r.getDescripcion());
                }
                System.out.println("\n" + "─".repeat(60));
            } else {
                System.out.println("\n⚠ No hay razones de devolución en el sistema");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar todos: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarRazonDevolucionListarTodosPorNombreParcial() {
        imprimirEncabezado("PRUEBA: RazonDevolucionBO - listarTodosPorNombreParcial(String)");

        try {
            // Prueba 1: Búsqueda con "cliente"
            String textoBusqueda1 = "cliente";
            System.out.println("═══ Búsqueda 1: Razones con '" + textoBusqueda1 + "' ═══\n");
            ArrayList<RazonDevolucionDTO> razones1 = razonDevolucionBO.listarTodosPorNombreParcial(textoBusqueda1);

            System.out.println("✓ Total encontrado: " + razones1.size());
            if (!razones1.isEmpty()) {
                System.out.println("\nResultados:");
                for (RazonDevolucionDTO r : razones1) {
                    System.out.println("  • ID: " + r.getRazonDevolucionId() + 
                                     " | " + r.getDescripcion());
                }
            }

            // Prueba 2: Búsqueda con "producto"
            String textoBusqueda2 = "producto";
            System.out.println("\n\n═══ Búsqueda 2: Razones con '" + textoBusqueda2 + "' ═══\n");
            ArrayList<RazonDevolucionDTO> razones2 = razonDevolucionBO.listarTodosPorNombreParcial(textoBusqueda2);

            System.out.println("✓ Total encontrado: " + razones2.size());
            if (!razones2.isEmpty()) {
                System.out.println("\nResultados:");
                for (RazonDevolucionDTO r : razones2) {
                    System.out.println("  • ID: " + r.getRazonDevolucionId() + 
                                     " | " + r.getDescripcion());
                }
            }

            // Prueba 3: Búsqueda con "error"
            String textoBusqueda3 = "error";
            System.out.println("\n\n═══ Búsqueda 3: Razones con '" + textoBusqueda3 + "' ═══\n");
            ArrayList<RazonDevolucionDTO> razones3 = razonDevolucionBO.listarTodosPorNombreParcial(textoBusqueda3);

            System.out.println("✓ Total encontrado: " + razones3.size());
            if (!razones3.isEmpty()) {
                System.out.println("\nResultados:");
                for (RazonDevolucionDTO r : razones3) {
                    System.out.println("  • ID: " + r.getRazonDevolucionId() + 
                                     " | " + r.getDescripcion());
                }
            } else {
                System.out.println("\n⚠ No se encontraron razones con ese criterio");
            }

            // Prueba 4: Búsqueda genérica con "de"
            String textoBusqueda4 = "de";
            System.out.println("\n\n═══ Búsqueda 4: Razones con '" + textoBusqueda4 + "' (genérica) ═══\n");
            ArrayList<RazonDevolucionDTO> razones4 = razonDevolucionBO.listarTodosPorNombreParcial(textoBusqueda4);

            System.out.println("✓ Total encontrado: " + razones4.size());
            System.out.println("\nPrimeras 5 razones:");
            int contador = 0;
            for (RazonDevolucionDTO r : razones4) {
                if (contador >= 5) break;
                System.out.println("  • ID: " + r.getRazonDevolucionId() + 
                                 " | " + r.getDescripcion());
                contador++;
            }

        } catch (Exception e) {
            System.out.println("✗ Excepción al listar por nombre parcial: " + e.getMessage());
        }

        imprimirSeparador();
    }
    
    
    // ==================== MÉTODOS DE PRUEBA USUARIO ====================

    private static void probarUsuarioInsertar() {
        imprimirEncabezado("PRUEBA: UsuarioBO - insertar(UsuarioDTO)");

        try {
            // Crear nuevo usuario
            UsuarioDTO nuevoUsuario = new UsuarioDTO();
            nuevoUsuario.setUsuario("test_usuario");
            nuevoUsuario.setTipoUsuarios(Tipo_Usuario.OPERARIO);
            nuevoUsuario.setCorreo("test@bodega.com");
            nuevoUsuario.setContrasenha("test123");
            nuevoUsuario.setNombre("Usuario de Prueba Test");
            nuevoUsuario.setTelefono("999888777");
            nuevoUsuario.setActivo(true);

            usuarioIdInsertado = usuarioBO.insertar(nuevoUsuario);

            if (usuarioIdInsertado != null && usuarioIdInsertado > 0) {
                System.out.println("✓ Usuario insertado exitosamente");
                System.out.println("  ID generado: " + usuarioIdInsertado);
                System.out.println("  Usuario: " + nuevoUsuario.getUsuario());
                System.out.println("  Tipo: " + nuevoUsuario.getTipoUsuarios());
                System.out.println("  Correo: " + nuevoUsuario.getCorreo());
                System.out.println("  Nombre: " + nuevoUsuario.getNombre());
                System.out.println("  Teléfono: " + nuevoUsuario.getTelefono());
                System.out.println("  Activo: " + nuevoUsuario.getActivo());
            } else {
                System.out.println("✗ Error al insertar usuario");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al insertar: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarUsuarioModificar() {
        imprimirEncabezado("PRUEBA: UsuarioBO - modificar(UsuarioDTO)");

        try {
            // Modificar el usuario ID=6 (op_caja1 - Ana Gomez)
            Integer usuarioIdAModificar = 6;
            UsuarioDTO usuarioExistente = usuarioBO.obtenerPorId(usuarioIdAModificar);

            if (usuarioExistente != null && usuarioExistente.getUsuarioId() != null) {
                System.out.println("Usuario ANTES de modificar:");
                imprimirUsuario(usuarioExistente);

                // Modificar valores
                String telefonoOriginal = usuarioExistente.getTelefono();
                usuarioExistente.setTelefono("911222333");
                usuarioExistente.setNombre(usuarioExistente.getNombre() + " [MODIFICADO]");

                Integer resultado = usuarioBO.modificar(usuarioExistente);

                if (resultado > 0) {
                    System.out.println("\n✓ Usuario modificado exitosamente");
                    UsuarioDTO usuarioModificado = usuarioBO.obtenerPorId(usuarioIdAModificar);
                    System.out.println("\nUsuario DESPUÉS de modificar:");
                    imprimirUsuario(usuarioModificado);

                    // Restaurar valores originales
                    usuarioModificado.setTelefono(telefonoOriginal);
                    usuarioModificado.setNombre(usuarioModificado.getNombre().replace(" [MODIFICADO]", ""));
                    usuarioBO.modificar(usuarioModificado);
                    System.out.println("\n✓ Usuario restaurado a valores originales");
                } else {
                    System.out.println("✗ Error al modificar usuario");
                }
            } else {
                System.out.println("✗ No se encontró el usuario con ID=" + usuarioIdAModificar);
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al modificar: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarUsuarioEliminarLogico() {
        imprimirEncabezado("PRUEBA: UsuarioBO - eliminarLogicoUsuario(UsuarioDTO)");

        try {
            // Usar el usuario insertado o el ID=10 como alternativa
            Integer usuarioIdAEliminar = (usuarioIdInsertado != null && usuarioIdInsertado > 0) 
                                          ? usuarioIdInsertado : 10;

            UsuarioDTO usuarioAEliminar = usuarioBO.obtenerPorId(usuarioIdAEliminar);

            if (usuarioAEliminar != null && usuarioAEliminar.getUsuarioId() != null) {
                System.out.println("Usuario ANTES de eliminar lógicamente:");
                System.out.println("  ID: " + usuarioAEliminar.getUsuarioId());
                System.out.println("  Usuario: " + usuarioAEliminar.getUsuario());
                System.out.println("  Nombre: " + usuarioAEliminar.getNombre());
                System.out.println("  Activo: " + usuarioAEliminar.getActivo());

                Integer resultado = usuarioBO.eliminarLogicoUsuario(usuarioAEliminar);

                if (resultado > 0) {
                    System.out.println("\n✓ Usuario eliminado lógicamente (marcado como inactivo)");

                    // Verificar el cambio
                    UsuarioDTO usuarioVerificacion = usuarioBO.obtenerPorId(usuarioIdAEliminar);
                    System.out.println("\nUsuario DESPUÉS de eliminar:");
                    System.out.println("  ID: " + usuarioVerificacion.getUsuarioId());
                    System.out.println("  Usuario: " + usuarioVerificacion.getUsuario());
                    System.out.println("  Activo: " + usuarioVerificacion.getActivo());

                    if (!usuarioVerificacion.getActivo()) {
                        System.out.println("\n✓ Verificación exitosa: Usuario marcado como INACTIVO");
                    }
                } else {
                    System.out.println("✗ Error al eliminar lógicamente el usuario");
                }
            } else {
                System.out.println("✗ No se encontró el usuario con ID=" + usuarioIdAEliminar);
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al eliminar lógicamente: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarUsuarioObtenerPorId() {
        imprimirEncabezado("PRUEBA: UsuarioBO - obtenerPorId(Integer)");

        try {
            // Obtener usuario ID=1 (adm_jefe - Javier Lopez Jefe)
            Integer usuarioId = 1;
            UsuarioDTO usuario = usuarioBO.obtenerPorId(usuarioId);

            if (usuario != null && usuario.getUsuarioId() != null) {
                System.out.println("✓ Usuario encontrado:");
                imprimirUsuario(usuario);
            } else {
                System.out.println("✗ No se encontró el usuario con ID=" + usuarioId);
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al obtener por ID: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarUsuarioObtenerCuenta() {
        imprimirEncabezado("PRUEBA: UsuarioBO - obtenerCuenta(String, String)");

        try {
            // Intentar login con credenciales correctas
            String correo1 = "jefe@bodega.com";
            String contrasenha1 = "admin_A1";

            System.out.println("═══ Prueba 1: Credenciales CORRECTAS ═══");
            System.out.println("Correo: " + correo1);
            System.out.println("Contraseña: " + contrasenha1);

            UsuarioDTO usuario1 = usuarioBO.obtenerCuenta(correo1, contrasenha1);

            if (usuario1 != null && usuario1.getUsuarioId() != null) {
                System.out.println("\n✓ Login exitoso:");
                System.out.println("  ID: " + usuario1.getUsuarioId());
                System.out.println("  Usuario: " + usuario1.getUsuario());
                System.out.println("  Tipo: " + usuario1.getTipoUsuarios());
                System.out.println("  Nombre: " + usuario1.getNombre());
                System.out.println("  Activo: " + usuario1.getActivo());
            } else {
                System.out.println("✗ Credenciales inválidas o usuario no encontrado");
            }

            // Intentar login con credenciales incorrectas
            String correo2 = "jefe@bodega.com";
            String contrasenha2 = "password_incorrecta";

            System.out.println("\n\n═══ Prueba 2: Credenciales INCORRECTAS ═══");
            System.out.println("Correo: " + correo2);
            System.out.println("Contraseña: " + contrasenha2);

            UsuarioDTO usuario2 = usuarioBO.obtenerCuenta(correo2, contrasenha2);

            if (usuario2 == null || usuario2.getUsuarioId() == null) {
                System.out.println("\n✓ Validación correcta: No se permite el acceso con credenciales incorrectas");
            } else {
                System.out.println("✗ Error de seguridad: Se permitió el acceso con credenciales incorrectas");
            }

            // Intentar login con correo inexistente
            String correo3 = "noexiste@bodega.com";
            String contrasenha3 = "cualquier_password";

            System.out.println("\n\n═══ Prueba 3: Usuario INEXISTENTE ═══");
            System.out.println("Correo: " + correo3);
            System.out.println("Contraseña: " + contrasenha3);

            UsuarioDTO usuario3 = usuarioBO.obtenerCuenta(correo3, contrasenha3);

            if (usuario3 == null || usuario3.getUsuarioId() == null) {
                System.out.println("\n✓ Validación correcta: Usuario inexistente rechazado");
            } else {
                System.out.println("✗ Error: Se encontró un usuario que no debería existir");
            }

        } catch (Exception e) {
            System.out.println("✗ Excepción al obtener cuenta: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarUsuarioObtenerPorCorreo() {
        imprimirEncabezado("PRUEBA: UsuarioBO - obtenerPorCorreo(String)");

        try {
            // Buscar por correo existente
            String correo1 = "caja1@bodega.com";
            System.out.println("═══ Búsqueda 1: Correo existente ═══");
            System.out.println("Correo: " + correo1);

            UsuarioDTO usuario1 = usuarioBO.obtenerPorCorreo(correo1);

            if (usuario1 != null && usuario1.getUsuarioId() != null) {
                System.out.println("\n✓ Usuario encontrado:");
                System.out.println("  ID: " + usuario1.getUsuarioId());
                System.out.println("  Usuario: " + usuario1.getUsuario());
                System.out.println("  Nombre: " + usuario1.getNombre());
                System.out.println("  Tipo: " + usuario1.getTipoUsuarios());
                System.out.println("  Correo: " + usuario1.getCorreo());
            } else {
                System.out.println("✗ No se encontró usuario con ese correo");
            }

            // Buscar por correo inexistente
            String correo2 = "noexiste@bodega.com";
            System.out.println("\n\n═══ Búsqueda 2: Correo inexistente ═══");
            System.out.println("Correo: " + correo2);

            UsuarioDTO usuario2 = usuarioBO.obtenerPorCorreo(correo2);

            if (usuario2 == null || usuario2.getUsuarioId() == null) {
                System.out.println("\n✓ Correcto: No se encontró usuario con ese correo");
            } else {
                System.out.println("✗ Error: Se encontró un usuario que no debería existir");
            }

        } catch (Exception e) {
            System.out.println("✗ Excepción al obtener por correo: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarUsuarioListarTodos() {
        imprimirEncabezado("PRUEBA: UsuarioBO - listarTodos()");

        try {
            ArrayList<UsuarioDTO> usuarios = usuarioBO.listarTodos();

            System.out.println("✓ Total de usuarios encontrados: " + usuarios.size());

            if (!usuarios.isEmpty()) {
                // Contar por tipo
                int admins = 0, operarios = 0, activos = 0, inactivos = 0;

                for (UsuarioDTO u : usuarios) {
                    if (u.getTipoUsuarios() == Tipo_Usuario.ADMINISTRADOR) admins++;
                    if (u.getTipoUsuarios() == Tipo_Usuario.OPERARIO) operarios++;
                    if (u.getActivo()) activos++;
                    else inactivos++;
                }

                System.out.println("\nEstadísticas:");
                System.out.println("  • Administradores: " + admins);
                System.out.println("  • Operarios: " + operarios);
                System.out.println("  • Activos: " + activos);
                System.out.println("  • Inactivos: " + inactivos);

                System.out.println("\nPrimeros 5 usuarios:");
                System.out.println("─".repeat(70));

                int contador = 0;
                for (UsuarioDTO u : usuarios) {
                    if (contador >= 5) break;
                    System.out.println("\n  " + (contador + 1) + ". " + u.getUsuario() + " - " + u.getNombre());
                    System.out.println("     Tipo: " + u.getTipoUsuarios() + " | Activo: " + u.getActivo());
                    contador++;
                }
                System.out.println("\n" + "─".repeat(70));
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar todos: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarUsuarioListarActivos() {
        imprimirEncabezado("PRUEBA: UsuarioBO - listarActivos()");

        try {
            ArrayList<UsuarioDTO> usuarios = usuarioBO.listarActivos();

            System.out.println("✓ Total de usuarios ACTIVOS: " + usuarios.size());

            if (!usuarios.isEmpty()) {
                // Contar por tipo
                int admins = 0, operarios = 0;

                for (UsuarioDTO u : usuarios) {
                    if (u.getTipoUsuarios() == Tipo_Usuario.ADMINISTRADOR) admins++;
                    if (u.getTipoUsuarios() == Tipo_Usuario.OPERARIO) operarios++;
                }

                System.out.println("\nDistribución:");
                System.out.println("  • Administradores activos: " + admins);
                System.out.println("  • Operarios activos: " + operarios);

                System.out.println("\nPrimeros 5 usuarios activos:");
                System.out.println("─".repeat(70));

                int contador = 0;
                for (UsuarioDTO u : usuarios) {
                    if (contador >= 5) break;
                    System.out.println("\n  " + (contador + 1) + ". " + u.getUsuario() + " (" + u.getTipoUsuarios() + ")");
                    System.out.println("     " + u.getNombre());
                    System.out.println("     Correo: " + u.getCorreo() + " | Tel: " + u.getTelefono());
                    contador++;
                }
                System.out.println("\n" + "─".repeat(70));
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar activos: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarUsuarioListarInactivos() {
        imprimirEncabezado("PRUEBA: UsuarioBO - listarInactivos()");

        try {
            ArrayList<UsuarioDTO> usuarios = usuarioBO.listarInactivos();

            System.out.println("✓ Total de usuarios INACTIVOS: " + usuarios.size());

            if (!usuarios.isEmpty()) {
                // Contar por tipo
                int admins = 0, operarios = 0;

                for (UsuarioDTO u : usuarios) {
                    if (u.getTipoUsuarios() == Tipo_Usuario.ADMINISTRADOR) admins++;
                    if (u.getTipoUsuarios() == Tipo_Usuario.OPERARIO) operarios++;
                }

                System.out.println("\nDistribución:");
                System.out.println("  • Administradores inactivos: " + admins);
                System.out.println("  • Operarios inactivos: " + operarios);

                System.out.println("\nUsuarios inactivos:");
                System.out.println("─".repeat(70));

                for (UsuarioDTO u : usuarios) {
                    System.out.println("\n  • " + u.getUsuario() + " - " + u.getNombre());
                    System.out.println("    Tipo: " + u.getTipoUsuarios() + " | ID: " + u.getUsuarioId());
                }
                System.out.println("\n" + "─".repeat(70));
            } else {
                System.out.println("\n⚠ No hay usuarios inactivos en el sistema");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar inactivos: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarUsuarioListarPorNombreParcial() {
        imprimirEncabezado("PRUEBA: UsuarioBO - listarPorNombreParcial(String)");

        try {
            // Prueba 1: Buscar "adm"
            String texto1 = "adm";
            System.out.println("═══ Búsqueda 1: Usuarios con '" + texto1 + "' ═══\n");
            ArrayList<UsuarioDTO> usuarios1 = usuarioBO.listarPorNombreParcial(texto1);

            System.out.println("✓ Total encontrado: " + usuarios1.size());
            if (!usuarios1.isEmpty()) {
                System.out.println("\nResultados:");
                for (UsuarioDTO u : usuarios1) {
                    System.out.println("  • " + u.getUsuario() + " - " + u.getNombre());
                    System.out.println("    Tipo: " + u.getTipoUsuarios() + " | Activo: " + u.getActivo());
                }
            }

            // Prueba 2: Buscar "op"
            String texto2 = "op";
            System.out.println("\n\n═══ Búsqueda 2: Usuarios con '" + texto2 + "' ═══\n");
            ArrayList<UsuarioDTO> usuarios2 = usuarioBO.listarPorNombreParcial(texto2);

            System.out.println("✓ Total encontrado: " + usuarios2.size());
            if (!usuarios2.isEmpty()) {
                System.out.println("\nPrimeros 5 resultados:");
                int contador = 0;
                for (UsuarioDTO u : usuarios2) {
                    if (contador >= 5) break;
                    System.out.println("  • " + u.getUsuario() + " - " + u.getNombre());
                    System.out.println("    Correo: " + u.getCorreo());
                    contador++;
                }
            }

            // Prueba 3: Buscar "caja"
            String texto3 = "caja";
            System.out.println("\n\n═══ Búsqueda 3: Usuarios con '" + texto3 + "' ═══\n");
            ArrayList<UsuarioDTO> usuarios3 = usuarioBO.listarPorNombreParcial(texto3);

            System.out.println("✓ Total encontrado: " + usuarios3.size());
            if (!usuarios3.isEmpty()) {
                System.out.println("\nResultados:");
                for (UsuarioDTO u : usuarios3) {
                    System.out.println("  • " + u.getUsuario() + " - " + u.getNombre());
                    System.out.println("    ID: " + u.getUsuarioId() + " | Tipo: " + u.getTipoUsuarios());
                }
            }

            // Prueba 4: Buscar texto inexistente
            String texto4 = "xyz123";
            System.out.println("\n\n═══ Búsqueda 4: Usuarios con '" + texto4 + "' (inexistente) ═══\n");
            ArrayList<UsuarioDTO> usuarios4 = usuarioBO.listarPorNombreParcial(texto4);

            System.out.println("✓ Total encontrado: " + usuarios4.size());
            if (usuarios4.isEmpty()) {
                System.out.println("\n✓ Correcto: No se encontraron usuarios con ese criterio");
            }

        } catch (Exception e) {
            System.out.println("✗ Excepción al listar por nombre parcial: " + e.getMessage());
        }

        imprimirSeparador();
    }

    // ==================== MÉTODOS AUXILIARES USUARIO ====================

    private static void imprimirUsuario(UsuarioDTO u) {
        System.out.println("  ID: " + u.getUsuarioId());
        System.out.println("  Usuario: " + u.getUsuario());
        System.out.println("  Tipo: " + u.getTipoUsuarios());
        System.out.println("  Correo: " + u.getCorreo());
        System.out.println("  Nombre completo: " + u.getNombre());
        System.out.println("  Teléfono: " + u.getTelefono());
        System.out.println("  Activo: " + u.getActivo());
    }
    
    private static void probarVentaInsertar() {
        imprimirEncabezado("PRUEBA: VentaBO - insertar(VentaDTO)");

        try {
            // Crear usuario para la venta (usar usuario existente ID=6 - op_caja1)
            UsuarioDTO usuarioVenta = usuarioBO.obtenerPorId(6);

            // Crear venta
            VentaDTO nuevaVenta = new VentaDTO();
            nuevaVenta.setTotal(125.50);
            nuevaVenta.setMetodoPago(Tipo_de_pago.EFECTIVO);
            nuevaVenta.setFecha(Date.valueOf("2025-10-07"));
            nuevaVenta.setUsuario(usuarioVenta);

            System.out.println("Insertando venta...");
            System.out.println("  Total: S/ " + nuevaVenta.getTotal());
            System.out.println("  Método de pago: " + nuevaVenta.getMetodoPago());
            System.out.println("  Fecha: " + nuevaVenta.getFecha());
            System.out.println("  Usuario: " + usuarioVenta.getUsuario() + " - " + usuarioVenta.getNombre());

            ventaIdInsertado = ventaBO.insertar(nuevaVenta);

            if (ventaIdInsertado != null && ventaIdInsertado > 0) {
                System.out.println("\n✓ Venta insertada exitosamente");
                System.out.println("  ID de venta generado: " + ventaIdInsertado);

                // Ahora insertar detalles de venta
                System.out.println("\n  Insertando detalles de la venta...");
                boolean detallesInsertados = insertarDetallesVenta(ventaIdInsertado);

                if (detallesInsertados) {
                    System.out.println("  ✓ Detalles de venta insertados correctamente");

                    // Mostrar detalles insertados
                    ArrayList<DetalleVentaDTO> detalles = detalleVentaBO.listarPorVenta(ventaIdInsertado);
                    System.out.println("\n  Detalles de la venta (Total items: " + detalles.size() + "):");
                    System.out.println("  " + "─".repeat(60));

                    for (DetalleVentaDTO det : detalles) {
                        System.out.println("    • " + det.getProducto().getNombre());
                        System.out.println("      Cantidad: " + det.getCantidad() + 
                                         " | Precio Unit: S/ " + det.getProducto().getPrecioUnitario() +
                                         " | Subtotal: S/ " + det.getSubtotal());
                    }
                    System.out.println("  " + "─".repeat(60));
                }
            } else {
                System.out.println("✗ Error al insertar venta");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al insertar: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static boolean insertarDetallesVenta(Integer ventaId) {
        try {
            // Crear ventaDTO con el ID
            VentaDTO venta = new VentaDTO();
            venta.setVentaId(ventaId);

            // Detalle 1: Agua Mineral (Producto ID=3)
            ProductoDTO producto1 = new ProductoDTO();
            producto1.setProductoId(3);
            producto1.setNombre("Agua Mineral 1L");
            producto1.setPrecioUnitario(1.50);

            DetalleVentaDTO detalle1 = new DetalleVentaDTO();
            detalle1.setVenta(venta);
            detalle1.setProducto(producto1);
            detalle1.setCantidad(10);
            detalle1.setSubtotal(15.00);

            // Detalle 2: Gaseosa Cola (Producto ID=4)
            ProductoDTO producto2 = new ProductoDTO();
            producto2.setProductoId(4);
            producto2.setNombre("Gaseosa Cola 2L");
            producto2.setPrecioUnitario(3.80);

            DetalleVentaDTO detalle2 = new DetalleVentaDTO();
            detalle2.setVenta(venta);
            detalle2.setProducto(producto2);
            detalle2.setCantidad(5);
            detalle2.setSubtotal(19.00);

            // Detalle 3: Papas Fritas (Producto ID=5)
            ProductoDTO producto3 = new ProductoDTO();
            producto3.setProductoId(5);
            producto3.setNombre("Papas Fritas Grandes");
            producto3.setPrecioUnitario(2.50);

            DetalleVentaDTO detalle3 = new DetalleVentaDTO();
            detalle3.setVenta(venta);
            detalle3.setProducto(producto3);
            detalle3.setCantidad(20);
            detalle3.setSubtotal(50.00);

            // Insertar detalles
            Integer resultado1 = detalleVentaBO.insertar(detalle1);
            Integer resultado2 = detalleVentaBO.insertar(detalle2);
            Integer resultado3 = detalleVentaBO.insertar(detalle3);

            return (resultado1 > 0 && resultado2 > 0 && resultado3 > 0);

        } catch (Exception e) {
            System.out.println("  ✗ Error al insertar detalles: " + e.getMessage());
            return false;
        }
    }

    private static void probarVentaObtenerPorId() {
        imprimirEncabezado("PRUEBA: VentaBO - obtenerPorId(Integer)");

        try {
            // Obtener venta ID=1 (existe en la BD)
            Integer ventaId = 1;
            System.out.println("Buscando venta con ID=" + ventaId + "...\n");

            VentaDTO venta = ventaBO.obtenerPorId(ventaId);

            if (venta != null && venta.getVentaId() != null) {
                System.out.println("✓ Venta encontrada:");
                imprimirVenta(venta);

                // Obtener y mostrar detalles de la venta
                ArrayList<DetalleVentaDTO> detalles = detalleVentaBO.listarPorVenta(ventaId);

                if (detalles != null && !detalles.isEmpty()) {
                    System.out.println("\n  Detalles de la venta (Total items: " + detalles.size() + "):");
                    System.out.println("  " + "─".repeat(70));

                    double totalCalculado = 0;
                    for (DetalleVentaDTO det : detalles) {
                        System.out.println("    • " + det.getProducto().getNombre());
                        System.out.println("      Cantidad: " + det.getCantidad() + 
                                         " | Precio: S/ " + det.getProducto().getPrecioUnitario() +
                                         " | Subtotal: S/ " + det.getSubtotal());
                        totalCalculado += det.getSubtotal();
                    }
                    System.out.println("  " + "─".repeat(70));
                    System.out.println("  Total calculado de detalles: S/ " + totalCalculado);
                    System.out.println("  Total registrado en venta: S/ " + venta.getTotal());

                    if (Math.abs(totalCalculado - venta.getTotal()) < 0.01) {
                        System.out.println("  ✓ Los totales coinciden correctamente");
                    } else {
                        System.out.println("  ⚠ Advertencia: Los totales no coinciden");
                    }
                }
            } else {
                System.out.println("✗ No se encontró la venta con ID=" + ventaId);
            }

            // Probar con ID inexistente
            Integer ventaIdInexistente = 99999;
            System.out.println("\n\n═══ Prueba con ID inexistente (" + ventaIdInexistente + ") ═══\n");

            VentaDTO ventaInexistente = ventaBO.obtenerPorId(ventaIdInexistente);

            if (ventaInexistente == null || ventaInexistente.getVentaId() == null) {
                System.out.println("✓ Correcto: No se encontró venta con ID inexistente");
            } else {
                System.out.println("✗ Error: Se encontró una venta que no debería existir");
            }

        } catch (Exception e) {
            System.out.println("✗ Excepción al obtener por ID: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarVentaListarTodos() {
        imprimirEncabezado("PRUEBA: VentaBO - listarTodos()");

        try {
            ArrayList<VentaDTO> ventas = ventaBO.listarTodos();

            System.out.println("✓ Total de ventas encontradas: " + ventas.size());

            if (!ventas.isEmpty()) {
                // Calcular estadísticas
                double totalEfectivo = 0, totalTransferencia = 0;
                int contadorEfectivo = 0, contadorTransferencia = 0;
                double montoTotal = 0;

                for (VentaDTO v : ventas) {
                    montoTotal += v.getTotal();
                    if (v.getMetodoPago() == Tipo_de_pago.EFECTIVO) {
                        totalEfectivo += v.getTotal();
                        contadorEfectivo++;
                    } else if (v.getMetodoPago() == Tipo_de_pago.TRANSFERENCIA) {
                        totalTransferencia += v.getTotal();
                        contadorTransferencia++;
                    }
                }

                System.out.println("\n═══ ESTADÍSTICAS GENERALES ═══");
                System.out.println("  Total ventas: " + ventas.size());
                System.out.println("  Monto total: S/ " + String.format("%.2f", montoTotal));
                System.out.println("\n  Ventas en EFECTIVO:");
                System.out.println("    Cantidad: " + contadorEfectivo);
                System.out.println("    Monto: S/ " + String.format("%.2f", totalEfectivo));
                System.out.println("\n  Ventas por TRANSFERENCIA:");
                System.out.println("    Cantidad: " + contadorTransferencia);
                System.out.println("    Monto: S/ " + String.format("%.2f", totalTransferencia));

                System.out.println("\n\n═══ Primeras 5 ventas ═══");
                System.out.println("─".repeat(70));

                int contador = 0;
                for (VentaDTO v : ventas) {
                    if (contador >= 5) break;
                    System.out.println("\n  Venta #" + (contador + 1) + ":");
                    System.out.println("    ID: " + v.getVentaId() + " | Fecha: " + v.getFecha());
                    System.out.println("    Total: S/ " + v.getTotal() + " | Método: " + v.getMetodoPago());

                    // Mostrar cantidad de items
                    ArrayList<DetalleVentaDTO> detalles = detalleVentaBO.listarPorVenta(v.getVentaId());
                    if (detalles != null) {
                        System.out.println("    Items: " + detalles.size() + " producto(s)");
                    }
                    contador++;
                }
                System.out.println("\n" + "─".repeat(70));

            } else {
                System.out.println("\n⚠ No hay ventas en el sistema");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar todos: " + e.getMessage());
        }

        imprimirSeparador();
    }

    private static void probarVentaListarTodosPorFecha() {
        imprimirEncabezado("PRUEBA: VentaBO - listarTodosPorFecha(Date)");

        try {
            // Prueba 1: Buscar ventas del 2025-10-07
            Date fecha1 = Date.valueOf("2025-10-07");
            System.out.println("═══ Búsqueda 1: Ventas del " + fecha1 + " ═══\n");

            ArrayList<VentaDTO> ventas1 = ventaBO.listarTodosPorFecha(fecha1);

            System.out.println("✓ Total de ventas encontradas: " + ventas1.size());

            if (!ventas1.isEmpty()) {
                double totalDia = 0;
                System.out.println("\nVentas del día:");
                System.out.println("─".repeat(70));

                for (VentaDTO v : ventas1) {
                    System.out.println("\n  • Venta ID: " + v.getVentaId());
                    System.out.println("    Total: S/ " + v.getTotal() + " | Método: " + v.getMetodoPago());
                    totalDia += v.getTotal();

                    // Mostrar productos vendidos
                    ArrayList<DetalleVentaDTO> detalles = detalleVentaBO.listarPorVenta(v.getVentaId());
                    if (detalles != null && !detalles.isEmpty()) {
                        System.out.println("    Productos: " + detalles.size() + " item(s)");
                        for (DetalleVentaDTO det : detalles) {
                            System.out.println("      - " + det.getProducto().getNombre() + 
                                             " (Cant: " + det.getCantidad() + ")");
                        }
                    }
                }
                System.out.println("\n" + "─".repeat(70));
                System.out.println("Total vendido en el día: S/ " + String.format("%.2f", totalDia));
            }

            // Prueba 2: Buscar ventas del 2025-10-01
            Date fecha2 = Date.valueOf("2025-10-01");
            System.out.println("\n\n═══ Búsqueda 2: Ventas del " + fecha2 + " ═══\n");

            ArrayList<VentaDTO> ventas2 = ventaBO.listarTodosPorFecha(fecha2);

            System.out.println("✓ Total de ventas encontradas: " + ventas2.size());

            if (!ventas2.isEmpty()) {
                double totalDia2 = 0;
                int totalItems = 0;

                for (VentaDTO v : ventas2) {
                    totalDia2 += v.getTotal();
                    ArrayList<DetalleVentaDTO> detalles = detalleVentaBO.listarPorVenta(v.getVentaId());
                    if (detalles != null) {
                        totalItems += detalles.size();
                    }
                }

                System.out.println("  Ventas: " + ventas2.size());
                System.out.println("  Items vendidos: " + totalItems);
                System.out.println("  Total del día: S/ " + String.format("%.2f", totalDia2));

                System.out.println("\n  Resumen de ventas:");
                for (VentaDTO v : ventas2) {
                    System.out.println("    • Venta " + v.getVentaId() + ": S/ " + v.getTotal() + 
                                     " (" + v.getMetodoPago() + ")");
                }
            }

            // Prueba 3: Buscar ventas en fecha sin registros
            Date fecha3 = Date.valueOf("2025-01-01");
            System.out.println("\n\n═══ Búsqueda 3: Ventas del " + fecha3 + " (sin ventas) ═══\n");

            ArrayList<VentaDTO> ventas3 = ventaBO.listarTodosPorFecha(fecha3);

            System.out.println("✓ Total de ventas encontradas: " + ventas3.size());

            if (ventas3.isEmpty()) {
                System.out.println("✓ Correcto: No hay ventas registradas en esa fecha");
            } else {
                System.out.println("  Se encontraron " + ventas3.size() + " venta(s) en esa fecha");
            }

            // Prueba 4: Comparar dos fechas diferentes
            Date fecha4a = Date.valueOf("2025-09-28");
            Date fecha4b = Date.valueOf("2025-10-06");

            System.out.println("\n\n═══ Búsqueda 4: Comparación entre dos fechas ═══\n");

            ArrayList<VentaDTO> ventas4a = ventaBO.listarTodosPorFecha(fecha4a);
            ArrayList<VentaDTO> ventas4b = ventaBO.listarTodosPorFecha(fecha4b);

            double total4a = ventas4a.stream().mapToDouble(VentaDTO::getTotal).sum();
            double total4b = ventas4b.stream().mapToDouble(VentaDTO::getTotal).sum();

            System.out.println("Fecha: " + fecha4a);
            System.out.println("  Ventas: " + ventas4a.size() + " | Total: S/ " + String.format("%.2f", total4a));

            System.out.println("\nFecha: " + fecha4b);
            System.out.println("  Ventas: " + ventas4b.size() + " | Total: S/ " + String.format("%.2f", total4b));

            System.out.println("\nDiferencia:");
            System.out.println("  Ventas: " + (ventas4b.size() - ventas4a.size()));
            System.out.println("  Monto: S/ " + String.format("%.2f", (total4b - total4a)));

        } catch (Exception e) {
            System.out.println("✗ Excepción al listar por fecha: " + e.getMessage());
        }

        imprimirSeparador();
    }

    // ==================== MÉTODOS AUXILIARES VENTA ====================

    private static void imprimirVenta(VentaDTO v) {
        System.out.println("  ID: " + v.getVentaId());
        System.out.println("  Fecha: " + v.getFecha());
        System.out.println("  Total: S/ " + v.getTotal());
        System.out.println("  Método de pago: " + v.getMetodoPago());
    }
    
}