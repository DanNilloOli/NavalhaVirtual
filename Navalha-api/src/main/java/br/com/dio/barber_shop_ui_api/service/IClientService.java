package br.com.dio.barber_shop_ui_api.service;

import br.com.dio.barber_shop_ui_api.entity.ClientEntity;
import org.springframework.http.ResponseEntity;

public interface IClientService {
    
    ResponseEntity<?> registro(ClientEntity clienteCadastro, String barberError);

    ClientEntity save(final ClientEntity entity);

    ClientEntity update(final ClientEntity entity);

    void delete(final String id);

    Iterable<ClientEntity> listar();
}
