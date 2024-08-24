package com.hackseoul.controller;

import com.hackseoul.apiPayload.ApiResponse;
import com.hackseoul.dto.PlantIdentificationRequest;
import com.hackseoul.dto.PlantResponse;
import com.hackseoul.service.PlantService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plant")
@Slf4j
public class PlantController {

    private final PlantService plantService;

    @PostMapping("/identify")
    @Operation(summary = "식물 판별 API입니다.", description = "이미지를 통해 식물을 판별하고 해당 식물이 병들었는지 여부를 반환합니다.")
    public ApiResponse<PlantResponse.plantInfoDTO> identifyPlant(@Valid @RequestBody PlantIdentificationRequest request){
        PlantResponse.plantInfoDTO plantInfoDTO = plantService.identifyPlant(request);

        return ApiResponse.onSuccess(plantInfoDTO);
    }

}
