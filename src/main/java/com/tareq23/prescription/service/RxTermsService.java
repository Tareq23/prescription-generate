package com.tareq23.prescription.service;

import com.tareq23.prescription.dto.response.RxTermsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RxTermsService {

    private final RestTemplate restTemplate;

    @Value("${third-party-api.rx-term.all-concept}")
    private String API_URL;

    public List<RxTermsResponse.Content> getAllConcepts() {
        return Objects.requireNonNull(restTemplate.getForObject(API_URL, RxTermsResponse.class)).getMinConceptGroup().getMinConcept();
    }
}
