import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Graph API.
 */
public class Graph {

    private final int V;

    private Bag<Integer>[] adj;

    private int number_of_e;

    private boolean[][] table;

    /**
     * create an empty graph with V vertices.
     *
     * @param V the number of vertices.
     */
    public Graph(int V) {
        this.V = V;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * create a graph from input stream.
     *
     * @param in input stream.
     */
    public Graph(In in) {

    }

    /**
     * add an edge v-w.
     *
     * @param v v vertices.
     * @param w w vertices.
     */
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * vertices adjacent to v.
     *
     * @param v v vertices.
     * @return vertices adjacent to v.
     */
    Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * number of vertices.
     *
     * @return number of vertices.
     */
    int V() {
        return this.V;
    }

    /**
     * number of edges.
     *
     * @return number of edges.
     */
    int E() {
        return this.number_of_e;
    }

    /**
     * string representation.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Compute the degree of v.
     *
     * @param G Graph.
     * @param v Vertices.
     * @return the degree of v.
     */
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) {
            degree++;
        }

        return degree;
    }

    /**
     * compute maximum degree.
     *
     * @param G Graph.
     * @return maximum degree.
     */
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V; v++) {
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }

    /**
     * compute average degree.
     *
     * @param G Graph.
     * @return average degree.
     */
    public static double averageDegree(Graph G) {
        return 2.0 * G.V() / G.E();
    }

    /**
     * count self-loops.
     *
     * @param G Graph.
     * @return count self-loops.
     */
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                if (v == w) count++;
        return count / 2;   // each edge counted twice
    }

    public static void main(String[] args) {
        // read graph from input stream.
        In in = new In(args[0]);
        Graph G = new Graph(in);

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                StdOut.println(v + " --> " + w);
            }

        }
    }
}
