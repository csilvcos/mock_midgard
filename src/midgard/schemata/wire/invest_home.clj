(ns midgard.schemata.wire.invest-home
  (:require [schema.core :as s]
            [common-core.schema :as cs]))

(def Invest-home
  {:id s/Uuid
   :title s/Str
   :description s/Str
   :image s/Str
   :tracking s/Str})

(s/defschema SimpleCustomer (cs/loose-schema Invest-home))