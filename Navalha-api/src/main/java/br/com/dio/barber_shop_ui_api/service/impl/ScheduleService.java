package br.com.dio.barber_shop_ui_api.service.impl;

import br.com.dio.barber_shop_ui_api.entity.ScheduleEntity;
import br.com.dio.barber_shop_ui_api.repository.IScheduleRepository;
import br.com.dio.barber_shop_ui_api.service.IScheduleService;
import br.com.dio.barber_shop_ui_api.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(final ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());
        return repository.save(entity);
    }

    @Override
    public void delete(final String id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
