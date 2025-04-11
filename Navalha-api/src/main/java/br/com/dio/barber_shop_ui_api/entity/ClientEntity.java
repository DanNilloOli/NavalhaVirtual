package br.com.dio.barber_shop_ui_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(
        name = "CLIENTS",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_EMAIL", columnNames = "email"),
                @UniqueConstraint(name = "UK_PHONE", columnNames = "phone")
        }
)

@Getter
@Setter
@ToString
public class ClientEntity {

    @Id
    private String id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 11, columnDefinition = "bpchar(11)")
    private String phone;

    @ToString.Exclude
    @OneToMany(mappedBy = "client", cascade = ALL, orphanRemoval = true)
    private Set<ScheduleEntity> schedules = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone);
    }
}
