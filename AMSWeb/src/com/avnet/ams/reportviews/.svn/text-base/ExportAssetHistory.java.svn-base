package com.avnet.ams.reportviews;

import java.util.ArrayList;
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

import com.avnet.ams.util.AMSDateUtil;
import com.avnet.assetportal.webservice.reportmanager.AssetDetails;
import com.avnet.assetportal.webservice.reportmanager.AssetReport;

public class ExportAssetHistory extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AssetReport assetReport = (AssetReport) model.get("assetReport");
		
		String searchType = model.get("selectedSearchType").toString();
		String searchString = model.get("selectedSearchString").toString();
		
		
		// checking for null
		if (assetReport == null) {
			HSSFSheet sheet = workbook.createSheet("Report");
			HSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("No data found");
		} else {

			List<AssetDetails> assetHistoryList = assetReport
					.getAssetHistoryList();

			// Setting column headings
			List<String> columnHeadingsUser = new ArrayList<String>();
			columnHeadingsUser.add("Asset Id");
			columnHeadingsUser.add("Asset Type");
			columnHeadingsUser.add("Date Of Issue");
			columnHeadingsUser.add("Date Of Return");
			
			List<String> columnHeadingsAsset = new ArrayList<String>();
			columnHeadingsAsset.add("User ID");
			columnHeadingsAsset.add("Designation");
			columnHeadingsAsset.add("Date Of Issue");
			columnHeadingsAsset.add("Date Of Return");


			// Writing data to Excel
			// create a new Excel sheet
			HSSFSheet sheet = workbook.createSheet("AssetReport");
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

			
			HSSFRow headerMain = sheet.createRow(0);
			
			if(searchType.equals("ASSET_ID"))
			{
				headerMain.createCell(0).setCellValue(columnHeadingsUser.get(0));
				headerMain.createCell(1).setCellValue(searchString);
				headerMain.getCell(0).setCellStyle(style);
				headerMain.getCell(1).setCellStyle(style);
			}
			if(searchType.equals("USER_ID"))
			{
				headerMain.createCell(0).setCellValue(columnHeadingsAsset.get(0));
				headerMain.createCell(1).setCellValue(searchString);
				headerMain.getCell(0).setCellStyle(style);
				headerMain.getCell(1).setCellStyle(style);	
			}
			
			// create header row
			HSSFRow header = sheet.createRow(1);

			// setting headings in respected columns
				if(searchType.equals("USER_ID"))
				{
					for (int i = 0; i < columnHeadingsUser.size(); i++) {
						header.createCell(i).setCellValue(columnHeadingsUser.get(i));
						header.getCell(i).setCellStyle(style);
					}
					
				}
				if(searchType.equals("ASSET_ID"))
				{
					for (int i = 0; i < columnHeadingsAsset.size(); i++) {
						header.createCell(i).setCellValue(columnHeadingsAsset.get(i));
						header.getCell(i).setCellStyle(style);
					}
			
				}

			// create data rows
			// count starts from 1
			int rowCount = 2;
			// row 1 is heading, so we're populating from row 2
			// looping through rows
			if(searchType.equals("USER_ID"))
			{
				for (int i = 0; i < assetHistoryList.size(); i++) {
					AssetDetails assetHistoryListLoopObject = assetHistoryList
							.get(i);
					HSSFRow aRow = sheet.createRow(rowCount++);			
					aRow.createCell(0).setCellValue(
							assetHistoryListLoopObject.getAssetIdentity());
					aRow.createCell(1).setCellValue(
							assetHistoryListLoopObject.getAssetType());
					aRow.createCell(2).setCellValue(
							AMSDateUtil.convertDateToString(AMSDateUtil
									.toDate(assetHistoryListLoopObject
											.getDateOfIssue())));
					aRow.createCell(3).setCellValue(
							AMSDateUtil.convertDateToString(AMSDateUtil
									.toDate(assetHistoryListLoopObject
											.getDateOfReturn())));

				}

			}
			if(searchType.equals("ASSET_ID"))
			{
				for (int i = 1; i < assetHistoryList.size(); i++) {
					AssetDetails assetHistoryListLoopObject = assetHistoryList
							.get(i);
					HSSFRow aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue(
							assetHistoryListLoopObject.getUserId());
					aRow.createCell(1).setCellValue(
							assetHistoryListLoopObject.getDesignation());
					aRow.createCell(2).setCellValue(
							AMSDateUtil.convertDateToString(AMSDateUtil
									.toDate(assetHistoryListLoopObject
											.getDateOfIssue())));
					aRow.createCell(3).setCellValue(
							AMSDateUtil.convertDateToString(AMSDateUtil
									.toDate(assetHistoryListLoopObject
											.getDateOfReturn())));
				}
	
			}
			
		}
		// end of else
		response.setHeader("Content-disposition",
				"attachment; filename=Asset_History.xls");
		// end of method
	}
}
