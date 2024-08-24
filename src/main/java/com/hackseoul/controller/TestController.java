package com.hackseoul.controller;

import com.hackseoul.apiPayload.ApiResponse;
import com.hackseoul.apiPayload.code.status.ErrorStatus;
import com.hackseoul.apiPayload.exception.handler.TempHandler;
import com.hackseoul.domain.Member;
import com.hackseoul.service.member.ProfileService;
import com.hackseoul.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class TestController {

    @GetMapping("/test/hello")
    @Operation(summary = "swagger 테스트용 API 입니다.", description = "simple API for swagger test!")
    public String hello() {
        return "Swagger Setting Success%%!";
    }

    @GetMapping("/test/error")
    @Operation(summary = "에러 통일 테스트용 API 입니다.", description = "simple API for API Error Response!")
    public String apiResponseTest() {
        throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }


}
