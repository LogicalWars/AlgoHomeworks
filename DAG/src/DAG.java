import java.util.*;

public class DAG<T> {
    private List<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> createVertex(T value) {
        Vertex<T> v = new Vertex<>(value);
        vertices.add(v);
        return v;
    }

    public void createEdge(Vertex<T> from, Vertex<T> to) {
        // добавляем в список смежности только в одном направлении
        from.getAdjacent().add(to);
    }

    public int path(Vertex<T> from, Vertex<T> to) {
        Map<Vertex<T>, Integer> paths = new HashMap<>(); // по городу даёт количество полётов чтобы до него добраться из from
        paths.put(from, 0); // добраться из пункта отправления до него же самого можно вообще не летая

        Queue<Vertex<T>> queue = new ArrayDeque<>(); // очередь обхода вершин
        Set<Vertex<T>> added = new HashSet<>(); // запоминаем все когда-либо побывавшие в очереди обхода вершины чтобы не дублироваться
        queue.add(from); // начинать будем из города отправления

        while (!queue.isEmpty()) { // пока очередь не пуста
            Vertex<T> v = ... // вынимаем следующий элемент из очереди

            // если v это город назначения, то возвращаем из paths готовый ответ для этой вершины

            // перебираем вершины по дугам из v
            // если такую вершину уже добавляли в очередь (воспользуйтесь коллекцией added), то пропускаем её.
            // иначе, добавляем её в очередь, added и paths (подумайте, сколько перелётов будет до этого города, если мы знаем сколько перелётов до v)
        }

        return -1;
    }

}