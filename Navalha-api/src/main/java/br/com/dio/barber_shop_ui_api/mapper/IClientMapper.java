package br.com.dio.barber_shop_ui_api.mapper;

import br.com.dio.barber_shop_ui_api.controller.request.SaveClientRequest;
import br.com.dio.barber_shop_ui_api.controller.response.SaveClientResponse;
import br.com.dio.barber_shop_ui_api.entity.ClientEntity;
import br.com.dio.barber_shop_ui_api.controller.request.UpdateClientRequest;
import br.com.dio.barber_shop_ui_api.controller.response.ClientDetailResponse;
import br.com.dio.barber_shop_ui_api.controller.response.ListClientResponse;
import br.com.dio.barber_shop_ui_api.controller.response.UpdateClientResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final ClientEntity entity);

    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final String id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final ClientEntity entity);

    ClientDetailResponse toDetailResponse(final ClientEntity entity);

    List<ListClientResponse> toListResponse(final List<ClientEntity> entities);
}
