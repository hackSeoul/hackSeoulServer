package com.hackseoul.dto;


import lombok.*;

import java.util.List;
import java.util.Map;

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
        private Long id;
        private String nickName;
        private String plantName;
        private String plantDescription;
        private boolean isPlant;
        private boolean isHealthy;
        private String disease;
        private String imageDirectory;
        private double longitude;
        private double latitude;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class plantListDTO{
        private List<plantSimpleDTO> plantListsDTO;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class plantNameListDTO{
        private int count;
        private List<plantNameDTO> plantNameListDTO;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class plantNameDTO{
        private String plantName;
        private List<plantSimpleDTO> plantListsDTO;    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class plantUsernameListDTO{
        private List<plantSpecificInfoDTO> plantListsDTO;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class plantSimpleDTO{
        private Long id;
        private double longitude;
        private double latitude;

    }


}
