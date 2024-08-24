package com.hackseoul.service;

import com.hackseoul.dto.PlantDiseaseResponse;
import com.hackseoul.dto.PlantIdentificationRequest;
import com.hackseoul.dto.PlantResponse;
import com.hackseoul.dto.PlantInformationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlantService {

    private final RestTemplate restTemplate;


    public PlantResponse.plantInfoDTO identifyPlant(PlantIdentificationRequest request){
        PlantInformationResponse plantInformation = getPlantInformation(request);
        PlantDiseaseResponse plantDisease = getPlantDisease(request);

        PlantResponse.plantInfoDTO plantInfoDTO = PlantResponse.plantInfoDTO.builder()
                .isPlant(plantInformation.getResult().getIs_plant().isBinary())
                .plantName("스노우플라워")
                .plantDescription("쏼라쏼라")
                .isHealthy(plantDisease.getResult().getIs_healthy().isBinary())
                .build();

        return plantInfoDTO;
    }

    public PlantInformationResponse getPlantInformation(PlantIdentificationRequest request) {
        String url = "https://plant.id/api/v3/identification";

        // 올바른 HttpHeaders 임포트
        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-Key", "IbarnlEs3XQe0t5SNWNhVJsZOL6WTJpgAfJVWVJku3rdgsuyh0");  // 필요시 Authorization 헤더 설정
        headers.set("Content-Type", "application/json");

        // 요청 Body 설정
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("images", new String[]{request.getImageData()});
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

    public PlantDiseaseResponse getPlantDisease(PlantIdentificationRequest request) {
        String url = "https://plant.id/api/v3/health_assessment";

        // 올바른 HttpHeaders 임포트
        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-Key", "IbarnlEs3XQe0t5SNWNhVJsZOL6WTJpgAfJVWVJku3rdgsuyh0");  // 필요시 Authorization 헤더 설정
        headers.set("Content-Type", "application/json");

        // 요청 Body 설정
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("images", new String[]{request.getImageData()});
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
}

