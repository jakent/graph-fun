(ns graphs-fun.adjacency-matrix-test
  (:require [clojure.test :refer :all]
            [graphs-fun.adjacency-matrix :refer :all]))

(def vertex->index
  {:a 0
   :b 1
   :c 2
   :d 3
   :e 4
   :f 5
   :g 6
   :h 7})

(def acyclic
  ;;        a
  ;;       / \
  ;;      b   c
  ;;     /   / \
  ;;    d   e   f
  ;;   /         \
  ;;  g           h
  ;;
  ;;a b c d e f g h
  [[0 1 1 0 0 0 0 0]                                        ;; a
   [1 0 0 1 0 0 0 0]                                        ;; b
   [1 0 0 0 1 1 0 0]                                        ;; c
   [0 1 0 0 0 0 1 0]                                        ;; d
   [0 0 1 0 0 0 0 0]                                        ;; e
   [0 0 1 0 0 0 0 1]                                        ;; f
   [0 0 0 1 0 0 0 0]                                        ;; g
   [0 0 0 0 0 1 0 0]])                                      ;; h

(def unicyclic
  ;;        a
  ;;       / \
  ;;      b   c
  ;;     /   / \
  ;;    d   e   f
  ;;   /     \ /
  ;;  g       h
  ;;
  ;;a b c d e f g h
  [[0 1 1 0 0 0 0 0]                                        ;; a
   [1 0 0 1 0 0 0 0]                                        ;; b
   [1 0 0 0 1 1 0 0]                                        ;; c
   [0 1 0 0 0 0 1 0]                                        ;; d
   [0 0 1 0 0 0 0 1]                                        ;; e
   [0 0 1 0 0 0 0 1]                                        ;; f
   [0 0 0 1 0 0 0 0]                                        ;; g
   [0 0 0 0 1 1 0 0]])                                      ;; h

(deftest bfs-test
  (testing "acyclic and have no cycles"
    (is (false? ((bfs acyclic :a vertex->index) :cycle?)))
    (is (= [:a :b :c :d :e :f :g :h]
           ((bfs acyclic :a vertex->index) :order))))

  (testing "unicyclic have one cycle"
    (is (true? ((bfs unicyclic :a vertex->index) :cycle?)))
    (is (= [:a :b :c :d :e :f :g :h]
           ((bfs acyclic :a vertex->index) :order)))))

(deftest dfs-test
  (testing "acyclic and have no cycles"
    (is (false? ((dfs acyclic :a vertex->index) :cycle?)))
    (is (= [:a :c :f :h :e :b :d :g]
           ((dfs acyclic :a vertex->index) :order))))

  (testing "unicyclic have one cycle"
    (is (true? ((dfs unicyclic :a vertex->index) :cycle?)))
    (is (= [:a :c :f :h :e :b :d :g]
           ((dfs acyclic :a vertex->index) :order)))))