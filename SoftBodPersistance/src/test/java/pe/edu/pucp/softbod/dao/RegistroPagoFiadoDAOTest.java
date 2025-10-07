//package pe.edu.pucp.softbod.dao;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import org.junit.jupiter.api.Test;
//import pe.edu.pucp.softbod.daoImp.RegistroPagosFiadosDAOImp;
//import pe.edu.pucp.softbod.model.RegistroPagosFiadosDTO;
//import pe.edu.pucp.softbod.model.Tipo_de_pago;
//
//public class RegistroPagosFiadosDAOTest {
//    
//    private RegistroPagosFiadosDAO registroPagosFiadosDAO;
//    
//    public RegistroPagosFiadosDAOTest() {
//        this.registroPagosFiadosDAO = new RegistroPagosFiadosDAOImp();
//    }
//
//    
//    @Test
//    public void testInsertar() {
//        System.out.println("insertar-RegistroPagosFiadosDAOTest");
//        ArrayList<Integer> listaRegistroPagosFiadosId = new ArrayList<>();
//        insertarRegistrosDePagosFiados(listaRegistroPagosFiadosId);
//    }
//
//
//    private void insertarRegistrosDePagosFiados(ArrayList<Integer> listaRegistroPagosFiadosId){
//        
//        RegistroPagosFiadosDTO registroPagoFiado = new RegistroPagosFiadosDTO();
//        registroPagoFiado.setUsuario(320);
//        registroPagoFiado.setCliente_al_fiado(356);
//        registroPagoFiado.setFecha(new Date(System.currentTimeMillis()));
//        registroPagoFiado.setMetodo_pago(Tipo_de_pago.TRANSFERENCIA);
//        registroPagoFiado.setMonto(10.9);
//        Integer resultado = this.registroPagosFiadosDAO.insertar(registroPagoFiado);
//        assert(resultado!=0);
//        listaRegistroPagosFiadosId.add(resultado);
//        //
//        registroPagoFiado.setUsuario(321);
//        registroPagoFiado.setCliente_al_fiado(356);
//        registroPagoFiado.setFecha(new Date(System.currentTimeMillis()));
//        registroPagoFiado.setMetodo_pago(Tipo_de_pago.TRANSFERENCIA);
//        registroPagoFiado.setMonto(30.0);
//        resultado = this.registroPagosFiadosDAO.insertar(registroPagoFiado);
//        assert(resultado!=0);
//        listaRegistroPagosFiadosId.add(resultado);
//        //
//        registroPagoFiado.setUsuario(322);
//        registroPagoFiado.setCliente_al_fiado(357);
//        registroPagoFiado.setFecha(new Date(System.currentTimeMillis()));
//        registroPagoFiado.setMetodo_pago(Tipo_de_pago.TRANSFERENCIA);
//        registroPagoFiado.setMonto(5.0);
//        resultado = this.registroPagosFiadosDAO.insertar(registroPagoFiado);
//        assert(resultado!=0);
//        listaRegistroPagosFiadosId.add(resultado);
//    }
//    
//
//}
