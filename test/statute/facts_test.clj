(ns statute.facts-test
  (:require [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest prt-has-spec-basis
  (let [sb (facts/spec-basis "PRT")]
    (is (= 3 (count sb)))
    (is (every? #(re-find #"dre\.pt|diariodarepublica\.pt" (:statute/url %)) sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["PRT" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["prt.labour-code-2009"]
         (mapv :statute/id (facts/by-topic "PRT" :labor))))
  (is (empty? (facts/by-topic "PRT" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
