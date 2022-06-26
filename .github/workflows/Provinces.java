// No of unique provinces given the n x n connected matrix information
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnionFind {
     public int [] root;
     public int [] rank;
  
     public UnionFind (int size) {
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
  
     public isconnected(int x, int y) {
        if (root[x] == root[y]) {
            return true;
        }
        return false;
     }
     
      public static void main(String[] args) {
        int size = 6;
        int province = 0;
        UnionFind unionFind = new UnionFind(size);

        int[][] isConnected = {{1,1,0,0,0,0},{1,1,0,0,0,0},{0,0,1,1,1,0},{0,0,1,1,0,0},{0,0,1,0,1,0}, {0,0,0,0,0,1}};
        for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                  if (isConnected[i][j] == 1) {
                       unionFind.union(i,j);
                  }
             }
        }
        

        Set<Integer> uniqueSet = new HashSet<>();
        Map<Integer,Set<Integer>> provinces = new HashMap<>();
        Set<Integer> getProvinceSet = new HashSet<>();
           
        // Once the graph is constructed via union , in the root aray find how many unique entries exist, that is the number of provinces. 
        // For each unique entry , add the constituents sharing the root
        for(int i=0 ; i< unionFind.root.length; i++) {
            if(provinces.get(unionFind.root[i]) != null) {
                getProvinceSet = provinces.get(unionFind.root[i]);
            } else {
                getProvinceSet = new HashSet<>();
            }
            getProvinceSet.add(i);
            provinces.put(unionFind.root[i], getProvinceSet);

            if(!uniqueSet.contains(unionFind.root[i])) {
                uniqueSet.add(unionFind.root[i]);
                province++;
            }
        }
      }     
}
