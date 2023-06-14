package com.example.furnifactory;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Getter
@Setter
public class UsedMaterialsReport {
    private Map<String, Double> usedMaterials;
}
