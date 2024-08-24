package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class ReportHandler extends GeneralException {
    public ReportHandler(BaseErrorCode code) {
        super(code);
    }
}
