package com.hackseoul.apiPayload.exception.handler;

import com.hackseoul.apiPayload.code.BaseErrorCode;
import com.hackseoul.apiPayload.exception.GeneralException;

public class PlantHandler extends GeneralException {
    public PlantHandler(BaseErrorCode code) {
        super(code);
    }
}
