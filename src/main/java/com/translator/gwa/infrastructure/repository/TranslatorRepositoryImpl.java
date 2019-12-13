package com.translator.gwa.infrastructure.repository;

import com.translator.gwa.domain.repository.TranslatorRepository;
import com.translator.gwa.infrastructure.configuration.TranslatorTextConfig;
import com.translator.gwa.infrastructure.entity.TranslatorRequest;
import com.translator.gwa.infrastructure.entity.TranslatorResponseData;
import com.translator.gwa.infrastructure.entity.TranslatorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class TranslatorRepositoryImpl implements TranslatorRepository {

    private TranslatorTextConfig translatorTextConfig;
    private RestTemplate restTemplate;

    /**
     * TextTranslatorController constructor.
     *
     * @param translatorTextConfig TranslatorTextConfig object.
     * @param restTemplateBuilder  RestTemplateBuilder object.
     */
    @Autowired
    public TranslatorRepositoryImpl(
            TranslatorTextConfig translatorTextConfig,
            RestTemplateBuilder restTemplateBuilder) {

        this.translatorTextConfig = translatorTextConfig;
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * ${inheritDoc}
     */
    @Override
    public List<TranslatorResponseData> translator(String[] translatorTexts, String[] toLanguageCodes)
            throws Exception {

        // リクエストを配列からリストに変換
        List<TranslatorRequest> translatorRequests = new ArrayList<>();
        for (String translatorText : translatorTexts) {
            translatorRequests.add(TranslatorRequest.builder().translatorText(translatorText).build());
        }

        // リクエストヘッダ
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add("Ocp-Apim-Subscription-Key", this.translatorTextConfig.getSubscriptionKey());

        // リクエストエンティティ
        RequestEntity requestEntity = RequestEntity
                .post(new URI(this.translatorTextConfig.getEndpoint() +
                        String.format("/translate?api-version=%s&to=%s",
                                this.translatorTextConfig.getApiVersion(), String.join(",", toLanguageCodes))))
                .headers(httpHeaders)
                .body(translatorRequests);

        try {
            // リクエスト
            ResponseEntity<TranslatorResponse[]> responseEntity =
                    this.restTemplate.exchange(requestEntity, TranslatorResponse[].class);
            TranslatorResponse[] translatorResponses = responseEntity.getBody();

            // レスポンスが空の場合エラー
            if (ObjectUtils.isEmpty(translatorResponses)) {
                throw new Exception("Failed to Translator Text API.");
            }

            // レスポンスからデータを抽出
            List<TranslatorResponseData> translatorResponseDataList = new ArrayList<>();
            for (TranslatorResponse translatorResponse : translatorResponses) {
                translatorResponseDataList.addAll(translatorResponse.getTranslatorResponseData());
            }

            return translatorResponseDataList;

        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new Exception("Failed to Translator Text API.");
        }
    }
}
