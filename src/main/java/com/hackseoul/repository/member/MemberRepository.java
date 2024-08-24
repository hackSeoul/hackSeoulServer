package com.hackseoul.repository.member;

import com.hackseoul.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);


    Optional<Member> findById(Long id);

    Optional<Member> findByRefreshToken(String refresh_token);
    List<Member> findAllByIdBetween(Long startId, Long endId);

    boolean existsByEmail(String email);
}
