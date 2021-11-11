package BankUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    // now -> yyyy-MM-dd hh:mm:ss
    public static String nowToDateTime() {
        String strData = null;
        Date data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sdf.format(data);
        return strData;
    }
}
