package com.Tablely.Tablely.global.service;

import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OneWayCipherService implements CipherService {
	@Override
	public String encrypt(String value) {
		return BCrypt.hashpw(value, BCrypt.gensalt());
	}

	@Override
	public boolean match(String rawValue, String encryptedValue) {
		return BCrypt.checkpw(rawValue, encryptedValue);
	}
}
