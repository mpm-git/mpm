package cn.com.mwsn.frame.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * 导出excel工具类
 * 
 * @author Administrator
 */
public class ExportExcelUtil {

    /**
     * 导出数据
     * @param data
     * @return
     * @throws Exception
     */
	public  static HSSFWorkbook exportExcel(List<List<String>> data, String sheet_name) throws Exception {
		HSSFWorkbook workbook = null;
		try {
			// 创建工作簿实例
			workbook = new HSSFWorkbook();
			// 创建工作表实例
			HSSFSheet sheet = workbook.createSheet(sheet_name);
			// 获取样式
			HSSFCellStyle t_style = createTitleStyle(workbook);
			HSSFCellStyle c_style = createContentStyle(workbook);
			if (data != null && data.size() > 0) {
				// 设置列宽
				setSheetColumnWidth(sheet, data.get(0).size());
				// 给excel填充数据
				for (int i = 0; i < data.size(); i++) {
					// 将dataList里面的数据取出来，假设这里取出来的是Model,也就是某个javaBean的意思啦
					List<String> row_data = data.get(i);
					HSSFRow row = sheet.createRow((short) i);// 建立新行
					for(int j = 0; j < row_data.size(); j++){
						if(i == 0){
							row.setHeight((short)400);
							createCell(row, j, t_style, HSSFCell.CELL_TYPE_STRING, row_data.get(j));
						}else{
							createCell(row, j, c_style, HSSFCell.CELL_TYPE_STRING, row_data.get(j));
						}
					}
				}
			} else {
				createCell(sheet.createRow(0), 0, t_style, HSSFCell.CELL_TYPE_STRING, "没有数据");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return workbook;

	}

	private static void setSheetColumnWidth(HSSFSheet sheet, int column_size) {
		// 根据你数据里面的记录有多少列，就设置多少列
		for(int i = 0; i < column_size; i++){
			sheet.setColumnWidth(i, 6000);
		}
	}

	/**
	 * 设置excel的title样式
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle createTitleStyle(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short) 300);
		boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
		//设置背景色
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setWrapText(true);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		return style;
	}
	
	/**
	 * 设置excel的内容样式
	 * @param wb
	 * @return
	 */
	private static HSSFCellStyle createContentStyle(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short) 200);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
		style.setWrapText(true);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		return style;
	}

	/**
	 * 创建Excel单元格
	 * @param row
	 * @param column
	 * @param style
	 * @param cellType
	 * @param value
	 */
	private static void createCell(HSSFRow row, int column, HSSFCellStyle style,int cellType, Object value) {
		HSSFCell cell = row.createCell(column);
		if (style != null) {
			cell.setCellStyle(style);
		}
		switch (cellType) {
		case HSSFCell.CELL_TYPE_BLANK: {
			break;
		}
		case HSSFCell.CELL_TYPE_STRING: {
			cell.setCellValue(value.toString());
			break;
		}
		case HSSFCell.CELL_TYPE_NUMERIC: {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(Double.parseDouble(value.toString()));
			break;
		}
		default:
			break;
		}
	}
}
