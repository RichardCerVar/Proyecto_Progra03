
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftBodBusiness;
using SoftBodBusiness.SoftWSDevolucion;
using System;
using System.Collections.Generic;
using System.ServiceModel;
using System.ServiceModel.Channels;
using System.ServiceModel.Description;
using System.ServiceModel.Dispatcher;
// Usando alias para evitar ambigüedad entre DTOs de diferentes servicios
using CategoriaDTO = SoftBodBusiness.SoftWSCategoria.categoriaDTO;
using ClienteAlFiadoDTO = SoftBodBusiness.SoftWSClienteAlFiado.clienteAlFiadoDTO;
using DetalleVentaDTO = SoftBodBusiness.SoftWSDetalleVenta.detalleVentaDTO;
//using DetalleDevolucionDTO = SoftBodBusiness.SoftWSDetalleDevolucion.detalleDevolucionDTO;
using HistorialOperacionesDTO = SoftBodBusiness.SoftWSHistorialOperaciones.historialOperacionesDTO;
namespace SoftBodPruebas
{
    //[TestClass]
    public class CategoriaBOTest
    {
        private CategoriaBO categoriaBO;

        public CategoriaBOTest()
        {
            this.categoriaBO = new CategoriaBO();
        }

        [TestMethod]
        public void PruebasCategoria()
        {
            Console.WriteLine("=== PRUEBAS CATEGORIA BO ===\n");

            // 1. Insertar Categoria
            Console.WriteLine("1. Insertando nueva categoria...");
            int nuevaCategoriaId = categoriaBO.insertarCategoria("Productos Embasados");
            Console.WriteLine($"   ID generado: {nuevaCategoriaId}\n");

            // 2. Verificar si nombre ya existe
            Console.WriteLine("2. Verificando si nombre 'Productos Embasados' ya existe...");
            bool existe = categoriaBO.nombreCategoriaYaExiste("Productos Embasados");
            Console.WriteLine($"   Existe: {existe}\n");

            // 3. Obtener categoria por ID
            Console.WriteLine($"3. Obteniendo categoria ID {nuevaCategoriaId}...");
            CategoriaDTO cat = categoriaBO.obtenerCategoriaPorId(nuevaCategoriaId);
            if (cat != null)
            {
                Console.WriteLine($"   ID: {cat.categoriaId}");
                Console.WriteLine($"   Descripción: {cat.descripcion}\n");
            }

            // 4. Listar todas las categorias
            Console.WriteLine("4. Listando todas las categorias...");
            List<CategoriaDTO> categorias = categoriaBO.listarTodasCategorias();
            Console.WriteLine($"   Total: {categorias.Count}");
            foreach (var c in categorias)
            {
                Console.WriteLine($"   - {c.categoriaId}: {c.descripcion}");
            }
            Console.WriteLine();

            // 5. Verificar si contiene productos
            Console.WriteLine($"5. Verificando si categoria {nuevaCategoriaId} contiene productos...");
            bool tieneProductos = categoriaBO.categoriaContieneProductos(nuevaCategoriaId);
            Console.WriteLine($"   Tiene productos: {tieneProductos}\n");

            // 6. Verificar si puede eliminarse
            Console.WriteLine($"6. Verificando si se puede eliminar categoria {nuevaCategoriaId}...");
            bool puedeEliminar = categoriaBO.puedeEliminarCategoria(nuevaCategoriaId);
            Console.WriteLine($"   Puede eliminar: {puedeEliminar}\n");

            // 7. Eliminar categoria
            if (puedeEliminar)
            {
                Console.WriteLine("7. Eliminando categoria...");
                int resultado = categoriaBO.eliminarCategoria(cat);
                Console.WriteLine($"   Resultado: {resultado}\n");
            }
        }
    }

    //[TestClass]
    public class ClienteAlFiadoBOTest
    {
        private ClienteAlFiadoBO clienteBO;

        public ClienteAlFiadoBOTest()
        {
            this.clienteBO = new ClienteAlFiadoBO();
        }

