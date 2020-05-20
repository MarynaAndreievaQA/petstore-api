package models;

import lombok.Builder;
import lombok.Getter;

    @Getter
    @Builder
    public class Order {
        private String id;
        private Integer petId;
        private Integer quantity;
        private String shipDate;
        private Status status;
        private String complete;
    }

