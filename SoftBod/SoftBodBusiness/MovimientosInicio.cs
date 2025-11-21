using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftBodBusiness
{
    public class MovimientosInicio
    {
        public int Id { get; set; }
        public string Tipo { get; set; }
        public string Titulo { get; set; }
        public string FechaHora { get; set; }
        public string Monto { get; set; }
        public string ColorMonto { get; set; }
        public string TipoBadge { get; set; }
        public string ColorBadge { get; set; }
        public string Icono { get; set; }
        public string ColorIcono { get; set; }
        public DateTime FechaOrden { get; set; }

        public MovimientosInicio()
        {
        }
        public MovimientosInicio(
            int id,
            string tipo,
            string titulo,
            string fechaHora,
            string monto,
            string colorMonto,
            string tipoBadge,
            string colorBadge,
            string icono,
            string colorIcono,
            DateTime fechaOrden)
        {
            Id = id;
            Tipo = tipo;
            Titulo = titulo;
            FechaHora = fechaHora;
            Monto = monto;
            ColorMonto = colorMonto;
            TipoBadge = tipoBadge;
            ColorBadge = colorBadge;
            Icono = icono;
            ColorIcono = colorIcono;
            FechaOrden = fechaOrden;
        }


    }
}
