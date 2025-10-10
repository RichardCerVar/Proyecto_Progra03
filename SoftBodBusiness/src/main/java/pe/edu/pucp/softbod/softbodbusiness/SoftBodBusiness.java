package pe.edu.pucp.softbod.softbodbusiness;

import pe.edu.pucp.softbod.bo.trazabilidad.HistorialDeOperacionBO;
import pe.edu.pucp.softbod.bo.rrhh.UsuarioBO;
import pe.edu.pucp.softbod.bo.gestclientes.RegistroPagoFiadoBO;
import pe.edu.pucp.softbod.bo.gestclientes.ClienteAlFiadoBO;
import pe.edu.pucp.softbod.bo.ventas.VentaAlFiadoBO;
import pe.edu.pucp.softbod.bo.ventas.VentaBO;
import pe.edu.pucp.softbod.bo.ventas.DetalleVentaBO;
import pe.edu.pucp.softbod.bo.devolucion.RazonDevolucionBO;
import pe.edu.pucp.softbod.bo.devolucion.DevolucionBO;
import pe.edu.pucp.softbod.bo.devolucion.DetalleDevolucionBO;
import pe.edu.pucp.softbod.bo.almacen.CategoriaBO;
import pe.edu.pucp.softbod.bo.almacen.ProductoBO;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.gestclientes.ClienteAlFiadoDTO;
import pe.edu.pucp.softbod.model.gestclientes.RegistroPagoFiadoDTO;
import pe.edu.pucp.softbod.model.ventas.VentaAlFiadoDTO;
import pe.edu.pucp.softbod.model.ventas.VentaDTO;
import pe.edu.pucp.softbod.model.ventas.DetalleVentaDTO;
import pe.edu.pucp.softbod.model.devolucion.RazonDevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.DevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.model.almacen.CategoriaDTO;
import pe.edu.pucp.softbod.model.almacen.ProductoDTO;
import java.util.ArrayList;
import pe.edu.pucp.softbod.model.util.*;
import java.sql.Date;
import java.time.LocalDate;
import pe.edu.pucp.softbod.daoImp.*;

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
    
    private static ClienteAlFiadoBO clienteAlFiadoBO;
    private static VentaAlFiadoBO ventaAlFiadoBO;
    private static Integer ventaFiadaIdInsertado = null;
    
    private static RegistroPagoFiadoBO registroPagoFiadoBO;
    private static Integer registroPagoFiadoIdInsertado = null;
    private static final Integer clienteIdPagoPrueba = 2;
    
    private static final String SEPARADOR = "=".repeat(80);
    private static final String SUBSEPARADOR = "-".repeat(80);
    
    private static CategoriaBO categoriaBO;
    private static ClienteAlFiadoBO clienteBO;
    
    private static DevolucionBO devolucionBO;
    private static DetalleDevolucionBO detalleDevBO;
    private static HistorialDeOperacionBO historialBO;
    
    
    public static void main(String[] args) {
        
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║        PRUEBAS COMPLETAS DEL ProductoBO - BACKEND ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
//        
        productoBO = new ProductoBO();
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
        imprimirSeparador();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║ PRUEBAS COMPLETAS DEL RazonDevolucionBO - BACKEND ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
////        
        razonDevolucionBO = new RazonDevolucionBO();
        // Pruebas RazonDevolucion
        probarRazonDevolucionInsertar();
        probarRazonDevolucionEliminar();
        probarRazonDevolucionObtenerPorId();
        probarRazonDevolucionListarTodos();
        probarRazonDevolucionListarTodosPorNombreParcial();
        
        imprimirSeparador();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║ PRUEBAS COMPLETAS DEL UsuarioBO - BACKEND         ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        
        usuarioBO = new UsuarioBO();
        // Pruebas Usuario
        probarUsuarioInsertar();
        probarUsuarioModificar();
        probarUsuarioEliminarLogico();
        probarUsuarioObtenerPorId();
        probarUsuarioObtenerCuenta();
        probarUsuarioObtenerPorCorreo();
        probarUsuarioListarTodos();
        probarUsuarioListarActivos();
        probarUsuarioListarInactivos();
        probarUsuarioListarPorNombreParcial();
        
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
        
        imprimirSeparador();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Registro De VentaAlFiadoBO - BACKEND               ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

        clienteAlFiadoBO = new ClienteAlFiadoBO();
        ventaAlFiadoBO = new VentaAlFiadoBO();
        // PRUEBAS VENTA AL FIADO
        probarVentaAlFiadoInsertar();
        probarVentaAlFiadoObtenerPorId();
        probarVentaAlFiadoListarTodos();
        probarVentaAlFiadoListarPorAliasCliente();
        probarVentaAlFiadoListarPorAliasClienteFecha();
//        
        imprimirSeparador();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Registro De RegistroDePagosalFiadoBO - BACKEND    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        // NUEVAS PRUEBAS RegistroPagoFiadoBO
        registroPagoFiadoBO = new RegistroPagoFiadoBO();
        probarRegistroPagoFiadoInsertar();
        probarRegistroPagoFiadoListarTodos();
        probarRegistroPagoFiadoListarPorAliasCliente();
        probarRegistroPagoFiadoListarPorAliasClienteConFecha();
        
        
        System.out.println(SEPARADOR);
        System.out.println("INICIANDO PRUEBAS DE DAOs RICHARD - SOFTBOD");
        System.out.println(SEPARADOR);
        
        // Ejecutar pruebas de cada DAO
        categoriaBO = new CategoriaBO();
        probarCategoriaBO();
        clienteBO = new ClienteAlFiadoBO();
        probarClienteAlFiadoBO();
        devolucionBO = new DevolucionBO();
        probarDevolucionBO();
        detalleDevBO = new DetalleDevolucionBO();
        probarDetalleDevolucionBO();
        historialBO = new HistorialDeOperacionBO();
        probarHistorialOperacionesBO();
        
        
        

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

    // ==================== MÉTODOS DE PRUEBA VENTA ====================

    private static void probarVentaInsertar() {
        imprimirEncabezado("PRUEBA 1: insertar(VentaDTO) y DetalleVenta");
        
        try {
            // 1. Obtener datos de referencia (Usuario y Productos)
            // Se asume la existencia de obtenerUsuarioBase y obtenerProductoBase
            UsuarioDTO usuario = usuarioBO.obtenerPorId(1); // Usuario con ID 1 (ej: ADMINISTRADOR)
            ProductoDTO prod1 = productoBO.obtenerPorId(1); // Producto 1 (ej: Bebida)
            ProductoDTO prod2 = productoBO.obtenerPorId(3); // Producto 3 (ej: Agua Mineral)
            
            if (usuario == null || prod1 == null || prod2 == null) {
                System.out.println("✗ Error de Setup: No se pudieron obtener los datos base (Usuario/Productos).");
                return;
            }

            // 2. Crear Venta DTO con MONTO CERO (sin insertar aún)
            VentaDTO nuevaVenta = new VentaDTO();
            nuevaVenta.setTotal(0.0); // Monto cero inicial
            nuevaVenta.setMetodoPago(Tipo_de_pago.TRANSFERENCIA);
            nuevaVenta.setFecha(new Date(System.currentTimeMillis())); // Fecha actual
            nuevaVenta.setUsuario(usuario);

            // 3. Crear y poblar el ArrayList de Detalles, calculando el total
            ArrayList<DetalleVentaDTO> detalles = new ArrayList<>();
            double totalVenta = 0.0;
            
            // Detalle 1: 5 unidades de Producto 1
            Integer cantidad1 = 5;
            Double subtotal1 = cantidad1 * prod1.getPrecioUnitario(); 
            DetalleVentaDTO det1 = new DetalleVentaDTO();
            det1.setProducto(prod1);
            det1.setCantidad(cantidad1);
            det1.setSubtotal(subtotal1);
            detalles.add(det1);
            totalVenta += subtotal1;

            // Detalle 2: 2 unidades de Producto 3
            Integer cantidad2 = 2;
            Double subtotal2 = cantidad2 * prod2.getPrecioUnitario();
            DetalleVentaDTO det2 = new DetalleVentaDTO();
            det2.setProducto(prod2);
            det2.setCantidad(cantidad2);
            det2.setSubtotal(subtotal2);
            detalles.add(det2);
            totalVenta += subtotal2;

            // 4. Settear el total final calculado en la Venta
            nuevaVenta.setTotal(totalVenta);

            // 5. Insertar la Venta principal (para obtener el ID)
            Integer idVentaGenerada = ventaBO.insertar(nuevaVenta);

            // 6. Insertar los Detalles
            if (idVentaGenerada != null && idVentaGenerada > 0) {
                ventaIdInsertado = idVentaGenerada; // Guardar para pruebas posteriores
                nuevaVenta.setVentaId(idVentaGenerada); // Actualizar el DTO de Venta
                
                // Iterar e insertar cada detalle con el ID de la Venta
                for (DetalleVentaDTO detalle : detalles) {
                    detalle.setVenta(nuevaVenta); // Asignar la Venta (con ID) al detalle
                    detalleVentaBO.insertar(detalle);
                }
                
                System.out.println("✓ Venta y sus " + detalles.size() + " detalles insertados exitosamente.");
                System.out.println("  ID de Venta generado: " + ventaIdInsertado);
                System.out.println("  Fecha:  " + nuevaVenta.getFecha());
                System.out.println("  Metodo de Pago:  " + nuevaVenta.getMetodoPago().name());
                System.out.println("  Monto Total: S/ " + String.format("%.2f", nuevaVenta.getTotal()));
            } else {
                System.out.println("✗ Error al insertar la Venta (ID no generado).");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al insertar Venta: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarVentaObtenerPorId() {
        imprimirEncabezado("PRUEBA 2: obtenerPorId(Integer) y listarPorVenta(Integer)");
        
        if (ventaIdInsertado == null) {
            System.out.println("AVISO: No se ejecutó la prueba. Ejecute probarVentaInsertar primero.");
            imprimirSeparador();
            return;
        }
        
        try {
            VentaDTO ventaEncontrada = ventaBO.obtenerPorId(ventaIdInsertado);
            
            if (ventaEncontrada != null) {
                // Obtener detalles asociados a la venta
                ArrayList<DetalleVentaDTO> detalles = detalleVentaBO.listarPorVenta(ventaIdInsertado);
                
                System.out.println("✓ Venta ID " + ventaIdInsertado + " encontrada exitosamente.");
                imprimirVentaConDetalles(ventaEncontrada, detalles);
            } else {
                System.out.println("✗ No se encontró la Venta con ID=" + ventaIdInsertado);
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al obtener Venta por ID: " + e.getMessage());
        }
        
        imprimirSeparador();
    }

    private static void probarVentaListarTodos() {
        imprimirEncabezado("PRUEBA 3: listarTodos() y listarPorVenta(Integer)");
        
        try {
            ArrayList<VentaDTO> ventas = ventaBO.listarTodos();
            
            System.out.println("✓ Total de Ventas encontradas: " + ventas.size());
            
            if (!ventas.isEmpty()) {
                System.out.println("\n--- Resumen de las últimas 3 Ventas ---");
                int limite = Math.min(3, ventas.size());
                
                // Se listan las últimas 'limite' ventas (asumiendo que listarTodos retorna de alguna forma ordenada)
                for (int i = 0; i < limite; i++) {
                    VentaDTO venta = ventas.get(i);
                    // Obtener detalles de la venta listada
                    ArrayList<DetalleVentaDTO> detalles = detalleVentaBO.listarPorVenta(venta.getVentaId());
                    System.out.println("\nVenta #" + (i + 1) + ":");
                    imprimirVentaConDetalles(venta, detalles);
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar todas las Ventas: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarVentaListarTodosPorFecha() {
        imprimirEncabezado("PRUEBA 4: listarTodosPorFecha(Date) y listarPorVenta(Integer)");
        
        try {
            // Usar la fecha de la venta insertada para asegurar resultados
            Date fechaPrueba = new Date(System.currentTimeMillis()); 

            ArrayList<VentaDTO> ventasPorFecha = ventaBO.listarTodosPorFecha(fechaPrueba);
            
            System.out.println("✓ Total de Ventas encontradas para la fecha " + fechaPrueba.toString() + ": " + ventasPorFecha.size());
            
            if (!ventasPorFecha.isEmpty()) {
                System.out.println("\n--- Ventas del día " + fechaPrueba.toString() + " ---");
                for (VentaDTO venta : ventasPorFecha) {
                    // Obtener detalles de la venta listada
                    ArrayList<DetalleVentaDTO> detalles = detalleVentaBO.listarPorVenta(venta.getVentaId());
                    imprimirVentaConDetalles(venta, detalles);
                }
            } else {
                System.out.println("No se encontraron ventas para la fecha " + fechaPrueba.toString() + ".");
            }
            
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar por fecha: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    // ==================== MÉTODOS AUXILIARES DE SOFTBODBUSSINESS ====================
 
    private static void imprimirVentaConDetalles(VentaDTO venta, ArrayList<DetalleVentaDTO> detalles) {
        System.out.println("  [VENTA ID: " + venta.getVentaId() + "]");
        System.out.println("    Fecha: " + venta.getFecha());
        System.out.println("    Método de Pago: " + venta.getMetodoPago());
        System.out.println("    Total: S/ " + String.format("%.2f", venta.getTotal()));
        if (venta.getUsuario() != null) {
            System.out.println("    Usuario (ID " + venta.getUsuario().getUsuarioId() + "): " + venta.getUsuario().getNombre() );
        }
        
        if (detalles != null && !detalles.isEmpty()) {
            System.out.println("    --- Detalles (" + detalles.size() + ") ---");
            for (int i = 0; i < detalles.size(); i++) {
                DetalleVentaDTO d = detalles.get(i);
                System.out.println("      " + (i+1) + ". Producto: " + d.getProducto().getNombre());
                System.out.println("         Cantidad: " + d.getCantidad() + " | Precio Unit.: S/ " + String.format("%.2f", d.getProducto().getPrecioUnitario()));
                System.out.println("         Subtotal: S/ " + String.format("%.2f", d.getSubtotal()));
            }
        } else {
            System.out.println("    --- Detalles: Sin detalles asociados. ---");
        }
    }
    
    private static void probarVentaAlFiadoInsertar() {
        imprimirEncabezado("PRUEBA 5: insertar(VentaAlFiadoDTO) y DetalleVenta");
        
        try {
            // 1. Obtener datos de referencia
            UsuarioDTO usuario = usuarioBO.obtenerPorId(1); // Asumiendo Usuario con ID 1
            ClienteAlFiadoDTO cliente = clienteAlFiadoBO.obtenerPorId(1); // Cliente de prueba
            ProductoDTO prod1 = productoBO.obtenerPorId(1);
            ProductoDTO prod2 = productoBO.obtenerPorId(2); 
            
            if (usuario == null || cliente == null || prod1 == null || prod2 == null) {
                System.out.println("✗ Error de Setup: No se pudieron obtener los datos base (Usuario/Cliente/Productos).");
                return;
            }

            // 2. Crear Venta DTO con MONTO CERO (Base de la Venta Fiada)
            VentaDTO nuevaVenta = new VentaDTO();
            nuevaVenta.setTotal(0.0); // Monto cero inicial
            nuevaVenta.setMetodoPago(Tipo_de_pago.TRANSFERENCIA); // Se usa TRANSFERENCIA ya que Tipo_de_pago.java no tiene VENTA_FIADA
            nuevaVenta.setFecha(new Date(System.currentTimeMillis())); 
            nuevaVenta.setUsuario(usuario);

            // 3. Crear detalles y calcular el total
            ArrayList<DetalleVentaDTO> detalles = new ArrayList<>();
            double totalVenta = 0.0;
            
            // Detalle 1: 3 unidades de Producto 1
            Integer cantidad1 = 3;
            Double subtotal1 = cantidad1 * prod1.getPrecioUnitario(); 
            DetalleVentaDTO det1 = new DetalleVentaDTO();
            det1.setProducto(prod1);
            det1.setCantidad(cantidad1);
            det1.setSubtotal(subtotal1);
            detalles.add(det1);
            totalVenta += subtotal1;

            // Detalle 2: 1 unidad de Producto 3
            Integer cantidad2 = 1;
            Double subtotal2 = cantidad2 * prod2.getPrecioUnitario();
            DetalleVentaDTO det2 = new DetalleVentaDTO();
            det2.setProducto(prod2);
            det2.setCantidad(cantidad2);
            det2.setSubtotal(subtotal2);
            detalles.add(det2);
            totalVenta += subtotal2;

            // 4. Settear el total final calculado en la Venta
            nuevaVenta.setTotal(totalVenta);

            // 5. Insertar la Venta principal (VentaBO.insertar)
            Integer idVentaGenerada = ventaBO.insertar(nuevaVenta);

            // 6. Crear y Insertar la Venta Al Fiado (VentaAlFiadoBO.insertar)
            if (idVentaGenerada != null && idVentaGenerada > 0) {
                nuevaVenta.setVentaId(idVentaGenerada); 
                
                VentaAlFiadoDTO nuevaVentaFiada = new VentaAlFiadoDTO();
                nuevaVentaFiada.setVenta(nuevaVenta);
                nuevaVentaFiada.setCliente(cliente);

                Integer idVentaFiadaGenerada = ventaAlFiadoBO.insertar(nuevaVentaFiada);

                if (idVentaFiadaGenerada != null && idVentaFiadaGenerada > 0) {
                    ventaFiadaIdInsertado = idVentaFiadaGenerada; // Guardar para pruebas
                    nuevaVentaFiada.setVentaFiadaId(idVentaFiadaGenerada);

                    // 7. Iterar e insertar cada detalle
                    for (DetalleVentaDTO detalle : detalles) {
                        detalle.setVenta(nuevaVenta); // Asignar la Venta (con ID) al detalle
                        detalleVentaBO.insertar(detalle);
                    }
                    
                    System.out.println("✓ Venta Al Fiado y sus " + detalles.size() + " detalles insertados exitosamente.");
                    System.out.println("  ID de Venta Al Fiado generado: " + ventaFiadaIdInsertado);
                    System.out.println("  Cliente: " + cliente.getAlias() + " | Monto Total: S/ " + String.format("%.2f", totalVenta));
                } else {
                    System.out.println("✗ Error al insertar la Venta Al Fiado (ID no generado).");
                }

            } else {
                System.out.println("✗ Error al insertar la Venta base (ID no generado).");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al insertar Venta Al Fiado: " + e.getMessage());
        }
        
        imprimirSeparador();
    }

    private static void probarVentaAlFiadoObtenerPorId() {
        imprimirEncabezado("PRUEBA 6: obtenerPorId(Integer) y listarPorVenta(Integer)");
        
        if (ventaFiadaIdInsertado == null) {
            System.out.println("AVISO: No se ejecutó la prueba. Ejecute probarVentaAlFiadoInsertar primero.");
            imprimirSeparador();
            return;
        }
        
        try {
            VentaAlFiadoDTO ventaFiadaEncontrada = ventaAlFiadoBO.obtenerPorId(ventaFiadaIdInsertado);
            
            if (ventaFiadaEncontrada != null && ventaFiadaEncontrada.getVenta() != null) {
                Integer ventaId = ventaFiadaEncontrada.getVenta().getVentaId();
                
                // Obtener detalles asociados a la venta base
                ArrayList<DetalleVentaDTO> detalles = detalleVentaBO.listarPorVenta(ventaId);
                
                System.out.println("✓ Venta Al Fiado ID " + ventaFiadaIdInsertado + " encontrada exitosamente.");
                imprimirVentaAlFiadoConDetalles(ventaFiadaEncontrada, detalles);
            } else {
                System.out.println("✗ No se encontró la Venta Al Fiado con ID=" + ventaFiadaIdInsertado + " o su Venta base está incompleta.");
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al obtener Venta Al Fiado por ID: " + e.getMessage());
        }
        
        imprimirSeparador();
    }

    private static void probarVentaAlFiadoListarTodos() {
        imprimirEncabezado("PRUEBA 7: listarTodos() y listarPorVenta(Integer)");
        
        try {
            ArrayList<VentaAlFiadoDTO> ventasFiadas = ventaAlFiadoBO.listarTodos();
            
            System.out.println("✓ Total de Ventas Al Fiado encontradas: " + ventasFiadas.size());
            
            if (!ventasFiadas.isEmpty()) {
                System.out.println("\n--- Resumen de las últimas 3 Ventas Al Fiado ---");
                int limite = Math.min(3, ventasFiadas.size());
                
                for (int i = 0; i < limite; i++) {
                    VentaAlFiadoDTO ventaFiada = ventasFiadas.get(i);
                    
                    // Cargar detalles de la Venta base
                    ArrayList<DetalleVentaDTO> detalles = new ArrayList<>();
                    if(ventaFiada.getVenta() != null){
                        // USO REQUERIDO: Se llama a DetalleVentaBO para listar los detalles.
                        detalles = detalleVentaBO.listarPorVenta(ventaFiada.getVenta().getVentaId());
                    }
                    
                    System.out.println("\nVenta Al Fiado #" + (i + 1) + ":");
                    imprimirVentaAlFiadoConDetalles(ventaFiada, detalles);
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar todas las Ventas Al Fiado: " + e.getMessage());
        }
        
        imprimirSeparador();
    }

    private static void probarVentaAlFiadoListarPorAliasCliente() {
        imprimirEncabezado("PRUEBA 8: listarTodosPorAliasCliente(String) y listarPorVenta(Integer)");
        
        try {
            ClienteAlFiadoDTO clientePrueba = clienteAlFiadoBO.obtenerPorId(1);
            String aliasPrueba = clientePrueba != null ? clientePrueba.getAlias() : "ClienteGenerico";
            
            ArrayList<VentaAlFiadoDTO> ventasFiadas = ventaAlFiadoBO.listarTodosPorAliasCliente(aliasPrueba);
            
            System.out.println("✓ Total de Ventas Al Fiado encontradas para el alias '" + aliasPrueba + "': " + ventasFiadas.size());
            
            if (!ventasFiadas.isEmpty()) {
                System.out.println("\n--- Resumen de Ventas Al Fiado para el alias '" + aliasPrueba + "' ---");
                for (VentaAlFiadoDTO ventaFiada : ventasFiadas) {
                    // Cargar detalles de la Venta base
                    ArrayList<DetalleVentaDTO> detalles = new ArrayList<>();
                    if(ventaFiada.getVenta() != null){
                        detalles = detalleVentaBO.listarPorVenta(ventaFiada.getVenta().getVentaId());
                    }
                    imprimirVentaAlFiadoConDetalles(ventaFiada, detalles);
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar Ventas Al Fiado por Alias: " + e.getMessage());
        }
        
        imprimirSeparador();
    }

    private static void probarVentaAlFiadoListarPorAliasClienteFecha() {
        imprimirEncabezado("PRUEBA 9: listarTodosPorAliasClienteFecha(String, Date) y listarPorVenta(Integer)");
        
        try {
            // Usar la fecha y el alias de la venta insertada para asegurar resultados
            Date fechaPrueba = new Date(System.currentTimeMillis()); 
            ClienteAlFiadoDTO clientePrueba = clienteAlFiadoBO.obtenerPorId(1);
            String aliasPrueba = clientePrueba != null ? clientePrueba.getAlias() : "ClienteGenerico";
            
            ArrayList<VentaAlFiadoDTO> ventasFiadas = ventaAlFiadoBO.listarTodosPorAliasClienteFecha(aliasPrueba, fechaPrueba);
            
            System.out.println("✓ Total de Ventas Al Fiado encontradas para alias '" + aliasPrueba + "' y fecha " + fechaPrueba.toString() + ": " + ventasFiadas.size());
            
            if (!ventasFiadas.isEmpty()) {
                System.out.println("\n--- Resumen de Ventas Al Fiado del día y alias especificado ---");
                for (VentaAlFiadoDTO ventaFiada : ventasFiadas) {
                    // Cargar detalles de la Venta base
                    ArrayList<DetalleVentaDTO> detalles = new ArrayList<>();
                    if(ventaFiada.getVenta() != null){
                        detalles = detalleVentaBO.listarPorVenta(ventaFiada.getVenta().getVentaId());
                    }
                    imprimirVentaAlFiadoConDetalles(ventaFiada, detalles);
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar Ventas Al Fiado por Alias y Fecha: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void imprimirVentaAlFiadoConDetalles(VentaAlFiadoDTO ventaFiada, ArrayList<DetalleVentaDTO> detalles) {
        VentaDTO venta = ventaFiada.getVenta();
        ClienteAlFiadoDTO cliente = ventaFiada.getCliente();

        System.out.println("  [VENTA AL FIADO ID: " + ventaFiada.getVentaFiadaId() + " / VENTA BASE ID: " + (venta != null ? venta.getVentaId() : "N/A") + "]");
        if (cliente != null) {
            System.out.println("    CLIENTE (ID " + cliente.getClienteId() + "): " + cliente.getAlias() + " (" + cliente.getNombre() + ")");
            System.out.println("    Fecha de Pago (Compromiso): " + (cliente.getFechaDePago() != null ? cliente.getFechaDePago().toString() : "N/A"));
        } else {
            System.out.println("    CLIENTE: N/A");
        }
        
        if (venta != null) {
            System.out.println("    Fecha de Venta: " + venta.getFecha());
            System.out.println("    Total: S/ " + String.format("%.2f", venta.getTotal()));
            if (venta.getUsuario() != null) {
                System.out.println("    Usuario Vendedor (ID " + venta.getUsuario().getUsuarioId() + "): " + venta.getUsuario().getNombre());
            }
        }
        
        if (detalles != null && !detalles.isEmpty()) {
            System.out.println("    --- Detalles (" + detalles.size() + ") ---");
            for (int i = 0; i < detalles.size(); i++) {
                DetalleVentaDTO d = detalles.get(i);
                System.out.println("      " + (i+1) + ". Producto: " + d.getProducto().getNombre());
                System.out.println("         Cantidad: " + d.getCantidad() + " | Subtotal: S/ " + String.format("%.2f", d.getSubtotal()));
            }
        } else {
            System.out.println("    --- Detalles: Sin detalles asociados. ---");
        }
    }
    
    // ==================== MÉTODOS DE PRUEBA REGISTRO PAGO FIADO (NUEVOS) ====================

    private static void probarRegistroPagoFiadoInsertar() {
        imprimirEncabezado("PRUEBA 10: insertar(RegistroPagoFiadoDTO)");
        
        try {
            // 1. Obtener datos de referencia
            UsuarioDTO usuario = usuarioBO.obtenerPorId(1); // Usuario que registra el pago
            ClienteAlFiadoDTO cliente = clienteAlFiadoBO.obtenerPorId(clienteIdPagoPrueba); // Cliente que realiza el pago
            
            if (usuario == null || cliente == null) {
                System.out.println("✗ Error de Setup: No se pudieron obtener los datos base (Usuario/Cliente).");
                return;
            }

            // 2. Crear RegistroPagoFiado DTO
            RegistroPagoFiadoDTO nuevoRegistro = new RegistroPagoFiadoDTO();
            nuevoRegistro.setUsuario(usuario);
            nuevoRegistro.setCliente(cliente);
            nuevoRegistro.setFecha(new Date(System.currentTimeMillis())); 
            nuevoRegistro.setMetodoPago(Tipo_de_pago.EFECTIVO); 
            nuevoRegistro.setMonto(45.50); // Monto pagado

            // 3. Insertar el Registro de Pago
            Integer idPagoGenerado = registroPagoFiadoBO.insertar(nuevoRegistro);

            if (idPagoGenerado != null && idPagoGenerado > 0) {
                registroPagoFiadoIdInsertado = idPagoGenerado; // Guardar para pruebas posteriores
                nuevoRegistro.setPagoId(idPagoGenerado);
                
                System.out.println("✓ Registro de Pago Al Fiado insertado exitosamente.");
                System.out.println("  ID de Pago generado: " + registroPagoFiadoIdInsertado);
                imprimirRegistroPagoFiado(nuevoRegistro);
            } else {
                System.out.println("✗ Error al insertar el Registro de Pago Al Fiado (ID no generado).");
            }

        } catch (Exception e) {
            System.out.println("✗ Excepción al insertar Registro de Pago Al Fiado: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarRegistroPagoFiadoListarTodos() {
        imprimirEncabezado("PRUEBA 11: listarTodos()");
        
        try {
            ArrayList<RegistroPagoFiadoDTO> registros = registroPagoFiadoBO.listarTodos();
            
            System.out.println("✓ Total de Registros de Pago Al Fiado encontrados: " + registros.size());
            
            if (!registros.isEmpty()) {
                System.out.println("\n--- Resumen de los últimos 3 Registros de Pago ---");
                int limite = Math.min(3, registros.size());
                
                for (int i = 0; i < limite; i++) {
                    RegistroPagoFiadoDTO registro = registros.get(i);
                    System.out.println("\nRegistro #" + (i + 1) + ":");
                    imprimirRegistroPagoFiado(registro);
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar todos los Registros de Pago Al Fiado: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarRegistroPagoFiadoListarPorAliasCliente() {
        imprimirEncabezado("PRUEBA 12: listarTodosPorAliasCliente(String)");
        
        try {
            // Usar el alias del cliente insertado en la prueba 10 (clienteIdPagoPrueba = 2)
            ClienteAlFiadoDTO clientePrueba = clienteAlFiadoBO.obtenerPorId(clienteIdPagoPrueba);
            String aliasPrueba = clientePrueba != null ? clientePrueba.getAlias() : "CLIENTE_PAGO_TEST";
            
            ArrayList<RegistroPagoFiadoDTO> registros = registroPagoFiadoBO.listarTodosPorAliasCliente(aliasPrueba);
            
            System.out.println("✓ Total de Registros de Pago encontrados para el alias '" + aliasPrueba + "': " + registros.size());
            
            if (!registros.isEmpty()) {
                System.out.println("\n--- Resumen de Pagos para el alias '" + aliasPrueba + "' ---");
                for (RegistroPagoFiadoDTO registro : registros) {
                    imprimirRegistroPagoFiado(registro);
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar Registros de Pago por Alias: " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    private static void probarRegistroPagoFiadoListarPorAliasClienteConFecha() {
        imprimirEncabezado("PRUEBA 13: listarTodosPorAliasClienteConFecha(String, Date)");
        
        try {
            // Usar el alias del cliente insertado en la prueba 10
            ClienteAlFiadoDTO clientePrueba = clienteAlFiadoBO.obtenerPorId(clienteIdPagoPrueba);
            String aliasPrueba = clientePrueba != null ? clientePrueba.getAlias() : "CLIENTE_PAGO_TEST";
            
            // Usar la fecha actual como fecha fin (para incluir los registros insertados hoy)
            Date fechaFinPrueba = new Date(System.currentTimeMillis()); 
            
            ArrayList<RegistroPagoFiadoDTO> registros = registroPagoFiadoBO.listarTodosPorAliasClienteConFechaFin(aliasPrueba, fechaFinPrueba);
            
            System.out.println("✓ Total de Registros de Pago encontrados para alias '" + aliasPrueba + "' en la fecha " + fechaFinPrueba.toString() + ": " + registros.size());
            
            if (!registros.isEmpty()) {
                System.out.println("\n--- Resumen de Pagos del alias especificado con fecha  ---");
                for (RegistroPagoFiadoDTO registro : registros) {
                    imprimirRegistroPagoFiado(registro);
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Excepción al listar Registros de Pago por Alias y Fecha : " + e.getMessage());
        }
        
        imprimirSeparador();
    }
    
    // ==================== MÉTODOS AUXILIARES NUEVOS (Registro Pago Fiado) ====================

    private static void imprimirRegistroPagoFiado(RegistroPagoFiadoDTO registro) {
        ClienteAlFiadoDTO cliente = registro.getCliente();
        
        System.out.println("  [PAGO ID: " + registro.getPagoId() + "]");
        if (cliente != null) {
            System.out.println("    CLIENTE (ID " + cliente.getClienteId() + "): " + cliente.getAlias() + " (" + cliente.getNombre() + ")");
        } else {
            System.out.println("    CLIENTE: N/A");
        }
        System.out.println("    Fecha de Pago: " + registro.getFecha());
        System.out.println("    Método de Pago: " + registro.getMetodoPago());
        System.out.println("    Monto Pagado: S/ " + String.format("%.2f", registro.getMonto()));
        
        // Mostrar el usuario vendedor (ID) solo en el caso de inserción para verificación
        if (registro.getUsuario() != null) {
             System.out.println("    Usuario Vendedor (ID, para verificación): " + registro.getUsuario().getUsuarioId());
        }
    }
    
    // ========== PRUEBAS DE CATEGORIA DAO ==========
    private static void probarCategoriaBO() {
        imprimirEncabezado("PRUEBAS DE CATEGORIA BO");
        
        
        try {
            // 1. INSERTAR CATEGORÍA
            System.out.println("\n1. INSERTANDO CATEGORÍA...");
            CategoriaDTO nuevaCategoria = new CategoriaDTO();
            nuevaCategoria.setDescripcion("Bebidas eNergizantes");
            
            Integer categoriaId = categoriaBO.insertar(nuevaCategoria);
            if (categoriaId != null && categoriaId > 0) {
                System.out.println("✓ Categoría insertada exitosamente. ID: " + categoriaId);
            } else {
                System.out.println("✗ Error al insertar categoría");
            }
            
            // 2. LISTAR TODAS LAS CATEGORÍAS
            System.out.println("\n2. LISTANDO TODAS LAS CATEGORÍAS...");
            ArrayList<CategoriaDTO> categorias = categoriaBO.litarTodos();
            if (categorias != null && !categorias.isEmpty()) {
                System.out.println("✓ Categorías encontradas: " + categorias.size());
                for (CategoriaDTO cat : categorias) {
                    System.out.println("   - ID: " + cat.getCategoriaId() + 
                                     " | Descripción: " + cat.getDescripcion());
                }
            } else {
                System.out.println("✗ No se encontraron categorías");
            }
            
            // 3. OBTENER POR ID
            if (categoriaId != null) {
                System.out.println("\n3. OBTENIENDO CATEGORÍA POR ID: " + categoriaId);
                CategoriaDTO categoria = categoriaBO.obtenerPorId(categoriaId);
                if (categoria != null && categoria.getCategoriaId() != null) {
                    System.out.println("✓ Categoría encontrada:");
                    System.out.println("   - ID: " + categoria.getCategoriaId());
                    System.out.println("   - Descripción: " + categoria.getDescripcion());
                } else {
                    System.out.println("✗ Categoría no encontrada");
                }
            }
            
            // 4. ELIMINAR CATEGORÍA
            if (categoriaId != null) {
                System.out.println("\n4. ELIMINANDO CATEGORÍA ID: " + categoriaId);
                CategoriaDTO categoriaEliminar = new CategoriaDTO();
                categoriaEliminar.setCategoriaId(categoriaId);
                Integer resultado = categoriaBO.eliminar(categoriaEliminar);
                if (resultado != null && resultado > 0) {
                    System.out.println("✓ Categoría eliminada exitosamente");
                } else {
                    System.out.println("✗ Error al eliminar categoría");
                }
            }
            
        } catch (Exception e) {
            System.out.println("✗ ERROR: " + e.getMessage());
        }
    }
    
    // ========== PRUEBAS DE CLIENTE AL FIADO DAO ==========
    private static void probarClienteAlFiadoBO() {
        imprimirEncabezado("PRUEBAS DE CLIENTE AL FIADO BO");
        
        try {
            // 1. INSERTAR CLIENTE
            System.out.println("\n1. INSERTANDO CLIENTE AL FIADO...");
            ClienteAlFiadoDTO nuevoCliente = new ClienteAlFiadoDTO();
            nuevoCliente.setAlias("CARLOS123");
            nuevoCliente.setNombre("Carlos Pérez García");
            nuevoCliente.setTelefono("987654321");
            nuevoCliente.setFechaDePago(Date.valueOf(LocalDate.now().plusDays(15)));
            nuevoCliente.setActivo(true);
            
            Integer clienteId = clienteBO.insertar(nuevoCliente);
            if (clienteId != null && clienteId > 0) {
                System.out.println("✓ Cliente insertado exitosamente. ID: " + clienteId);
            } else {
                System.out.println("✗ Error al insertar cliente");
            }
            
            // 2. LISTAR TODOS LOS CLIENTES
            System.out.println("\n2. LISTANDO TODOS LOS CLIENTES...");
            ArrayList<ClienteAlFiadoDTO> clientes = clienteBO.litarTodos();
            if (clientes != null && !clientes.isEmpty()) {
                System.out.println("✓ Clientes encontrados: " + clientes.size());
                for (ClienteAlFiadoDTO cli : clientes) {
                    System.out.println("   - ID: " + cli.getClienteId() + 
                                     " | Alias: " + cli.getAlias() +
                                     " | Nombre: " + cli.getNombre() +
                                     " | Activo: " + cli.getActivo()+
                                     " | Deuda Total: " + cli.getMontoDeuda());
                }
            } else {
                System.out.println("✗ No se encontraron clientes");
            }
            
            // 3. OBTENER POR ID
            if (clienteId != null) {
                System.out.println("\n3. OBTENIENDO CLIENTE POR ID: " + clienteId);
                ClienteAlFiadoDTO cliente = clienteBO.obtenerPorId(clienteId);
                if (cliente != null && cliente.getClienteId() != null) {
                    System.out.println("✓ Cliente encontrado:");
                    System.out.println("   - ID: " + cliente.getClienteId());
                    System.out.println("   - Alias: " + cliente.getAlias());
                    System.out.println("   - Nombre: " + cliente.getNombre());
                    System.out.println("   - Teléfono: " + cliente.getTelefono());
                    System.out.println("   - Fecha de Pago: " + cliente.getFechaDePago());
                    System.out.println("   - Activo: " + cliente.getActivo());
                    System.out.println("   - Deuda Total: " + cliente.getMontoDeuda());
                } else {
                    System.out.println("✗ Cliente no encontrado");
                }
            }
            
            // 4. MODIFICAR CLIENTE
            if (clienteId != null) {
                System.out.println("\n4. MODIFICANDO CLIENTE ID: " + clienteId);
                ClienteAlFiadoDTO clienteModificar = new ClienteAlFiadoDTO();
                clienteModificar.setClienteId(clienteId);
                clienteModificar.setAlias("CArLO_MOD");
                clienteModificar.setNombre("Carlos Pérez Modificado");
                clienteModificar.setTelefono("999888777");
                clienteModificar.setFechaDePago(Date.valueOf(LocalDate.now().plusDays(30)));
                clienteModificar.setActivo(false);
                
                Integer resultado = clienteBO.modificar(clienteModificar);
                if (resultado != null && resultado > 0) {
                    System.out.println("✓ Cliente modificado exitosamente");
                    
                    // Verificar modificación
                    ClienteAlFiadoDTO clienteVerif = clienteBO.obtenerPorId(clienteId);
                    System.out.println("   Datos actualizados:");
                    System.out.println("   - Alias: " + clienteVerif.getAlias());
                    System.out.println("   - Nombre: " + clienteVerif.getNombre());
                } else {
                    System.out.println("✗ Error al modificar cliente");
                }
            }
            
            // 5. LISTAR CON LIKE
            System.out.println("\n5. BUSCANDO CLIENTES CON LIKE 'C'...");
            ArrayList<ClienteAlFiadoDTO> clientesFiltrados = clienteBO.litarTodosLike("C");
            if (clientesFiltrados != null && !clientesFiltrados.isEmpty()) {
                System.out.println("✓ Clientes encontrados: " + clientesFiltrados.size());
                for (ClienteAlFiadoDTO cli : clientesFiltrados) {
                    System.out.println("   - " + cli.getAlias() + " - " + cli.getNombre());
                }
            } else {
                System.out.println("✗ No se encontraron clientes con el filtro");
            }
            
        } catch (Exception e) {
            System.out.println("✗ ERROR: " + e.getMessage());
        }
    }
    
    // ========== PRUEBAS DE DEVOLUCION DAO ==========
    private static void probarDevolucionBO() {
        imprimirEncabezado("PRUEBAS DE DEVOLUCION BO");
        
        
        try {
            // Nota: Estas pruebas asumen que existe un usuario con ID 2
            // Ajusta según tu base de datos
            
            // 1. INSERTAR DEVOLUCIÓN
            System.out.println("\n1. INSERTANDO DEVOLUCIÓN...");
            DevolucionDTO nuevaDevolucion = new DevolucionDTO();
            nuevaDevolucion.setTotal(30.00);
            nuevaDevolucion.setFecha(Date.valueOf(LocalDate.now()));
            
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setUsuarioId(2); // Asume que existe usuario con ID 2
            nuevaDevolucion.setUsuario(usuario);
            
            Integer devolucionId = devolucionBO.insertar(nuevaDevolucion);
            if (devolucionId != null && devolucionId > 0) {
                System.out.println("✓ Devolución insertada exitosamente. ID: " + devolucionId);
            } else {
                System.out.println("✗ Error al insertar devolución");
            }
            
            // 2. LISTAR TODAS LAS DEVOLUCIONES
            System.out.println("\n2. LISTANDO TODAS LAS DEVOLUCIONES...");
            ArrayList<DevolucionDTO> devoluciones = devolucionBO.litarTodos();
            if (devoluciones != null && !devoluciones.isEmpty()) {
                System.out.println("✓ Devoluciones encontradas: " + devoluciones.size());
                for (DevolucionDTO dev : devoluciones) {
                    System.out.println("   - ID: " + dev.getDevolucionId() + 
                                     " | Total: S/. " + dev.getTotal() +
                                     " | Fecha: " + dev.getFecha());
                }
            } else {
                System.out.println("✗ No se encontraron devoluciones");
            }
            
            // 3. OBTENER POR ID
            if (devolucionId != null) {
                System.out.println("\n3. OBTENIENDO DEVOLUCIÓN POR ID: " + devolucionId);
                DevolucionDTO devolucion = devolucionBO.obtenerPorId(devolucionId);
                if (devolucion != null && devolucion.getDevolucionId() != null) {
                    System.out.println("✓ Devolución encontrada:");
                    System.out.println("   - ID: " + devolucion.getDevolucionId());
                    System.out.println("   - Total: S/. " + devolucion.getTotal());
                    System.out.println("   - Fecha: " + devolucion.getFecha());
                } else {
                    System.out.println("✗ Devolución no encontrada");
                }
            }
            
            // 4. LISTAR POR FECHA
            System.out.println("\n4. LISTANDO DEVOLUCIONES POR FECHA DE HOY...");
            ArrayList<DevolucionDTO> devFecha = devolucionBO.listarPorFecha(
                Date.valueOf(LocalDate.now())
            );
            System.out.println("✓ Devoluciones de hoy: " + (devFecha != null ? devFecha.size() : 0));
            
            // 5. LISTAR POR USUARIO
            System.out.println("\n5. LISTANDO DEVOLUCIONES POR USUARIO ID: 2");
            ArrayList<DevolucionDTO> devUsuario = devolucionBO.listarPorUsuario(2);
            System.out.println("✓ Devoluciones del usuario: " + (devUsuario != null ? devUsuario.size() : 0));
            
            // 6. LISTAR POR USUARIO Y FECHA
            System.out.println("\n5. LISTANDO DEVOLUCIONES POR FECHA DE HOY Y USUARIO ID: 7 ");
            ArrayList<DevolucionDTO> devUsuFech = devolucionBO.listarPorUsuarioYFecha(
                    7,Date.valueOf(LocalDate.now()));
            System.out.println("✓ Devoluciones del usuario: " + (devUsuFech != null ? devUsuFech.size() : 0));
            
        } catch (Exception e) {
            System.out.println("✗ ERROR: " + e.getMessage());
        }
    }
    
    // ========== PRUEBAS DE DETALLE DEVOLUCION DAO ==========
        private static void probarDetalleDevolucionBO() {
        imprimirEncabezado("PRUEBAS DE DETALLE DEVOLUCION BO");
        
        
        try {
            // Nota: Estas pruebas asumen que existen devolucion, producto y razón
            
            // 1. INSERTAR DETALLE
            System.out.println("\n1. INSERTANDO DETALLE DE DEVOLUCIÓN...");
            DetalleDevolucionDTO nuevoDetalle = new DetalleDevolucionDTO();
            
            DevolucionDTO devolucion = new DevolucionDTO();
            devolucion.setDevolucionId(2); // Asume que existe
            nuevoDetalle.setDevolucion(devolucion);
            
            ProductoDTO producto = new ProductoDTO();
            producto.setProductoId(3); // Asume que existe
            nuevoDetalle.setProducto(producto);
            
            nuevoDetalle.setCantidad(2);
            nuevoDetalle.setSubtotal(80.00);
            
            RazonDevolucionDTO razon = new RazonDevolucionDTO();
            razon.setRazonDevolucionId(1); // Asume que existe
            nuevoDetalle.setRazonDevolucion(razon);
            
            Integer resultado = detalleDevBO.insertar(nuevoDetalle);
            if (resultado == 0) resultado = devolucion.getDevolucionId();
            System.out.println("Resultado: " + resultado);
            if (resultado != null && resultado > 0) {
                System.out.println("✓ Detalle insertado exitosamente");
            } else {
                System.out.println("✗ Error al insertar detalle");
            }
            
            // 2. LISTAR TODOS
            System.out.println("\n2. LISTANDO TODOS LOS DETALLES...");
            ArrayList<DetalleDevolucionDTO> detalles = detalleDevBO.litarTodos();
            if (detalles != null && !detalles.isEmpty()) {
                System.out.println("✓ Detalles encontrados: " + detalles.size());
                for (DetalleDevolucionDTO det : detalles) {
                    System.out.println("   - Devolución: " + det.getDevolucion().getDevolucionId() + 
                                     " | Cantidad: " + det.getCantidad() +
                                     " | Subtotal: S/. " + det.getSubtotal());
                }
            } else {
                System.out.println("✗ No se encontraron detalles");
            }
            
            // 3. LISTAR POR DEVOLUCION
            System.out.println("\n3. LISTANDO DETALLES POR DEVOLUCIÓN ID: 2");
            ArrayList<DetalleDevolucionDTO> detDev = detalleDevBO.listarPorDevolucion(2);
            System.out.println("✓ Detalles encontrados: " + (detDev != null ? detDev.size() : 0));
            
            // 4. LISTAR POR PRODUCTO
            System.out.println("\n4. LISTANDO DETALLES POR PRODUCTO ID: 1");
            ArrayList<DetalleDevolucionDTO> detProd = detalleDevBO.listarPorProducto(1);
            System.out.println("✓ Detalles encontrados: " + (detProd != null ? detProd.size() : 0));
            
            // 5. LISTAR POR RAZON DEVOLUCION
            System.out.println("\n4. LISTANDO DETALLES POR RAZON DEVOLUCION ID: 2");
            ArrayList<DetalleDevolucionDTO> detRaz = detalleDevBO.
                             listarPorRazonDevolucion("Fecha de caducidad vencida");
            System.out.println("✓ Detalles encontrados: " + (detRaz != null ? detRaz.size() : 0));
            
            // 6. OBTENER POR ID
            System.out.println("Resultado Antes del Obtener Id: " + resultado);
            if (resultado != null && resultado > 0) {
                System.out.println("\n3. OBTENIENDO DEVOLUCIÓN POR ID: " + resultado
                        + " PRODUCTO ID " + 1);
                DetalleDevolucionDTO dev = detalleDevBO.obtenerPorId(resultado,1);
                if (dev != null && dev.getDevolucion().getDevolucionId()!= null) {
                    System.out.println("✓ Detalle Devolución encontrada:");
                    System.out.println("   - Cantidad: " + dev.getCantidad());
                    System.out.println("   - SubTotal: S/. " + dev.getSubtotal());
                    System.out.println("   - Fecha: " + dev.getDevolucion().getFecha());
                } else {
                    System.out.println("✗ Devolución no encontrada");
                }
            }
            
        } catch (Exception e) {
            System.out.println("✗ ERROR: " + e.getMessage());
        }
    }
        
        // ========== PRUEBAS DE HISTORIAL OPERACIONES DAO ==========
    private static void probarHistorialOperacionesBO() {
        imprimirEncabezado("PRUEBAS DE HISTORIAL OPERACIONES BO");
        
        try {
            // 1. LISTAR TODOS
            System.out.println("\n1. LISTANDO TODO EL HISTORIAL...");
            ArrayList<HistorialOperacionesDTO> historial = historialBO.listarTodos();
            if (historial != null && !historial.isEmpty()) {
                System.out.println("✓ Registros encontrados: " + historial.size());
                int contador = 0;
                for (HistorialOperacionesDTO hist : historial) {
                    if (contador < 5) { // Mostrar solo los primeros 5
                        System.out.println("   - ID: " + hist.getOperacionId() + 
                                         " | Tabla: " + hist.getTablaAfectada() +
                                         " | Operación: " + hist.getOperacion() +
                                         " | Fecha: " + hist.getFechaHora());
                        contador++;
                    }
                }
                if (historial.size() > 5) {
                    System.out.println("   ... y " + (historial.size() - 5) + " registros más");
                }
            } else {
                System.out.println("✗ No se encontraron registros en el historial");
            }
            
            // 2. LISTAR POR TABLA
            System.out.println("\n2. LISTANDO OPERACIONES DE TABLA 'BOD_CATEGORIA'");
            ArrayList<HistorialOperacionesDTO> porTabla = 
                historialBO.listarPorTabla("BOD_CATEGORIA");
            System.out.println("✓ Registros encontrados: " + 
                (porTabla != null ? porTabla.size() : 0));
            
            // 3. LISTAR POR FECHA
            System.out.println("\n3. LISTANDO OPERACIONES DE HOY");
            ArrayList<HistorialOperacionesDTO> porFecha = 
                historialBO.listarPorFecha(Date.valueOf(LocalDate.now()));
            System.out.println("✓ Registros de hoy: " + 
                (porFecha != null ? porFecha.size() : 0));
            
        } catch (Exception e) {
            System.out.println("✗ ERROR: " + e.getMessage());
        }
    }
    
}