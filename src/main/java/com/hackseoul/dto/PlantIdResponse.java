package com.hackseoul.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PlantIdResponse {
    private String accessToken;
    private String modelVersion;
    private String customId;
    private InputData input;
    private Result result;
    private String status;
    private boolean slaCompliantClient;
    private boolean slaCompliantSystem;
    private double created;
    private double completed;

    @Data
    public static class InputData {
        private double latitude;
        private double longitude;
        private String health;
        private boolean similarImages;
        private List<String> images;
        private String datetime;
    }

    @Data
    public static class Result {
        private IsPlant isPlant;
        private IsHealthy isHealthy;
        private Disease disease;

        @Data
        public static class IsPlant {
            private double probability;
            private double threshold;
            private boolean binary;
        }

        @Data
        public static class IsHealthy {
            private double probability;
            private double threshold;
            private boolean binary;
        }

        @Data
        public static class Disease {
            private List<Suggestion> suggestions;

            @Data
            public static class Suggestion {
                private String id;
                private String name;
                private double probability;
                private List<SimilarImage> similarImages;
                private Map<String, String> details;

                @Data
                public static class SimilarImage {
                    private String id;
                    private String url;
                    private String licenseName;
                    private String licenseUrl;
                    private String citation;
                    private double similarity;
                    private String urlSmall;
                }
            }
        }
    }
}
