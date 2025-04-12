package com.connectfour.repository;

import com.connectfour.entity.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatsRepository extends JpaRepository<UserStats, Long> {
    UserStats findByUserId(Long userId);
}
