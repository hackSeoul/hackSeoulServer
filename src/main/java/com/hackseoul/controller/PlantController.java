package com.hackseoul.controller;

import com.hackseoul.apiPayload.ApiResponse;
import com.hackseoul.dto.PlantRequest;
import com.hackseoul.dto.PlantResponse;
import com.hackseoul.service.PlantService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plant")
@Slf4j
public class PlantController {

    private final PlantService plantService;

    @PostMapping("/identify")
    @Operation(summary = "식물 판별 API입니다.", description = "이미지를 통해 식물을 판별하고 해당 식물이 병들었는지 여부를 반환합니다.")
    public ApiResponse<PlantResponse.plantBasicInfo> identifyPlant(@Valid @RequestBody PlantRequest.PlantIdentificationDTO request){
        PlantResponse.plantBasicInfo plantBasicInfo = plantService.identifyPlant(request.getImageData());

        return ApiResponse.onSuccess(plantBasicInfo);
    }

    @PostMapping("/save")
    @Operation(summary = "식물 정보 저장하는 API입니다.", description = "이미지를 통해 식물의 정보와 질병 유무를 확인하고 DB에 저장합니다.")
    public ApiResponse<PlantResponse.plantSpecificInfoDTO> savePlant(@Valid @RequestBody PlantRequest.PlantSaveDTO request) throws IOException {
        PlantResponse.plantSpecificInfoDTO plantSpecificInfoDTO = plantService.savePlant(request);
        return ApiResponse.onSuccess(plantSpecificInfoDTO);
    }

    @GetMapping("/list")
    @Operation(summary = "전체 식물 리스트 조회하는 API입니다.", description = "전체 식물 리스트를 조회하는 API입니다.")
    public ApiResponse<PlantResponse.plantListDTO> getEntirePlant(){
        List<PlantResponse.plantSimpleDTO> entireList = plantService.getEntireList();
        return ApiResponse.onSuccess(PlantResponse.plantListDTO.builder()
                .plantListsDTO(entireList)
                .build());
    }

    @GetMapping("{id}")
    @Operation(summary = "id를 활용해 특정 식물을 조회하는 API입니다.",description = "id를 입력하세요....")
    public ApiResponse<PlantResponse.plantSpecificInfoDTO> getSpecificPlant(@PathVariable long id){
        PlantResponse.plantSpecificInfoDTO specificPlant = plantService.getSpecificPlant(id);
        return ApiResponse.onSuccess(specificPlant);
    }
}
