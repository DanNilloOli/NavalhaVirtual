package br.com.dio.barber_shop_ui_api.service.impl;

import br.com.dio.barber_shop_ui_api.entity.ClientEntity;
import br.com.dio.barber_shop_ui_api.repository.IClientRepository;
import br.com.dio.barber_shop_ui_api.service.IClientService;
import br.com.dio.barber_shop_ui_api.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    private final IClientRepository repository;
    private final IClientQueryService queryService;

    @Override
    public ResponseEntity<?> registro(ClientEntity clienteCadastro, String barberError) {
        if (clienteCadastro.getName() == null || clienteCadastro.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("O nome precisa ser preenchido");
        }

        if (clienteCadastro.getEmail() == null || clienteCadastro.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("O email precisa ser preenchido");
        }

        if (clienteCadastro.getPhone() == null || clienteCadastro.getPhone().isEmpty()) {
            return ResponseEntity.badRequest().body("O telefone precisa ser preenchido");
        }

        HttpStatus status = barberError.equals("/cadastro") ? HttpStatus.CREATED : HttpStatus.OK;
        return new ResponseEntity<>(repository.save(clienteCadastro), status);
    }

    @Override
    public ClientEntity save(final ClientEntity entity) {
        queryService.verifyEmail(entity.getId(), entity.getEmail());
        queryService.verifyPhone(entity.getId(), entity.getPhone());

        return repository.save(entity);
    }

    @Override
    public ClientEntity update(final ClientEntity entity) {
        queryService.verifyName(entity.getId());
        queryService.verifyEmail(entity.getId(), entity.getEmail());
        queryService.verifyPhone(entity.getId(), entity.getPhone());

        var stored = queryService.findById(entity.getId());
        stored.setName(entity.getName());
        stored.setEmail(entity.getEmail());
        stored.setPhone(entity.getPhone());
        return repository.save(stored);
    }

    @Override
    public void delete(final String id) {
        queryService.findById(id);
        repository.deleteById(id);
    }

    @Override
    public Iterable<ClientEntity> listar() {
        return repository.findAll();
    }
}
