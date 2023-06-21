package br.com.appetitegourmet.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.appetitegourmet.api.dto.PessoaResponse;
import br.com.appetitegourmet.api.models.Pessoa;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(target = "sexoExtenso", expression = "java(pessoa.isSexo() ? \"Masculino\" : \"Feminino\")")
    PessoaResponse pessoaToPessoaResponse(Pessoa pessoa);

}
