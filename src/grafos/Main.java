package grafos;

import java.io.*;
import java.util.*;

public class Main {

    public static Grafo<String> carregarArquivo(String caminho) {
        // Grafo fixo: não direcionado e ponderado
        Grafo<String> g = new Grafo<>(false, true);

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty() || linha.startsWith("#")) continue;

                String[] partes = linha.split(",");
                String o = partes[0].trim();
                String d = partes[1].trim();
                float peso = Float.parseFloat(partes[2].trim());

                g.adicionarAresta(o, d, peso);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }

        return g;
    }

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // --- ARQUIVO FIXO ---
        String caminho = "grafo_teste.txt";
        System.out.println("Carregando grafo do arquivo: " + caminho);

        Grafo<String> g = carregarArquivo(caminho);

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Mostrar grafo");
            System.out.println("2 - Busca em Largura (BFS)");
            System.out.println("3 - Menor caminho (Dijkstra)");
            System.out.println("4 - Árvore Geradora Mínima (Prim)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(teclado.nextLine());
            System.out.println();

            switch (opcao) {

                case 1:
                    System.out.println("=== GRAFO ===");
                    g.imprimirGrafo();
                    break;

                case 2:
                    System.out.print("Vértice inicial: ");
                    String inicioBfs = teclado.nextLine();
                    System.out.println("Resultado BFS: " + g.buscaLargura(inicioBfs));
                    break;

                case 3:
                    System.out.print("Vértice de origem: ");
                    String inicioDij = teclado.nextLine();
                    System.out.println("Distâncias: " + Algoritmos.dijkstra(g, inicioDij));
                    break;

                case 4:
                    System.out.print("Vértice inicial do Prim: ");
                    String inicioPrim = teclado.nextLine();

                    List<Aresta<String>> mst = Algoritmos.prim(g, inicioPrim);
                    float soma = 0;

                    System.out.println("Árvore Geradora Mínima:");
                    for (Aresta<String> a : mst) {
                        System.out.println(a);
                        soma += a.getPeso();
                    }

                    System.out.println("Peso total da AGM = " + soma);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
