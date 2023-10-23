package com.miu.flightmanagement.airlinebookingservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalErrorResponse {

    private String message;
    private String errorStatus;
}
