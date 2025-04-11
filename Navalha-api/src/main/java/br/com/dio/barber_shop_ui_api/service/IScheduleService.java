package br.com.dio.barber_shop_ui_api.service;

import br.com.dio.barber_shop_ui_api.entity.ScheduleEntity;

public interface IScheduleService {
    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final String id);
}
