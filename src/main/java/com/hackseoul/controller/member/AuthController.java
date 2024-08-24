package com.hackseoul.controller.member;

import com.hackseoul.apiPayload.ApiResponse;
import com.hackseoul.converter.MemberConverter;
import com.hackseoul.domain.Member;
import com.hackseoul.dto.member.MemberRequest;
import com.hackseoul.dto.member.MemberResponse;
import com.hackseoul.service.member.AuthService;
import com.hackseoul.util.JWTUtil;
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
    public ApiResponse<MemberResponse.myProfileMemberDTO> joinMember(
            @RequestBody @Valid MemberRequest.JoinRequestDTO joinRequestDTO) {
        String email = joinRequestDTO.getEmail();
        String password = joinRequestDTO.getPassword();
        Member member = authService.joinMember(email, password);

        return ApiResponse.onSuccess(MemberConverter.toMyProfileDTO(member));
    }

    @PostMapping("/refresh")
    @Operation(summary = "refresh token을 통한 access, refresh token 재발급 API 입니다.", description = "API for Refresh Token")
    public ApiResponse<Object> refreshTokens(
            @RequestBody MemberRequest.RefreshTokenRequestDTO refreshTokenRequestDTO) {

        String refreshToken = refreshTokenRequestDTO.getRefreshToken();

        MemberResponse.RefreshTokenResponseDTO refreshTokenResponseDTO = authService.verifyRefreshToken(
                refreshToken);

        return ApiResponse.onSuccess(refreshTokenResponseDTO);
    }

}
