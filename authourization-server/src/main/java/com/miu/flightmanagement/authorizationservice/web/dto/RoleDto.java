package com.miu.flightmanagement.authorizationservice.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    private String name;
}
