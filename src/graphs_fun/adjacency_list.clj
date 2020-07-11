(ns graphs-fun.adjacency-list)

(defn dfs [graph init-node]
  (loop [stack   [init-node]
         visited []
         cycle   false]
    (if (empty? stack)
      {:order  visited
       :cycle? cycle}
      (let [node        (last stack)
            neighbors   (graph node)
            not-visited (filter (fn [n] (not (some #{n} visited))) neighbors)
            new-stack   (into (pop stack) not-visited)]
        (if (some #{node} visited)
          (recur new-stack visited true)
          (recur new-stack (conj visited node) cycle))))))


(defn bfs [graph init-node]
  (loop [queue   (list init-node)
         visited []
         cycle   false]
    (if (empty? queue)
      {:order  visited
       :cycle? cycle}
      (let [node        (first queue)
            neighbors   (graph node)
            not-visited (filter (fn [n] (not (some #{n} visited))) neighbors)
            new-queue   (concat (rest queue) not-visited)]
        (if (some #{node} visited)
          (recur new-queue visited true)
          (recur new-queue (conj visited node) cycle))))))