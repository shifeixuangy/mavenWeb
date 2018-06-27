package util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by shifeixuan on 2018/4/28.
 */
public class Doexcel {
    public static void main(String[] args) throws Exception{
        FileWriter xx = new FileWriter(new File("C:\\Users\\admin\\Desktop\\222.txt"));

        Doexcel.test1(xx);
        xx.flush();
        xx.close();

    }

    public static  void test1( FileWriter xx)throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\admin\\Desktop\\1111.xls")));
        HSSFSheet sheet = null;
        String chepai =null;
        String zhongliang =null;
        String jin =null;
        String chu =null;
        int i = workbook.getSheetIndex("匹配"); // sheet表名
        sheet = workbook.getSheetAt(i);
        for (int j = 0; j < 404; j++) {// getLastRowNumz
            // 获取最后一行的行标
            HSSFRow row = sheet.getRow(j);
            if (row != null) {
                chepai =row.getCell(3).getStringCellValue();
                Double zhongliang1 =row.getCell(7).getNumericCellValue();
                zhongliang = zhongliang1.toString();
                SimpleDateFormat simpleFormatter1 =new SimpleDateFormat("yyyy/M/d H:mm:ss");
                SimpleDateFormat simpleFormatter2 =new SimpleDateFormat("yyMMddHHmmss");
                Date data1 =row.getCell(8).getDateCellValue();
                Date data2 =row.getCell(9).getDateCellValue();
                 jin =simpleFormatter2.format(data1);
                 chu =simpleFormatter2.format(data2);
                xx.write("{\n" +
                        "    \"_class\" : \"java.util.LinkedHashMap\",\n" +
                        "    \"factoryId\" : 1006078,\n" +
                        "    \"factoryName\" : \"龙里县生活垃圾卫生填埋场\",\n" +
                        "    \"designScale\" : 120,\n" +
                        "    \"type\" :4,\n" +
                        "    \"treatmentProcess\" : 4,\n" +
                        "    \"carNumber\" : \""+chepai+"\",\n" +
                        "    \"carMarket\" : \"黔南布依族苗族自治州\",\n" +
                        "    \"carMarketId\" : 5227,\n" +
                        "    \"carCounty\" : \"龙里县\",\n" +
                        "    \"carCountyId\" : 522730,\n" +
                        "    \"trashCarId\" : 1012158,\n" +
                        "    \"inTime\" : \""+jin+"\",\n" +
                        "    \"outTime\" : \""+chu+"\",\n" +
                        "    \"netWeight\" : \""+zhongliang+"\",\n" +
                        "    \"time\" : NumberLong(1512640162588),\n" +
                        "    \"text\" : \"\"\n" +
                        "}");
            }
            System.out.println(chepai+"--"+zhongliang+"--"+jin);
        }
    }

}
