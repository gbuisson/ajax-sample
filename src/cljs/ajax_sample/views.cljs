(ns ajax-sample.views
  (:require
   [re-frame.core :as re-frame]
   [ajax-sample.subs :as subs]))

(defn input-panel []
  [:input {:on-key-up #(re-frame/dispatch
                        [:input-changed (some-> % .-currentTarget .-value)])}])

(defn output-panel []
  (let [text (re-frame/subscribe [::subs/text])]
    [:div (str @text)]))

(defn main-panel []
  [:div
   [input-panel]
   [output-panel]])
