package com.example.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequest {
    private String model;
    private String[] functionalRequirements;
    private String[] nonFunctionalRequirements;
    private String additionalContext;
}
