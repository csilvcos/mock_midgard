(ns midgard.diplomat.http-out
  (:require [schema.core :as s]
            [common-core.protocols.http-client :as http-client]))

(def bookmarks-settings
  {:fix-income
   [{:method       :get
     :schema-resps {200 {:customer s.customer/SimpleCustomer}}}]})


(s/defn invest-home :- s.w.income-report/WireIncomeReport
        [token :- s/Str
         http :- http-client/IHttpClient]
        (->> {:url     :fix-income
              :method  :get
              :authorization {:token token}}
             (http-client/req! http)
             :body
             :value))
