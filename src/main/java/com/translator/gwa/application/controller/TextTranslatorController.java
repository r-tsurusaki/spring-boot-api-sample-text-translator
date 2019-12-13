package com.translator.gwa.application.controller;

import com.translator.gwa.application.resources.TextTranslatorRequest;
import com.translator.gwa.application.resources.TextTranslatorResponse;

import com.translator.gwa.domain.service.TextTranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
     * @param httpServletRequest    HttpServletRequest object.
     * @return TextTranslatorResponse object
     * @throws Exception Internal Server Error.
     */
    @RequestMapping(value = "/text-translator", method = RequestMethod.POST)
    public TextTranslatorResponse textTranslator(@RequestBody @Validated TextTranslatorRequest textTranslatorRequest,
                                                 HttpServletRequest httpServletRequest) throws Exception {

        return TextTranslatorResponse.builder()
                .message("done!")
                .translatorResponseDataList(this.textTranslatorService.textTranslator(
                        textTranslatorRequest.getTranslatorTexts(), textTranslatorRequest.getToLanguageCodes()))
//                .requestId(httpServletRequest.getAttribute("requestId").toString())
                .build();
    }
}
