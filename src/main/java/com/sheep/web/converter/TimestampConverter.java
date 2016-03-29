package com.sheep.web.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.sheep.common.log.LoggerFactory;

public class TimestampConverter implements Converter<String, Timestamp> {
    
    private static Logger logger = LoggerFactory.getLogger();
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public TimestampConverter(){
        super();
    }

    public Timestamp convert(String text) {
        
        if(StringUtils.isEmpty(text))
        {
            return null;
        }
        
        try {
            if (text.indexOf(":") == -1 && text.length() == 10) {
                return new Timestamp(dateFormat.parse(text).getTime());
            } else if (text.indexOf(":") > 0 && text.length() == 19) {
                return new Timestamp(datetimeFormat.parse(text).getTime());
            }            
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        
        return null;
    }

}
