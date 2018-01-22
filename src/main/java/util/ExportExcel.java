package util;
import net.sf.json.JSONObject;
import java.io.*;

/**
 * Created by shifeixuan on 2018/1/22.
 */
public class ExportExcel {

    public static String getJsonString() throws Exception {
        InputStream inputStream = new FileInputStream("C:\\Users\\admin\\Desktop\\weight.json");
        //对应的字符编码转换
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str = null;
        StringBuffer sb = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        reader.close();
        return sb.toString();
    }

    public static String jsonToObj(String jsonStr) throws Exception {
        String[] xxx=jsonStr.split("}");
        for (int i = 0; i < xxx.length; i++) {
            xxx[i] = xxx[i]+"}";
        }

        String fatherName="";
        for (int i = 0; i <xxx.length ; i++) {
            JSONObject jsonObject = JSONObject.fromObject(xxx[i]);
             fatherName = jsonObject.getString("time");
            System.out.println(fatherName);
        }
        return fatherName;
    }

}
