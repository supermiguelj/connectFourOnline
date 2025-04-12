package com.connectfour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectfour.entity.UserStats;

@Repository
public interface UserStatsRepository extends JpaRepository<UserStats, String> {
    
}
