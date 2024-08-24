package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class MannerHandler extends GeneralException {
    public MannerHandler(BaseErrorCode code) {
        super(code);
    }
}
