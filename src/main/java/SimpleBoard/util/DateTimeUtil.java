package SimpleBoard.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DateTimeUtil {
    public Date addOneDay(Date date){
        Calendar temp = Calendar.getInstance();
        temp.setTime(date);
        temp.add(Calendar.DATE, 1);
        return temp.getTime();
    }
}
