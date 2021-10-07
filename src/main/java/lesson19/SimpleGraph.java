package lesson19;

import java.util.*;

class Vertex {
    public Vertex parent;
    public int value;
    public int index;
    public boolean hit;


    public Vertex(int val, int index) {
        value = val;
        this.index = index;
        hit = false;
    }
}

public class SimpleGraph {
    private final Vertex[] vertex;
    private final int[][] mAdjacency;
    private final int maxVertex;
    Stack<Vertex> stack;

    public SimpleGraph(int size) {
        maxVertex = size;
        mAdjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void addVertex(int value) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == null) {
                vertex[i] = new Vertex(value, i);
                break;
            }
        }
    }

    public void removeVertex(int v) {
        this.vertex[v] = null;
        for (int i = 0; i < mAdjacency.length; i++) {
            mAdjacency[i][v] = 0;
            mAdjacency[v][i] = 0;
        }
    }

    public boolean isEdge(int v1, int v2) {
        return mAdjacency[v1][v2] == 1 || mAdjacency[v2][v1] == 1;
    }

    public void addEdge(int v1, int v2) {
        mAdjacency[v1][v2] = 1;
        mAdjacency[v2][v1] = 1;
    }

    public void removeEdge(int v1, int v2) {
        mAdjacency[v1][v2] = 0;
        mAdjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> depthFirstSearch(int vFrom, int vTo) {
        stack = new Stack<>();
        for (Vertex root : vertex) {
            root.hit = false;
        }
        dfs(vertex[vFrom], vertex[vTo]);
        return new ArrayList<>(stack);
    }

    public void dfs(Vertex vFrom, Vertex vTo) {
        vFrom.hit = true;
        stack.push(vFrom);
        for (int i = 0; i < maxVertex; i++) {
            if (mAdjacency[vFrom.index][i] == 1 && vertex[i].value == vTo.value) {
                stack.add(vertex[i]);
                return;
            }
        }
        for (int i = 0; i < maxVertex; i++) {
            if (mAdjacency[vFrom.index][i] == 1 && !vertex[i].hit) {
                dfs(vertex[i], vTo);
                return;
            }
        }
        stack.pop();
        if (!stack.isEmpty()) {
            dfs(stack.pop(), vTo);
        }
    }

    public ArrayList<Vertex> breadthFirstSearch(int vFrom, int vTo) {
        var path = new ArrayList<Vertex>();
        var vertices = new LinkedList<Vertex>();
        boolean flag = true;
        vertices.add(vertex[vFrom]);
        for (var i : vertex) {
            i.hit = false;
            i.parent = null;
        }
        while (!vertices.isEmpty() && flag) {
            var v = vertices.poll();
            for (var i = 0; i < maxVertex; i++) {
                if (mAdjacency[v.index][i] == 1 && !vertex[i].hit) {
                    Vertex vertex = this.vertex[i];
                    vertex.parent = v;
                    if (vertex == this.vertex[vTo]) {
                        flag = false;
                        break;
                    }
                    vertex.hit = true;
                    vertices.add(vertex);
                }
            }
            v.hit = true;
        }
        var p = vertex[vTo].parent;
        if (p != null) {
            path.add(vertex[vTo]);
        }
        while (p != null) {
            path.add(p);
            if (p.parent != null && p.value == p.parent.value) {
                break;
            }
            p = p.parent;

        }
        Collections.reverse(path);
        return path;
    }

    public ArrayList<Vertex> weakVertices() {
        var vertices = new ArrayList<Vertex>();
        for (Vertex i : vertex) {
            i.hit = false;
        }
        Vertex one;
        Vertex two;
        Vertex temp;
        for (var i = 0; i < maxVertex; i++) {
            one = vertex[i];
            if (!one.hit) {
                for (var j = 0; j < maxVertex; j++) {
                    if (i != j && mAdjacency[i][j] == 1) {
                        two = vertex[j];
                        temp = isTriangle(one, two);
                        if (temp != null) {
                            one.hit = true;
                            two.hit = true;
                            temp.hit = true;
                            break;
                        }
                    }
                }
            }
        }
        for (Vertex v : vertex) {
            if (!v.hit) {
                vertices.add(v);
            }
        }
        return vertices;
    }


    private Vertex isTriangle(Vertex v1, Vertex v2) {
        for (var i = 0; i < maxVertex; i++) {
            if (mAdjacency[v1.index][i] == 1 && mAdjacency[v2.index][i] == 1 && mAdjacency[v1.index][v2.index] == 1) {
                return vertex[i];
            }
        }
        return null;
    }

}
