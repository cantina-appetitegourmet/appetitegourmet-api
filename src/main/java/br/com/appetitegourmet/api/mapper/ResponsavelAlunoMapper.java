package br.com.appetitegourmet.api.mapper;

import br.com.appetitegourmet.api.dto.ResponsavelAlunoEditReq;
import br.com.appetitegourmet.api.dto.ResponsavelAlunoRequest;
import br.com.appetitegourmet.api.dto.ResponsavelAlunoResponse;
import br.com.appetitegourmet.api.models.ResponsavelAluno;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponsavelAlunoMapper {
    ResponsavelAlunoMapper INSTANCE = Mappers.getMapper(ResponsavelAlunoMapper.class);

    ResponsavelAluno responsavelAlunoRequestToResponsavelAluno(ResponsavelAlunoRequest responsavelAlunoRequest);
    ResponsavelAluno ResponsavelAlunoEditReqToResponsavelAluno(ResponsavelAlunoEditReq ResponsavelAlunoEditReq);
    List<ResponsavelAlunoResponse> responsavelAlunosToResponsavelAlunosResponse(List<ResponsavelAluno> responsavelAlunos);
}
