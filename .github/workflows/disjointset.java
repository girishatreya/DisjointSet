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
    public void basic_find(int x) {
        while (x != root[x]) {
            x = root[x];
            
        }
        return x;
    }
    
    // Optimize finding the root by using path compression 
    // As we walk up finding the root for a given node , fix the root for the ancestors in a recursive manner
    public void find(int x) {
        if (x == root[x]) {
            return x;
        } 
        root[x] = find(root[x]);
        return;
    }
    
    // 
    
    public static void main(String args[]) {
      int x=10;
      int y=25;
      int z=x+y;

      System.out.println("Sum of x+y = " + z);
    }
}
