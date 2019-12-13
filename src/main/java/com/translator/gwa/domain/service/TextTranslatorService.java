package com.translator.gwa.domain.service;

import com.translator.gwa.domain.repository.TranslatorRepository;
import com.translator.gwa.infrastructure.entity.TranslatorResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextTranslatorService {

    private TranslatorRepository translatorRepository;

    /**
     * TextTranslatorController constructor.
     *
     * @param translatorRepository TranslatorRepository object.
     */
    @Autowired
    public TextTranslatorService(TranslatorRepository translatorRepository) {

        this.translatorRepository = translatorRepository;
    }

    /**
     * Translator text.
     *
     * @param translatorTexts translatorTexts.
     * @param toLanguageCodes To language codes.
     * @return TranslatorResponseData list.
     * @throws Exception Internal Server Error.
     */
    public List<TranslatorResponseData> textTranslator(String[] translatorTexts, String[] toLanguageCodes)
            throws Exception {

        return this.translatorRepository.translator(translatorTexts, toLanguageCodes);
    }
}
