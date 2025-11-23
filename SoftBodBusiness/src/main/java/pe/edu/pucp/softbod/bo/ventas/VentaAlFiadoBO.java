package pe.edu.pucp.softbod.bo.ventas;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.ventas.VentaAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.ventas.VentaAlFiadoDAOImpl;
import pe.edu.pucp.softbod.model.gestclientes.ClienteAlFiadoDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;
import pe.edu.pucp.softbod.model.ventas.DetalleVentaDTO;
import pe.edu.pucp.softbod.model.ventas.VentaAlFiadoDTO;
import pe.edu.pucp.softbod.model.ventas.VentaDTO;

public class VentaAlFiadoBO {
    
    private final VentaAlFiadoDAO ventaAlFiadoDAO;
    private final VentaBO ventaBO;
    
    public VentaAlFiadoBO(){
        this.ventaAlFiadoDAO = new VentaAlFiadoDAOImpl();
        this.ventaBO = new VentaBO();
    }
    
    public VentaAlFiadoDTO obtenerPorId(Integer ventaFiada_Id){
        return this.ventaAlFiadoDAO.obtenerPorId(ventaFiada_Id);
    }
    
    public ArrayList<VentaAlFiadoDTO> listarTodos(){
        return this.ventaAlFiadoDAO.listarTodos();
    }
    
    public ArrayList<VentaAlFiadoDTO> listarTodosPorAliasCliente(String aliasCliente){
        return this.ventaAlFiadoDAO.listarTodosPorAliasCliente(aliasCliente);
    }
    
    public ArrayList<VentaAlFiadoDTO> listarTodosPorAliasClienteFecha(String aliasCliente, String fecha){
        return this.ventaAlFiadoDAO.listarTodosPorAliasClienteFecha(aliasCliente, fecha);
    }
    
    public Integer insertar(
            ClienteAlFiadoDTO cliente,
            UsuarioDTO usuario,//el front ya tiene al usuario
            String metodoPago,  //el front ya tiene el tipo de pago
            ArrayList<DetalleVentaDTO> detallesVenta) {
        
        VentaAlFiadoDTO nuevaVentaFiada = new VentaAlFiadoDTO();
        
        Integer idNuevaVenta = this.ventaBO.insertar(usuario, metodoPago, detallesVenta);
        VentaDTO nuevaVenta = new VentaDTO();
        nuevaVenta.setVentaId(idNuevaVenta);
        
        nuevaVentaFiada.setCliente(cliente);
        nuevaVentaFiada.setVenta(nuevaVenta);

        return this.ventaAlFiadoDAO.insertar(nuevaVentaFiada);
    }
    
}

