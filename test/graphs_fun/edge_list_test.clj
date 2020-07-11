(ns graphs-fun.edge-list-test
  (:require [clojure.test :refer :all]
            [graphs-fun.edge-list :refer :all]))

(def acyclic
  ;;        a
  ;;       / \
  ;;      b   c
  ;;     /   / \
  ;;    d   e   f
  ;;   /         \
  ;;  g           h
  #{[:a :b] [:a :c]
    [:b :a] [:c :a]
    [:b :d] [:c :e] [:c :f]
    [:d :b] [:e :c] [:f :c]
    [:d :g] [:f :h]
    [:g :d] [:h :f]})

(deftest transform
  (testing "transform edge list to adjacency list"
    (is (= {:a #{:b :c}
            :b #{:a :d}
            :d #{:b :g}
            :g #{:d}
            :c #{:a :e :f}
            :e #{:c}
            :f #{:c :h}
            :h #{:f}} (edge->adjacency acyclic)))))
