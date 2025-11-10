using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.Remoting.Metadata.W3cXsd2001;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftWSVenta;

namespace SoftBodBusiness
{
    public class VentaBO
    {
        private VentaClient ventaSOAP;

        public VentaBO()
        {
            ventaSOAP = new VentaClient();
        }

        public int insertarVenta(usuarioDTO usuario, tipoDePago metodoPago, detalleVentaDTO[] detallesVenta)
        {
            return this.ventaSOAP.insertarVenta(usuario, metodoPago, detallesVenta);
            //el 0 es porque aparece un parametro fantasma arg0 que es el wrapper general de la llamada,
        }

        public ventaDTO obtenerVentaPorId(int idVenta)
        {
            return this.ventaSOAP.obtenerVentaPorId(idVenta);
        }

        public List<ventaDTO> listarTodasVentas()
        {
            // El proxy normalmente devuelve un array, así que se convierte:
            return this.ventaSOAP.listarTodosVentas().ToList();
        }

        public List<ventaDTO> listarVentasPorFecha(string fecha)
        {
            return this.ventaSOAP.listarVentasPorFecha(fecha).ToList();
        }
    }
}
