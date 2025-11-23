using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using SoftBodBusiness.SoftWSProducto;

namespace SoftBodBusiness
{
    public class ProductoBO
    {
        private ProductoClient productoSOAP;

        public ProductoBO()
        {
            productoSOAP = new ProductoClient();
        }

        public int insertarProducto(categoriaDTO categoria, string nombre, double precioUnitario,
                                    string unidadMedida, int stock, int stockMinimo, bool activo)
        {
            return productoSOAP.insertarProducto(categoria, nombre, precioUnitario, unidadMedida,
                                                 stock, stockMinimo, activo);
        }

        public int modificarProducto(productoDTO producto)
        {
            return productoSOAP.modificarProducto(producto);
        }

        public productoDTO obtenerProductoPorId(int productoId)
        {
            return productoSOAP.obtenerProductoPorId(productoId);
        }

        public int eliminarProducto(int iDproducto)
        {
            return productoSOAP.eliminarProducto(iDproducto);
        }

        public List<productoDTO> listarTodosProductos()
        {
            return productoSOAP.listarTodosProductos()?.ToList()
                   ?? new List<productoDTO>();
        }

        public List<productoDTO> listarTodosConFiltroProductos(bool activo, string categoria, string nombreProducto)
        {
            return productoSOAP.listarTodosConFiltroProductos(activo, categoria, nombreProducto)?.ToList()
                   ?? new List<productoDTO>();
        }

        public byte[] ReporteDeInventario()
        {
            return productoSOAP.reporteDeInventario();
        }

        public void abrirReporte(HttpResponse response, string nombReporte, byte[] reporte)
        {
            response.Clear();
            response.ContentType = "application/pdf";
            response.AddHeader("Content-Disposition", "inline; filename=" + nombReporte + ".pdf");
            response.BinaryWrite(reporte);
            response.End();
        }
    }
}