import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindCycle {

    public int[] root;
    public int[] rank;

    public FindCycle(int size) {

        root = new int[size];
        rank = new int[size];

        for(int i = 0 ; i < size; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if(x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    //Union is modifed to detect a cycle. 
    // If 2 vertices already share the same root, it means they are already connected and we are trying to form a cycle
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY) {
            if(rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
            return false;
        } else if (x != y && rootX == rootY) {
            return true;
        }
        return false;
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {

        int[][] edges = {{0,1},{1,2},{2,3}};
        int[][] edges1 = {{0,1},{1,2},{2,3},{3,4}};
        int[][] edges3 = {{0,1}, {2,3}};
        int size = 4;
        FindCycle uf = new FindCycle(size);

        for(int i = 0; i < edges.length ; i++) {
            //check for cycle everytime you try to perform union.
            if(uf.union(edges[i][0], edges[i][1])) {
                System.out.println("There's a cycle");
            }
        }

        
        // Each unique root represents a different graph/tree.
        Set<Integer> unique_roots = new HashSet <>();
        for(int k = 0; k < uf.root.length; k++) {
                // The way edges are presented to the union function may result in having to use the find function instead of directly accessing the root array .
                if (!unique_roots.contains(uf.find(uf.root[k]))) {
                    unique_roots.add(uf.find(uf.root[k]));        
                } 
        }
        System.out.println("No of unique graphs "+ unique_roots.size());
    }
        
    }

    
 
 
   
     
     
