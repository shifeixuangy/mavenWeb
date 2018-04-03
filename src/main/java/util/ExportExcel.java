package util;

import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

    public static HSSFWorkbook jsonToObj(String jsonStr) throws Exception {
        String[] xxx = jsonStr.split("}");
        List array = new ArrayList();
        for (int i = 0; i < xxx.length; i++) {
            xxx[i] = xxx[i] + "}";
            array.add(xxx[i]);
        }
        String msg = "";
        HSSFWorkbook hSSFWorkbook = null;

        hSSFWorkbook = doExcel(array);

        return hSSFWorkbook;
    }

    private static HSSFWorkbook doExcel(List msg) {
        HSSFWorkbook xls = new HSSFWorkbook();
        HSSFSheet sheet = xls.createSheet();
        HSSFFont font1 = xls.createFont();
        HSSFFont font2 = xls.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 14);// 设置字体大小
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 13);// 设置字体大小
        HSSFCellStyle style = xls.createCellStyle();
        HSSFCellStyle style1 = xls.createCellStyle();
        HSSFCellStyle style3 = xls.createCellStyle();
        //水平居中
        style.setAlignment(CellStyle.ALIGN_CENTER);// 居中
        //水平居中
        style3.setAlignment(CellStyle.ALIGN_CENTER);// 居中
        style3.setFont(font2);
        //加粗边框
        style.setFont(font1);
        style1.setAlignment(CellStyle.ALIGN_CENTER);// 居中
        //字体大小
        HSSFFont font = xls.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 18);// 设置字体大小
        HSSFRow row = null;
        //设置表头
        sheet.setColumnWidth(0, 8 * 256);
        sheet.setColumnWidth(1, 40 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 23 * 256);
        sheet.setColumnWidth(4, 23 * 256);
        sheet.setColumnWidth(5, 12 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 30 * 256);
        sheet.setDefaultRowHeightInPoints(25);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 6));
        HSSFRow row1 = sheet.createRow(0);// 第一行
        row1.setHeightInPoints(40);
        style1.setFont(font);
        HSSFCell cell9 = row1.createCell(1);
        cell9.setCellValue("1");
        cell9.setCellStyle(style1);
        HSSFRow row2 = sheet.createRow(1);// 第二行
        HSSFCell cell1 = row2.createCell(0);
        cell1.setCellValue("2");
        cell1.setCellStyle(style);
        HSSFCell cell2 = row2.createCell(1);
        cell2.setCellValue("3");
        cell2.setCellStyle(style);
        HSSFCell cell3 = row2.createCell(2);
        cell3.setCellValue("4");
        cell3.setCellStyle(style);
        HSSFCell cell4 = row2.createCell(3);
        cell4.setCellValue("5");
        cell4.setCellStyle(style);
        HSSFCell cell5 = row2.createCell(4);
        cell5.setCellValue("6");
        cell5.setCellStyle(style);
        HSSFCell cell6 = row2.createCell(5);
        cell6.setCellValue("7");
        cell6.setCellStyle(style);
        HSSFCell cell7 = row2.createCell(6);
        cell7.setCellValue("8");
        cell7.setCellStyle(style);
        HSSFCell cell8 = row2.createCell(7);
        cell8.setCellValue("9");
        cell8.setCellStyle(style);
        HSSFCell cell;
        for (int i = 0; i < msg.size(); i++) {
            JSONObject jsonObject = JSONObject.fromObject(msg.get(i));
            String msg1 = jsonObject.getString("msg");
            String[] msgArray = msg1.split(",");
            row = sheet.createRow(i + 2);
            cell = row.createCell(0);
            cell.setCellValue(msgArray[0]);
            cell.setCellStyle(style3);
            cell = row.createCell(1);
            cell.setCellValue(msgArray[1]);
            cell.setCellStyle(style3);
            cell = row.createCell(2);
            cell.setCellValue(msgArray[2]);
            cell.setCellStyle(style3);
            cell = row.createCell(3);
            cell.setCellValue(msgArray[3]);
            cell.setCellStyle(style3);
            cell = row.createCell(4);
            cell.setCellValue(msgArray[4]);
            cell.setCellStyle(style3);
            cell = row.createCell(5);
            cell.setCellValue(msgArray[5]);
            cell.setCellStyle(style3);
            cell = row.createCell(6);
            cell.setCellValue(msgArray[6]);
            cell.setCellStyle(style3);

        }

        return xls;

    }

    public static void renderExcel(HttpServletResponse response, HttpServletRequest request,
                                   String fileName, HSSFWorkbook hwb) throws IOException {
        final String userAgent = request.getHeader("USER-AGENT");
        String fileNameUTF8 = URLEncoder.encode(fileName, "UTF8");//其他浏览器
        response.reset();
        if (userAgent.indexOf("Mozilla") != -1) {//google,火狐浏览器
            fileNameUTF8 = new String(fileName.getBytes(), "ISO8859-1");
        }
        response.setHeader("Content-disposition", fileNameUTF8);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileNameUTF8);
        response.setHeader("Pragma", "No-cache");
        OutputStream ouputStream = response.getOutputStream();
        hwb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}
