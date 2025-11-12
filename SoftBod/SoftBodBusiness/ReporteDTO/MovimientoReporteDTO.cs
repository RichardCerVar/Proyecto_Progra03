using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftBodBusiness.ReporteDTO
{
    public class MovimientoReporteDTO
    {
        public int ID { get; set; }

        public DateTime Fecha { get; set; }

        public string Hora { get; set; }

        public string Tipo { get; set; }

        public string Cliente { get; set; }

        public string TipoPago { get; set; }

        public double Total { get; set; }

        public string ProductosResumen { get; set; }

        public bool EsFiado { get; set; }
    }
}