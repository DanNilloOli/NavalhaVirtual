package br.com.dio.barber_shop_ui_api.repository;

import br.com.dio.barber_shop_ui_api.entity.ScheduleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface IScheduleRepository extends MongoRepository<ScheduleEntity, String> {
    List<ScheduleEntity> findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(
            final Instant startAt,
            final Instant endAt
    );

    boolean existsByStartAtAndEndAt(final Instant startAt, final Instant endAt);
}
