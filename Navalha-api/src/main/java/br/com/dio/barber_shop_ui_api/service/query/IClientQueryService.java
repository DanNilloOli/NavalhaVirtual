package br.com.dio.barber_shop_ui_api.service.query;

import br.com.dio.barber_shop_ui_api.entity.ClientEntity;

import java.util.List;

public interface IClientQueryService {

    ClientEntity findById(final String id);

    List<ClientEntity> list();

    void verifyName(final String name);

    void verifyPhone(final String phone);
    void verifyPhone(final String id, final String phone);

    void verifyEmail(final String email);
    void verifyEmail(final String id, final String email);
}
