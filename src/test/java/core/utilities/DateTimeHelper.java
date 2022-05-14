package core.utilities;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {

    public static String getCurrentDateTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, hh:mma");
        LocalDateTime localDateTime = LocalDateTime.now();
        return dateTimeFormatter.format(localDateTime);
    }

    public static String getCurrentDate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        LocalDateTime localDateTime = LocalDateTime.now();
        return dateTimeFormatter.format(localDateTime);
    }
    public static String getCurrentTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh.mm.ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        return dateTimeFormatter.format(localDateTime);
    }
}
