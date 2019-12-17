# spring-boot-api-sample
Translator Text APIを使用した、SpringBootのRESTfulAPIのAPIサンプルです。

リクエストパラメータの言語を自動検出して、指定した言語へ翻訳します。

# ローカル環境での起動手順
1. プロジェクトをSpring開発環境へcloneする

   - [STSの場合](http://tikemin.hatenablog.com/entry/2013/12/15/223508)
   - [IntelliJの場合](https://zenryokuservice.com/wp/2018/06/30/intellij-idea-git〜gitリポジトリからクローン〜/)
2. [こちら](https://docs.microsoft.com/ja-jp/azure/cognitive-services/translator/quickstart-translate?pivots=programming-language-java)を参考に「Translator Text」のリソースを作成する。
3. 作成したリソースのkeyを実行環境変数 "TRANSLATOR_TEXT_API_SUBSCRIPTION_KEY" に設定する。

   例： [EnvFileプラグイン](https://plugins.jetbrains.com/plugin/7861-envfile/)を使用して環境変数を読み込む。
   
4. Spring Bootアプリケーションを起動する。
5. http://localhost:8080/swagger-ui.html にアクセスする。

   Swagger2を実装しているので上記からUIで実行できる。
   
# I/F仕様
## リクエストパラメータ
| 項目 | 型 | 備考 |
|:-----|:---------|:-----------------------------------|
| Text | String[] | 翻訳したい文字列を配列で設定します。 |
| to   | String[] | 翻訳したい言語コードを配列で指定します。 |

※言語コードについては[こちら](https://github.com/r-tsurusaki/spring-boot-api-sample-text-translator/blob/develop/src/main/java/com/translator/gwa/application/contents/LanguageCode.java)を参照。

## レスポンスパラメータ
### 正常時
| 項目 | 型 | 備考 |
|:----------|:---------|:------------------------------|
| data      | String[] | 翻訳後のデータオブジェクト |
| data.to   | String   | 文字列変換後の言語コード |
| data.text | String   | 言語コードの言語で変換した文字列 |

### 異常時
| 項目 | 型 | 備考 |
|:--------|:-------|:------------------------------|
| xtrack  | String | TraceID |
| message | String | エラーメッセージ |

