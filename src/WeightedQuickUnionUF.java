/**
 *
 */
public class WeightedQuickUnionUF {
    private int id[];
    private int sz[];
    private int count;

    /**
     *
     * @param N
     */
    public WeightedQuickUnionUF(int N) {
        id = new int[N];
        sz = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
}