        [TestMethod]
        public void PruebasClienteAlFiado()
        {
            Console.WriteLine("=== PRUEBAS CLIENTE AL FIADO BO ===\n");

            // 1. Insertar cliente
            Console.WriteLine("1. Insertando nuevo cliente al fiado...");
            int nuevoClienteId = clienteBO.insertarClienteAlFiado(
                "Juanes",
                "Juan Pérez García",
                "987654321",
                "2024-12-15"
            );
            Console.WriteLine($"   ID generado: {nuevoClienteId}\n");

            // 2. Obtener cliente por ID
            Console.WriteLine($"2. Obteniendo cliente ID {nuevoClienteId}...");
            ClienteAlFiadoDTO cliente = clienteBO.obtenerClienteAlFiadoPorId(nuevoClienteId);
            if (cliente != null)
            {
                Console.WriteLine($"   ID: {cliente.clienteId}");
                Console.WriteLine($"   Alias: {cliente.alias}");
                Console.WriteLine($"   Nombre: {cliente.nombre}");
                Console.WriteLine($"   Teléfono: {cliente.telefono}");
                Console.WriteLine($"   Fecha de Pago: {cliente.fechaDePago}");
                Console.WriteLine($"   Monto Deuda: {cliente.montoDeuda}");
                Console.WriteLine($"   Activo: {cliente.activo}\n");
            }

            // 3. Modificar cliente
            Console.WriteLine("3. Modificando datos del cliente...");
            cliente.telefono = "999888777";
            cliente.fechaDePago = "2024-12-20";
            int resultadoMod = clienteBO.modificarClienteAlFiado(cliente);
            Console.WriteLine($"   Resultado: {resultadoMod}\n");

            // 4. Listar todos los clientes
            Console.WriteLine("4. Listando todos los clientes al fiado...");
            List<ClienteAlFiadoDTO> clientes = clienteBO.listarTodosClientesAlFiado();
            Console.WriteLine($"   Total: {clientes.Count}");
            foreach (var c in clientes)
            {
                Console.WriteLine($"   - {c.clienteId}: {c.alias} - {c.nombre}");
            }
            Console.WriteLine();

            // 5. Buscar clientes con filtro LIKE
            Console.WriteLine("5. Buscando clientes con 'Juan'...");
            List<ClienteAlFiadoDTO> clientesFiltrados = clienteBO.listarTodosClientesAlFiadoLike("Juan");
            Console.WriteLine($"   Encontrados: {clientesFiltrados.Count}");
            foreach (var c in clientesFiltrados)
            {
                Console.WriteLine($"   - {c.alias}: {c.nombre}");
            }
            Console.WriteLine();

            // 6. Bloquear cliente por morosidad
            Console.WriteLine($"6. Bloqueando cliente {nuevoClienteId} por morosidad...");
            clienteBO.bloquearClienteAlFiadoPorMorosidad(nuevoClienteId);
            Console.WriteLine("   Cliente bloqueado\n");

            // 7. Desbloquear cliente
            Console.WriteLine($"7. Desbloqueando cliente {nuevoClienteId}...");
            clienteBO.desbloquearClienteAlFiado(nuevoClienteId);
            Console.WriteLine("   Cliente desbloqueado\n");
        }
    }

    //[TestClass]
    //public class DetalleDevolucionBOTest
    //{
    //    private DetalleDevolucionBO detalleBO;

    //    public DetalleDevolucionBOTest()
    //    {
    //        this.detalleBO = new DetalleDevolucionBO();
    //    }

    //    [TestMethod]
    //    public void PruebasDetalleDevolucion()
    //    {
    //        Console.WriteLine("=== PRUEBAS DETALLE DEVOLUCION BO ===\n");

    //         Nota: Para estas pruebas necesitas IDs válidos de devolución, producto y razón
    //        int devolucionIdPrueba = 1;
    //        int productoIdPrueba = 1;

