package util;

import jdbc.JDBCUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shifeixuan on 2017/12/5.
 */
public class TxtWriter {
    public static void main(String[] args) throws IOException,SQLException{
        Integer x = 1;
        String y ="2";
        System.out.println(x+y);
        File file = new File("C:\\Users\\admin\\Desktop\\1111.txt");
        FileReader fr = new FileReader(file);
        FileWriter fw = new FileWriter("C:\\Users\\admin\\Desktop\\2222.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);

        String str = null;
        Connection con  = JDBCUtil.getConnection();
        String sql = "SELECT dc.device_id,tc.FID FROM web_device_card dc  left join web_trash_car   tc  on dc .city =tc.car_market  where car_market=5222 and dc.device_id='170033K'  order by dc.device_id";
        PreparedStatement pt = con.prepareStatement(sql);
        ResultSet row =pt.executeQuery();
        while (row.next()){
                System.out.println(row.getString("device_id"));
            bw.write("MDM,"+row.getString("device_id")+",LJ,{time:yyMMddHHmmss},{time:yyMMddHHmmss},"+row.getString("FID")+",{5,20,2},{1000,9999,0}");
            bw.newLine();
        }
        row.close();
        pt.close();
        con.close();
        while((str = br.readLine()) != null){
            System.out.println(str);

        }
        bw.close();
        br.close();
    }
}
