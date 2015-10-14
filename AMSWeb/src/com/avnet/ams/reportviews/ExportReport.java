package com.avnet.ams.reportviews;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.avnet.assetportal.webservice.reportmanager.ReportResults;
import com.avnet.assetportal.webservice.reportmanager.ReportResultsList;

public class ExportReport extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ReportResults reportResults = (ReportResults) model
				.get("reportResults");

		// checking for null
		if (reportResults == null) {
			HSSFSheet sheet = workbook.createSheet("Report");
			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("No data found");
		} else {

			List<ReportResultsList> reportResultsList = reportResults
					.getReportResults();

			// first row from service contains column names
			ReportResultsList firstRowList = reportResultsList.get(0);
			// Already formatted in previous method
			List<String> columnHeadings = firstRowList.getReportParameters();

			// Writing data to Excel
			// create a new Excel sheet
			HSSFSheet sheet = workbook.createSheet("Report");
			sheet.setDefaultColumnWidth(30);

			// create style for header cells
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			// create header row
			HSSFRow header = sheet.createRow(0);

			// setting headings in respected columns
			for (int i = 0; i < columnHeadings.size(); i++) {
				header.createCell(i).setCellValue(columnHeadings.get(i));
				header.getCell(i).setCellStyle(style);
			}

			// create data rows
			// count starts from 1
			int rowCount = 1;
			// row 1 is heading, so we're populating from row 2
			// looping through rows
			for (int i = 1; i < reportResultsList.size(); i++) {
				ReportResultsList reportRowsListLoopObject = reportResultsList
						.get(i);
				HSSFRow aRow = sheet.createRow(rowCount++);
				// looping through cells
				for (int j = 0; j < reportRowsListLoopObject
						.getReportParameters().size(); j++) {
					List<String> cellValues = reportRowsListLoopObject
							.getReportParameters();
					aRow.createCell(j).setCellValue(cellValues.get(j));
					// end of inner loop
				}
				// end of outer loop
			}

		}
		// end of else
		response.setHeader("Content-disposition",
				"attachment; filename=Report.xls");
		// end of method
	}
}
