package com.translator.gwa.application.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.translator.gwa.application.resources.validation.LanguageCodeValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class TextTranslatorRequest {

    @JsonProperty("Text")
    @NotEmpty(message = "Parameter 'Text' is a required field.")
    private String[] translatorTexts;

    @JsonProperty("to")
    @NotEmpty(message = "Parameter 'to' is a required field.")
    @LanguageCodeValidator
    private String[] toLanguageCodes;
}
