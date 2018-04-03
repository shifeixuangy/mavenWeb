package learn;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shifeixuan on 2018/3/22.
 */
public class ClassLibTest {
    public static void main(String[] args) throws Exception{
//        Scanner sc =new Scanner(System.in);
//       if (sc.hasNext()){
//           System.out.println(sc.next());
//       }
        Pattern pt = Pattern.compile("abbisExsit");
        boolean isExsit =pt.matches("abb.*","abb");
        System.out.println(isExsit);
        Matcher mt =pt.matcher("abbabbabbabb");
        if(mt.find()){
            System.out.println(mt.group(0));
            System.out.println(mt.group(1));
            System.out.println(mt.group(2));
        }

        System.out.println(System.getProperties()+"---");
        System.out.println(System.getenv("JAVA_HOME"));
        Runtime rt = Runtime.getRuntime();
        Integer y =Integer.valueOf("2");
        System.out.println(y);
//        System.out.println(rt.exec("notepad.exe"));
    }
}