    //         1. Listar todos los detalles
    //        Console.WriteLine("1. Listando todos los detalles de devolución...");
    //        List<DetalleDevolucionDTO> detalles = detalleBO.listarTodosDetallesDevolucion();
    //        Console.WriteLine($"   Total: {detalles.Count}");
    //        foreach (var d in detalles)
    //        {
    //            Console.WriteLine($"   - Producto ID: {d.producto?.productoId}, Cantidad: {d.cantidad}, Subtotal: {d.subtotal}");
    //        }
    //        Console.WriteLine();

    //         2. Listar por devolución
    //        Console.WriteLine($"2. Listando detalles de devolución ID {devolucionIdPrueba}...");
    //        List<DetalleDevolucionDTO> detallesPorDev = detalleBO.listarDetallesDevolucionPorDevolucion(devolucionIdPrueba);
    //        Console.WriteLine($"   Total: {detallesPorDev.Count}");
    //        foreach (var d in detallesPorDev)
    //        {
    //            Console.WriteLine($"   - Producto: {d.producto?.nombre}, Cantidad: {d.cantidad}");
    //        }
    //        Console.WriteLine();

    //         3. Listar por producto
    //        Console.WriteLine($"3. Listando devoluciones del producto ID {productoIdPrueba}...");
    //        List<DetalleDevolucionDTO> detallesPorProd = detalleBO.listarDetallesDevolucionPorProducto(productoIdPrueba);
    //        Console.WriteLine($"   Total: {detallesPorProd.Count}\n");

    //         4. Listar por razón
    //        Console.WriteLine("4. Listando devoluciones por razón 'Producto dañado'...");
    //        List<DetalleDevolucionDTO> detallesPorRazon = detalleBO.listarDetallesDevolucionPorRazon("Producto dañado");
    //        Console.WriteLine($"   Total: {detallesPorRazon.Count}\n");

    //         5. Obtener detalle específico
    //        Console.WriteLine($"5. Obteniendo detalle Producto {productoIdPrueba} - Devolución {devolucionIdPrueba}...");
    //        DetalleDevolucionDTO detalle = detalleBO.obtenerDetalleDevolucionPorId(productoIdPrueba, devolucionIdPrueba);
    //        if (detalle != null)
    //        {
    //            Console.WriteLine($"   Producto: {detalle.producto?.nombre}");
    //            Console.WriteLine($"   Cantidad: {detalle.cantidad}");
    //            Console.WriteLine($"   Subtotal: {detalle.subtotal}");
    //            Console.WriteLine($"   Razón: {detalle.razonDevolucion?.descripcion}\n");
    //        }
    //    }
    //}

    //[TestClass]
    public class DetalleVentaBOTest
    {
        private DetalleVentaBO detalleBO;

        public DetalleVentaBOTest()
        {
            this.detalleBO = new DetalleVentaBO();
        }

