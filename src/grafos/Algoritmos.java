package grafos;

import java.util.*;

public class Algoritmos {

    // ---------------------------------------------------
    // DIJKSTRA - menor caminho
    // ---------------------------------------------------
    public static <T> Map<T, Float> dijkstra(Grafo<T> g, T origem) {
        Map<T, Float> dist = new HashMap<>();

        for (Vertice<T> v : g.getVertices())
            dist.put(v.getDado(), Float.POSITIVE_INFINITY);

        dist.put(origem, 0f);

        PriorityQueue<T> fila = new PriorityQueue<>(Comparator.comparing(dist::get));
        fila.add(origem);

        while (!fila.isEmpty()) {
            T atual = fila.poll();
            Vertice<T> vAtual = null;

            for (Vertice<T> v : g.getVertices())
                if (v.getDado().equals(atual))
                    vAtual = v;

            if (vAtual == null) continue;

            for (Aresta<T> a : vAtual.getAdjacentes()) {
                T dest = a.getDestino().getDado();
                float nova = dist.get(atual) + a.getPeso();

                if (nova < dist.get(dest)) {
                    dist.put(dest, nova);
                    fila.add(dest);
                }
            }
        }

        return dist;
    }

    // ---------------------------------------------------
    // PRIM - árvore geradora mínima
    // ---------------------------------------------------
    public static <T> List<Aresta<T>> prim(Grafo<T> g, T inicio) {
        List<Aresta<T>> agm = new ArrayList<>();

        Vertice<T> vInicio = null;
        for (Vertice<T> v : g.getVertices())
            if (v.getDado().equals(inicio))
                vInicio = v;

        if (vInicio == null) return agm;

        Set<Vertice<T>> visitados = new HashSet<>();
        PriorityQueue<Aresta<T>> fila =
                new PriorityQueue<>(Comparator.comparing(Aresta::getPeso));

        visitados.add(vInicio);
        fila.addAll(vInicio.getAdjacentes());

        while (!fila.isEmpty() && visitados.size() < g.getVertices().size()) {
            Aresta<T> menor = fila.poll();
            Vertice<T> destino = menor.getDestino();

            if (!visitados.contains(destino)) {
                visitados.add(destino);
                agm.add(menor);

                for (Aresta<T> a : destino.getAdjacentes())
                    if (!visitados.contains(a.getDestino()))
                        fila.add(a);
            }
        }

        return agm;
    }
}
