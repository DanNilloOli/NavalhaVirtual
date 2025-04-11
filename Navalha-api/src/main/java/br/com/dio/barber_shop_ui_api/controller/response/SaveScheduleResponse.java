package br.com.dio.barber_shop_ui_api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record SaveScheduleResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("startAt")
        Instant startAt,
        @JsonProperty("endAt")
        Instant endAt,
        @JsonProperty("clientId")
        String clientId
) {}
