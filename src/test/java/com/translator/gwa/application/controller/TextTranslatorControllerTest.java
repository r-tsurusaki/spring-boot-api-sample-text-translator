package com.translator.gwa.application.controller;

import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.translator.gwa.application.resources.TextTranslatorRequest;
import com.translator.gwa.domain.service.TextTranslatorService;
import com.translator.gwa.infrastructure.entity.TranslatorResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
class TextTranslatorControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TextTranslatorController textTranslatorController;

    @Mock
    private TextTranslatorService textTranslatorService;

    private static final String ENDPOINT = "/text-translator";

    @BeforeEach
    void setup() {

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.textTranslatorController).build();
    }

    @Test
    void textTranslator() throws Exception {

        // TODO tracerがnullになる

        // リクエストパラメータ
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(TextTranslatorRequest.builder()
                .translatorTexts(new String[]{"test"})
                .toLanguageCodes(new String[]{"ja"})
                .build());

        // ServiceのMock
        List<TranslatorResponseData> response = new ArrayList<>();
        response.add(TranslatorResponseData.builder()
                .text("テスト")
                .to("jp")
                .build());
        when(this.textTranslatorService.textTranslator(any(), any())).thenReturn(response);

        // テスト実行
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is("done!")))
                .andExpect(jsonPath("data[0].text", is("テスト")))
                .andExpect(jsonPath("data[0].to", is("jp")));
    }

    @Test
    void badRequest() throws Exception {

        // リクエストパラメータ
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(TextTranslatorRequest.builder()
                .toLanguageCodes(new String[]{"ja"})
                .build());

        // テスト実行
        this.mockMvc.perform(MockMvcRequestBuilders
                .post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
}