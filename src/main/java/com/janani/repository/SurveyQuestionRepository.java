package com.janani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janani.entity.SurveyQuestion;

@Repository
public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Integer>{

    List<SurveyQuestion> findByCategoryId(Integer categoryId);

}
