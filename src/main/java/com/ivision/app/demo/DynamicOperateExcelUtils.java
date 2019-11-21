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
        FileInputStream fis = new FileInputStream("D:\\FilesAndDatas\\serverResources\\Ivisionアンケート.xlsx");
        XSSFWorkbook workBook = new XSSFWorkbook(fis);

        // 进行模板的克隆(接下来的操作都是针对克隆后的sheet)
        XSSFSheet sheet = workBook.cloneSheet(0);
        workBook.setSheetName(0, "アンケート結果まとめ"); // 给sheet命名

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
            data.put("kaisyamei", "会社名" + i);
            data.put("syozoku", "所属／役職" + i);
            data.put("simei", "氏名" + i);
            data.put("denwabangou", "電話番号" + i);
            data.put("email", "E-mail" + i);
            data.put("senntaku1", "選択一" + i);
            data.put("kaitou1", "回答一" + i);
            data.put("senntaku2", "選択二" + i);
            data.put("kaitou2", "回答二" + i);
            data.put("senntaku3", "選択三" + i);
            data.put("kaitou3", "回答三" + i);
            data.put("kaitou4", "回答四" + i);
            data.put("kaitou5", "回答五" + i);
            data.put("senntaku6", "選択六" + i);
            data.put("kaitou6", "回答六" + i);
            data.put("senntaku7", "選択七" + i);
            data.put("kaitou7", "回答七" + i);
            datas.add(data);
        }
        // 插入行
         sheet.shiftRows(4, 4 + datas.size(), datas.size(), false, false);// 第1个参数是指要开始插入的行，第2个参数是结尾行数,第三个参数表示动态添加的行数
        for (int i = 0; i < datas.size(); i++) {
            XSSFRow creRow = sheet.createRow(4 + i);
            
            creRow.setRowStyle(sheet.getRow(4).getRowStyle());
            creRow.createCell(0).setCellValue(datas.get(i).get("no").toString());
            creRow.createCell(1).setCellValue(datas.get(i).get("kaisyamei").toString());
            creRow.createCell(2).setCellValue(datas.get(i).get("syozoku").toString());
            creRow.createCell(3).setCellValue(datas.get(i).get("simei").toString());
            creRow.createCell(4).setCellValue(datas.get(i).get("denwabangou").toString());
            creRow.createCell(5).setCellValue(datas.get(i).get("email").toString());
            creRow.createCell(6).setCellValue(datas.get(i).get("senntaku1").toString());
            creRow.createCell(7).setCellValue(datas.get(i).get("kaitou1").toString());
            creRow.createCell(8).setCellValue(datas.get(i).get("senntaku2").toString());
            creRow.createCell(9).setCellValue(datas.get(i).get("kaitou2").toString());
            creRow.createCell(10).setCellValue(datas.get(i).get("senntaku3").toString());
            creRow.createCell(11).setCellValue(datas.get(i).get("kaitou3").toString());
            creRow.createCell(12).setCellValue(datas.get(i).get("kaitou4").toString());
            creRow.createCell(13).setCellValue(datas.get(i).get("kaitou5").toString());
            creRow.createCell(14).setCellValue(datas.get(i).get("senntaku6").toString());
            creRow.createCell(15).setCellValue(datas.get(i).get("kaitou6").toString());
            creRow.createCell(16).setCellValue(datas.get(i).get("senntaku7").toString());
            creRow.createCell(17).setCellValue(datas.get(i).get("kaitou7").toString());
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
