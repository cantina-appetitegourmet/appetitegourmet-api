package br.com.appetitegourmet.api.mapper;

import br.com.appetitegourmet.api.dto.ContratoPlanoReq;
import br.com.appetitegourmet.api.dto.ContratoPlanoRequest;
import br.com.appetitegourmet.api.models.ContratoPlano;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratoPlanoMapper {
    ContratoPlanoMapper INSTANCE = Mappers.getMapper(ContratoPlanoMapper.class);

    ContratoPlano contratoPlanoReqtoContratoPlano(ContratoPlanoReq contratoPlanoReq);
    List<ContratoPlano> contratoPlanosRequestToContratosPlano(List<ContratoPlanoRequest> contratoPlanosReq);
}
