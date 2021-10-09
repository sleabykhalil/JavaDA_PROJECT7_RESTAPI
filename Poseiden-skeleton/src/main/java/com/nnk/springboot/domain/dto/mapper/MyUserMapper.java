package com.nnk.springboot.domain.dto.mapper;
import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.domain.dto.MyUserDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MyUserMapper {
     MyUserDto myUserToMyUserDte(MyUser myUser) ;
}
