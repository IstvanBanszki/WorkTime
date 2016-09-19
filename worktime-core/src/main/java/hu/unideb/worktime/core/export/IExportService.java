package hu.unideb.worktime.core.export;

import javax.servlet.http.HttpServletResponse;

public interface IExportService {
    
    void exportAbsences(Integer key, String dateFilter, Integer excelType, HttpServletResponse response);

    void exportAdminAbsences(Integer key, String dateFilter, Boolean showDailyWorkhours, Integer excelType, HttpServletResponse response);

    void exportWorklogs(Integer key, String dateFilter, Integer excelType, HttpServletResponse response);

    void exportAdminWorklogs(Integer key, String dateFilter, Boolean notApprove, Integer excelType, HttpServletResponse response);
}
