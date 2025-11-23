using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.Script.Serialization; // Necesario para serializar el JSON del Repeater
using System.Globalization;
// --- Simulación de DTOs ---
// NOTA: Estos 'using' deben apuntar a las referencias reales si el proyecto las tiene
// Si no las tienes, tendrás que definir las estructuras de los DTOs dummy.
// Para este ejemplo, usaremos clases internas que simulen los DTOs.

namespace SoftBodWA
{
    // --- Simulación de Estructuras DTO (Replicando las que se usarían en un proyecto real) ---
    // En un proyecto real, estas serían importadas desde SoftBodBusiness.SoftWSVenta, etc.
    // Aquí definimos solo lo necesario para la simulación.

    public class ProductoSimulado
    {
        public int id { get; set; }
        public string nombre { get; set; }
        public double precio { get; set; } // Precio unitario de la venta
        public int cantidad { get; set; }  // Cantidad vendida
    }

    public class VentaSimulada
    {
        public int ventaId { get; set; }
        public string clienteNombre { get; set; }
        public DateTime fecha { get; set; }
        public double total { get; set; }
        public List<ProductoSimulado> productos { get; set; }

        // Propiedad auxiliar para serializar los productos a JSON en el Repeater
        public string ProductosJson => new JavaScriptSerializer().Serialize(productos);
        public string FechaFormat => fecha.ToString("yyyy-MM-dd HH:mm");
        public string TotalFormat => total.ToString("N2", CultureInfo.InvariantCulture);
    }
    // ------------------------------------------------------------------------------------------

    public partial class RegistrarDevolucion : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarRazonesDevolucion();
                // 🟢 NUEVO: Cargar las ventas simuladas para el Repeater
                CargarVentasRecientesSimuladas();
            }
        }

        private void CargarRazonesDevolucion()
        {
            try
            {
                // Dejamos la carga estática del DropDownList definida en el ASPX, 
                // para evitar tener que simular el DTO de RazonDevolucionBO aquí.
                // Si la quieres cargar desde C#, descomenta el código siguiente
                /*
                ddlRazonDevolucion.Items.Insert(0, new ListItem("Seleccionar razón", ""));
                ddlRazonDevolucion.Items.Add(new ListItem("Producto dañado", "1"));
                ddlRazonDevolucion.Items.Add(new ListItem("Fecha de caducidad vencida", "2"));
                // ... añadir el resto de razones si es necesario
                */
            }
            catch (Exception)
            {
            }
        }

        // 🟢 NUEVO MÉTODO: Simulación de la obtención de ventas
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

            // 🟢 Asignar al Repeater
            rptVentasRecientes.DataSource = listaVentas;
            rptVentasRecientes.DataBind();
        }

        protected void btnProcesarDevolucion_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(txtIdVenta.Text))
            {
                // Se cambió a ScriptManager para ser compatible con UpdatePanel si lo hubiera, 
                // pero ClientScript.RegisterStartupScript también es válido aquí.
                ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Debe ingresar el ID de Venta.');", true);
                return;
            }

            if (string.IsNullOrEmpty(ddlRazonDevolucion.SelectedValue))
            {
                ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Debe seleccionar una Razón de Devolución.');", true);
                return;
            }

            // ... (validación de ID de Venta)

            try
            {
                int idVenta = int.Parse(txtIdVenta.Text);
                int idRazon = Convert.ToInt32(ddlRazonDevolucion.SelectedValue);

                // --- 🟢 LÓGICA DE SIMULACIÓN DE PROCESAMIENTO ---
                // En este punto, necesitarías el valor del 'carrito' del JS (oculto en un HiddenField).
                // Como no proporcionaste el HiddenField en el ASPX, solo simulamos.

                // float montoSimulado = ObtenerMontoTotalDelCarritoJS(); // Simulación
                string mensajeExito = $"Devolución (Simulada) procesada con éxito para la Venta #{idVenta} con Razón ID {idRazon}. Monto estimado: S/.X.XX";
                // Si quieres calcular un monto simualdo, puedes usar el valor en el texto del botón:
                // string montoSimulado = btnProcesarDevolucion.Text.Replace(" Procesar Devolución - ", "");
                // string mensajeExito = $"Devolución (Simulada) procesada con éxito para la Venta #{idVenta}. Monto: {montoSimulado}";

                ClientScript.RegisterStartupScript(this.GetType(), "successAlert", $"alert('{mensajeExito}');", true);

                // Limpiar la UI
                txtIdVenta.Text = string.Empty;
                ddlRazonDevolucion.SelectedIndex = 0;
                // El JS se encarga de resetear el estado del botón y la lista de productos
            }
            catch (Exception ex)
            {
                ClientScript.RegisterStartupScript(this.GetType(), "errorAlert", $"alert('Error al procesar la devolución: {ex.Message.Replace("'", "")}');", true);
            }
        }

        protected void btnVerDetalleVenta_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string idVenta = btn.CommandArgument;

            string script = $"alert('Simulando la apertura de la modal de detalle para la Venta ID: {idVenta}');";
            ScriptManager.RegisterStartupScript(this, GetType(), "VerDetalle", script, true);
        }
    }
}