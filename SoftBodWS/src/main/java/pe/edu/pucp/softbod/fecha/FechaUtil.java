package pe.edu.pucp.softbod.fecha;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class FechaUtil {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yy");   // ejemplo: 7/12/25
    
    public static Date toSqlDate(String fechaString) {
        LocalDate fecha = LocalDate.parse(fechaString, INPUT_FORMAT);
        return Date.valueOf(fecha);
    }
}
