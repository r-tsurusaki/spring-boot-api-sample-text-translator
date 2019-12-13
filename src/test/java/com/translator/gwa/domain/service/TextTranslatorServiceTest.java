package com.translator.gwa.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.translator.gwa.application.resources.TextTranslatorRequest;
import com.translator.gwa.domain.repository.TranslatorRepository;
import com.translator.gwa.infrastructure.entity.TranslatorResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TextTranslatorServiceTest {

    private TextTranslatorService textTranslatorService;

    @Mock
    private TranslatorRepository translatorRepository;

    private TextTranslatorRequest textTranslatorRequest;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.textTranslatorService = new TextTranslatorService(this.translatorRepository);

        this.textTranslatorRequest = TextTranslatorRequest.builder()
                .translatorTexts(new String[]{"test"})
                .toLanguageCodes(new String[]{"ja"})
                .build();
    }

    @Test
    void textTranslator() throws Exception {

        // Mock
        List<TranslatorResponseData> mockResponse = new ArrayList<>();
        mockResponse.add(TranslatorResponseData.builder()
                .text("テスト")
                .to("jp")
                .build());
        when(this.translatorRepository.translator(
                this.textTranslatorRequest.getTranslatorTexts(), this.textTranslatorRequest.getToLanguageCodes()))
                .thenReturn(mockResponse);

        // テスト実行
        List<TranslatorResponseData> actual = this.textTranslatorService.textTranslator(
                this.textTranslatorRequest.getTranslatorTexts(), this.textTranslatorRequest.getToLanguageCodes());

        // 結果検証
        assertEquals(mockResponse.size(), actual.size());
        assertEquals(mockResponse.get(0).getText(), actual.get(0).getText());
        assertEquals(mockResponse.get(0).getTo(), actual.get(0).getTo());
        verify(this.translatorRepository, times(1)).translator(any(), any());
    }

    @Test
    void throwsException() throws Exception {

        // Mock
        String errorMessage = "Internal Server Error.";
        doThrow(new Exception(errorMessage)).when(translatorRepository).translator(any(), any());

        try {
            // テスト実行
            this.textTranslatorService.textTranslator(
                    this.textTranslatorRequest.getTranslatorTexts(), this.textTranslatorRequest.getToLanguageCodes());
            fail("Successful completion.");

        } catch (Exception exception) {
            // 結果検証
            verify(this.translatorRepository, times(1)).translator(any(), any());
        }
    }
}