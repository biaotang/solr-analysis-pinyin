package com.macrowing.exception;

/**
 * @author	biao.tang
 * 2019年3月12日
 */
public class ConfigErrorException extends RuntimeException {
	private static final long serialVersionUID = -1646652186846793695L;
	private final String mesage;

    public ConfigErrorException(String message) {
        this.mesage=message;
    }
    public String getMessage() {
        return this.mesage;
    }
}
