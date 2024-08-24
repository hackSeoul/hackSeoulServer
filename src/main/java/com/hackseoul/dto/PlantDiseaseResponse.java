package com.hackseoul.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PlantDiseaseResponse {
    private String access_token;
    private String model_version;
    private String custom_id;
    private Input_data input;
    private Result result;
    private String status;
    private boolean sla_compliant_client;
    private boolean sla_compliant_system;
    private double created;
    private double completed;

    @Data
    public static class Input_data {
        private double latitude;
        private double longitude;
        private String health;
        private boolean similar_images;
        private List<String> images;
        private String datetime;
    }

    @Data
    public static class Result {
        private Is_plant is_plant;
        private Is_healthy is_healthy;
        private Disease disease;

        @Data
        public static class Is_plant {
            private double probability;
            private double threshold;
            private boolean binary;
        }

        @Data
        public static class Is_healthy {
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
                private List<Similar_image> similar_images;
                private Map<String, String> details;

                @Data
                public static class Similar_image {
                    private String id;
                    private String url;
                    private String license_name;
                    private String license_url;
                    private String citation;
                    private double similarity;
                    private String url_small;
                }
            }
        }
    }
}
