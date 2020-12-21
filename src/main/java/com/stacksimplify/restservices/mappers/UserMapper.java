package com.stacksimplify.restservices.mappers;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //user to userDTOS
    @Mappings({
            @Mapping(source = "email", target = "emailAddress"),
            @Mapping(source = "role", target = "roleName")
    })
    UserMsDto userToUserMsDto(User user);

    //Users to List<UsersDtos>

    List<UserMsDto> usersToUserDtos(List<User> users);

}
