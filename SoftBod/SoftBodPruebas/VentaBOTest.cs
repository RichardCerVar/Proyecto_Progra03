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

        [TestMethod]
        public void TestInsertarVenta()
        {
            Console.WriteLine("Insertar Venta");

            usuarioDTO usuario = new usuarioDTO();
            usuario.usuarioId = 1;
            usuario.nombre = "Juan Pérez";
            usuario.correo = "juan.perez@example.com";

            tipoDePago metodoPago = tipoDePago.EFECTIVO;

            detalleVentaDTO detalle1 = new detalleVentaDTO();
            productoDTO producto1 = new productoDTO();
            producto1.productoId = 1;
            producto1.nombre = "Arroz Superior";
            producto1.precioUnitario = 25.50;
            detalle1.producto = producto1;
            detalle1.cantidad = 3;
            detalle1.subtotal = 76.50;

            detalleVentaDTO detalle2 = new detalleVentaDTO();
            productoDTO producto2 = new productoDTO();
            producto2.productoId = 2;
            producto2.nombre = "Aceite Vegetal";
            producto2.precioUnitario = 15.00;
            detalle2.producto = producto2;
            detalle2.cantidad = 2;
            detalle2.subtotal = 30.00;

            detalleVentaDTO[] detallesVenta = new detalleVentaDTO[] { detalle1, detalle2 };

            int resultado = this.ventaBO.insertarVenta(usuario, metodoPago, detallesVenta);

            Assert.IsTrue(resultado > 0);

            // Segunda venta con transferencia
            metodoPago = tipoDePago.TRANSFERENCIA;
            detalleVentaDTO detalle3 = new detalleVentaDTO();
            productoDTO producto3 = new productoDTO();
            producto3.productoId = 3;
            producto3.nombre = "Azúcar Blanca";
            producto3.precioUnitario = 3.50;
            detalle3.producto = producto3;
            detalle3.cantidad = 5;
            detalle3.subtotal = 17.50;

            detalleVentaDTO[] detallesVenta2 = new detalleVentaDTO[] { detalle3 };

            resultado = this.ventaBO.insertarVenta(usuario, metodoPago, detallesVenta2);

            Assert.IsTrue(resultado > 0);
        }

        [TestMethod]
        public void TestObtenerVentaPorId()
        {
            Console.WriteLine("Obtener Venta Por Id");

            ventaDTO venta = this.ventaBO.obtenerVentaPorId(1);
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
            Console.WriteLine("Listar Ventas por Fecha hoy");

            string fecha = DateTime.Now.ToString("yyyy-MM-dd");

            List<ventaDTO> ventas = this.ventaBO.listarVentasPorFecha(fecha);
            foreach (var venta in ventas)
            {
                Console.WriteLine(venta.total);
            }
            Assert.IsNotNull(ventas);
            Assert.IsTrue(ventas.Count >= 0);

            // Test con fecha específica
            Console.WriteLine("Listar Ventas por Fecha: 2025-09-15");
            ventas = this.ventaBO.listarVentasPorFecha("2025-01-15");
            Assert.IsNotNull(ventas);
            foreach (var venta in ventas)
            {
                Console.WriteLine(venta.total);
            }
        }
    }
}
