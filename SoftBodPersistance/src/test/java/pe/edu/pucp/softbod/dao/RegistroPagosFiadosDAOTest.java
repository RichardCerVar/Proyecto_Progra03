//package pe.edu.pucp.softbod.dao;
////
////import java.sql.Date;
////import java.util.ArrayList;
////import org.junit.jupiter.api.Test;
////import pe.edu.pucp.softbod.daoImp.RegistroPagosFiadosDAOImp;
////import pe.edu.pucp.softbod.model.RegistroPagosFiadosDTO;
////import pe.edu.pucp.softbod.model.Tipo_de_pago;
////
////public class RegistroPagosFiadosDAOTest {
////    
////    private RegistroPagosFiadosDAO registroPagosFiadosDAO;
////    
////    public RegistroPagosFiadosDAOTest() {
////        this.registroPagosFiadosDAO = new RegistroPagosFiadosDAOImp();
////    }
////
////    
////    @Test
////    public void testInsertar() {
////        System.out.println("insertar-Registro-Pagos_Fiado");
////        ArrayList<Integer> listaRegistroPagosFiadosId = new ArrayList<>();
////        insertarRegistrosDePagosFiados(listaRegistroPagosFiadosId);
//////        eliminarTodo();
////    }
////
////
////    private void insertarRegistrosDePagosFiados(ArrayList<Integer> listaRegistroPagosFiadosId){
////        
////        RegistroPagosFiadosDTO registroPagoFiado = new RegistroPagosFiadosDTO();
////        registroPagoFiado.setUsuario(227);
////        registroPagoFiado.setCliente_al_fiado(178);
////        registroPagoFiado.setFecha(new Date(System.currentTimeMillis()));
////        registroPagoFiado.setMetodo_pago(Tipo_de_pago.TRANSFERENCIA);
////        registroPagoFiado.setMonto(10.9);
////        Integer resultado = this.registroPagosFiadosDAO.insertar(registroPagoFiado);
////        assert(resultado!=0);
////        listaRegistroPagosFiadosId.add(resultado);
////        //
////        registroPagoFiado.setUsuario(227);
////        registroPagoFiado.setCliente_al_fiado(178);
////        registroPagoFiado.setFecha(new Date(System.currentTimeMillis()));
////        registroPagoFiado.setMetodo_pago(Tipo_de_pago.TRANSFERENCIA);
////        registroPagoFiado.setMonto(30.0);
////        resultado = this.registroPagosFiadosDAO.insertar(registroPagoFiado);
////        assert(resultado!=0);
////        listaRegistroPagosFiadosId.add(resultado);
////        //
////        registroPagoFiado.setUsuario(228);
////        registroPagoFiado.setCliente_al_fiado(179);
////        registroPagoFiado.setFecha(new Date(System.currentTimeMillis()));
////        registroPagoFiado.setMetodo_pago(Tipo_de_pago.TRANSFERENCIA);
////        registroPagoFiado.setMonto(5.0);
////        resultado = this.registroPagosFiadosDAO.insertar(registroPagoFiado);
////        assert(resultado!=0);
////        listaRegistroPagosFiadosId.add(resultado);
////    }
    
