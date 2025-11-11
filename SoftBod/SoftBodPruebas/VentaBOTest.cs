using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftBodBusiness;
using SoftBodBusiness.SoftWSVenta;

namespace SoftBodPruebas
{
    [TestClass]
    public class VentaBOTest
    {
        private VentaBO ventaBO;

        public VentaBOTest()
        {
            this.ventaBO = new VentaBO();
        }

        //[TestMethod]
        //public void TestInsertarVenta()
        //{
        //    Console.WriteLine("Insertar Venta");

        //    usuarioDTO usuario = new usuarioDTO();
        //    usuario.usuarioId = 1;
        //    usuario.usuarioIdSpecified = true;
        //    usuario.nombre = "Juan Pérez";
        //    usuario.correo = "juan.perez@example.com";

        //    tipoDePago metodoPago = tipoDePago.EFECTIVO;

        //    detalleVentaDTO detalle1 = new detalleVentaDTO();
        //    productoDTO producto1 = new productoDTO();
        //    producto1.productoId = 1;
        //    producto1.productoIdSpecified = true;
        //    producto1.nombre = "Arroz Superior";
        //    producto1.precioUnitario = 25.50;
        //    producto1.precioUnitarioSpecified = true;
        //    detalle1.producto = producto1;
        //    detalle1.cantidad = 3;
        //    detalle1.cantidadSpecified = true;
        //    detalle1.subtotal = 76.50;
        //    detalle1.subtotalSpecified = true;

        //    detalleVentaDTO detalle2 = new detalleVentaDTO();
        //    productoDTO producto2 = new productoDTO();
        //    producto2.productoId = 2;
        //    producto2.productoIdSpecified = true;
        //    producto2.nombre = "Aceite Vegetal";
        //    producto2.precioUnitario = 15.00;
        //    producto2.precioUnitarioSpecified = true;
        //    detalle2.producto = producto2;
        //    detalle2.cantidad = 2;
        //    detalle2.cantidadSpecified= true;
        //    detalle2.subtotal = 30.00;
        //    detalle2.subtotalSpecified = true;

        //    detalleVentaDTO[] detallesVenta = new detalleVentaDTO[] { detalle1, detalle2 };

        //    int resultado = this.ventaBO.insertarVenta(usuario, metodoPago, detallesVenta);

        //    Assert.IsTrue(resultado > 0);
        //}

        [TestMethod]
        public void TestObtenerVentaPorId()
        {
            Console.WriteLine("Obtener Venta Por Id");

            ventaDTO venta = this.ventaBO.obtenerVentaPorId(30);
            Console.WriteLine(venta.total);

            Assert.IsNotNull(venta);
            Assert.IsTrue(venta.ventaId > 0);
            Assert.IsNotNull(venta.usuario);
            Assert.IsTrue(venta.total > 0);
        }

        [TestMethod]
        public void TestListarTodasVentas()
        {
            Console.WriteLine("Listar Todas las Ventas");

            List<ventaDTO> ventas = this.ventaBO.listarTodasVentas();
            foreach (var venta in ventas)
            {
                Console.WriteLine(venta.total);
            }
            Assert.IsNotNull(ventas);
            Assert.IsTrue(ventas.Count >= 0);
        }

        [TestMethod]
        public void TestListarVentasPorFecha()
        {
            Console.WriteLine("Listar Ventas por Fecha: 2025-10-09");
            string fecha = "2025-10-09";

            List<ventaDTO> ventas = this.ventaBO.listarVentasPorFecha(fecha);
            foreach (var venta in ventas)
            {
                Console.WriteLine(venta.total);
            }
            Assert.IsNotNull(ventas);
            Assert.IsTrue(ventas.Count >= 0);
        }
    }
}
