using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.Script.Serialization;
using System.Globalization;

namespace SoftBodWA
{
    // ------------------------------------------------------
    //   DTOs DE SIMULACIÓN
    // ------------------------------------------------------
    public class ProductoSimulado
    {
        public int id { get; set; }
        public string nombre { get; set; }
        public double precio { get; set; }
        public int cantidad { get; set; }
    }

    public class VentaSimulada
    {
        public int ventaId { get; set; }
        public string clienteNombre { get; set; }
        public DateTime fecha { get; set; }
        public double total { get; set; }
        public List<ProductoSimulado> productos { get; set; }

        public string ProductosJson => new JavaScriptSerializer().Serialize(productos);

        public string FechaFormat => fecha.ToString("yyyy-MM-dd HH:mm");

        public string TotalFormat => total.ToString("N2", CultureInfo.InvariantCulture);
    }

    // ------------------------------------------------------
    //   CODE BEHIND PRINCIPAL
    // ------------------------------------------------------
    public partial class RegistrarDevolucion : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarRazonesDevolucion();
                CargarVentasRecientesSimuladas();
            }
        }

        private void CargarRazonesDevolucion()
        {
            // Tus razones ya están en el ASPX, así que no toco nada aquí.
        }

        private void CargarVentasRecientesSimuladas()
        {
            var listaVentas = new List<VentaSimulada>
            {
                new VentaSimulada
                {
                    ventaId = 1,
                    clienteNombre = "Juan Pérez",
                    fecha = new DateTime(2025, 11, 12, 10, 30, 0),
                    total = 8.80,
                    productos = new List<ProductoSimulado>
                    {
                        new ProductoSimulado { id = 101, nombre = "Arroz Diana 500g", precio = 2.50, cantidad = 2 },
                        new ProductoSimulado { id = 102, nombre = "Aceite Girasol 1L", precio = 3.80, cantidad = 1 }
                    }
                },
                new VentaSimulada
                {
                    ventaId = 62,
                    clienteNombre = "El Vecino 1",
                    fecha = new DateTime(2025, 10, 7, 10, 20, 0),
                    total = 68.00,
                    productos = new List<ProductoSimulado>
                    {
                        new ProductoSimulado { id = 201, nombre = "Leche Entera 1L", precio = 5.55, cantidad = 10 },
                        new ProductoSimulado { id = 202, nombre = "Azúcar Blanca 1Kg", precio = 1.20, cantidad = 10 }
                    }
                },
                new VentaSimulada
                {
                    ventaId = 20,
                    clienteNombre = "Cliente Genérico",
                    fecha = new DateTime(2025, 10, 3, 14, 5, 0),
                    total = 8.00,
                    productos = new List<ProductoSimulado>
                    {
                        new ProductoSimulado { id = 301, nombre = "Pan de Molde", precio = 4.00, cantidad = 2 }
                    }
                }
            };

            rptVentasRecientes.DataSource = listaVentas;
            rptVentasRecientes.DataBind();
        }

        protected void btnVerDetalleVenta_Click(object sender, EventArgs e)
        {
            // Simulación: si quieres que el botón "Ver" abra otra página o popup, aquí lo pones.
            // Por ahora no hace nada, pero no genera error.
        }

        protected void btnProcesarDevolucion_Click(object sender, EventArgs e)
        {
            // Aquí iría la lógica real de devolución.
            // Como es simulación, solo evitamos errores.
        }
    }
}