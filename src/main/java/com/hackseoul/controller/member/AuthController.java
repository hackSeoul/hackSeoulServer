package com.hackseoul.controller.member;

import com.hackseoul.apiPayload.ApiResponse;
import com.hackseoul.dto.member.MemberRequest;
import com.hackseoul.service.member.AuthService;
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
@RequestMapping("/v1/member")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    @Operation(summary = "회원가입 API 입니다.", description = "API for join")
    public ApiResponse<String> joinMember(
            @RequestBody @Valid MemberRequest.JoinRequestDTO joinRequestDTO) {
        String email = joinRequestDTO.getEmail();
        String password = joinRequestDTO.getPassword();

        return ApiResponse.onSuccess("성공했습니다.");
    }


}
