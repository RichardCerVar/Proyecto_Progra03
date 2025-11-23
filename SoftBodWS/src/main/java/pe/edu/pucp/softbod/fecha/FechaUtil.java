package pe.edu.pucp.softbod.fecha;

import java.sql.Date;

public class FechaUtil {
    public static Date toSqlDate(String fechaString) {
        return Date.valueOf(fechaString);
    }
}
