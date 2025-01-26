package com.tareq23.prescription.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RxTermsResponse {
    private MinConceptGroup minConceptGroup;

    @Data
    public static class MinConceptGroup {
        private List<Content> minConcept;
    }

    @Data
    public static class Content {
        private String fullName;
        private String termType;
        private String rxcui;
    }
}

