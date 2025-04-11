package br.com.dio.barber_shop_ui_api.service.impl;

import br.com.dio.barber_shop_ui_api.entity.ScheduleEntity;
import br.com.dio.barber_shop_ui_api.exception.NotFoundException;
import br.com.dio.barber_shop_ui_api.exception.ScheduleInUseException;
import br.com.dio.barber_shop_ui_api.repository.IScheduleRepository;
import br.com.dio.barber_shop_ui_api.service.query.IScheduleQueryService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private final IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(final String id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Agendamento com ID " + id + " não encontrado.")
        );
    }

    @Override
    public List<ScheduleEntity> findInMonth(final Instant startAt, final Instant endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(final Instant startAt, final Instant endAt) {
        if (repository.existsByStartAtAndEndAt(startAt, endAt)) {
            var message = "Já existe um cliente agendado no horário solicitado entre " + startAt + " e " + endAt + ".";
            throw new ScheduleInUseException(message);
        }
    }
}
