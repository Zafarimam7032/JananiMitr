package com.janani.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.janani.entity.CumulativeRiskScore;

@Repository
public interface CumulativeRiskScoreReposiotry extends JpaRepository<CumulativeRiskScore, Integer> {

    @Query("SELECT new map(crs.category as category, crs.finalRiskValue as finalRiskValue) FROM CumulativeRiskScore crs WHERE crs.rchId = :rchId AND crs.createdAt = CURRENT_DATE")
    List<Map<String, Object>> findCumulativeRiskScoresByRchIdAndCurrentDate(@Param("rchId") long rchId);
}
