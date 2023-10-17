package com.miu.flightmanagement.authorizationservice.web.error;

public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287165L;

    public UserNotExistException() {
        super();
    }

    public UserNotExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserNotExistException(final String message) {
        super(message);
    }

    public UserNotExistException(final Throwable cause) {
        super(cause);
    }}
