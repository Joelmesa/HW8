/******************************************************************
 *
 *   Joel Mesa / Comp 272 001
 *
 *   This java file contains the problem solutions of canFinish and
 *   numGroups methods.
 *
 ********************************************************************/
import java.util.*;

import java.util.*;

class ProblemSolutions {

    public boolean canFinish(int numExams, int[][] prerequisites) {
        int[] inDegree = new int[numExams];
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numExams; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            adj.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numExams; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return count == numExams;
    }

    public int numGroups(int[][] adjMatrix) {
        int n = adjMatrix.length;
        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsUndirected(i, adjMatrix, visited);
                count++;
            }
        }

        return count;
    }

    private void dfsUndirected(int node, int[][] matrix, boolean[] visited) {
        visited[node] = true;

        for (int j = 0; j < matrix.length; j++) {
            // Treat as undirected by checking either direction
            if ((matrix[node][j] == 1 || matrix[j][node] == 1) && !visited[j]) {
                dfsUndirected(j, matrix, visited);
            }
        }
    }

}

