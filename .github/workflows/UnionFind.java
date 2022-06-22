package com.bmuschko.testcontainers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//Figure out if the the edges provided are connected or not 
public class UnionFind {
    int [] root;
    int [] rank; 
    
    public void UnionFind(int size ) {
        root = new int [size];
        rank = new int [size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1;
        }
        return;
    }
    
    // Find root of a node. 
    // root is the node for whom the root is itself
    public int basic_find(int x) {
        while (x != root[x]) {
            x = root[x];
            
        }
        return x;
    }
    
    // Optimize finding the root by using path compression 
    // As we walk up finding the root for a given node , fix the root for the ancestors in a recursive manner
    public int find(int x) {
        if (x == root[x]) {
            return x;
        } 
        return root[x] = find(root[x]);
        
    }
    
    // Do a union of 2 nodes by attaching root of first node to the root of the other node
    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y); 
        
        if (rootx != rooty) {
            // If rank/depth of rootx is higher, make root of y attach  to root of x
            if (rank[rootx] > rank[rooty]) {
                root[rooty] = rootx;
            } else if (rank[rooty] > rank[rootx]) {
                // If rank of rooty is higher , then make root of x attach  to root of y
                root[rootx] = rooty;
            } else {
                // Attach root of x to root of y. Increase rank of root y
                root[rootx] = rooty;
                rank[rooty] += 1;
            }
            
        }
    }
    
    public boolean connected(int x, int y) {
       if (find(x) == find(y)) {
           return true;
       } 
       return false;
    }
    
    public static void main(String args[]) {
      UnionFind uf = new UnionFind(10);
        // 1-2-5-6-7 3-8-9 4
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(3, 8);
        uf.union(8, 9);
        System.out.println(uf.connected(1, 5)); // true
        System.out.println(uf.connected(5, 7)); // true
        System.out.println(uf.connected(4, 9)); // false
        // 1-2-5-6-7 3-8-9-4
        uf.union(9, 4);
        System.out.println(uf.connected(4, 9)); // true
    }
}