//    @Test
//    public void testListarTodos() {
//        System.out.println("listarTodos-Registro-Pagos_Fiado");
//        
//        ArrayList<Integer> listaRegistroPagosFiadosId = new ArrayList<>();
//        insertarRegistrosDePagosFiados(listaRegistroPagosFiadosId);
//        
//        ArrayList<RegistroPagosFiadosDTO> listaRegistroPagosFiado = this.registroPagosFiadosDAO.listarTodos();
//        assertEquals(listaRegistroPagosFiado.size(),listaRegistroPagosFiadosId.size());
//       
//        for (Integer i = 0; i < listaRegistroPagosFiadosId.size(); i++) {
//           assertEquals(listaRegistroPagosFiadosId.get(i), listaRegistroPagosFiado.get(i).getPagoId()); //<- ES VALIDO, PERO COMO NUESTRA PK ES STRING SE ORDENA DIFERENTE EN EL SQL
//        }
//        eliminarTodo();
//    }
//    
//    @Test
//    public void testObtenerPorId() {
//        System.out.println("obtenerPorId-Registro-Pagos_Fiado");
//        ArrayList<Integer> listaRegistroPagosFiadosId = new ArrayList<>();
//        insertarRegistrosDePagosFiados(listaRegistroPagosFiadosId);
//        
//        RegistroPagosFiadosDTO registroPagosFiado = this.registroPagosFiadosDAO.obtenerPorId(listaRegistroPagosFiadosId.get(0));
//        assertEquals(registroPagosFiado.getPagoId(), listaRegistroPagosFiadosId.get(0));
//        
//        registroPagosFiado = this.registroPagosFiadosDAO.obtenerPorId(listaRegistroPagosFiadosId.get(1));
//        assertEquals(registroPagosFiado.getPagoId(), listaRegistroPagosFiadosId.get(1));
//        
//        registroPagosFiado = this.registroPagosFiadosDAO.obtenerPorId(listaRegistroPagosFiadosId.get(2));
//        assertEquals(registroPagosFiado.getPagoId(), listaRegistroPagosFiadosId.get(2));
//        eliminarTodo();
//    }

//    @Test
//    public void testModificar() {
//        System.out.println("modificar-Registro-Pagos_Fiado");
//        //listar Id Registros(insertando)
//        ArrayList<Integer> listaRegistroPagosFiadosId = new ArrayList<>();
//        insertarRegistrosDePagosFiados(listaRegistroPagosFiadosId);
//        //listartodo RegistrosAlFIado
//        ArrayList<RegistroPagosFiadosDTO> listaRegistroPagosFiado = this.registroPagosFiadosDAO.listarTodos();
//        assertEquals(listaRegistroPagosFiado.size(),listaRegistroPagosFiadosId.size());
//        //MODIFICA 1
//        listaRegistroPagosFiado.get(0).setMonto(90.0);
//        this.registroPagosFiadosDAO.modificar(listaRegistroPagosFiado.get(0));
//        //MODIFICA 2
//        listaRegistroPagosFiado.get(1).setMetodo_pago(Tipo_de_pago.EFECTIVO);
//        this.registroPagosFiadosDAO.modificar(listaRegistroPagosFiado.get(1));
//        //MODIFICA 3
//        listaRegistroPagosFiado.get(2).setMonto(13.0);
//        this.registroPagosFiadosDAO.modificar(listaRegistroPagosFiado.get(2));
//        
//        //listar clientes modificados para verificar;
//        ArrayList<RegistroPagosFiadosDTO> listaRegistroPagosFiadoModific = this.registroPagosFiadosDAO.listarTodos();
//        assertEquals(listaRegistroPagosFiado.size(),listaRegistroPagosFiadoModific.size());
//        for (Integer i = 0; i < listaRegistroPagosFiado.size(); i++) {//verificar lo cambiado
//            assertEquals(listaRegistroPagosFiado.get(i).getMetodo_pago(),listaRegistroPagosFiadoModific.get(i).getMetodo_pago());
//            assertEquals(listaRegistroPagosFiado.get(i).getMonto(),listaRegistroPagosFiadoModific.get(i).getMonto());
//        }
//        eliminarTodo();
//    }
//
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar-Registro-Pagos_Fiado");
//        ArrayList<Integer> listaRegistroPagosFiadosId = new ArrayList<>();
//        insertarRegistrosDePagosFiados(listaRegistroPagosFiadosId);
//        eliminarTodo();
//    }
    
//    private void eliminarTodo() {
//        ArrayList<RegistroPagosFiadosDTO> listaRegistroPagosFiado = this.registroPagosFiadosDAO.listarTodos();
//        for (Integer i = 0; i < listaRegistroPagosFiado.size(); i++) {
//            Integer resultado = this.registroPagosFiadosDAO.eliminar(listaRegistroPagosFiado.get(i));
//            assert(resultado!=0);
//            RegistroPagosFiadosDTO registroPagoFiado = this.registroPagosFiadosDAO.obtenerPorId(listaRegistroPagosFiado.get(i).getPagoId());
//            assertNull(registroPagoFiado);
//        }
//    }
//}
