package br.com.dio.barber_shop_ui_api.service.query;

import br.com.dio.barber_shop_ui_api.entity.ScheduleEntity;

import java.time.Instant;
import java.util.List;

public interface IScheduleQueryService {

    ScheduleEntity findById(final String id);

    List<ScheduleEntity> findInMonth(final Instant startAt, final Instant endAt);

    void verifyIfScheduleExists(final Instant startAt, final Instant endAt);
}
