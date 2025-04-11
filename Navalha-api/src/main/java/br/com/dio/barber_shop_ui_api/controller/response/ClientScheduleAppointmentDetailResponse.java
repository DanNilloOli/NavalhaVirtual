package br.com.dio.barber_shop_ui_api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientScheduleAppointmentDetailResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("name")
        String name,
        @JsonProperty("email")
        String email,
        @JsonProperty("phone")
        String phone
) {}
