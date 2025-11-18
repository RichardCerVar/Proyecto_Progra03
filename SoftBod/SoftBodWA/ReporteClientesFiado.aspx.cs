using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBodWA
{
    public enum Tipo_de_pago { EFECTIVO, TRANSFERENCIA, FIADO }

    public class ProductoDTO
    {
        public string Nombre { get; set; }
    }

    public class ClienteAlFiadoDTO
    {
        public int ClienteId { get; set; }
        public string Alias { get; set; }
        public string Nombre { get; set; }
        public string Telefono { get; set; }
        public double MontoDeuda { get; set; } 
        public string FechaDePago { get; set; }
    }

    public class RegistroPagoFiadoDTO
    {
        public string Fecha { get; set; }
        public double Monto { get; set; }
        public Tipo_de_pago MetodoPago { get; set; }
        public string Descripcion { get; set; }
    }

    public class VentaDTO
    {
        public int? VentaId { get; set; }
        public double Total { get; set; }
        public string Fecha { get; set; }
        public List<DetalleVentaDTO> Detalles { get; set; }
    }

    public class DetalleVentaDTO
    {
        public ProductoDTO Producto { get; set; }
        public int Cantidad { get; set; }
        public double Subtotal { get; set; }
    }

    public class VentaAlFiadoDTO
    {
        public VentaDTO Venta { get; set; }
    }


    public partial class ReporteClientesFiado : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarClientesDropDownList();
                if (ddlCliente.Items.Count > 0)
                {
                    CargarReporte(int.Parse(ddlCliente.SelectedValue));
                }
            }
        }

        private void CargarClientesDropDownList()
        {
            var clientes = new List<ClienteAlFiadoDTO>
            {
                new ClienteAlFiadoDTO { ClienteId = 1, Alias = "juan123", Nombre = "Juan Pérez", MontoDeuda = 85.30 },
                new ClienteAlFiadoDTO { ClienteId = 2, Alias = "maria88", Nombre = "María López", MontoDeuda = 12.50 }
            };

            ddlCliente.DataSource = clientes;
            ddlCliente.DataTextField = "Alias";
            ddlCliente.DataValueField = "ClienteId";
            ddlCliente.DataBind();
        }

        private void CargarReporte(int clienteId)
        {
            // --- 1. Información del Cliente (Simulación) ---
            ClienteAlFiadoDTO infoCliente = ObtenerInfoClienteDummy(clienteId);

            lblAlias.Text = infoCliente.Alias;
            lblDatosNombre.Text = infoCliente.Nombre;
            lblDatosTelefono.Text = infoCliente.Telefono;

            lblDeudaTotal.Text = $"S/. {infoCliente.MontoDeuda:N2}";
            lblUltimaCompra.Text = "14/01/2024";
            lblTotalPagos.Text = $"S/. 37.00";

            // --- 2. Historial de Compras ---
            List<VentaAlFiadoDTO> historialCompras = ObtenerHistorialVentasAlFiadoDummy();
            rptHistorialCompras.DataSource = historialCompras;
            rptHistorialCompras.DataBind();
            lblComprasPeriodo.Text = historialCompras.Count.ToString();

            // --- 3. Historial de Pagos ---
            List<RegistroPagoFiadoDTO> historialPagos = ObtenerHistorialPagosFiadoDummy();
            rptHistorialPagos.DataSource = historialPagos;
            rptHistorialPagos.DataBind();
            lblPagosPeriodo.Text = historialPagos.Count.ToString();
        }

        protected void rptHistorialCompras_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            if (e.Item.ItemType == ListItemType.Item || e.Item.ItemType == ListItemType.AlternatingItem)
            {
                VentaAlFiadoDTO ventaFiada = (VentaAlFiadoDTO)e.Item.DataItem;
                VentaDTO venta = ventaFiada.Venta;

                Repeater rptDetalleProductos = (Repeater)e.Item.FindControl("rptDetalleProductos");

                if (rptDetalleProductos != null && venta.Detalles != null)
                {
                    rptDetalleProductos.DataSource = venta.Detalles;
                    rptDetalleProductos.DataBind();
                }
            }
        }

        // --- Manejo de Eventos ---
        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            if (ddlCliente.SelectedValue != null)
            {
                int clienteId = int.Parse(ddlCliente.SelectedValue);
                CargarReporte(clienteId);
            }
        }

        private ClienteAlFiadoDTO ObtenerInfoClienteDummy(int clienteId)
        {
            return new ClienteAlFiadoDTO
            {
                ClienteId = clienteId,
                Alias = "juan123",
                Nombre = "Juan Pérez",
                Telefono = "987654321",
                MontoDeuda = 85.30,
                FechaDePago = "28/11/2025"
            };
        }

        private List<VentaAlFiadoDTO> ObtenerHistorialVentasAlFiadoDummy()
        {
            // La capa BO debería obtener los detalles de la venta y adjuntarlos al VentaDTO
            return new List<VentaAlFiadoDTO>
            {
                new VentaAlFiadoDTO
                {
                    Venta = new VentaDTO
                    {
                        VentaId = 101,
                        Total = 18.30,
                        Fecha = "14/01/2024 15:45",
                        Detalles = new List<DetalleVentaDTO>
                        {
                            new DetalleVentaDTO { Producto = new ProductoDTO { Nombre = "Leche Entera 1L" }, Cantidad = 3, Subtotal = 5.55 },
                            new DetalleVentaDTO { Producto = new ProductoDTO { Nombre = "Azúcar Blanca 1kg" }, Cantidad = 2, Subtotal = 2.40 }
                        }
                    }
                },
                new VentaAlFiadoDTO
                {
                    Venta = new VentaDTO
                    {
                        VentaId = 102,
                        Total = 45.00,
                        Fecha = "10/01/2024 09:15",
                        Detalles = new List<DetalleVentaDTO>
                        {
                            new DetalleVentaDTO { Producto = new ProductoDTO { Nombre = "Arroz Diana 5kg" }, Cantidad = 1, Subtotal = 12.50 },
                            new DetalleVentaDTO { Producto = new ProductoDTO { Nombre = "Detergente 2kg" }, Cantidad = 1, Subtotal = 24.90 }
                        }
                    }
                }
            };
        }

        private List<RegistroPagoFiadoDTO> ObtenerHistorialPagosFiadoDummy()
        {
            return new List<RegistroPagoFiadoDTO>
            {
                new RegistroPagoFiadoDTO { Fecha = "12/01/2024", Monto = 15.00, MetodoPago = Tipo_de_pago.EFECTIVO, Descripcion = "Pago Parcial (Efectivo)" },
                new RegistroPagoFiadoDTO { Fecha = "08/01/2024", Monto = 22.00, MetodoPago = Tipo_de_pago.TRANSFERENCIA, Descripcion = "Pago Total (Transferencia)" }
            };
        }
    }
}