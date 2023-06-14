package com.example.furnifactory;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/finance")
    public FinanceReportDto getFinanceReport(FinanceReportCommand command) {
        return reportService.getFinanceReport(command);
    }

    @GetMapping("/used-materials")
    public UsedMaterialsReport getUsedMaterialsReport(UsedMaterialsReportCommand command) {
        return reportService.usedMaterialsReport(command);
    }

}
