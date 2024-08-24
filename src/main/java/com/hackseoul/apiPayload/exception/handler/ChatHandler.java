package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class ChatHandler extends GeneralException {

    public ChatHandler(BaseErrorCode code) {
        super(code);
    }
}
