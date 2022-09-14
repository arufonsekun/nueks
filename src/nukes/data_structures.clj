(ns nukes.data-structures)

; Maps: hash-maps & sorted-maps

; 1. Simple map
(def simple-map-name {:first-name "Nukes" :middle-name "" :last-name "Nofura"})

; 2. Nested map
(def nested-map-name {:name {:first "Nukes" :middle "" :last "Nofura"}})

; 3. Another way of constructing a map
(def alphabet (hash-map :a 1 :b 2 :c 3 :d 4))

(defn full-name
  "Gets full name"
  []
  (str (:first-name simple-map-name) " " (:last-name simple-map-name)))

; Getting values from maps
(defn get-char-order
  "Get the ascending order of a char"
  [char-key]
  (get alphabet char-key (str "Given " char-key " was not found in alphabet")))

; Example of get-in (usefull for nested maps)
(defn get-name
  "Get keys values from nested maps"
  [key-name-vec]
  (get-in nested-map-name key-name-vec))

; Example of treating map as a function and key as param
(defn get-full-name
  "Get full name from simple map"
  []
  (str (simple-map-name :first-name) " " (simple-map-name :last-name)))


; Vectors

(def simple-vector [2 1 4 3 5 0])

(def epic-names (vector "Arufonsekun" "Nukes Nofura" "Aranis"))

(defn simple-get 
  "Get value from vector"
  [index]
  (get simple-vector index "Default value"))

(defn append
  "Add a value into a vector"
  [epic-name]
  (conj epic-names epic-name))

; Lists

(def simple-list '(2 0 1 4 3 8))

(def epic-list-of-dogs (list "Boris"))

(defn list-get
  "List get using nth, throw exception in case index is out of bounds. (slower than vector get)"
  [index]
  (nth simple-list index "Default Value"))

(defn insert
  "List conj func, insert elements at the beginning"
  [dog]
  (conj epic-list-of-dogs dog))


; Sets

; Allow duplicated keys
(def simple-set (hash-set 2 1 1 1 2 3 4 5 4 6 6 ))

; Not allow duplicated keys
(def even-more-simple-set #{1 4 6 3 2 5})

(defn set-append
  "Adds values to set"
  [value]
  (conj simple-set value))

(defn is-in
  "Checks if a value is in the set"
  [value]
  (contains? simple-set value))