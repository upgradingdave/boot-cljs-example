(ns up.simple
  (:require [reagent.core :as r]))

(defn widget [data]
  (let [msg (get @data :message "Hmm, something's not right")]
    [:div.container
     [:div.col-10
      [:div.alert.alert-success msg]
      [:form
       [:div.form-group
        [:label "Message: "]
        [:input.form-control {:type "text"
                              :value msg
                              :on-change #(swap! data assoc-in [:message] 
                                                 (.-value (.-target %)))}]]]]]))

(def data (r/atom {:message "Simple Text Box Example"}))

(defn ^:export main []
  (if-let [node (.getElementById js/document "simple")]
    (r/render-component [widget data] node)))