        [TestMethod]
        public void PruebasDetalleVenta()
        {
            Console.WriteLine("=== PRUEBAS DETALLE VENTA BO ===\n");

            int ventaIdPrueba = 1;
            int productoIdPrueba = 1;

            // 1. Listar todos los detalles
            //MUY GRANDE
            //Console.WriteLine("1. Listando todos los detalles de venta...");
            //List<DetalleVentaDTO> detalles = detalleBO.listarTodosDetallesVenta().GetRange(0,20);
            //Console.WriteLine($"   Total: {detalles.Count}");
            //foreach (var d in detalles)
            //{
            //    Console.WriteLine($"   - Venta: {d.venta?.ventaId}, Producto: {d.producto?.nombre}, Cantidad: {d.cantidad}");
            //}
            //Console.WriteLine();

            // 2. Listar por venta
            Console.WriteLine($"2. Listando detalles de venta ID {ventaIdPrueba}...");
            List<DetalleVentaDTO> detallesPorVenta = detalleBO.listarDetallesVentaPorVenta(ventaIdPrueba);
            Console.WriteLine($"   Total: {detallesPorVenta.Count}");
            foreach (var d in detallesPorVenta)
            {
                Console.WriteLine($"   - Producto: {d.producto?.nombre}");
                Console.WriteLine($"     Cantidad: {d.cantidad}, Subtotal: {d.subtotal}");
            }
            Console.WriteLine();

            // 3. Listar por producto
            Console.WriteLine($"3. Listando ventas del producto ID {productoIdPrueba}...");
            List<DetalleVentaDTO> detallesPorProd = detalleBO.listarDetallesVentaPorProducto(productoIdPrueba);
            Console.WriteLine($"   Total: {detallesPorProd.Count}\n");

            // 4. Obtener detalle específico
            Console.WriteLine($"4. Obteniendo detalle Producto {productoIdPrueba} - Venta {ventaIdPrueba}...");
            DetalleVentaDTO detalle = detalleBO.obtenerDetalleVentaPorId(productoIdPrueba, ventaIdPrueba);
            if (detalle != null)
            {
                Console.WriteLine($"   Producto: {detalle.producto?.nombre}");
                Console.WriteLine($"   Cantidad: {detalle.cantidad}");
                Console.WriteLine($"   Subtotal: {detalle.subtotal}\n");
            }
        }
    }

    //[TestClass]
    //public class DevolucionBOTest
    //{
    //    private DevolucionBO devolucionBO;

    //    public DevolucionBOTest()
    //    {
    //        this.devolucionBO = new DevolucionBO();
    //    }

    //    [TestMethod]
    //    public void PruebasDevolucion()
    //    {
    //        //Console.WriteLine("=== PRUEBAS DEVOLUCION BO ===\n");

    //        //int usuarioIdPrueba = 2;

    //        //// 1. Listar todas las devoluciones
    //        //Console.WriteLine("1. Listando todas las devoluciones...");
    //        //List<DevolucionDTO> devoluciones = devolucionBO.listarTodasDevoluciones();
    //        //Console.WriteLine($"   Total: {devoluciones.Count}");
    //        //foreach (var d in devoluciones)
    //        //{
    //        //    Console.WriteLine($"   - ID: {d.devolucionId}, Fecha: {d.fecha}, Total: {d.total}");
    //        //}
    //        //Console.WriteLine();

    //        //// 2. Listar por fecha
    //        //Console.WriteLine("2. Listando devoluciones del 2025-10-09...");
    //        //List<DevolucionDTO> devPorFecha = devolucionBO.listarDevolucionesPorFecha("2025-10-09");
    //        //Console.WriteLine($"   Total: {devPorFecha.Count}\n");

    //        //// 3. Listar por usuario
    //        //Console.WriteLine($"3. Listando devoluciones del usuario ID {usuarioIdPrueba}...");
    //        //List<DevolucionDTO> devPorUsuario = devolucionBO.listarDevolucionesPorUsuario(usuarioIdPrueba);
    //        //Console.WriteLine($"   Total: {devPorUsuario.Count}");
    //        //foreach (var d in devPorUsuario)
    //        //{
    //        //    Console.WriteLine($"   - {d.devolucionId}: {d.fecha} - S/. {d.total}");
    //        //}
    //        //Console.WriteLine();

    //        //// 4. Listar por usuario y fecha
    //        //Console.WriteLine($"4. Listando devoluciones del usuario {usuarioIdPrueba} en fecha 2025-10-09...");
    //        //List<DevolucionDTO> devPorUsuarioFecha = devolucionBO.listarDevolucionesPorUsuarioYFecha(usuarioIdPrueba, "2025-10-09");
    //        //Console.WriteLine($"   Total: {devPorUsuarioFecha.Count}\n");

