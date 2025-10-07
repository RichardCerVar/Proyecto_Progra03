package pe.edu.pucp.softbod.daoImp.util;

public class ClienteAlFiadoParametrosBuilder {
    
    private String cadena;
    
    public ClienteAlFiadoParametrosBuilder(){
        this.cadena = null;
    }
    
    public ClienteAlFiadoParametrosBuilder conCedena(String cadena){
        this.cadena = cadena;
        return this;
    }
    
    public ClienteAlFiadoParametros BuildClienteAlFiadoParametros (){
        ClienteAlFiadoParametros parametros = new ClienteAlFiadoParametros();
        parametros.setCadena(cadena);
        return parametros;
    }

    public String getCadena() { return cadena; }

    public void setCadena(String cadena) { this.cadena = cadena; }
}
