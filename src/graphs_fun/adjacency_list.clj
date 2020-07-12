(ns graphs-fun.adjacency-list
  (:require [graphs_fun.core :as core]))

(defn find-neighbors [vertex graph]
  (get graph vertex))

(defn dfs [graph initial-vertex]
  ((core/dfs find-neighbors)
   graph initial-vertex))

(defn bfs [graph initial-vertex]
  ((core/bfs find-neighbors)
   graph initial-vertex))