    //        //// 5. Obtener devolución por ID
    //        //if (devoluciones.Count > 0)
    //        //{
    //        //    int devId = devoluciones[0].devolucionId;
    //        //    Console.WriteLine($"5. Obteniendo devolución ID {devId}...");
    //        //    DevolucionDTO dev = devolucionBO.obtenerDevolucionPorId(devId);
    //        //    if (dev != null)
    //        //    {
    //        //        Console.WriteLine($"   ID: {dev.devolucionId}");
    //        //        Console.WriteLine($"   Fecha: {dev.fecha}");
    //        //        Console.WriteLine($"   Total: {dev.total}");
    //        //        Console.WriteLine($"   Usuario: {dev.usuario?.nombre}\n");
    //        //    }
    //        //}

    //        //Console.WriteLine("Nota: Los métodos insertarDevolucion y registrarDevolucionCompleta");
    //        //Console.WriteLine("requieren objetos DTO completos para ser probados.\n");
    //        Console.WriteLine("Insertar Devolucion.\n");

    //        int idUser = 2;
    //        SoftBodBusiness.SoftWSDevolucion.usuarioDTO user = new SoftBodBusiness.SoftWSDevolucion.usuarioDTO(); 
    //        user.usuarioId = idUser;


    //        List<SoftBodBusiness.SoftWSDevolucion.detalleDevolucionDTO> detallesDev = new List<SoftBodBusiness.SoftWSDevolucion.detalleDevolucionDTO>();

    //        // 🔁 Crear 4 detalles distintos
    //        for (int i = 1; i <= 4; i++)
    //        {
    //            var detalleDev = new SoftBodBusiness.SoftWSDevolucion.detalleDevolucionDTO();
                
    //            detalleDev.razonDevolucion = new SoftBodBusiness.SoftWSDevolucion.razonDevolucionDTO();
    //            detalleDev.razonDevolucion.razonDevolucionId = 1;
    //            detalleDev.subtotal = 10.90;
    //            detalleDev.cantidad = 4;
    //            detalleDev.producto = new SoftBodBusiness.SoftWSDevolucion.productoDTO();
    //            detalleDev.producto.productoId = i; // 1, 2, 3, 4

    //            detallesDev.Add(detalleDev);
    //        }
    //        var devBo = new DevolucionBO();
    //        //client.Endpoint.EndpointBehaviors.Add(new SoapInspectorBehavior()); // behavior que captura XML
    //        int nuevaDev = devBo.insertarDevolucion(user, detallesDev.ToArray());
    //        Console.WriteLine("Nueva Devolucion con id: " + nuevaDev + " insertada");

    //    }

        public class SoapInspectorBehavior : IEndpointBehavior
        {
            public void AddBindingParameters(ServiceEndpoint endpoint, BindingParameterCollection bindingParameters) { }
            public void ApplyClientBehavior(ServiceEndpoint endpoint, ClientRuntime clientRuntime)
            {
                clientRuntime.MessageInspectors.Add(new SoapInspector());
            }
            public void ApplyDispatchBehavior(ServiceEndpoint endpoint, EndpointDispatcher endpointDispatcher) { }
            public void Validate(ServiceEndpoint endpoint) { }
        }

        public class SoapInspector : IClientMessageInspector
        {
            public void AfterReceiveReply(ref Message reply, object correlationState)
            {
                Console.WriteLine("SOAP Response:");
                Console.WriteLine(reply.ToString());
            }

