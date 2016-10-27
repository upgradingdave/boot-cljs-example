(ns up.core
  (:require [reagent.core :as r]))

(defn hello [data]
  (let [msg (get @data :message "Hmm, something's not right")]
    [:div.alert.alert-success msg]))

(def data (r/atom {:message "Hello World!"}))

(defn ^:export main []
  (if-let [node (.getElementById js/document "core")]
    (r/render-component [hello data] node)))

;;(main)

