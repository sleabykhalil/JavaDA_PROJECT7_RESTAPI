package com.nnk.springboot.domain.dto.mapper;

import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.domain.dto.MyUserDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-09T09:09:58+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class MyUserMapperImpl implements MyUserMapper {

    @Override
    public MyUserDto myUserToMyUserDte(MyUser myUser) {
        if ( myUser == null ) {
            return null;
        }

        MyUserDto myUserDto = new MyUserDto();

        myUserDto.setId( myUser.getId() );
        myUserDto.setUsername( myUser.getUsername() );
        myUserDto.setPassword( myUser.getPassword() );
        myUserDto.setFullname( myUser.getFullname() );
        myUserDto.setRole( myUser.getRole() );

        return myUserDto;
    }
}
