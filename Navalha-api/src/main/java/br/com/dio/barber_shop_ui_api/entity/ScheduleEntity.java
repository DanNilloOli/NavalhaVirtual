package br.com.dio.barber_shop_ui_api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;

@Document(collection = "schedules")
@Getter
@Setter
@ToString
public class ScheduleEntity {

    @Id
    private String id;

    private Instant startAt;
    private Instant endAt;

    @ToString.Exclude
    @DBRef
    private ClientEntity client;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleEntity that = (ScheduleEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(startAt, that.startAt) && Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startAt, endAt);
    }
}
