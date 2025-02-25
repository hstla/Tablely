package com.Tablely.Tablely.user.facade;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Tablely.Tablely.global.exception.ErrorCode;
import com.Tablely.Tablely.global.jwt.JwtUserInfo;
import com.Tablely.Tablely.global.service.CipherService;
import com.Tablely.Tablely.user.UserException;
import com.Tablely.Tablely.user.domain.User;
import com.Tablely.Tablely.user.domain.UserType;
import com.Tablely.Tablely.user.dto.UserJoinReqDto;
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
		UserJoinReqDto userReqDto = new UserJoinReqDto("name", "test@email.com", UserType.CUSTOMER, "qwe123", "qwe123");
		//when then
		assertDoesNotThrow(() -> userFacade.add(userReqDto));
	}

	@Test
	@DisplayName("이메일이 중복되어 회원가입에 실패한다.")
	public void addEmailDuplicated() {
		// given
		UserJoinReqDto userReqDto = new UserJoinReqDto("name", "test@email.com", UserType.CUSTOMER, "qwe123", "qwe123");

		doThrow(new UserException(ErrorCode.USER_DUPLICATED))
			.when(userQueryService).checkEmailDuplicated("test@email.com");

		// when & then
		assertThatThrownBy(() -> userFacade.add(userReqDto))
			.isInstanceOf(UserException.class)
			.hasMessage(ErrorCode.USER_DUPLICATED.getDescription());
	}

	// 로그인에 성공한다.
	@Test
	@DisplayName("로그인에 성공한다.")
	public void loginSuccess() throws Exception {
		//given
		String email = "test@email.com";
		String password = "1234";
		User mockUser = new User(email, password, "name1",UserType.CUSTOMER);

		when(userQueryService.findByEmail(email)).thenReturn(mockUser);
		when(oneWayCipherService.match(password, mockUser.getPassword())).thenReturn(true);

		//when
		JwtUserInfo loginUser = userFacade.login("test@email.com", password);

		//then
		assertThat(loginUser.getUserType()).isEqualTo(mockUser.getUserType());
	}

	// 로그인에 실패한다.
	@Test
	@DisplayName("비밀번호가 달라 INVALID_PASSWORD(비밀번호가 일치하지 않습니다.)에러를 발생시켜 로그인에 실패한다.")
	public void loginFail() throws Exception {
		//given
		String email = "test@email.com";
		String password = "1234";
		User mockUser = new User(email, password, "name1",UserType.CUSTOMER);

		when(userQueryService.findByEmail(email)).thenReturn(mockUser);
		when(oneWayCipherService.match(password, mockUser.getPassword())).thenReturn(false);

		//when && then
		assertThatThrownBy(() -> userFacade.login(email, password))
			.isInstanceOf(UserException.class)
			.hasMessage(ErrorCode.INVALID_PASSWORD.getDescription());

	}
}