package com.hackseoul.dto;


import lombok.*;

public class PlantResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class plantBasicInfo {
        private String plantName;
        private String plantDescription;
        private boolean isPlant;
        private boolean isHealthy;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class plantSpecificInfoDTO{
        private String nickName;
        private String plantName;
        private String plantDescription;
        private boolean isPlant;
        private boolean isHealthy;
        private String disease;
        private double longitude;
        private double latitude;
    }

}
