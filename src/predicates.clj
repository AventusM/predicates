(ns predicates)

(defn
  sum-f
  [f g x]
  (+ (f x) (g x))
  )

(sum-f inc dec 4)
(sum-f inc identity 5)
(sum-f identity - 10)

(defn
  less-than
  [n]
  (fn [k] (< k n))
  )

;muuttuja k tulee taulukon alkioista
(filter (less-than 3) [1 2 3 4 5])
(filter (less-than 4) [-2 12 3 4 0])

(defn
  equal-to
  [n]
  (fn [k] (== k n))
  )

(filter (equal-to 2) [2 1 3 2.0])
(filter (equal-to 2) [3 4 5 6])

(defn
  set->predicate
  [a-set]
  (fn [x] (contains? a-set x))
  )

(filter (set->predicate #{1 2 3}) [0 2 4 6])
(filter (set->predicate #{1 2 3 nil}) [2 nil 4 nil 6])

;Ohjeiden seuraaminen tuo tulosta.. who knew?
(defn
  pred-and
  [pred1 pred2]
  (fn [x] (and (pred1 x) (pred2 x)))
  )

(filter (pred-and pos? even?) [1 2 -4 0 6 7 -3])
(filter (pred-and pos? odd?) [1 2 -43 0 6 7 -3])
(filter (pred-and (complement nil?) empty?) [[] '() nil {} #{}])

(defn
  pred-or
  [pred1 pred2]
  (fn [x] (or (pred1 x) (pred2 x)))
  )

(filter (pred-or pos? odd?) [1 2 -4 0 6 7 -3])
(filter (pred-or pos? even?) [1 2 -4 0 6 7 -3])

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn
  blank?
  [string]
  (every? whitespace? string)
  )

(blank? " \t\n\t ")
(blank? "  \t a")
(blank? "")

(defn
  has-award?
  [book award]
  (contains? (:awards book) award)
  )

(defn HAS-ALL-THE-AWARDS?
  [book awards]
  (every? (fn [x] (has-award? book x)) awards)
  )


;intuitio - tarkistetaan filtteröidyn joukon koko
;ongelma - first tyyliset metodit
;1. korjaus (or) - ei onnistu (molemmin päin)
;2. korjaus - vaihdetaan filter mappiin
(defn
  my-some
  [pred a-seq]
  (map (fn [x] (pred x)) a-seq)
  )

(my-some even? [1 3 5 7])
(my-some even? [1 3 5 7 8])
(my-some nil? [1 nil 2])
(my-some first [[false] [1]])

(defn my-every? [pred a-seq]
  :-)

(defn prime? [n]
  :-)
;^^
