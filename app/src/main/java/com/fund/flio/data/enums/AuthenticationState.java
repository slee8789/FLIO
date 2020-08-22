package com.fund.flio.data.enums;

public enum AuthenticationState {
    NONE,        // Initial state
    UNAUTHENTICATED,        // Initial state, the user needs to authenticate
    AUTHENTICATED,        // The user has authenticated successfully
    INVALID_AUTHENTICATION,  // Authentication failed
    KAKAO_EMAIL_NEED_AGREE  // Authentication failed
}
