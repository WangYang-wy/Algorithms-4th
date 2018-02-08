public class DepthFirstPaths {

    private boolean[] marked;

    private int[] edgeTo;

    private int s;

    /**
     * Constructor.
     *
     * @param G Graph.
     * @param s Vertex.
     */
    public DepthFirstPaths(Graph G, int s) {
        // initial.
        dfs(G, s);
    }

    /**
     * recursive DFS does the work.
     *
     * @param G Graph.
     * @param v Vertex.
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }
}
