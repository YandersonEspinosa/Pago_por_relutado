package utils;

import javax.swing.text.DateFormatter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateConversor {
    public static Date localDatetoUtilDAte(LocalDate localDate){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        try {
            date = Date.from(Instant.from(LocalDate.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())));
        }catch (Exception e){
            System.err.println("Error al transfomar localdate a utildate");
        }
        return date;
    }
    public static LocalDate utilToLocaldate(Date date){
        LocalDate localDate =LocalDate.now();
        try {
            localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }catch (Exception e){
            System.err.println("Error al transfomar utildate a localdatt");
        }
        return localDate;
    }
    public static String localDateToStrng(LocalDate localDate, String pattern){
        if (localDate == null){
            return "";
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatter);
        }catch (Exception e){
            System.err.println("Error al formatear LocalDate a cadene");
            return "";
        }
    }
}
