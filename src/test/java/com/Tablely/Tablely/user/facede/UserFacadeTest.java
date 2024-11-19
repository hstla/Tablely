package com.Tablely.Tablely.user.facede;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.global.service.CipherService;
import com.Tablely.Tablely.user.UserException;
import com.Tablely.Tablely.user.domain.User;
import com.Tablely.Tablely.user.domain.UserType;
import com.Tablely.Tablely.user.dto.UserAddReqDto;
import com.Tablely.Tablely.user.repository.UserRepository;
import com.Tablely.Tablely.user.service.UserQueryService;
import com.Tablely.Tablely.user.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserFacadeTest {
	@InjectMocks
	UserFacade userFacade;

	@Mock
	UserService userService;
	@Mock
	UserQueryService userQueryService;
	@Mock
	CipherService oneWayCipherService;


	@Test
	@DisplayName("회원가입을 성공한다.")
	public void add() {
	    //given
		UserAddReqDto userReqDto = new UserAddReqDto("name", "test@email.com", UserType.CUSTOMER, "qwe123", "qwe123");
	    //when then
		assertDoesNotThrow(() -> userFacade.add(userReqDto));
	}

	@Test
	@DisplayName("이메일이 중복되어 회원가입에 실패한다.")
	public void addEmailDuplicated() {
		// given
		UserAddReqDto userReqDto = new UserAddReqDto("name", "test@email.com", UserType.CUSTOMER, "qwe123", "qwe123");

		String encryptedEmail = "test@email.com";
		doThrow(new UserException(ErrorCode.USER_DUPLICATED))
			.when(userQueryService).checkEmailDuplicated(encryptedEmail);
		doReturn(encryptedEmail).when(oneWayCipherService).encrypt(userReqDto.getEmail());

		// when & then
		assertThatThrownBy(() -> userFacade.add(userReqDto))
			.isInstanceOf(UserException.class)
			.hasMessage(ErrorCode.USER_DUPLICATED.getDescription());
	}
}