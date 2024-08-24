package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class NotificationHandler extends GeneralException {

    public NotificationHandler(BaseErrorCode code) {
        super(code);
    }
}
