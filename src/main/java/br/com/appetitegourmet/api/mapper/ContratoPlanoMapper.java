package br.com.appetitegourmet.api.mapper;

import br.com.appetitegourmet.api.dto.ContratoPlanoReq;
import br.com.appetitegourmet.api.models.ContratoPlano;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContratoPlanoMapper {
    ContratoPlanoMapper INSTANCE = Mappers.getMapper(ContratoPlanoMapper.class);

    ContratoPlano contratoPlanoReqtoContratoPlano(ContratoPlanoReq contratoPlanoReq);
}
