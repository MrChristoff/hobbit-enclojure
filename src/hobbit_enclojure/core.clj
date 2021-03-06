(ns hobbit-enclojure.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part)#"^left-" "right-")
   :size (:size part)})

; Initial function for creating the right side of the Hobbit:
; (defn symmetrize-body-parts
;   "Expects a sequence of maps, that have a :name and :size"
;   [asym-body-parts]
;   (loop [remaining-asym-parts asym-body-parts
;          final-body-parts[]]
;     (if (empty? remaining-asym-parts)
;      final-body-parts
;      (let [[part & remaining] remaining-asym-parts]
;        (recur remaining
;          (into final-body-parts
;            (set [part (matching-part part)])))))))


(defn symmetrize-body-parts
  "Better symmetrize function; expects a sequence of maps, that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(defn hit
  [asym-body-parts]
  (let [sym-parts (symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumated-size (:size part)]
      (if (> accumated-size target)
        part
        (recur remaining (+ accumated-size (:size (first remaining))))))))

(defn add-one-hundred
  [num]
  (+ num 100))

(defn dec-maker
  "create custom decrimenter"
  [dec-by]
  #(- % dec-by))

(def dec7 (dec-maker 7))
