package com.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mybatis.beans.Employee;

public class ExcleImpl {

	public ExcleImpl()
	{

	}
	public void export(List<Employee> list,String[] titles, ServletOutputStream out) throws Exception {
		try {
			// 第一步，创建一个workbook，对应一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();

			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet hssfSheet = workbook.createSheet("sheet1");

			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

			HSSFRow row = hssfSheet.createRow(0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

			// 居中样式
			hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCell hssfCell = null;
			for (int i = 0; i < titles.length; i++) {
				hssfCell = row.createCell(i);// 列索引从0开始
				hssfCell.setCellValue(titles[i]);// 列名1
				hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
			}

			// 第五步，写入实体数据
//			Person person1 = new Person("1", "张三", "123", "26");
//			Person person2 = new Person("2", "李四", "123", "18");
//			Person person3 = new Person("3", "王五", "123", "77");
//			Person person4 = new Person("4", "徐小筱", "123", "1");


			// 这里我把list当做数据库啦
//			ArrayList<Person> list = new ArrayList<Person>();
//			list.add(person1);
//			list.add(person2);
//			list.add(person3);
//			list.add(person4);

			for (int i = 0; i < list.size(); i++) {
				row = hssfSheet.createRow(i + 1);
				Employee employee = list.get(i);

				// 第六步，创建单元格，并设置值
				Integer id = null;
				if (employee.getId() != null) {
					id = employee.getId();
				}
				String name = "";
				if (employee.getLastName() != null) {
					name = employee.getLastName();
				}
//				String sex = null;
//				if (employee.getSex().) {
//					String sex_str = employee.getSex();
//					if (sex_str == "1"||sex_str.equals("1"))
//						sex = "男";
//					else {
//						sex = "女";
//					}
//				}
				int sex = employee.getSex();
				String email = "";
				if (employee.getEmail() != null) {
					email = employee.getEmail();
				}
				row.createCell(0).setCellValue(id);
				row.createCell(1).setCellValue(name);
				row.createCell(2).setCellValue(sex);
				row.createCell(3).setCellValue(email);
			}

			// 第七步，将文件输出到客户端浏览器
			try {
				workbook.write(out);
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("导出信息失败！");

		}
	}
}
