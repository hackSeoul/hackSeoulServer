package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class BoardHandler extends GeneralException {
    public BoardHandler(BaseErrorCode code) {
        super(code);
    }
}
