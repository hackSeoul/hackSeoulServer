package com.hackseoul.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlantInformationResponse {

    private String access_token;
    private String model_version;
    private String custom_id;
    private Input input;
    private Result result;
    private String status;
    private boolean sla_compliant_client;
    private boolean sla_compliant_system;
    private double created;
    private double completed;

    @Data
    public static class Input {
        private double latitude;
        private double longitude;
        private boolean similar_images;
        private List<String> images;
        private String datetime;
    }

    @Data
    public static class Result {
        private Is_plant is_plant;
        private Classification classification;

        @Data
        public static class Is_plant {
            private double probability;
            private boolean binary;
            private double threshold;
        }

        @Data
        public static class Classification {
            private List<Suggestion> suggestions;

            @Data
            public static class Suggestion {
                private String id;
                private String name;
                private double probability;
                private List<Similar_image> similar_images;
                private Details details;

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

                @Data
                public static class Details {
                    private String language;
                    private String entity_id;
                }
            }
        }
    }
}
