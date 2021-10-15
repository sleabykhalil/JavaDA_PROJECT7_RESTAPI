package com.nnk.springboot.servicesImpl;

import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.repositories.MyUserRepository;
import com.nnk.springboot.services.MyUserService;
import com.nnk.springboot.services.servicesImpl.MyUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyUserServiceImplTest {
    @Mock
    MyUserRepository myUserRepositoryMock;

    MyUserService myUserServiceUnderTest;

    @BeforeEach
    void setUp() {
        myUserServiceUnderTest = new MyUserServiceImpl(myUserRepositoryMock);
    }

    @Test
    void findUserByUsername() {
        //given
        MyUser myUser = new MyUser();
        myUser.setUsername("test");
        when(myUserRepositoryMock.findByUsername(anyString())).thenReturn(myUser);
        //when
        MyUser result = myUserServiceUnderTest.findUserByUsername("username");
        //then
        assertThat(result.getUsername()).isEqualTo("test");
    }

}