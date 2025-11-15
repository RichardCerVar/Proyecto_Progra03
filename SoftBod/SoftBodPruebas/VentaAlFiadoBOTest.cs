//using System;
//using System.Collections.Generic;
//using Microsoft.VisualStudio.TestTools.UnitTesting;
//using SoftBodBusiness;
//using SoftBodBusiness.SoftWSVentaAlFiado;
//namespace SoftBodPruebas
//{
//    [TestClass]
//    public class VentaAlFiadoBOTest
//    {
//        private VentaAlFiadoBO ventaAlFiadoBO;

//        public VentaAlFiadoBOTest()
//        {
//            this.ventaAlFiadoBO = new VentaAlFiadoBO();
//        }

//        //[TestMethod]
//        //public void TestInsertarVentaAlFiado()
//        //{
//        //    Console.WriteLine("Insertar Venta Al Fiado");

//        //    clienteAlFiadoDTO cliente = new clienteAlFiadoDTO();
//        //    cliente.clienteId = 10;
//        //    cliente.clienteIdSpecified = true;
//        //    cliente.alias = "CLIENTE001";
//        //    cliente.nombre = "María González";

//        //    usuarioDTO usuario = new usuarioDTO();
//        //    usuario.usuarioId = 1;
//        //    usuario.usuarioIdSpecified = true;
//        //    usuario.nombre = "Juan Pérez";

//        //    tipoDePago metodoPago = tipoDePago.FIADO;
//        //    //solo importa el ID
//        //    detalleVentaDTO detalle1 = new detalleVentaDTO();
//        //    productoDTO producto1 = new productoDTO();
//        //    producto1.productoId = 1;
//        //    producto1.productoIdSpecified = true;
//        //    producto1.nombre = "Arroz";
//        //    detalle1.producto = producto1;
//        //    detalle1.cantidad = 2;
//        //    detalle1.cantidadSpecified = true;
//        //    detalle1.subtotal = 51.00;
//        //    detalle1.subtotalSpecified = true;
//        //    //solo importa el ID
//        //    detalleVentaDTO detalle2 = new detalleVentaDTO();
//        //    productoDTO producto2 = new productoDTO();
//        //    producto2.productoId = 2;
//        //    producto2.productoIdSpecified = true;
//        //    producto2.nombre = "Aceite";
//        //    detalle2.producto = producto2;
//        //    detalle2.cantidad = 1;
//        //    detalle2.cantidadSpecified = true;
//        //    detalle2.subtotal = 15.00;
//        //    detalle2.subtotalSpecified = true;

//        //    detalleVentaDTO[] detallesVenta = new detalleVentaDTO[] { detalle1, detalle2 };

//        //    int resultado = this.ventaAlFiadoBO.insertarVentaAlFiado(
//        //        cliente,
//        //        usuario,
//        //        metodoPago,
//        //        detallesVenta
//        //    );

//        //    Assert.IsTrue(resultado > 0);

//        //}

//        [TestMethod]
//        public void TestObtenerVentaAlFiadoPorId()
//        {
//            Console.WriteLine("Obtener Venta Al Fiado Por Id");

//            ventaAlFiadoDTO venta = this.ventaAlFiadoBO.obtenerVentaAlFiadoPorId(5);
//            Console.WriteLine(venta.cliente.alias);
//            Console.WriteLine(venta.venta.total);
//            Assert.IsNotNull(venta);
//            Assert.IsTrue(venta.ventaFiadaId > 0);
//            Assert.IsNotNull(venta.cliente);
//            Assert.IsNotNull(venta.venta);
//        }

//        [TestMethod]
//        public void TestListarTodasVentasAlFiado()
//        {
//            Console.WriteLine("Listar Todas las Ventas Al Fiado");

//            List<ventaAlFiadoDTO> ventas = this.ventaAlFiadoBO.listarTodasVentasAlFiado();
//            foreach (var venta in ventas)
//            {
//                Console.WriteLine(venta.cliente.alias);
//                Console.WriteLine(venta.venta.total);
//            }
//            Assert.IsNotNull(ventas);
//            Assert.IsTrue(ventas.Count >= 0);
//        }

//        [TestMethod]
//        public void TestListarVentasAlFiadoPorAliasCliente()
//        {
//            Console.WriteLine("Listar Ventas Al Fiado por Alias de Cliente");

//            List<ventaAlFiadoDTO> ventas = this.ventaAlFiadoBO.listarVentasAlFiadoPorAliasCliente(
//                "V"
//            );
//            foreach (var venta in ventas)
//            {
//                Console.WriteLine(venta.cliente.alias);
//                Console.WriteLine(venta.venta.total);
//            }
//            Assert.IsNotNull(ventas);
//            Assert.IsTrue(ventas.Count >= 0);
//        }

//        [TestMethod]
//        public void TestListarVentasAlFiadoPorAliasClienteYFecha()
//        {
//            Console.WriteLine("Listar Ventas Al Fiado por Alias y Fecha");

//            string fecha = "2025-10-09";

//            List<ventaAlFiadoDTO> ventas = this.ventaAlFiadoBO.listarVentasAlFiadoPorAliasClienteYFecha(
//                "V",
//                fecha
//            );
//            foreach (var venta in ventas)
//            {
//                Console.WriteLine(venta.cliente.alias);
//                Console.WriteLine(venta.venta.total);
//            }
//            Assert.IsNotNull(ventas);
//            Assert.IsTrue(ventas.Count >= 0);
//        }
//    }
//}
