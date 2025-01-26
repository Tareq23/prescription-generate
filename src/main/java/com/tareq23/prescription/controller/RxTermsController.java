package com.tareq23.prescription.controller;

import com.tareq23.prescription.dto.response.RxTermsResponse;
import com.tareq23.prescription.service.RxTermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RxTermsController {

    private final RxTermsService rxTermsService;

    @GetMapping("/rx-terms")
    public ResponseEntity<?> getRxTerms() {
        return ResponseEntity.ok(rxTermsService.getAllConcepts());
    }
}
