package br.com.dio.barber_shop_ui_api.service.impl;

import br.com.dio.barber_shop_ui_api.entity.ClientEntity;
import br.com.dio.barber_shop_ui_api.exception.NotFoundException;
import br.com.dio.barber_shop_ui_api.exception.PhoneInUseException;
import br.com.dio.barber_shop_ui_api.repository.IClientRepository;
import br.com.dio.barber_shop_ui_api.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {

    private final IClientRepository repository;

    @Override
    public ClientEntity findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado o cliente com id " + id));
    }

    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
    }

    @Override
    public void verifyPhone(String phone) {
        if (repository.existsByPhone(phone)) {
            var message = "O telefone " + phone + " já está em uso.";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyPhone(final String id, final String phone) {
        var optional = repository.findByPhone(phone);
        if (optional.isPresent() && !Objects.equals(optional.get().getId(), id)) {
            var message = "O telefone " + phone + " já está em uso.";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(final String email) {
        if (repository.existsByEmail(email)) {
            var message = "O e-mail " + email + " já está em uso.";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(final String id, final String email) {
        var optional = repository.findByEmail(email);
        if (optional.isPresent() && !Objects.equals(optional.get().getId(), id)) {
            var message = "O e-mail " + email + " já está em uso.";
            throw new PhoneInUseException(message);
        }
    }
}
