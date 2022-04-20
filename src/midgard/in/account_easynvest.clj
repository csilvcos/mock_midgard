(ns midgard.schemata.in.account-easynvest
  (:require [common-core.schema :as schema]
            [schema.core :as s]
            [common-core.schema :as cs]))

(s/defschema Account
            (schema/loose-schema
              {:id s/Uuid}))

(s/defschema Invest
             (cs/loose-schema
               {:id s/Uuid
                (s/optional-key :account) Account}))