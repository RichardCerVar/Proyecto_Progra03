package pe.edu.pucp.softbod.bo.gestclientes;

import pe.edu.pucp.softbod.bo.gestclientes.ClienteAlFiadoBO;
import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.trazabilidad.HistorialDeOperacionBO;
import pe.edu.pucp.softbod.bo.util.OperacionBOBase;
import pe.edu.pucp.softbod.dao.gestclientes.RegistroPagoFiadoDAO;
import pe.edu.pucp.softbod.daoImp.gestclientes.RegistroPagoFiadoDAOImpl;
import pe.edu.pucp.softbod.model.gestclientes.ClienteAlFiadoDTO;
import pe.edu.pucp.softbod.model.gestclientes.RegistroPagoFiadoDTO;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;

public class RegistroPagoFiadoBO extends OperacionBOBase{
    
    private final RegistroPagoFiadoDAO registroPagoFiadoDAO;
    private final ClienteAlFiadoBO clienteAlFiadoBO;
    private final HistorialDeOperacionBO historialBO;
    
    public RegistroPagoFiadoBO(){
        this.registroPagoFiadoDAO = new RegistroPagoFiadoDAOImpl();
        this.clienteAlFiadoBO = new ClienteAlFiadoBO();
        this.historialBO = new HistorialDeOperacionBO();
    }
    
    // ==================== MÉTODOS DAO EXISTENTES ====================
    
    public Integer insertar(Integer pagoId, UsuarioDTO usuario, ClienteAlFiadoDTO cliente, 
                                  Date fecha, String metodoPago, Double monto){
        Tipo_de_pago metPago;
        if (metodoPago.equals(Tipo_de_pago.EFECTIVO.name()))
            metPago = Tipo_de_pago.EFECTIVO;
        else metPago = Tipo_de_pago.TRANSFERENCIA;
        
        RegistroPagoFiadoDTO registroPagoFiado = new RegistroPagoFiadoDTO(pagoId, usuario, cliente, 
                                   fecha, metPago, monto);
        return this.registroPagoFiadoDAO.insertar(registroPagoFiado);
    }
    
    public ArrayList<RegistroPagoFiadoDTO> listarTodos(){
        return this.registroPagoFiadoDAO.listarTodos();
    }
    
    public ArrayList<RegistroPagoFiadoDTO> listarTodosPorAliasCliente(String aliasCliente){
        return this.registroPagoFiadoDAO.listarTodosPorAliasCliente(aliasCliente);
    }
    
    public ArrayList<RegistroPagoFiadoDTO> listarTodosPorAliasClienteConFechaFin(String aliasCliente, Date fechaFin){
        return this.registroPagoFiadoDAO.listarTodosPorAliasClienteConFechaFin(aliasCliente, fechaFin);
    }

    public Integer registrarPago(RegistroPagoFiadoDTO pago) {
        try {
            // 1. Validar datos de entrada
            if (pago == null || pago.getCliente() == null || 
                pago.getCliente().getClienteId() == null || pago.getMonto() == null) {
                System.err.println("Error: Datos de pago inválidos");
                return null;
            }
            
            // 2. Validar que tenga usuario asignado
            if (pago.getUsuario() == null || pago.getUsuario().getUsuarioId() == null) {
                System.err.println("Error: El pago debe tener un usuario asignado");
                return null;
            }
            
            // 3. Validar que el monto sea válido
            if (!validarMontoPago(pago.getMonto(), pago.getCliente().getClienteId())) {
                System.err.println("Error: Monto de pago inválido");
                return null;
            }
            
            // 4. Insertar el registro de pago
            Integer pagoId = this.registroPagoFiadoDAO.insertar(pago);
            
            if (pagoId == null || pagoId <= 0) {
                System.err.println("Error: No se pudo registrar el pago");
                return null;
            }
            
            // 5. Registrar en el historial de operaciones
            registrarEnHistorial(pago.getUsuario(), "BOD_REGISTRO_PAGOS_FIADOS", Tipo_Operacion.INSERCION);
            
            // 6. Retornar el ID del pago registrado
            System.out.println("✓ Pago registrado exitosamente. ID: " + pagoId);
            System.out.println("  Cliente: " + pago.getCliente().getAlias());
            System.out.println("  Monto pagado: S/ " + String.format("%.2f", pago.getMonto()));
            return pagoId;
            
        } catch (Exception e) {
            System.err.println("Error al registrar pago: " + e.getMessage());
            return null;
        }
    }

    public Boolean validarMontoPago(Double monto, Integer clienteId) {
        try {
            // 1. Validar que el monto sea mayor a 0
            if (monto == null || monto <= 0) {
                System.err.println("Error: El monto debe ser mayor a 0");
                return Boolean.FALSE;
            }
            
            // 2. Obtener el cliente para verificar su deuda
            ClienteAlFiadoDTO cliente = this.clienteAlFiadoBO.obtenerPorId(clienteId);
            
            if (cliente == null || cliente.getClienteId() == null) {
                System.err.println("Error: Cliente no encontrado");
                return Boolean.FALSE;
            }
            
            // 3. Validar que el cliente tenga deuda
            if (cliente.getMontoDeuda() == null || cliente.getMontoDeuda() <= 0) {
                System.err.println("Error: El cliente no tiene deuda pendiente");
                return Boolean.FALSE;
            }
            
            // 4. Validar que el monto no sea mayor a la deuda
            if (monto > cliente.getMontoDeuda()) {
                System.err.println("Error: El monto (" + monto + 
                                 ") no puede ser mayor a la deuda actual (" + 
                                 cliente.getMontoDeuda() + ")");
                return Boolean.FALSE;
            }
            
            return Boolean.TRUE;
            
        } catch (Exception e) {
            System.err.println("Error al validar monto de pago: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Double calcularTotalPagosRealizados(Integer clienteId, Date inicio, Date fin) {
        try {
            if (clienteId == null || inicio == null || fin == null) {
                return 0.0;
            }
            
            // Validar que la fecha de inicio no sea posterior a la fecha fin
            if (inicio.after(fin)) {
                System.err.println("Error: Fecha de inicio posterior a fecha fin");
                return 0.0;
            }
            
            // Obtener el cliente para obtener su alias
            ClienteAlFiadoDTO cliente = this.clienteAlFiadoBO.obtenerPorId(clienteId);
            
            if (cliente == null || cliente.getAlias() == null) {
                return 0.0;
            }
            
            // Obtener todos los pagos del cliente
            ArrayList<RegistroPagoFiadoDTO> pagosCliente = 
                this.registroPagoFiadoDAO.listarTodosPorAliasCliente(cliente.getAlias());
            
            if (pagosCliente == null || pagosCliente.isEmpty()) {
                return 0.0;
            }
            
            double totalPagos = 0.0;
            
            // Sumar los pagos dentro del rango de fechas
            for (RegistroPagoFiadoDTO pago : pagosCliente) {
                if (pago.getFecha() != null && pago.getMonto() != null) {
                    // Verificar que la fecha esté dentro del rango
                    if (!pago.getFecha().before(inicio) && !pago.getFecha().after(fin)) {
                        totalPagos += pago.getMonto();
                    }
                }
            }
            
            return totalPagos;
            
        } catch (Exception e) {
            System.err.println("Error al calcular total de pagos: " + e.getMessage());
            return 0.0;
        }
    }
    
}