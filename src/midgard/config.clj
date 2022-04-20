(ns midgard.config
  (:require [clojure.string :as string]
            [common-core.protocols.config :as protocols.config]
            [common-core.time :as time]
            [common-core.types.time :as t-time]
            [schema.core :as s]))

(defn version [config] (protocols.config/get! config [:version]))


