(ns midgard.service
  (:require [common-core.misc :as misc]
            [common-core.schema :as schema]
            [schema.core :as s]
            [common-finagle.interceptors.context :as finagle]
            [common-io.interceptors.adapt :as adapt]
            [common-io.interceptors.auth :as auth]
            [common-io.interceptors.doc :as doc]
            [common-io.interceptors.errors :as errors]
            [common-io.interceptors.identity :as identity]
            [common-io.interceptors.instrument :as instrument]
            [common-io.interceptors.logging :as logging]
            [common-io.interceptors.pedestal-exporter :as pedestal-exporter]
            [common-io.interceptors.tracing :as tracing]
            [common-io.interceptors.visibility :as visibility]
            [common-io.interceptors.wire :as wire]
            [io.pedestal.http.route :as route]
            [midgard.config :as config]))

(def version-schema {:version s/Str})

(defn current-version
  [{{config :config} :component}]
  {:status 200 :body {:version (config/version config)}})

(def discovery-endpoint
  {:send_investments "/v1/home/mobile"})

(def discovery-schema
  (schema/loose-schema (misc/map-vals (constantly String) discovery-endpoint)))

(defn get-product-invest
  [{customer-id :uuid
    {:keys [reference-year]} :data
    {:keys [http s3-attachments config prometheus]} :component}]
  ())

(def common-interceptors
  [(instrument/instrument)
   (visibility/cid)
   (errors/catch-externalize)
   wire/to-wire
   (errors/catch)
   (pedestal-exporter/pedestal-exporter)
   (tracing/pedestal-tracing)
   wire/from-wire
   (identity/add-identity)
   (finagle/context)
   (logging/log)])

(def route
  (route/expand-routes
    #{["/api/v1"
       :get (conj common-interceptors
                  (doc/desc "Current version")
                  (auth/public)
                  (adapt/externalize! {200 version-schema})
                  current-version)
       :route-name :version]

      ["/v1/home/mobile" :get (conj common-interceptors
                                    (doc/desc "get product at /invest for the customer")
                                    (adapt/path-id->uuid)
                                    (auth/allow? #{"savings-admin" "investments-admin"})
                                    (adapt/coerce! {:reference-year s/Int}
                                                   get-product-invest))]}))
