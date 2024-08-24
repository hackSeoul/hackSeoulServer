package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode code) {
        super(code);
    }
}
