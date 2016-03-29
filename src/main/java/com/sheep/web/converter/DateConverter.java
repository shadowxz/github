package com.sheep.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.sheep.common.log.LoggerFactory;

public class DateConverter implements Converter<String, Date> {
    
    private static Logger logger = LoggerFactory.getLogger();
    
    public DateConverter(){
        super();
    }

    public Date convert(String text) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        if(StringUtils.isEmpty(text))
        {
            return null;
        }
        
        try {
            if (text.indexOf(":") == -1 && text.length() == 10) {
                return dateFormat.parse(text);
            } else if (text.indexOf(":") > 0 && text.length() == 19) {
                return datetimeFormat.parse(text);
            }
        } catch (ParseException e) {
            //e.printStackTrace();
            logger.error(e.getMessage() , e);
        }
        
        return null;
    }
    
    /*
    public static void main(String args[]){
        DateConverter d = new DateConverter();
        d.convert("2012-01-03");
    }
    */

}

