package com.translator.gwa.application.controller;

import com.translator.gwa.application.resources.ErrorResponse;
import com.translator.gwa.application.resources.TextTranslatorRequest;
import com.translator.gwa.application.resources.TextTranslatorResponse;

import com.translator.gwa.domain.service.TextTranslatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextTranslatorController {

    private TextTranslatorService textTranslatorService;

    /**
     * TextTranslatorController constructor.
     *
     * @param textTranslatorService TextTranslatorService class.
     */
    @Autowired
    public TextTranslatorController(TextTranslatorService textTranslatorService) {

        this.textTranslatorService = textTranslatorService;
    }

    /**
     * Endpoint of text translator.
     *
     * @param textTranslatorRequest TextTranslatorRequest onject.
     * @return TextTranslatorResponse object
     * @throws Exception Internal Server Error.
     */
    @ApiOperation(value = "文字列を翻訳する。")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "翻訳文字列, 言語コード", response = TextTranslatorResponse.class),
            @ApiResponse(code = 400, message = "文字列が未設定、または不正な言語コード", response = ErrorResponse.class)})
    @RequestMapping(value = "/text-translator", method = RequestMethod.POST)
    public TextTranslatorResponse textTranslator(
            @ApiParam(name = "body", required = true, value = "翻訳文字列と翻訳後言語コード")
            @RequestBody @Validated TextTranslatorRequest textTranslatorRequest)
            throws Exception {

        return TextTranslatorResponse.builder()
                .xTrack(MDC.get("X-Track"))
                .translatorResponseDataList(this.textTranslatorService.textTranslator(
                        textTranslatorRequest.getTranslatorTexts(), textTranslatorRequest.getToLanguageCodes()))
                .build();
    }
}
