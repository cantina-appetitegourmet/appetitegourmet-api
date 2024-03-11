package br.com.appetitegourmet.api.spring.login.mapper;

import br.com.appetitegourmet.api.spring.login.payload.response.UserInfoResponse;
import br.com.appetitegourmet.api.spring.login.security.services.UserDetailsImpl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {
    UserDetailsMapper INSTANCE = Mappers.getMapper(UserDetailsMapper.class);
    UserInfoResponse UserDetailsImplToUserInfoResponse(UserDetailsImpl userDetailsImpl);
}
