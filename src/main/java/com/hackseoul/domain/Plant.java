package com.hackseoul.domain;

import com.hackseoul.domain.common.BaseDateTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Plant")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Plant extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    private Long id;

    @Column(name = "nickName", nullable = false, length = 100)
    private String nickName = "default";

    @Column(name = "plantName", length = 100)
    private String plantName;

    @Column(name = "plantDescription", length = 1000)
    private String plantDescription;

    @Column(name = "isHealthy", nullable = false)
    private boolean isHealthy;

    @Column(name = "isPlant", nullable = false)
    private boolean isPlant;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "disease")
    private String disease;

}

