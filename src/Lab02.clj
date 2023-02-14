(ns Lab02)
(require 'clojure.string)


(defn add-values-for-keys [hashmap setOfKeys]
  (reduce + (map #(hashmap % 0) setOfKeys)))

(defn min1 [coll]
  (loop [c coll curMin (first coll)]
    (if (empty? (rest c))
      (min curMin (first coll))

      (recur (rest c) (min (first c) curMin)))))

(defn take1 [num coll]
  (loop [n num c coll acc []]
    (if (or (<= n 0) (empty? c))
      acc
      (recur (- n 1) (rest c) (conj acc (first c)))
      )
    )
  )

(defmacro safe-nth [coll n]
  `(if (and (>= ~n 0) (< ~n (count ~coll))) (nth ~coll ~n)))

; Examples of the threading macro:
; First n perfect squares function
(defn firstNSquares [n]
  (->>
    ; Start with an infinite sequence
    (range)
    ; Take the first n numbers
    (take n)
    ; Square each one and return the list
    (map #(* % %))
    )
  )

(defn simonSays [s]
  (->>
    ; Take the inputted string
    s
    ; Trim it, getting rid of all spaces at the ends.
    (clojure.string/trim)
    ; Make simon shout what he is saying by changing the string from the previous step to uppercase
    (clojure.string/upper-case)
    ; Add simon says to the beginning.
    (str "Simon says, ")
    )
  )

; As you can see above, the threading macro allows me to write code in a way that feels intuitive,
; almost like how one would think of solving a problem in their head. It works by taking the first value we pass
; to the macro, applying it as the first argument to the next function, and continuing this process until the end.
; So, in the first example, (range) gives us an infinite sequence which we use as the argument to the next function, 'take'.
; we then continue this process downwards.

(defn add-values-for-keys [hashmap setOfKeys]
  (reduce + (map #(hashmap % 0) setOfKeys)))

(defn add-values-for-keys [hashmap setOfKeys]
  (->>
    setOfKeys
    (map #(hashmap % 0))
    (reduce +)
    ))

