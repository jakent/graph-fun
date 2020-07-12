(ns graphs_fun.core)

(defn visited? [vertex visited]
  (some #{vertex} visited))

(defn dfs [find-neighbors]
  (fn [graph initial-vertex]
    (loop [stack   [initial-vertex]
           visited []
           cycle   false]
      (if (empty? stack)
        {:order  visited
         :cycle? cycle}
        (let [vertex      (last stack)
              neighbors   (find-neighbors vertex graph)
              not-visited (filter (fn [n] (not (visited? n visited))) neighbors)
              new-stack   (into (pop stack) not-visited)]
          (if (visited? vertex visited)
            (recur new-stack visited true)
            (recur new-stack (conj visited vertex) cycle)))))))

(defn bfs [find-neighbors]
  (fn [graph initial-vertex]
    (loop [queue   (list initial-vertex)
           visited []
           cycle   false]
      (if (empty? queue)
        {:order  visited
         :cycle? cycle}
        (let [vertex      (first queue)
              neighbors   (find-neighbors vertex graph)
              not-visited (filter (fn [n] (not (visited? n visited))) neighbors)
              new-queue   (concat (rest queue) not-visited)]
          (if (visited? vertex visited)
            (recur new-queue visited true)
            (recur new-queue (conj visited vertex) cycle)))))))