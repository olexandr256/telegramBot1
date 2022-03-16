package org.mybot.response.converteerData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponseDate {

    private Integer dateInput;

    public ResponseDate(Integer dateInput) {
        this.dateInput = dateInput;
    }

    public String toTime(){
        String pattern = " HH год. mm хв";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//                System.out.println(date);
        return simpleDateFormat.format(new Date(((long)dateInput+2*3600)*1000));
//        return String.valueOf(dateInput/3600/24);
    }
}
