package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.MyUser;
import com.nnk.springboot.repositories.MyUserRepository;
import com.nnk.springboot.services.MyUserService;
import com.nnk.springboot.services.servicesImpl.MyUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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
        Optional<MyUser> myUser = Optional.of(new MyUser());
        myUser.get().setUsername("test");
        when(myUserRepositoryMock.findByUsername(anyString())).thenReturn(myUser);
        //when
        MyUser result = myUserServiceUnderTest.findUserByUsername("username");
        //then
        assertThat(result.getUsername()).isEqualTo("test");
    }

    @Test
    void findUserById() {
        //given
        Optional<MyUser> myUser = Optional.of(new MyUser());
        myUser.get().setId(1);
        when(myUserRepositoryMock.findById(1)).thenReturn(myUser);
        //when
        MyUser result = myUserServiceUnderTest.findById(1);
        //then
        assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    void findUserByIdWhenNotFoundReturnNull() {
        //given
        when(myUserRepositoryMock.findById(1)).thenReturn(Optional.empty());
        //when
        MyUser result = myUserServiceUnderTest.findById(1);
        //then
        assertThat(result).isNull();
    }

    @Test
    void findAll() {
        //given
        MyUser myUser = new MyUser(1, "userTest", "Password", "FullName", "Role_admin");
        List<MyUser> myUserList = new ArrayList<>();
        myUserList.add(myUser);
        when(myUserRepositoryMock.findAll()).thenReturn(myUserList);
        //when
        List<MyUser> result = myUserServiceUnderTest.findAll();
        //then
        assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    void add() {
        //given
        MyUser myUser = new MyUser(1, "userTest", "Password", "FullName", "Role_admin");
        when(myUserRepositoryMock.save(myUser)).thenReturn(myUser);
        //when
        MyUser result = myUserServiceUnderTest.add(myUser);
        //then
        assertThat(result.getId()).isEqualTo(myUser.getId());
    }

    @Test
    void update() {
        //given
        MyUser myUser = new MyUser(1, "userTest", "Password", "FullName", "Role_admin");
        when(myUserRepositoryMock.save(myUser)).thenReturn(myUser);
        //when
        MyUser result = myUserServiceUnderTest.update(myUser);
        //then
        assertThat(result.getId()).isEqualTo(myUser.getId());
    }

    @Test
    void delete() {
        //given
        MyUser myUser = new MyUser(1, "userTest", "Password", "FullName", "Role_admin");
        when(myUserRepositoryMock.findById(1)).thenReturn(Optional.of(myUser));
        doNothing().when(myUserRepositoryMock).deleteById(1);
        //when
        myUserServiceUnderTest.delete(1);
        //then
        verify(myUserRepositoryMock, times(1)).deleteById(1);
    }
}