(defproject api-mock "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Proprietary"}

  :dependencies [[org.clojure/clojure "1.10.3"]

                 [common-core        "15.43.0"]
                 [common-crypto      "10.31.1"]
                 [common-datomic     "10.1.0"]
                 [common-db          "25.2.1"]
                 [common-finagle     "11.5.0"]
                 [common-http-client "15.16.0"]
                 [common-io          "51.2.0"]
                 [common-kafka       "13.9.0"]
                 [common-metrics     "10.8.0"]
                 [common-repl        "0.4.0"]
                 [common-i18n        "4.28.0"]

                 [org.jsoup/jsoup                                     "1.14.3"]
                 [com.openhtmltopdf/openhtmltopdf-core                "1.0.10"]
                 [com.openhtmltopdf/openhtmltopdf-pdfbox              "1.0.10"]
                 [com.openhtmltopdf/openhtmltopdf-rtl-support         "1.0.10"]
                 [com.openhtmltopdf/openhtmltopdf-svg-support         "1.0.10"]
                 [com.openhtmltopdf/openhtmltopdf-jsoup-dom-converter "1.0.0"]]

  :plugins [[lein-cloverage "1.2.2"]
            [lein-vanity "0.2.0"]
            [s3-wagon-private "1.3.5"]
            [lein-ancient "0.7.0"]
            [lein-nsorg "0.3.0"]]

  :repositories [["central" {:url "https://repo1.maven.org/maven2/" :snapshots false}]
                 ["clojars" {:url "https://clojars.org/repo/"}]
                 ["nu-maven" {:url "s3p://nu-maven/releases/" :snapshots false :sign-releases false}]]

  :exclusions [log4j]

:repl-options {:init-ns api-mock.core}
  :test-paths ["test/unit" "test/integration" "postman/"])
