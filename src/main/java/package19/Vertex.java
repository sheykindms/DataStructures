package package19;

import java.util.*;

class Vertex {
    public Vertex parent;
    public int Value;
    public int index;
    public boolean Hit;
    

    public Vertex(int val, int index) {
        Value = val;
        this.index = index;
        Hit = false;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;
    Stack<Vertex> stack;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == null) {
                vertex[i] = new Vertex(value, i);
                break;
            }
        }
    }

    public void RemoveVertex(int v) {
        this.vertex[v] = null;
        for (int i = 0; i < m_adjacency.length; i++) {
            m_adjacency[i][v] = 0;
            m_adjacency[v][i] = 0;
        }
    }

    public boolean IsEdge(int v1, int v2) {
        return m_adjacency[v1][v2] == 1 || m_adjacency[v2][v1] == 1;
    }

    public void AddEdge(int v1, int v2) {
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        stack = new Stack<>();
        for (Vertex root : vertex) {
            root.Hit = false;
        }
        dfs(vertex[VFrom], vertex[VTo]);
        return new ArrayList<>(stack);
    }

    public void dfs(Vertex VFrom, Vertex Vto) {
        VFrom.Hit = true;
        stack.push(VFrom);
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[VFrom.index][i] == 1 && vertex[i].Value == Vto.Value) {
                stack.add(vertex[i]);
                return;
            }
        }
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[VFrom.index][i] == 1 && !vertex[i].Hit) {
                dfs(vertex[i], Vto);
                return;
            }
        }
        stack.pop();
        if (!stack.isEmpty()) {
            dfs(stack.pop(), Vto);
        }
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        var path = new ArrayList<Vertex>();
        var vertices = new LinkedList<Vertex>();
        boolean flag = true;
        vertices.add(vertex[VFrom]);
        for (var i : vertex) {
            i.Hit = false;
            i.parent = null;
        }
        while (!vertices.isEmpty() && flag) {
            var v = vertices.poll();
            for (var i = 0; i < max_vertex; i++) {
                if (m_adjacency[v.index][i] == 1 && !vertex[i].Hit) {
                    Vertex vertex = this.vertex[i];
                    vertex.parent = v;
                    if (vertex == this.vertex[VTo]) {
                        flag = false;
                        break;
                    }
                    vertex.Hit = true;
                    vertices.add(vertex);
                }
            }
            v.Hit = true;
        }
        var p = vertex[VTo].parent;
        if (p != null) {
            path.add(vertex[VTo]);
        }
        while (p != null) {
            path.add(p);
            if (p.parent != null && p.Value == p.parent.Value) {
                break;
            }
            p = p.parent;

        }
        Collections.reverse(path);
        return path;
    }

}
