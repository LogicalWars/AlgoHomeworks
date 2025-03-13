import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Graph<String> socialNetwork = new Graph<>(); // создание графа

        // создание вершин-страниц социальной сети
        Vertex<String> petya = socialNetwork.createVertex("Петя");
        Vertex<String> olya = socialNetwork.createVertex("Оля");
        Vertex<String> dasha = socialNetwork.createVertex("Даша");
        Vertex<String> katya = socialNetwork.createVertex("Катя");

        // создание рёбер - добавления в друзья
        socialNetwork.createEdge(petya, olya);
        socialNetwork.createEdge(olya, dasha);
        socialNetwork.createEdge(dasha, petya);
        socialNetwork.createEdge(dasha, katya);

        Vertex<String> pasha = socialNetwork.createVertex("Паша");
        Vertex<String> kostya = socialNetwork.createVertex("Костя");

        socialNetwork.createEdge(pasha, kostya);

        // поиск достижимости между анкетами
        System.out.println(socialNetwork.isConnected(petya, olya)); // true
        System.out.println(socialNetwork.isConnected(petya, katya)); // true
        System.out.println(socialNetwork.isConnected(pasha, kostya)); // true
        System.out.println(socialNetwork.isConnected(dasha, kostya)); // false

    }

}