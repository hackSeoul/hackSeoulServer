package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class SocketHandler extends GeneralException {

    public SocketHandler(BaseErrorCode code) {
        super(code);
    }
}
