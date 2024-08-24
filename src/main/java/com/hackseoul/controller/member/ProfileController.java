package com.hackseoul.controller.member;

import com.hackseoul.apiPayload.ApiResponse;
import com.hackseoul.converter.MemberConverter;
import com.hackseoul.domain.Member;
import com.hackseoul.dto.member.MemberResponse;
import com.hackseoul.service.member.ProfileService;
import com.hackseoul.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
@Slf4j
public class ProfileController {

    private final ProfileService profileService;


    @Operation(summary = "내 프로필 조회 API 입니다. (jwt 토큰 O)", description = "API for looking up member with jwt")
    @GetMapping("/profile")
    public ApiResponse<MemberResponse.myProfileMemberDTO> getMemberJWT() {
        Long memberId = JWTUtil.getCurrentUserId();

        Member myProfile = profileService.findMember(memberId);

        return ApiResponse.onSuccess(MemberConverter.toMyProfileDTO(myProfile));
    }

    @Operation(summary = "다른 회원 프로필 조회 API 입니다. (jwt 토큰 X)", description = "API for looking up member")
    @GetMapping("/profile/other")
    public ApiResponse<MemberResponse.myProfileMemberDTO> getMember(@RequestParam("id") Long memberId) {
        Member myProfile = profileService.findMember(memberId);

        return ApiResponse.onSuccess(MemberConverter.toMyProfileDTO(myProfile));
    }

}
