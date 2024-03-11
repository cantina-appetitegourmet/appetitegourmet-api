package br.com.appetitegourmet.api.mapper;

import br.com.appetitegourmet.api.dto.ContratoRequest;
import br.com.appetitegourmet.api.dto.ContratoResponse;
import br.com.appetitegourmet.api.models.Contrato;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContratoMappler {
    ContratoMappler INSTANCE = Mappers.getMapper(ContratoMappler.class);

    Contrato contratoRequestToContrato(ContratoRequest contratoRequest);
    ContratoResponse contratoToContratoResponse(Contrato contratoResponse);
}
