package com.example.furnifactory;

import com.example.furnifactory.furniture.FurnitureService;
import com.example.furnifactory.order.OrderService;
import com.example.furnifactory.order.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderService orderService;

    private final FurnitureService furnitureService;

    public FinanceReportDto getFinanceReport(FinanceReportCommand command) {
        OrderSpecification orderSpecification = new OrderSpecification();
        orderSpecification.setCreatedAtFrom(command.getFrom().atStartOfDay());
        orderSpecification.setCreatedAtTo(command.getTo().atTime(23, 59, 59));
        return orderService.getAll(orderSpecification).stream()
                .map(order -> {
                    FinanceReportDto financeReportDto = new FinanceReportDto();
                    financeReportDto.setFrom(command.getFrom());
                    financeReportDto.setTo(command.getTo());
                    financeReportDto.setIncome(order.getPrice());
                    financeReportDto.setOutcome(order.getPrice() * 0.7);
                    financeReportDto.setProfit(financeReportDto.getIncome() - financeReportDto.getOutcome());
                    return financeReportDto;
                })
                .reduce(new FinanceReportDto(), (a, b) -> {
                    a.setFrom(b.getFrom());
                    a.setTo(b.getTo());
                    a.setIncome(a.getIncome() + b.getIncome());
                    a.setOutcome(a.getOutcome() + b.getOutcome());
                    a.setProfit(a.getProfit() + b.getProfit());
                    return a;
                });
    }

    public UsedMaterialsReport usedMaterialsReport(UsedMaterialsReportCommand command) {
        OrderSpecification orderSpecification = new OrderSpecification();
        orderSpecification.setCreatedAtFrom(command.getFrom().atStartOfDay());
        orderSpecification.setCreatedAtTo(command.getTo().atTime(23, 59, 59));
        Map<String, Double> usedMaterials = new HashMap<>();
        orderService.getAll(orderSpecification).forEach(
                orderDto -> furnitureService.get(orderDto.getFurnitureId()).getMaterials().forEach(
                        materialDto -> {
                            if (usedMaterials.containsKey(materialDto.getMaterial_name())) {
                                usedMaterials.put(materialDto.getMaterial_name(),
                                        usedMaterials.get(materialDto.getMaterial_name()) +
                                                furnitureService.getFurnitureArea(furnitureService.get(orderDto.getFurnitureId())));
                            } else {
                                usedMaterials.put(materialDto.getMaterial_name(), furnitureService.getFurnitureArea(furnitureService.get(orderDto.getFurnitureId())));
                            }
                        }
                )
        );
        UsedMaterialsReport usedMaterialsReport = new UsedMaterialsReport();
        usedMaterialsReport.setUsedMaterials(usedMaterials);
        return usedMaterialsReport;

    }
}
