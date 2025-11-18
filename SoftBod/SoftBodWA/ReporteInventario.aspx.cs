using SoftBodBusiness;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBodWA
{
    public class ReporteInventarioDTO
    {
        public string ProductoNombre { get; set; }
        public string CategoriaDescripcion { get; set; }
        public int StockActual { get; set; }
        public int StockMinimo { get; set; }
        public double ValorTotal { get; set; }
    }

    public partial class ReporteInventario : System.Web.UI.Page
    {

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarReporte();
            }
        }

        private void CargarReporte()
        {
            // LÓGICA DE CARGA DE DATOS FALTA
            // Aquí iría la llamada a reporteBO.obtenerInventario(fechaSeleccionada)

            var datosEjemplo = new List<ReporteInventarioDTO>
            {
                new ReporteInventarioDTO { ProductoNombre = "Arroz Diana 500g", CategoriaDescripcion = "Granos", StockActual = 45, StockMinimo = 10, ValorTotal = 112.50 },
                new ReporteInventarioDTO { ProductoNombre = "Aceite Girasol 1L", CategoriaDescripcion = "Aceites", StockActual = 12, StockMinimo = 5, ValorTotal = 45.60 },
                new ReporteInventarioDTO { ProductoNombre = "Azúcar Blanca 1kg", CategoriaDescripcion = "Endulzantes", StockActual = 8, StockMinimo = 15, ValorTotal = 9.60 },
                new ReporteInventarioDTO { ProductoNombre = "Leche Entera 1L", CategoriaDescripcion = "Lácteos", StockActual = 25, StockMinimo = 10, ValorTotal = 46.25 }
            };

            rptEstadoInventario.DataSource = datosEjemplo;
            rptEstadoInventario.DataBind();

            double valorTotal = datosEjemplo.Sum(d => d.ValorTotal);
            int stockBajo = datosEjemplo.Count(d => d.StockActual <= d.StockMinimo);

            lblValorTotalInventario.Text = $"S/. {valorTotal:N2}";
            lblProductosStockBajo.Text = stockBajo.ToString();
        }

        protected void btnExportarReporte_Click(object sender, EventArgs e)
        {
            
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            
        }
    }
}