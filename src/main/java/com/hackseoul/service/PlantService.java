package com.hackseoul.service;

import com.hackseoul.apiPayload.code.status.ErrorStatus;
import com.hackseoul.apiPayload.exception.handler.PlantHandler;
import com.hackseoul.domain.Plant;
import com.hackseoul.dto.PlantDiseaseResponse;
import com.hackseoul.dto.PlantRequest;
import com.hackseoul.dto.PlantResponse;
import com.hackseoul.dto.PlantInformationResponse;
import com.hackseoul.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlantService {

    private final RestTemplate restTemplate;
    private final PlantRepository plantRepository;
    private final FileService fileService;

    public List<PlantResponse.plantSimpleDTO> getEntireList(){
        List<PlantResponse.plantSimpleDTO> plantSimpleDTOList = plantRepository.findAll().stream()
                .map(plant -> PlantResponse.plantSimpleDTO.builder()
                        .id(plant.getId())
                        .longitude(plant.getLongitude())
                        .latitude(plant.getLatitude())
                        .build())
                .collect(Collectors.toList());
        return plantSimpleDTOList;

    }

    public PlantResponse.plantSpecificInfoDTO savePlant(PlantRequest.PlantSaveDTO request) throws IOException {
        // 닉네임 null값 확인
        String nickName = request.getNickName()!=null ? request.getNickName() : "익명의 새싹";

        // 식물 이름 알아내기
        PlantResponse.plantBasicInfo plantBasicInfo = identifyPlant(request.getImageData());

        Plant plant = Plant.builder()
                .nickName(nickName)
                .longitude(request.getLongitude())
                .latitude(request.getLatitude())
                .plantName("개나리")
                .plantDescription("개나리는 쏼라쏼라")
                .disease("병명1")
                .isPlant(plantBasicInfo.isPlant())
                .isHealthy(plantBasicInfo.isHealthy())
                .imageDirectory(request.getImageDirectory())
                .build();


        plantRepository.save(plant);

        String s = fileService.saveBase64File(request.getImageData(), nickName);

        return PlantResponse.plantSpecificInfoDTO.builder()
                .id(plant.getId())
                .isPlant(plant.isPlant())
                .plantName(plant.getPlantName())
                .plantDescription(plant.getPlantDescription())
                .disease(plant.getDisease())
                .isHealthy(plant.isHealthy())
                .latitude(plant.getLatitude())
                .longitude(plant.getLongitude())
                .nickName(plant.getNickName())
                .imageDirectory(plant.getImageDirectory())
                .build();

    }

    public PlantResponse.plantBasicInfo identifyPlant(String imageData){
        PlantInformationResponse plantInformation = getPlantInformation(imageData);
        PlantDiseaseResponse plantDisease = getPlantDisease(imageData);

        PlantResponse.plantBasicInfo plantBasicInfo = PlantResponse.plantBasicInfo.builder()
                .isPlant(plantInformation.getResult().getIs_plant().isBinary())
                .plantName("스노우플라워")
                .plantDescription("쏼라쏼라")
                .isHealthy(plantDisease.getResult().getIs_healthy().isBinary())
                .build();

        return plantBasicInfo;
    }

    public PlantInformationResponse getPlantInformation(String imageData) {
        String url = "https://plant.id/api/v3/identification";

        // 올바른 HttpHeaders 임포트
        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-Key", "LIKiNOCxVUnZrFjQvx35Ao3xkWQbxaam6dduHaP64l7iKmb6fM");  // 필요시 Authorization 헤더 설정
        headers.set("Content-Type", "application/json");

        // 요청 Body 설정
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("images", new String[]{imageData});
        requestBody.put("similar_images", true);

        // HttpEntity 생성
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            // POST 요청 보내기
            ResponseEntity<PlantInformationResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, PlantInformationResponse.class);

            // 응답 상태 코드 확인
            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                PlantInformationResponse responseBody = responseEntity.getBody();
                // 응답 데이터 저장 및 반환
                return responseBody;
            } else {
                System.out.println("Error: " + responseEntity.getStatusCode());
                return null;
            }
        } catch (HttpClientErrorException e) {
            System.out.println("HTTP Error: " + e.getStatusCode());
            System.out.println("Response Body: " + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public PlantDiseaseResponse getPlantDisease(String imageData) {
        String url = "https://plant.id/api/v3/health_assessment";

        // 올바른 HttpHeaders 임포트
        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-Key", "LIKiNOCxVUnZrFjQvx35Ao3xkWQbxaam6dduHaP64l7iKmb6fM");  // 필요시 Authorization 헤더 설정
        headers.set("Content-Type", "application/json");

        // 요청 Body 설정
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("images", new String[]{imageData});
        requestBody.put("similar_images", true);

        // HttpEntity 생성
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            // POST 요청 보내기
            ResponseEntity<PlantDiseaseResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, PlantDiseaseResponse.class);

            // 응답 상태 코드 확인
            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
                PlantDiseaseResponse responseBody = responseEntity.getBody();
                // 응답 데이터 저장 및 반환
                return responseBody;
            } else {
                System.out.println("Error: " + responseEntity.getStatusCode());
                return null;
            }
        } catch (HttpClientErrorException e) {
            System.out.println("HTTP Error: " + e.getStatusCode());
            System.out.println("Response Body: " + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public PlantResponse.plantSpecificInfoDTO getSpecificPlant(Long id){
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new PlantHandler(ErrorStatus.PLANT_NOT_FOUND));



        return PlantResponse.plantSpecificInfoDTO.builder()
                .id(plant.getId())
                .imageDirectory(plant.getImageDirectory())
                .disease(plant.getDisease())
                .nickName(plant.getNickName())
                .latitude(plant.getLatitude())
                .longitude(plant.getLongitude())
                .plantDescription(plant.getPlantDescription())
                .plantName(plant.getPlantName())
                .isPlant(plant.isPlant())
                .isHealthy(plant.isHealthy())
                .build();
    }
}

