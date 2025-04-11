package br.com.dio.barber_shop_ui_api.controller.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record SaveScheduleRequest(
        @JsonProperty("startAt")
        @NotNull(message = "A data e hora de início são obrigatórias")
        Instant startAt,

        @JsonProperty("endAt")
        @NotNull(message = "A data e hora de término são obrigatórias")
        Instant endAt,

        @JsonProperty("clientId")
        @NotBlank(message = "O ID do cliente é obrigatório")
        String clientId
) {}