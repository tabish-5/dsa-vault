package StriverATZ.Recursion.Hard;

public class MColoringProblem {
    boolean graphColoring(int[][] edges, int m, int n) {
       // Build adjacency matrix
        boolean[][] graph = new boolean[n][n];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u][v] = true;
            graph[v][u] = true;
        }

        int[] color = new int[n];

        // Try coloring starting from vertex 0
        return solve(0, n, m, graph, color);
    }

    private boolean solve(int vertex, int n, int m,
                          boolean[][] graph, int[] color) {

        // All vertices are colored
        if (vertex == n) {
            return true;
        }

        // Try every available color
        for (int c = 1; c <= m; c++) {

            if (isSafe(vertex, c, n, graph, color)) {
                color[vertex] = c;

                if (solve(vertex + 1, n, m, graph, color)) {
                    return true;
                }

                // Backtrack
                color[vertex] = 0;
            }
        }

        return false;
    }

    private boolean isSafe(int vertex, int c, int n,
                           boolean[][] graph, int[] color) {

        // Check all adjacent vertices
        for (int v = 0; v < n; v++) {
            if (graph[vertex][v] && color[v] == c) {
                return false;
            }
        }

        return true;
    }
}
