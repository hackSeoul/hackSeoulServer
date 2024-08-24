package com.hackseoul.dto;


import lombok.*;

public class PlantResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class plantInfoDTO{
        private String plantName;
        private String plantDescription;
        private boolean isPlant;
        private boolean isHealthy;
    }

}