            public object BeforeSendRequest(ref Message request, IClientChannel channel)
            {
                Console.WriteLine("SOAP Request:");
                Console.WriteLine(request.ToString());
                return null;
            }
        }
    }

    //[TestClass]
    public class HistorialOperacionesBOTest
    {
        private HistorialOperacionesBO historialBO;

        public HistorialOperacionesBOTest()
        {
            this.historialBO = new HistorialOperacionesBO();
        }

        [TestMethod]
        public void PruebasHistorialOperaciones()
        {
            Console.WriteLine("=== PRUEBAS HISTORIAL OPERACIONES BO ===\n");

            int usuarioIdPrueba = 1;

            // 1. Listar todo el historial
            Console.WriteLine("1. Listando todas las operaciones...");
            List<HistorialOperacionesDTO> historial = historialBO.listarTodosHistorialOperaciones();
            Console.WriteLine($"   Total: {historial.Count}");
            for (int i = 0; i < Math.Min(5, historial.Count); i++)
            {
                var h = historial[i];
                Console.WriteLine($"   - {h.operacionId}: {h.operacion} en {h.tablaAfectada} - {h.fechaHora}");
            }
            Console.WriteLine();

            // 2. Listar por usuario
            Console.WriteLine($"2. Listando operaciones del usuario ID {usuarioIdPrueba}...");
            List<HistorialOperacionesDTO> histPorUsuario = historialBO.listarHistorialOperacionesPorUsuario(usuarioIdPrueba);
            Console.WriteLine($"   Total: {histPorUsuario.Count}\n");

            // 3. Listar por tabla
            Console.WriteLine("3. Listando operaciones en tabla 'Producto'...");
            List<HistorialOperacionesDTO> histPorTabla = historialBO.listarHistorialOperacionesPorTabla("BOD_PRODUCTO");
            Console.WriteLine($"   Total: {histPorTabla.Count}");
            foreach (var h in histPorTabla)
            {
                Console.WriteLine($"   - {h.operacion}: {h.fechaHora}");
            }
            Console.WriteLine();

            // 4. Listar por tipo de operación
            Console.WriteLine("4. Listando operaciones de tipo 'INSERCION'...");
            List<HistorialOperacionesDTO> histPorTipo = historialBO.listarHistorialOperacionesPorTipoOperacion("INSERCION");
            Console.WriteLine($"   Total: {histPorTipo.Count}\n");

            // 5. Listar por fecha
            Console.WriteLine("5. Listando operaciones del 2025-10-07...");
            List<HistorialOperacionesDTO> histPorFecha = historialBO.listarHistorialOperacionesPorFecha("2025-10-07");
            Console.WriteLine($"   Total: {histPorFecha.Count}\n");

            // 6. Listar por usuario y tabla
            Console.WriteLine($"6. Listando operaciones del usuario {usuarioIdPrueba} en tabla 'BOD_PRODUCTO'...");
            List<HistorialOperacionesDTO> histUsuarioTabla = historialBO.listarHistorialOperacionesPorUsuarioYTabla(usuarioIdPrueba, "BOD_PRODUCTO");
            Console.WriteLine($"   Total: {histUsuarioTabla.Count}\n");

            // 7. Listar por tabla y tipo
            Console.WriteLine("7. Listando MODIFICACION en tabla 'Producto'...");
            List<HistorialOperacionesDTO> histTablaYTipo = historialBO.listarHistorialOperacionesPorTablaYTipoOperacion("BOD_PRODUCTO", "MODIFICACION");
            Console.WriteLine($"   Total: {histTablaYTipo.Count}\n");

            // 9. Obtener por ID
            if (historial.Count > 0)
            {
                int opId = historial[0].operacionId;
                Console.WriteLine($"9. Obteniendo operación ID {opId}...");
                HistorialOperacionesDTO op = historialBO.obtenerHistorialOperacionPorId(opId);
                if (op != null)
                {
                    Console.WriteLine($"   ID: {op.operacionId}");
                    Console.WriteLine($"   Operación: {op.operacion}");
                    Console.WriteLine($"   Tabla: {op.tablaAfectada}");
                    Console.WriteLine($"   Fecha/Hora: {op.fechaHora}");
                    Console.WriteLine($"   Usuario: {op.usuario?.nombre}\n");
                }
            }


            // 8. Listar con filtros complejos
            Console.WriteLine("8. Listando con filtros múltiples...");
            List<HistorialOperacionesDTO> histFiltrado = historialBO.listarHistorialOperacionesConFiltros(
                null,
                "BOD_VENTAS", // nombreTabla
                null, // tipoOperacion (vacío = sin filtro)
                null, // fechaOperacion
                null, // usuarioId (0 = sin filtro)
                null, // usuario
                null, // tipoUsuario
                true // estado
            );
            Console.WriteLine($"   Total: {histFiltrado.Count}\n");

            
        }
    }
