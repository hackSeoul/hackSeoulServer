package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class PageHandler extends GeneralException {
    public PageHandler(BaseErrorCode code) {
        super(code);
    }
}
