package br.com.dio.barber_shop_ui_api.controller;

import br.com.dio.barber_shop_ui_api.controller.request.SaveScheduleRequest;
import br.com.dio.barber_shop_ui_api.controller.response.SaveScheduleResponse;
import br.com.dio.barber_shop_ui_api.controller.response.ScheduleAppointmentMonthResponse;
import br.com.dio.barber_shop_ui_api.entity.ScheduleEntity;
import br.com.dio.barber_shop_ui_api.mapper.IScheduleMapper;
import br.com.dio.barber_shop_ui_api.service.IScheduleService;
import br.com.dio.barber_shop_ui_api.service.query.IScheduleQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.YearMonth;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("schedules")
@AllArgsConstructor
public class ScheduleController {

    private final IScheduleService service;
    private final IScheduleQueryService queryService;
    private final IScheduleMapper mapper;

    @PostMapping
@ResponseStatus(CREATED)
SaveScheduleResponse save(@RequestBody @Valid SaveScheduleRequest request) {
    try {
        System.out.println("=== RECEBENDO REQUISIÇÃO PARA CRIAR AGENDAMENTO ===");
        System.out.println("ClientId: " + request.clientId());
        System.out.println("StartAt: " + request.startAt());
        System.out.println("EndAt: " + request.endAt());
        
        System.out.println("=== MAPEANDO PARA ENTIDADE ===");
        var entity = mapper.toEntity(request);
        
        System.out.println("=== ENTIDADE APÓS MAPEAMENTO ===");
        System.out.println("ID: " + entity.getId());
        System.out.println("StartAt: " + entity.getStartAt());
        System.out.println("EndAt: " + entity.getEndAt());
        System.out.println("Cliente: " + (entity.getClient() != null ? "ID=" + entity.getClient().getId() : "null"));
        
        System.out.println("=== SALVANDO ENTIDADE ===");
        service.save(entity);
        System.out.println("=== ENTIDADE SALVA COM SUCESSO ===");
        
        return mapper.toSaveResponse(entity);
    } catch (Exception e) {
        System.out.println("=== ERRO AO CRIAR AGENDAMENTO ===");
        System.out.println("Tipo de exceção: " + e.getClass().getName());
        System.out.println("Mensagem: " + e.getMessage());
        e.printStackTrace();
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar agendamento: " + e.getMessage(), e);
    }
}

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final String id) {
        service.delete(id);
    }

    @PostMapping("month")
    ScheduleAppointmentMonthResponse listMonth(@RequestBody final ScheduleRequest request) {
        try {
            var yearMonth = YearMonth.of(request.getYear(), request.getMonth());
            var startAt = yearMonth.atDay(1)
                .atTime(0, 0, 0, 0)
                .atOffset(UTC)
                .toInstant();
            var endAt = yearMonth.atEndOfMonth()
                .atTime(23, 59, 59, 999_999_999)
                .atOffset(UTC)
                .toInstant();

            var entities = (List<ScheduleEntity>) queryService.findInMonth(startAt, endAt);
            if (entities.isEmpty()) {
                System.out.println("Sem agendamentos para o mês " + request.getYear() + "/" + request.getMonth());
            }

            return mapper.toMonthResponse(request.getYear(), request.getMonth(), entities);

        } catch (Exception e) {

            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor", e);
        }
    }

    public static class ScheduleRequest {
        private int year;
        private int month;
        
        public int getYear() {
            return year;
        }
        
        public void setYear(int year) {
            this.year = year;
        }
        
        public int getMonth() {
            return month;
        }
        
        public void setMonth(int month) {
            this.month = month;
        }
    }
}
