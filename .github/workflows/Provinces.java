// No of unique provinces given the n x n connected matrix information
package com.bmuschko.testcontainers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnionFind {
     public int [] root;
     public int [] rank;
  
     public void UnionFind (int size) {
            root = new int [size];
            rank = new int [size]; 
            for (int i = 0; i < size; i++) {
              root[i] = i;
              rank[i] = 1;
                
            }
       
     }
  
      // Path compression optimized find
     public int find(int x) {
       if (x == root[x]) {
         return x;
       }
       return root[x] = find(root[x]);
     }
  
     // Rank optimized union
     public void union(int x, int y) {
        int root_x = find(x);
        int root_y = find(y); 
       
        if (root_x != root_y) {
            if (rank[root_x] > rank[root_y]) {
                 root[root_y] = root_x;
            } else if (rank[root_y] > rank[root_x]) {
                 root[root_x] = root_y;
            } else {
                 root[root_x] = root_y;
                 rank[root_y] += 1;
              
            }
        }
     }
  
     public isConnected(int x, int y) {
        if (root[x] == root[y]) {
            return true;
        }
        return false;
     }
     
}
