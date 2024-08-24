package com.hackseoul.dto.member;

import lombok.*;

import java.util.List;

public class MemberResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class blockedMemberDTO {

        Long memberId;
        Integer profileImg;
        String email;
        String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class LoginResponseDTO {

        String accessToken;
        String refreshToken;
        Integer profileImage;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class RefreshTokenResponseDTO {

        String accessToken;
        String refreshToken;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class myProfileMemberDTO {

        Long id;
        Integer profileImg;
        String email;
        String updatedAt;
    }


}
