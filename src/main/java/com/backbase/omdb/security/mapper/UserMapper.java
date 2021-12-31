package com.backbase.omdb.security.mapper;


import com.backbase.omdb.security.entity.UserEntity;
import com.backbase.omdb.security.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User convertToModel(UserEntity userEntity);

}
