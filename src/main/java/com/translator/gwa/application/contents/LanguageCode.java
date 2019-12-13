package com.translator.gwa.application.contents;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@SuppressWarnings("SpellCheckingInspection")
public enum LanguageCode {

    /**
     * 言語コード
     * https://docs.microsoft.com/ja-jp/azure/cognitive-services/translator/language-support
     */
    AF("af"),               // アフリカーンス語
    AR("ar"),               // アラビア語
    BN("bn"),               // ベンガル語
    BS("bs"),               // ボスニア語 (ラテン)
    BG("bg"),               // ブルガリア語
    YUE("yue"),             // 広東語 (繁体字)
    CA("ca"),               // カタルニア語
    ZH_HANS("zh-Hans"),     // 中国語 (簡体字)
    ZH_HANT("zh-Hant"),     // 中国語 (繁体字)
    HR("hr"),               // クロアチア語
    CS("cs"),               // チェコ語
    DA("da"),               // デンマーク語
    NL("nk"),               // オランダ語
    EN("en"),               // 英語
    ET("et"),               // エストニア語
    FJ("fj"),               // フィジー語
    FIL("fil"),             // フィリピン語
    FI("fi"),               // フィンランド語
    FR("fr"),               // フランス語
    DE("de"),               // ドイツ語
    EL("el"),               // ギリシャ語
    HT("ht"),               // ハイチ・クレオール語
    HE("he"),               // ヘブライ語
    HI("hi"),               // ヒンディー語
    MWW("mww"),             // ミャオ語
    HU("hu"),               // ハンガリー語
    IS("is"),               // アイスランド語
    ID("id"),               // インドネシア語
    IT("it"),               // イタリア語
    JP("ja"),               // 日本語
    SW("sw"),               // スワヒリ語
    TLH("tlh"),             // クリンゴン語
    TLH_QAAK("tlh-Qaak"),   // クリンゴン語 (plqaD)
    KO("ko"),               // 韓国語
    LV("lv"),               // ラトビア語
    LT("lt"),               // リトアニア語
    MG("mg"),               // マダガスカル語
    MS("ms"),               // マレー語
    MT("mt"),               // マルタ語
    MI("mi"),               // マオリ語
    NB("nb"),               // ノルウェー語
    FA("fa"),               // ペルシャ語
    PL("pl"),               // ポーランド語
    PT("pt"),               // ポルトガル語
    OTQ("otq"),             // オトミ語
    RO("ro"),               // ルーマニア語
    RU("ru"),               // ロシア語
    SM("sm"),               // サモア語
    SR_CYRL("sr-Cyrl"),     // セルビア語 (キリル文字)
    SR_LATN("sr-Latn"),     // セルビア語 (ラテン)
    SK("sk"),               // スロバキア語
    SL("sl"),               // スロベニア語
    ES("es"),               // スペイン語
    SV("sv"),               // スウェーデン語
    TY("ty"),               // タヒチ語
    TA("ta"),               // タミル語
    TE("te"),               // テルグ語
    TH("th"),               // タイ語
    TO("to"),               // トンガ語
    TR("tr"),               // トルコ語
    UK("uk"),               // ウクライナ語
    UR("ur"),               // ウルドゥー語
    VI("vi"),               // ベトナム語
    CY("cy"),               // ウェールズ語
    YUA("yua");             // ユカテコ語

    private String code;
}
