package com.hackseoul.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlantIdentificationResponse {
    private String plantName;
    private String diseaseInfo;
    private List<String> possibleDiseases;
    private boolean isHealthy;
}
