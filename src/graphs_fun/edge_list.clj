(ns graphs-fun.edge-list)

(defn edge->adjacency [graph]
  (->> graph
       (group-by first)
       (map (fn [[k v]] [k (->> v
                                flatten
                                (remove #{k})
                                (into #{}))]))
       (into {})))