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

    @GetMapping("/nickName/{nickName}")
    @Operation(summary = "특정 사용자의 식물 리스트 조회하는 API입니다.", description = "전체 식물 리스트를 조회하는 API입니다.")
    public ApiResponse<PlantResponse.plantUsernameListDTO> getPlantWithUsername(@PathVariable String nickName){
        List<PlantResponse.plantSpecificInfoDTO> plantListWithNickName = plantService.getPlantListWithNickName(nickName);
        return ApiResponse.onSuccess(PlantResponse.plantUsernameListDTO.builder()
                .plantListsDTO(plantListWithNickName)
                .build());
    }

    @GetMapping("/{latitude}/{longitude}")
    @Operation(summary = "근처에 있는 식물들의 종류를 알려줍니다.", description = "근처 식물이 몇 개 있는지, 어떤 식물인지, 각 식물의 id는 무엇인지 알려줍니다.")
    public ApiResponse<PlantResponse.plantNameListDTO> getPlantWithName(@PathVariable double latitude, @PathVariable double longitude) {
        PlantResponse.plantNameListDTO plantNameListDTO = plantService.getPlantNameList(latitude, longitude);
        return ApiResponse.onSuccess(plantNameListDTO);
    }

    @GetMapping("{id}")
    @Operation(summary = "id를 활용해 특정 식물을 조회하는 API입니다.",description = "id를 입력하세요....")
    public ApiResponse<PlantResponse.plantSpecificInfoDTO> getSpecificPlant(@PathVariable long id){
        PlantResponse.plantSpecificInfoDTO specificPlant = plantService.getSpecificPlant(id);
        return ApiResponse.onSuccess(specificPlant);
    }
}
