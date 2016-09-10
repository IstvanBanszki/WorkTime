package hu.unideb.worktime.core.export;

import hu.unideb.worktime.api.model.absence.AbsenceResponse;
import hu.unideb.worktime.api.model.worklog.WorklogResponse;
import hu.unideb.worktime.jdbc.absence.SqlCallAbsence;
import hu.unideb.worktime.jdbc.worklog.SqlCallWorklog;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExportService implements IExportService {

    @Autowired
    SqlCallWorklog sqlCallWorklog;
    @Autowired
    SqlCallAbsence sqlCallAbsence;
    private Logger LOGGER = LoggerFactory.getLogger(ExportService.class);
    private static final String FILE_NAME_ABSENCE_XLS = "ExportAbsence.xls";
    private static final String FILE_NAME_ABSENCE_XLSX = "ExportAbsence.xlsx";
    private static final String FILE_NAME_WORKLOG_XLS = "ExportWorklog.xls";
    private static final String FILE_NAME_WORKLOG_XLSX = "ExportWorklog.xlsx";
    private static final String SHEET_NAME_ABSENCE = "ExportAbsence";
    private static final String SHEET_NAME_WORKLOG = "ExportWorklog";
    private static final String XLS_CONTENT_TYPE = "application/vnd.ms-excel";
    private static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Override
    public void exportAbsences(Integer key, String dateFilter, Integer excelType, HttpServletResponse servletResponse) {

        servletResponse.setContentType(((excelType == 1) ? XLS_CONTENT_TYPE : XLSX_CONTENT_TYPE));
        servletResponse.setHeader("Content-Disposition", "attachment; filename=" + ((excelType == 1) ? FILE_NAME_ABSENCE_XLS : FILE_NAME_ABSENCE_XLSX));

        List<AbsenceResponse> absences = this.sqlCallAbsence.getAbsence(key, dateFilter);

        Workbook wb = (excelType == 1) ? new HSSFWorkbook() : new XSSFWorkbook();
        Sheet sheet = wb.createSheet(SHEET_NAME_ABSENCE);

        CellStyle dataCellStyle = this.getDataCellStyle(wb);
        CellStyle dateCellStyle = this.getDateCellStyle(wb);
        int rowIndex = 1;

        this.createAbsenceHeaderRow(sheet.createRow(0), this.getHeaderCellStyle(wb));

        for (AbsenceResponse response : absences) {

            this.createAbsenceRow(sheet.createRow(rowIndex++), dataCellStyle, dateCellStyle, response);
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);

        try (ServletOutputStream outputStream = servletResponse.getOutputStream()) {
            wb.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportWorklogs(Integer key, String dateFilter, Integer excelType, HttpServletResponse servletResponse) {

        servletResponse.setContentType(((excelType == 1) ? XLS_CONTENT_TYPE : XLSX_CONTENT_TYPE));
        servletResponse.setHeader("Content-Disposition", "attachment; filename=" + ((excelType == 1) ? FILE_NAME_WORKLOG_XLS : FILE_NAME_WORKLOG_XLSX));

        List<WorklogResponse> worklogs = this.sqlCallWorklog.getWorklog(key, dateFilter);

        Workbook wb = (excelType == 1) ? new HSSFWorkbook() : new XSSFWorkbook();
        Sheet sheet = wb.createSheet(SHEET_NAME_WORKLOG);

        CellStyle dataCellStyle = this.getDataCellStyle(wb);
        CellStyle dateCellStyle = this.getDateCellStyle(wb);
        int rowIndex = 1;

        this.createWorklogHeaderRow(sheet.createRow(0), this.getHeaderCellStyle(wb));

        for (WorklogResponse response : worklogs) {

            this.createWorklogRow(sheet.createRow(rowIndex++), dataCellStyle, dateCellStyle, response);
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        try (ServletOutputStream outputStream = servletResponse.getOutputStream()) {
            wb.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**********************************/
    /*   DataRow utility methods   */
    /**********************************/
    private void createAbsenceRow(Row row, CellStyle dataCellStyle, CellStyle dateCellStyle, AbsenceResponse response) {

        Cell beginDateCell = row.createCell(0);
        beginDateCell.setCellValue(Date.from(response.getBeginDate().atZone(ZoneId.systemDefault()).toInstant()));
        beginDateCell.setCellStyle(dateCellStyle);

        Cell endDateCell = row.createCell(1);
        endDateCell.setCellValue(Date.from(response.getEndDate().atZone(ZoneId.systemDefault()).toInstant()));
        endDateCell.setCellStyle(dateCellStyle);

        Cell typeCell = row.createCell(2);
        typeCell.setCellValue(response.getAbsenceType().getName());
        typeCell.setCellStyle(dataCellStyle);

        Cell statusCell = row.createCell(3);
        statusCell.setCellValue(response.getStatus().getName());
        typeCell.setCellStyle(dataCellStyle);
    }

    private void createWorklogRow(Row row, CellStyle dataCellStyle, CellStyle dateCellStyle, WorklogResponse response) {

        Cell beginDateCell = row.createCell(0);
        beginDateCell.setCellValue(Date.from(response.getBeginDate().atZone(ZoneId.systemDefault()).toInstant()));
        beginDateCell.setCellStyle(dateCellStyle);

        Cell workHourCell = row.createCell(1);
        workHourCell.setCellValue(response.getWorkHour());
        workHourCell.setCellStyle(dataCellStyle);
    }

    /**********************************/
    /*   HeaderRow utility methods   */
    /**********************************/
    private void createAbsenceHeaderRow(Row headerRow, CellStyle headerCellStyle) {

        Cell beginDateCell = headerRow.createCell(0);
        beginDateCell.setCellValue("Begin Date");
        beginDateCell.setCellStyle(headerCellStyle);

        Cell endDateCell = headerRow.createCell(1);
        endDateCell.setCellValue("End Date");
        endDateCell.setCellStyle(headerCellStyle);

        Cell absenceTypeCell = headerRow.createCell(2);
        absenceTypeCell.setCellValue("Absence Type");
        absenceTypeCell.setCellStyle(headerCellStyle);

        Cell statusCell = headerRow.createCell(3);
        statusCell.setCellValue("Status");
        statusCell.setCellStyle(headerCellStyle);
    }

    private void createWorklogHeaderRow(Row headerRow, CellStyle headerCellStyle) {

        Cell beginDateCell = headerRow.createCell(0);
        beginDateCell.setCellValue("Begin Date");
        beginDateCell.setCellStyle(headerCellStyle);

        Cell workHourCell = headerRow.createCell(1);
        workHourCell.setCellValue("WorkHour");
        workHourCell.setCellStyle(headerCellStyle);
    }
    
    /**********************************/
    /*   CellStype utility methods   */
    /**********************************/
    private CellStyle getHeaderCellStyle(Workbook wb) {

        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(CellStyle.BORDER_THIN);

        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 13);
        font.setFontName("Arial");
        font.setBold(true);
        headerCellStyle.setFont(font);

        return headerCellStyle;
    }

    private CellStyle getDataCellStyle(Workbook wb) {

        CellStyle dataCellStyle = wb.createCellStyle();
        dataCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        dataCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dataCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        dataCellStyle.setBorderTop(CellStyle.BORDER_THIN);

        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("Arial");
        dataCellStyle.setFont(font);

        return dataCellStyle;
    }

    private CellStyle getDateCellStyle(Workbook wb) {

        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle dateCellStyle = wb.createCellStyle();

        dateCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        dateCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dateCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        dateCellStyle.setBorderTop(CellStyle.BORDER_THIN);

        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy.mm.dd"));

        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("Arial");
        dateCellStyle.setFont(font);

        return dateCellStyle;
    }
}