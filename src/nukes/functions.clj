(ns nukes.functions
  (:require [clojure.string :as string]))


;; Multiple arity function
(defn multiple-arity-func
  "Example of multiple arity function"
  ([x y z]
   (println "Three arguments were passed"))
  ([x y]
   (println "Two arguments were passed"))
  ([x]
   (println "U'v passed 1 arguments to the function"))
  ([]
   (println "U'v passed 0 arguments to the function, shame ling ling")))


;; Use multiple arity to set default value to a parameter
(defn default-value
  "Example of default value using multiple arity"
  ([x y]
   (+ x y))
  ([x]
   (default-value x 0)))

(defn greet
  "Greet people"
  [person-name]
  (str "Greetings " person-name ", be welcome!"))

;; Variable-arity functions (rest parameter)
(defn greet-people
  "Gets N people names and greets all of them"
  [ & people]
  (map greet people))

(defn favorite-things
  [name & things]
  (str "Hi, " name " here are your favorite things: "
       (string/join ", " things) "."))

;; Destructuring
(defn classifier
  [[best-choice second-choice & unimportant-choices]]
  (str "Your best choice is: " best-choice "\n"
       "Your second choice is: " second-choice "\n"
       "Your uninportant choices is: "
       (string/join ", " unimportant-choices)))

;; Map destructuring
(defn mordor-location
  [{lat :lat lng :lng}]
;;[{:keys [lat lng]}]
  (str
   (str "Mordor latitude: " lat)
   "\n"
   (str "Mordor longitude: " lng)))

;; Anonymous functions
(defn double-it
  [numbers]
  (map (fn [n] (* n 2)) numbers))
  

;; Another way of defining a anonymous func
;; %1, %2, %3 -> fst, snd and thd parameter
;; %& -> rest parameter
(defn half-it
  [numbers]
  (map double (map #(/ % 2) numbers)))

;; Returning function - clojures

(defn inc-maker
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

;; Task: create a hobbit hitting tool

;; Hobbit model from the book "Clojure for the Brave and True"
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

;; def (global) and let(local, new scope) statements

(def dalmatian-list ["Pongo" "Perdita" "Cruelus" "Pombas"])

(let [dalmatians (take 2 dalmatian-list)] dalmatians)

;; Using rest parameters with let
(let [[pongo & the-rest] dalmatian-list] [pongo the-rest])

(defn loop-example
  [upper-bound]
  (loop [i 0]
    (println (str "i = " i))
    (if (> i upper-bound)
      (println "Bye ;)")
      (recur (inc i)))))

;; Recursive version of (loop-example) but using func
;; Always use loop 'cause performance is better

(defn loop-func
  ([upper-bound]
   (loop-func 0 upper-bound))
  ([iter upper-bound]
   (println (str "iter: " iter))
   (if (> iter (- upper-bound 1))
     (println "Bye")
     (loop-func (inc iter) upper-bound))))

;; Regex
;; form: 
;;      #"regex-string"
(defn body-part-matcher
  [body-part]
  { :name (clojure.string/replace (:name body-part) #"^left-" "right-") :size (:size body-part) })

(defn hobbit-symmetrizer
  [hobbit-body-parts]
  (loop [remainig-parts hobbit-body-parts
         symetrized-parts []]
    (if (empty? remainig-parts)
      symetrized-parts
      (let [[head & tail] remainig-parts]
        (recur tail
               (into symetrized-parts 
                     (set [head (body-part-matcher head)])))))))


;; Same as hobbit-symmetrizer but using reduce instead
(defn hobbit-symmetrizer-reduce
  [hobbit-body-parts]
  (reduce (fn [symetrized-parts body-part] ;; reduce function
            (into symetrized-parts (set [body-part (body-part-matcher body-part)])))
          [] ;; reduce initial value
          hobbit-body-parts ;; reduce collection
          ))