package com.example.exception.handler;

import com.example.exception.GeneralException;
import com.example.payload.status.BaseStatus;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseStatus status) {
        super(status);
    }
}