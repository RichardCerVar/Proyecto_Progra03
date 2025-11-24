using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBodBusiness.SoftBodWSServices;

namespace SoftBodBusiness
{
    public class RegistroPagoFiadoBO
    {
        private RegistroPagoFiadoClient registroSOAP;

        public RegistroPagoFiadoBO()
        {
            this.registroSOAP = new RegistroPagoFiadoClient();
        }

        public int insertarRegistroPagoFiado(usuarioDTO usuario,
            clienteAlFiadoDTO cliente, string metodoPago, double monto)
        {
            return this.registroSOAP.insertarRegistroPagoFiado(usuario, cliente, metodoPago, monto
            );
        }

        public List<registroPagoFiadoDTO> listarTodosRegistrosPagoFiado()
        {
            return this.registroSOAP.listarTodosRegistrosPagoFiado()?.ToList()
                   ?? new List<registroPagoFiadoDTO>();
        }

        public List<registroPagoFiadoDTO> listarRegistrosPagoFiadoPorAliasCliente(string aliasCliente)
        {
            return this.registroSOAP.listarRegistrosPagoFiadoPorAliasCliente(aliasCliente)?.ToList()
                   ?? new List<registroPagoFiadoDTO>();
        }

        public List<registroPagoFiadoDTO> listarRegistrosPagoFiadoPorAliasClienteConFechaFin(
            string aliasCliente, string fechaFin)
        {
            return this.registroSOAP
                .listarRegistrosPagoFiadoPorAliasClienteConFechaFin(aliasCliente, fechaFin)?.ToList()
                ?? new List<registroPagoFiadoDTO>();
        }

        public int registrarPagoFiado(registroPagoFiadoDTO pago)
        {
            return this.registroSOAP.registrarPagoFiado(pago);
        }
    }
}