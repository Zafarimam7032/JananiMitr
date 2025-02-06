package com.janani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.janani.entity.QuestionCategory;

@Repository
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Integer> {
	
    @Query("SELECT q FROM QuestionCategory q WHERE q.categoryId = 1")
    List<QuestionCategory> findCategory1();

    @Query("SELECT q FROM QuestionCategory q WHERE q.categoryId = 2")
    List<QuestionCategory> findCategory2();

    @Query("SELECT q FROM QuestionCategory q WHERE q.categoryId = 3")
    List<QuestionCategory> findCategory3();

}
