(ns midgard.controllers.investments_report
  (:require [schema.core :as s]
            [common-core.protocols.http-client :as http-client]))

(s/defn display_view!
  [customer-id :- s/Uuid
   reference=year :- s/Int
   http :- http-client/IHttpClient]
  (let [customer (http-out/customer customer-id http)
        investment (l.)]))
