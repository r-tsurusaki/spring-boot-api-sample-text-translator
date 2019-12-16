package com.translator.gwa.application.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.translator.gwa.infrastructure.entity.TranslatorResponseData;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TextTranslatorResponse {

    @JsonProperty("data")
    private List<TranslatorResponseData> translatorResponseDataList;
}
