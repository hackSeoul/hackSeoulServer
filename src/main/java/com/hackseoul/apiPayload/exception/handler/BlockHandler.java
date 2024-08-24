package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class BlockHandler extends GeneralException {
    public BlockHandler(BaseErrorCode code) {
        super(code);
    }
}
