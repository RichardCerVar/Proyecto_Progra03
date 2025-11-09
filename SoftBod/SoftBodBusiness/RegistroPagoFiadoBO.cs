using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftWSRegistroPagoFiado;

namespace SoftBodBusiness
{
    public class RegistroPagoFiadoBO
    {
        private RegistroPagoFiadoClient registroSOAP;

        public RegistroPagoFiadoBO()
        {
            this.registroSOAP = new RegistroPagoFiadoClient();
        }

        public int insertarRegistroPagoFiado(int pagoId, usuarioDTO usuario,
            clienteAlFiadoDTO cliente, date fecha, string metodoPago, double monto)
        {
            // WSDL normalmente genera java.sql.Date → System.DateTime → correcto
            return this.registroSOAP.insertarRegistroPagoFiado(
                pagoId, usuario, cliente, fecha, metodoPago, monto
            );
        }

        public List<registroPagoFiadoDTO> listarTodosRegistrosPagoFiado()
        {
            return this.registroSOAP.listarTodosRegistrosPagoFiado().ToList();
        }

        public List<registroPagoFiadoDTO> listarRegistrosPagoFiadoPorAliasCliente(string aliasCliente)
        {
            return this.registroSOAP.listarRegistrosPagoFiadoPorAliasCliente(aliasCliente).ToList();
        }

        public List<registroPagoFiadoDTO> listarRegistrosPagoFiadoPorAliasClienteConFechaFin(
            string aliasCliente, date fechaFin)
        {
            return this.registroSOAP
                .listarRegistrosPagoFiadoPorAliasClienteConFechaFin(aliasCliente, fechaFin)
                .ToList();
        }

        public int registrarPagoFiado(registroPagoFiadoDTO pago)
        {
            return this.registroSOAP.registrarPagoFiado(pago);
        }
    }
}
