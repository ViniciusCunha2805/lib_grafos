package grafos;

import java.util.ArrayList;
import java.util.List;

public class Vertice<T> {

    private T dado;
    private List<Aresta<T>> adjacentes;

    public Vertice(T dado) {
        this.dado = dado;
        this.adjacentes = new ArrayList<>();
    }

    public T getDado() {
        return dado;
    }

    public List<Aresta<T>> getAdjacentes() {
        return adjacentes;
    }

    public void adicionarAresta(Vertice<T> destino, float peso) {
        adjacentes.add(new Aresta<>(this, destino, peso));
    }

    @Override
    public String toString() {
        return dado.toString();
    }
}
