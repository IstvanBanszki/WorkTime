package hu.unideb.worktime.core.export;

import javax.servlet.http.HttpServletResponse;

public interface IExportService {
    
    void exportAbsences(Integer key, String dateFilter, Integer excelType, HttpServletResponse response);

    void exportWorklogs(Integer key, String dateFilter, Integer excelType, HttpServletResponse response);
}
