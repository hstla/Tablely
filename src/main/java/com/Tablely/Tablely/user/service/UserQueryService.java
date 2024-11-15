package com.Tablely.Tablely.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Tablely.Tablely.user.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserQueryService {
    @Autowired
    private UserRepository userRepository;

    //

}
