package com.Tablely.Tablely.user.facede;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.Tablely.Tablely.user.dto.UserAddReqDto;
import com.Tablely.Tablely.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    @Transactional
    public void add(UserAddReqDto userAddReqDto) {
        userService.join(userAddReqDto.getName(), userAddReqDto.getEmail(), userAddReqDto.getUserType(), userAddReqDto.getPassword());

    }
}
