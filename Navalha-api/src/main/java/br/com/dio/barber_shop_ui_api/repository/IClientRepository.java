package br.com.dio.barber_shop_ui_api.repository;

import br.com.dio.barber_shop_ui_api.entity.ClientEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends MongoRepository<ClientEntity, String> {

    boolean existsByName(final String name);
    boolean existsByEmail(final String email);
    boolean existsByPhone(final String phone);

    Optional <ClientEntity> findByName(final String name);
    Optional <ClientEntity> findByEmail(final String email);
    Optional <ClientEntity> findByPhone(final String phone);
}
