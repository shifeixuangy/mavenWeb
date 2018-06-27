package learn;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by shifeixuan on 2018/3/16.
 */
public class BaseTypeTest {
    public static void main(String[] args) {
        float a =0.11123F;
        short b =123;
        long c =11111111111L;
        long d =11111;
        char e ='\\';
        char f ='\"';

        System.out.println(Byte.MAX_VALUE+"=Int最大值");
        System.out.println(Byte.MIN_VALUE+"=Int最小值");
        System.out.println(Short.MAX_VALUE+"=Int最大值");
        System.out.println(Short.MIN_VALUE+"=Int最小值");
        System.out.println(Integer.MAX_VALUE+"=Int最大值");
        System.out.println(Integer.MIN_VALUE+"=Int最小值");
        System.out.println(Long.MIN_VALUE+"=Int最小值");
        System.out.println(Long.MIN_VALUE+"=Int最小值");
        System.out.println(Double.MIN_VALUE+"=Int最小值");
        System.out.println(Double.MIN_VALUE+"=Int最小值");
        System.out.println(Float.MIN_VALUE+"=Int最小值");
/*        String x =BaseTypeTest.DecodeDate("21");
        System.out.println(x);*/
        Map<String,Object>  map = new HashedMap();
        long y =10000L;
        map.put("y",y);
        Object yy = map.get("y");
        System.out.println(yy.getClass());




    }

    public static String DecodeDate(String field) {
        String date = "";
        if (field.length() <= 12&&field.length() > 0) {
            date += 20;
        }
        date += field.substring(0, 2) + "-";
        date += field.substring(2, 4) + "-";
        date += field.substring(4, 6) + " ";
        date += field.substring(6, 8) + ":";
        date += field.substring(8, 10);
        return date;
    }
}
