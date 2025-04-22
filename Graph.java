/******************************************************************
 *
 *   Joel Mesa / Comp 272 001
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.*;

public class Graph {
    int numVertices;
    LinkedList<Integer>[] adjListArr;
    List<Integer> vertexValues;

    public Graph(int numV) {
        numVertices = numV;
        adjListArr = new LinkedList[numVertices];
        vertexValues = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjListArr[i] = new LinkedList<>();
            vertexValues.add(0);
        }
    }

    public void setValue(int vertexIndex, int value) {
        if (vertexIndex >= 0 && vertexIndex < numVertices) {
            vertexValues.set(vertexIndex, value);
        } else {
            throw new IllegalArgumentException("Invalid vertex index: " + vertexIndex);
        }
    }

    public void addEdge(int src, int dest) {
        adjListArr[src].add(dest);
    }

    public int findRoot() {
        int[] incoming = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int neighbor : adjListArr[i]) {
                incoming[neighbor]++;
            }
        }

        int root = -1;
        for (int i = 0; i < numVertices; i++) {
            if (incoming[i] == 0) {
                if (root != -1) return -1;
                root = i;
            }
        }

        return (root != -1) ? vertexValues.get(root) : -1;
    }
}
