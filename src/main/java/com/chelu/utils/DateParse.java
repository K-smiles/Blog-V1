package com.chelu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParse {

    public static Date parseDate(String dateStr)
    {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date t = null;
        try {
            t = ft.parse(dateStr);

        } catch (ParseException e) {
            System.out.println("Unparseable using " + ft);
        }

        return  t;
    }


    public static String getDateStr(Date date)
    {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = null;
        strDate = ft.format(date);

        return  strDate;
    }
    public static void main(String[] args) throws ParseException {

        String str="2022-12-27";
        System.out.println(DateParse.parseDate(str));
        System.out.println(DateParse.getDateStr(new Date()));


    }
}
