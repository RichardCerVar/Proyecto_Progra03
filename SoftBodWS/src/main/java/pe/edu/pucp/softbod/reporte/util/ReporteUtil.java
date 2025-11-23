package pe.edu.pucp.softbod.reporte.util;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.softbod.db.DBManager;
import pe.edu.pucp.softbod.fecha.FechaUtil;

public class ReporteUtil {
    
    private static byte[] invocarReporte(String nombreReporte){
        HashMap parametros = null;
        return invocarReporte(nombreReporte, parametros);
    }
    
    private static byte[] invocarReporte(String nombreReporte, HashMap parametros){
        byte[] reporte = null;
        Connection conexion = DBManager.getInstance().getConnection();
        String nombReporte = "/" + nombreReporte + ".jasper";
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(ReporteUtil.class.getResource(nombReporte));
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, conexion);
            reporte = JasperExportManager.exportReportToPdf(jp);
        } catch (JRException ex) {
            Logger.getLogger(ReporteUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reporte;
    }
    
    public static byte[] reporteDevolucionesYVentas(String fechaInicio, String fechaFin){
        HashMap parametros = new HashMap();
        parametros.put("FECHA", FechaUtil.toSqlDate(fechaInicio));
        parametros.put("FECHA_FIN", FechaUtil.toSqlDate(fechaFin));
        return invocarReporte("ReporteDeVentasYDevoluciones",parametros);
    }
    
    public static byte[] reporteHistorialDeOperaciones(String operacion, String tabla, String usuario){
        HashMap parametros = new HashMap();
        parametros.put("P_OPERACION", operacion);
        parametros.put("P_TABLA", tabla);
        parametros.put("P_USUARIO", usuario);
        return invocarReporte("HistorialDeOperaciones",parametros);
    }
    
    public static byte[] reporteClienteAlFiado(String fechaInicio, String fechaFin, Integer clienteId){
        HashMap parametros = new HashMap();
        parametros.put("FECHA_INICIO", FechaUtil.toSqlDate(fechaInicio));
        parametros.put("FECHA_FIN", FechaUtil.toSqlDate(fechaFin));
        parametros.put("CLIENTE_ID", clienteId);
        return invocarReporte("ReporteDeClientesAlFiado",parametros);
    }
    
    public static byte[] reporteDeInventario(){
        return invocarReporte("ReporteDeInventario");
    }
    
}
