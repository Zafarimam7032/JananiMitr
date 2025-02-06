package com.janani.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janani.entity.Image;

@Repository
public interface ImageReposiotry extends JpaRepository<Image, Integer> {
	
	boolean existsByRchIdAndClassificationAndCreatedAtBetween(Long rchId, String classification, Date sevenDaysAgo, Date currentDate);

}
