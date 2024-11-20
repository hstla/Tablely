package com.Tablely.Tablely.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.user.UserException;
import com.Tablely.Tablely.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {

    @InjectMocks
    UserQueryService userQueryService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("이메일 중복을 체크가 통과한다.")
    void duplicateSuccess() {
        //given when
        userQueryService.checkEmailDuplicated("test@email.com");

        //then
        verify(userRepository, times(1)).existsByEmail("test@email.com");
    }

    @Test
    @DisplayName("이메일 중복을 체크가 실패하여 USER_DUPLICATED 예외가 발생한다.")
    void duplicateFailed() {
        //given
        String email = "test@email.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // when then
        assertThatThrownBy(() -> userQueryService.checkEmailDuplicated(email))
            .isInstanceOf(UserException.class)
            .hasMessage(ErrorCode.USER_DUPLICATED.getDescription());
    }
}