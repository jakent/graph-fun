(ns graphs-fun.adjacency-list-test
  (:require [clojure.test :refer :all]
            [graphs-fun.adjacency-list :refer :all]))

(def acyclic
  ;;        a
  ;;       / \
  ;;      b   c
  ;;     /   / \
  ;;    d   e   f
  ;;   /         \
  ;;  g           h
  {:a [:b :c]
   :b [:a :d]
   :d [:b :g]
   :g [:d]
   :c [:a :e :f]
   :e [:c]
   :f [:c :h]
   :h [:f]})

(def unicyclic
  ;;        a
  ;;       / \
  ;;      b   c
  ;;     /   / \
  ;;    d   e   f
  ;;   /     \ /
  ;;  g       h
  {:a [:b :c]
   :b [:a :d]
   :d [:b :g]
   :g [:d]
   :c [:a :e :f]
   :e [:c :h]
   :f [:c :h]
   :h [:e :f]})

(deftest bfs-test
  (testing "acyclic and have no cycles"
    (is (false? ((bfs acyclic :a) :cycle?)))
    (is (= [:a :b :c :d :e :f :g :h]
           ((bfs acyclic :a) :order))))

  (testing "unicyclic have one cycle"
    (is (true? ((bfs unicyclic :a) :cycle?)))
    (is (= [:a :b :c :d :e :f :g :h]
           ((bfs acyclic :a) :order)))))

(deftest dfs-test
  (testing "acyclic and have no cycles"
    (is (false? ((dfs acyclic :a) :cycle?)))
    (is (= [:a :c :f :h :e :b :d :g]
           ((dfs acyclic :a) :order))))

  (testing "unicyclic have one cycle"
    (is (true? ((dfs unicyclic :a) :cycle?)))
    (is (= [:a :c :f :h :e :b :d :g]
           ((dfs acyclic :a) :order)))))
