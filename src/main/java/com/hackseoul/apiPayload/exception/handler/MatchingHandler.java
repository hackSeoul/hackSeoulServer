package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class MatchingHandler extends GeneralException {
    public MatchingHandler(BaseErrorCode code) {
        super(code);
    }
}
