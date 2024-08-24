package com.hackseoul.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PlantRequest {
    @Getter
    public static class PlantIdentificationDTO{
        @NotEmpty(message = "imageData는 필수입니다.")
        private String imageData;
    }

    @Getter
    public static class PlantSaveDTO{
        @NotEmpty(message = "imageData는 필수입니다.")
        private String imageData;
        private String nickName;
        @NotNull(message = "경도 값은 필수입니다.")
        private double longitude;
        @NotNull(message = "위도 값은 필수입니다.")
        private double lantitude;
    }
}

