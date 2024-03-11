package br.com.appetitegourmet.api.spring.login.mapper;

import br.com.appetitegourmet.api.spring.login.models.User;
import br.com.appetitegourmet.api.spring.login.payload.request.SignupRequest;
import br.com.appetitegourmet.api.spring.login.payload.response.UserInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User signupRequestToUserMapper(SignupRequest SignupRequest);
    UserInfoResponse userToUserInfoResponse(User SignupRequest);
}
