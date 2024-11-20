package com.Tablely.Tablely.user.service;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.Tablely.Tablely.user.domain.User;
import com.Tablely.Tablely.user.domain.UserType;
import com.Tablely.Tablely.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("회원가입에 성공한다.")
    void add() {
        //given when
        userService.join("name", "test@email.com", UserType.CUSTOMER, "qwe123");

        //then
        verify(userRepository, times(1)).save(any(User.class));
    }
}