(ns graphs-fun.adjacency-matrix
  (:require [graphs_fun.core :as core]
            [clojure.set :as set]))

(defn find-neighbors [vertex->index]
  (fn [vertex graph]
    (->> vertex
         (get vertex->index)
         (get graph)
         (keep-indexed (fn [index weight]
                         (if (pos? weight) index)))
         (map (set/map-invert vertex->index)))))

(defn dfs [graph initial-vertex vertex->index]
  ((core/dfs (find-neighbors vertex->index))
   graph initial-vertex))

(defn bfs [graph initial-vertex vertex->index]
  ((core/bfs (find-neighbors vertex->index))
   graph initial-vertex))

