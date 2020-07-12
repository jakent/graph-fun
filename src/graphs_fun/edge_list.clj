(ns graphs-fun.edge-list
  (:require [graphs_fun.core :as core]))

(defn edge->adjacency-list [graph]
  (->> graph
       (group-by first)
       (map (fn [[k v]] [k (->> v
                                flatten
                                (remove #{k})
                                (into #{}))]))
       (into {})))

(defn find-neighbors [vertex graph]
  (->> graph
       (filter #(some #{vertex} %))
       flatten
       (into #{})
       (remove #{vertex})
       (sort-by identity)))                                 ;; only necessary for our unit tests

(defn dfs [graph initial-vertex]
  ((core/dfs find-neighbors)
   graph initial-vertex))

(defn bfs [graph initial-vertex]
  ((core/bfs find-neighbors)
   graph initial-vertex))