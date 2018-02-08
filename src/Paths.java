import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Paths {

    private boolean[] marked;

    private int[] edgeTo;

    private int s;

    /**
     * find paths in G from source s.
     *
     * @param G Graph.
     * @param s source.
     */
    Paths(Graph G, int s) {

    }

    /**
     * is there a path from s to v?.
     *
     * @param v end.
     * @return is there a path from s to v?.
     */
    boolean hasPathTo(int v) {
        return marked[v]; // 理解为是否在一个集合里面。
    }

    /**
     * path from s to v; null if no such path.
     *
     * @param v end.
     * @return path from s to v; null if no such path.
     */
    Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }

        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Paths paths = new Paths(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (paths.hasPathTo(v)) {
                StdOut.println(v);
            }
        }
    }
}
