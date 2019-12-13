package com.translator.gwa.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TranslatorResponse {

    @JsonProperty("translations")
    private List<TranslatorResponseData> translatorResponseData;
}
