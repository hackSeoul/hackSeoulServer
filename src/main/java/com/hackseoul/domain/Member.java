package com.hackseoul.domain;

import com.hackseoul.domain.common.BaseDateTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Member")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Column(name = "profile_image")
    private Integer profileImage;

    @Column(name = "refresh_token")
    private String refreshToken;


    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}

