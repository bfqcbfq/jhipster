package com.ivision.app.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DynamicOperateExcelUtils {

	public static void main(String[] args) throws IOException {
		// 读取源文件
        FileInputStream fis = new FileInputStream("D:\\FilesAndDatas\\serverResources\\三菱重工MGS-CN调查问卷结果.xlsx");
        XSSFWorkbook workBook = new XSSFWorkbook(fis);

        // 进行模板的克隆(接下来的操作都是针对克隆后的sheet)
        XSSFSheet sheet = workBook.cloneSheet(0);
        workBook.setSheetName(0, "调查问卷结果"); // 给sheet命名

        // 读取指定cell的内容
//        XSSFCell nameCell = sheet.getRow(1).getCell(0);
//        XSSFCell nameCell2 = sheet.getRow(1).getCell(1);
//        System.out.println(nameCell.getStringCellValue());
//        System.out.println(nameCell2.getStringCellValue());

        // 替换单元格内容(注意获取的cell的下标是合并之前的下标)
//        replaceCellValue(sheet.getRow(1).getCell(2), "xxxxx时间");
//        replaceCellValue(sheet.getRow(2).getCell(2), "xxxxx人");

        // 动态插入数据-增加行
        List<Map<String, Object>> datas = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("no", "" + i);
            data.put("name", "姓名" + i);
            data.put("companyName", "公司名称" + i);
            data.put("telphone", "电话" + i);
            data.put("email", "E-mail" + i);
            data.put("q1", "问题一" + i);
            data.put("q2", "问题二" + i);
            data.put("q3", "问题三" + i);
            data.put("q4", "问题四" + i);
            data.put("q5", "问题五" + i);
            data.put("q6", "问题六" + i);
            data.put("q7", "问题七" + i);
            data.put("q8", "问题八" + i);
            data.put("q9", "问题九" + i);
            data.put("q10", "问题十" + i);
            data.put("comment", "意见" + i);
            datas.add(data);
        }
        // 插入行
         sheet.shiftRows(4, 4 + datas.size(), datas.size(), false, false);// 第1个参数是指要开始插入的行，第2个参数是结尾行数,第三个参数表示动态添加的行数
        for (int i = 0; i < datas.size(); i++) {
            XSSFRow creRow = sheet.createRow(4 + i);
            
            creRow.setRowStyle(sheet.getRow(4).getRowStyle());
            creRow.createCell(0).setCellValue(datas.get(i).get("no").toString());
            creRow.createCell(1).setCellValue(datas.get(i).get("name").toString());
            creRow.createCell(2).setCellValue(datas.get(i).get("companyName").toString());
            creRow.createCell(3).setCellValue(datas.get(i).get("telphone").toString());
            creRow.createCell(4).setCellValue(datas.get(i).get("email").toString());
            creRow.createCell(5).setCellValue(datas.get(i).get("q1").toString());
            creRow.createCell(6).setCellValue(datas.get(i).get("q2").toString());
            creRow.createCell(7).setCellValue(datas.get(i).get("q3").toString());
            creRow.createCell(8).setCellValue(datas.get(i).get("q4").toString());
            creRow.createCell(9).setCellValue(datas.get(i).get("q5").toString());
            creRow.createCell(10).setCellValue(datas.get(i).get("q6").toString());
            creRow.createCell(11).setCellValue(datas.get(i).get("q7").toString());
            creRow.createCell(12).setCellValue(datas.get(i).get("q8").toString());
            creRow.createCell(13).setCellValue(datas.get(i).get("q9").toString());
            creRow.createCell(14).setCellValue(datas.get(i).get("q10").toString());
            creRow.createCell(15).setCellValue(datas.get(i).get("comment").toString());
        }

        // 输出为一个新的Excel，也就是动态修改完之后的excel
        String fileName = "test" + System.currentTimeMillis() + ".xlsx";
        OutputStream out = new FileOutputStream("D:" + "/FilesAndDatas/serverResources/" + fileName);
        workBook.removeSheetAt(0); // 移除workbook中的模板sheet
        workBook.write(out);

        fis.close();
        out.flush();
        out.close();
	}
	
	
	/**
     * 替换单元格的内容，单元格的获取位置是合并单元格之前的位置，也就是下标都是合并之前的下表
     * 
     * @param cell
     *            单元格
     * @param value
     *            需要设置的值
     */
    public static void replaceCellValue(Cell cell, Object value) {
        String val = value != null ? String.valueOf(value) : "";
        cell.setCellValue(val);
    }
	
	
}
