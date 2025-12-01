package grafos;

import java.util.*;

public class Grafo<T> {

    private List<Vertice<T>> vertices;
    private boolean direcionado;
    private boolean ponderado;

    public Grafo(boolean direcionado, boolean ponderado) {
        this.vertices = new ArrayList<>();
        this.direcionado = direcionado;
        this.ponderado = ponderado;
    }

    private Vertice<T> buscarVertice(T dado) {
        for (Vertice<T> v : vertices)
            if (v.getDado().equals(dado))
                return v;
        return null;
    }

    public void adicionarVertice(T dado) {
        if (buscarVertice(dado) == null)
            vertices.add(new Vertice<>(dado));
    }

    public void adicionarAresta(T origem, T destino, float peso) {
        Vertice<T> v1 = buscarVertice(origem);
        Vertice<T> v2 = buscarVertice(destino);

        if (v1 == null) {
            v1 = new Vertice<>(origem);
            vertices.add(v1);
        }
        if (v2 == null) {
            v2 = new Vertice<>(destino);
            vertices.add(v2);
        }

        float w = ponderado ? peso : 1;

        v1.adicionarAresta(v2, w);

        if (!direcionado)
            v2.adicionarAresta(v1, w);
    }

    public List<T> buscaLargura(T origem) {
        Vertice<T> inicial = buscarVertice(origem);
        List<T> ordem = new ArrayList<>();
        if (inicial == null) return ordem;

        Set<Vertice<T>> visitados = new HashSet<>();
        Queue<Vertice<T>> fila = new LinkedList<>();

        fila.add(inicial);
        visitados.add(inicial);

        while (!fila.isEmpty()) {
            Vertice<T> v = fila.poll();
            ordem.add(v.getDado());

            for (Aresta<T> a : v.getAdjacentes()) {
                Vertice<T> dest = a.getDestino();
                if (!visitados.contains(dest)) {
                    visitados.add(dest);
                    fila.add(dest);
                }
            }
        }
        return ordem;
    }

    public List<Vertice<T>> getVertices() {
        return vertices;
    }

    public void imprimirGrafo() {
        for (Vertice<T> v : vertices) {
            System.out.print(v + ": ");
            for (Aresta<T> a : v.getAdjacentes())
                System.out.print(a.getDestino() + "(" + a.getPeso() + ") ");
            System.out.println();
        }
    }
}
