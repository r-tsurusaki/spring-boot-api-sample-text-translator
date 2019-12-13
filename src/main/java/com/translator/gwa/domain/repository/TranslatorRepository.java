package com.translator.gwa.domain.repository;

import com.translator.gwa.infrastructure.entity.TranslatorResponseData;

import java.util.List;

public interface TranslatorRepository {

    /**
     * Translator text.
     *
     * @param translatorTexts translatorTexts.
     * @param toLanguageCodes To language codes.
     * @return translatorText
     * @throws Exception Internal Server Error.
     */
    List<TranslatorResponseData> translator(String[] translatorTexts, String[] toLanguageCodes) throws Exception;
}
