(ns nukes.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm little nuke"))


(defn flow-control
  "Control flow example, else branch is OPTIONAL"
  [a]
  (if a
    "The passed parameter is truthy"
    "The passed parameter is falsey"))


(defn the-do
  "Do operator wraps up multiple forms in parentheses and run each of then"
  [num]
  (if (= (mod num 2) 0)
    (do
      (println "Given number is even")
      "Well done")
    (do
      (println "Given number is odd")
      "Good luck next time ;)")
    ))

(defn the-when
  "'when' it's like 'if' but with no else branch. Use it when you what to do many things when a cond is true and you whant to return nil if cond is false"
  [something]
  (when something
    (println "Something was truthy evaluated")
    "Yaaas"))

(defn is-nil
  "Checks is something is nil"
  [something]
  (nil? something))


(defn is-equals
  "Checks whether two things are equal or not"
  [thing1, thing2]
  (= thing1 thing2))


; Amazing online moba game nicknames
(def awesome-moba-nicknames ["Arufonsekun" "Nukes Nofura"])

(defn greet-person
  "Greets the person given a person code"
  [person-code]
  (str "Be very welcome sir " 
       (if (= person-code 1)
         (first awesome-moba-nicknames)
         (second awesome-moba-nicknames))))