package com.translator.gwa.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TranslatorRequest {

    @JsonProperty("Text")
    private String translatorText;
}
