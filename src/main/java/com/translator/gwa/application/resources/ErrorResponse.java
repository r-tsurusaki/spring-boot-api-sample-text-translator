package com.translator.gwa.application.resources;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

    private String xTrack;
    private String message;
}
