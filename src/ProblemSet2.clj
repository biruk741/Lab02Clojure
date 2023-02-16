(ns ProblemSet2)

(defn fib-nth [nth]
  (cond
    (= nth 0) 0
    (= nth 1) 1
    :else (loop [i 1  n2 0 n1 1]
            (if (= i nth)                                   ; continue until i reaches nth
              n1
              (recur (+ i 1) n1 (+ n2 n1))))
    )
  )

; Helper function for the count-seq function below
(defn count-helper [seq count]
  (if (not (seqable? seq))
    0
    (reduce (fn [acc s] (+ acc
                           (if (seqable? s)
                             (inc (count-helper s 0))
                             0)))
            count
            seq)))

(defn count-seqs [seq]
  (count-helper seq 1)) ; Start at 1 to count the first outer sequence. This will still return 0 if the outer element is not a sequence.



(defn sort-by-strlen [seq]
  (sort #(compare (count %1) (count %2)) seq)
  )

; already sorted seq
;(sort-by-strlen ["dog", "cat", "bird", "fish"])
;=> ("dog" "cat" "bird" "fish")

; some elements of equal length
;(sort-by-strlen ["aaa", "b", "cc", "d", "aaa", "eee"])
;=> ("b" "d" "cc" "aaa" "aaa" "eee")

;(sort-by-strlen [])
;=> ()

; Elements of equal length
;(sort-by-strlen ["car", "bed", "cat", "and", "dog"])
;=> ("car" "bed" "cat" "and" "dog")

(defn sort-seq-hashmaps [seq]
  (sort #(compare (%1 :age) (%2 :age)) seq)
  )


;(sort-seq-hashmaps [{:name "Alice" :age 30 :hometown "New York"}])
;=> ({:name "Alice", :age 30, :hometown "New York"})

;(sort-seq-hashmaps [{:name "Bob" :age 25 :hometown "Chicago"}
;                    {:name "Charlie" :age 32 :hometown "San Francisco"}
;                    {:name "Alice" :age 20 :hometown "New York"}])

;=>
;({:name "Alice", :age 20, :hometown "New York"}
; {:name "Bob", :age 25, :hometown "Chicago"}
; {:name "Charlie", :age 32, :hometown "San Francisco"})

;(sort-seq-hashmaps [])
;=> ()

;(sort-seq-hashmaps [{:name "Alice" :age 27 :hometown "New York"}
;                    {:name "Bob" :age 32 :hometown "San Francisco"}
;                    {:name "Charlie" :age 24 :hometown "Boston"}
;                    {:name "David" :age 38 :hometown "Seattle"}
;                    {:name "Emily" :age 29 :hometown "Chicago"}
;                    ])
;=>
;({:name "Charlie", :age 24, :hometown "Boston"}
; {:name "Alice", :age 27, :hometown "New York"}
; {:name "Emily", :age 29, :hometown "Chicago"}
; {:name "Bob", :age 32, :hometown "San Francisco"}
; {:name "David", :age 38, :hometown "Seattle"})

(defn sort-bigintegers [seq]
  (sort #(.compareTo %1 %2) seq)
  )

;(sort-bigintegers [(BigInteger. "10") (BigInteger. "20") (BigInteger. "15")])
;=> (10 15 20)

;(sort-bigintegers [(BigInteger. "9223372036854775807") (BigInteger. "-9223372036854775808")])
;=> (-9223372036854775808 9223372036854775807)

;(sort-bigintegers [(BigInteger. "10151") (BigInteger. "213516130") (BigInteger. "112462462464265") (BigInteger. "-1513565") (BigInteger. "-347473755")])
;=> (-347473755 -1513565 10151 213516130 112462462464265)

;(sort-bigintegers [(BigInteger. "1") (BigInteger. "-1") (BigInteger. "0")])
;=> (-1 0 1)

(defmacro impl [p q]
  `(if (not ~p) true ~q))