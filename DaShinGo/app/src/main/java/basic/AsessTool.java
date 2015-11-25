package basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




/**
 * 字符串校验
 * @author Administrator
 *
 */
public class AsessTool {

    public static String getNameSpace(String str){
        StringBuffer buf = new StringBuffer();
        buf.append("<query xmlns=\""+str+"\" />");
        return buf.toString();
    }


    public static boolean judgeStrNull(String str){
        return str==null||str.equals("");
    }

    public static String getNotNullStr(String str){
        if(judgeStrNull(str))
            return "";
        return str.trim();
    }

    public static boolean judgeStrListNull(String ... strlist){
        boolean isNull=false;
        for (int i = 0; i < strlist.length; i++) {
            if(strlist[i]==null||strlist[i].equals("")){
                isNull = true;
                break;
            }
        }
        return isNull;
    }

    public static String getStar(String str){
        if(str.length()>4){
            return str.substring(0,str.length()-4)+"****";
        }
        return str;
    }

    public static <T> boolean judgeStrListSize0(List<T> strList){
        if(strList==null||strList.isEmpty()){
            return true;
        }
        return false;
    }


    public static boolean isPhoneNum(String sphone){
        return sphone.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    }


    public static int parseInt(String value){
        int re = 0;
        try {
            re=Integer.parseInt(value);
        } catch (NumberFormatException e) {
            re = 0;
        }
        return re;
    }
    public static int parseInt(String value,int defaultValue){
        int re = 0;
        try {
            re=Integer.parseInt(value);
        } catch (NumberFormatException e) {
            re = defaultValue;
        }
        return re;
    }

    public static double parseDouble(String value){
        double re = 0;
        try {
            re=Double.parseDouble(value);
        } catch (NumberFormatException e) {
            re = 0;
        }
        return re;
    }
    public static double parseDouble(String value,double dv){
        double re = 0;
        try {
            re=Double.parseDouble(value);
        } catch (NumberFormatException e) {
            re = dv;
        }
        return re;
    }

    public static boolean parseBoolean(String value,boolean defaultValue){
        boolean  re = defaultValue;
        try {
            re= Boolean.valueOf(value);
        } catch (Exception e) {
            re = defaultValue;
        }
        return re;
    }

    public static Date getDateTimeByStr(String str) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.parse(str);
        return date;
    }

    public static boolean ArrayContainsValue(Object [] list,Object s){
        for(Object obj : list){
            if(obj.equals(s)){
                return true;
            }
        }
        return false;
    }

}
