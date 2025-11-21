package com.example.api.controllers;

import com.example.api.models.RecommendationRequest;
import com.example.api.services.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {
    @GetMapping
    public ResponseEntity<String> getRecommendation(@RequestBody RecommendationRequest requirements){
        RecommendationService rs = new RecommendationService();
        String answer = rs.getRecommendation(
                requirements.getModel(),
                requirements.getFunctionalRequirements(),
                requirements.getNonFunctionalRequirements(),
                requirements.getAdditionalContext());
        return new ResponseEntity<>(answer,HttpStatus.OK);
    }
}
