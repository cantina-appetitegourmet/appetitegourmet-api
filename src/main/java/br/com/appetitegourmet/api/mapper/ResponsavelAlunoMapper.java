package br.com.appetitegourmet.api.mapper;

import br.com.appetitegourmet.api.dto.ResponsavelAlunoRequest;
import br.com.appetitegourmet.api.models.ResponsavelAluno;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResponsavelAlunoMapper {
    ResponsavelAlunoMapper INSTANCE = Mappers.getMapper(ResponsavelAlunoMapper.class);

    ResponsavelAluno responsavelAlunoRequestToResponsavel(ResponsavelAlunoRequest responsavelAlunoRequest);
}
