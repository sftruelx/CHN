package com.mycompany.util;


import org.joda.time.DateTime;

import java.util.Date;

public class DateUtils {

        public static Date now(){
                return DateTime.now().toDate();
        }

        public static String formatSimpleDate(Date date){
                DateTime dateTime = new DateTime(date);
                return (dateTime.toString("yyyy-MM-dd"));
        }

        public static Date formatStringDate(String date){
                DateTime dateTime = new DateTime(date);
                return dateTime.toDate();
        }

        public static Date plusOneDay(Date Date){
                DateTime dateTime = new DateTime(Date);
                return dateTime.plusDays(1).toDate();
        }
    
    
}
