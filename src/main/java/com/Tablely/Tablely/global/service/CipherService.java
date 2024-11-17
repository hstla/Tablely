package com.Tablely.Tablely.global.service;

public interface CipherService {
	String encrypt(String value);
	boolean match(String rawValue, String encryptedValue);
}
