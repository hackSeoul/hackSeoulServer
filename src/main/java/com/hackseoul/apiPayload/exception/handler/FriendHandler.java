package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class FriendHandler extends GeneralException {

    public FriendHandler(BaseErrorCode code) {
        super(code);
    }
}
