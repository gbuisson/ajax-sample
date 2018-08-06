(ns ajax-sample.events
  (:require
   [re-frame.core :as re-frame]
   [ajax-sample.db :as db]
   [cljs.core.async :refer [<! go]]
   [cljs-http.client :as client]))

(defn update-text
  [{:keys [db]} [_ new-text]]
  (let [transformed
        (:body
         (go (<! (client/get (:url db)
                             {:query-params {:text new-text}}))))]

    {:db (assoc db :text transformed)}))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-fx
 :input-changed
 update-text)
