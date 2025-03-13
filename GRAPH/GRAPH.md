# Домашнее задание к занятию "Графы"

## Цель задания

1. Попрактиковаться в обходах графов

------

## Материалы, которые пригодятся для выполнения задания

1. [Реализуем алгоритм поиска в глубину](https://habr.com/ru/company/otus/blog/660725/)

------

## Задание 1 (обязательное)

Социальную сеть можно представить себе в виде графа, где вершинами будут аккаунты пользователей, а наличие ребра будет показывать наличие между двумя пользователями дружбы (что они друг друга добавили в друзья).

Например, рассмотрим такой граф:

<img width="440" alt="image" src="https://user-images.githubusercontent.com/53707586/216818760-c1bedd77-dad9-497d-9724-257404ca911c.png">

Здесь мы видим, например, что Петя дружит с Дашей, Даша с Катей, а Петя с Катей не дружат, как и Катя с Пашей.

Вам нужно написать метод в графе, который позволяет определить, можно ли гуляя по спискам друзей с одной страницы дойти до второй.

Например, от Пети можно дойти до Оли (они дружат), до Кати (через страницу Даши), но не до Паши.

### Заготовка кода
Используйте этот код в качестве заготовки кода вашего проекта. Менять код в `main` нельзя.

```java
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
```

Заготовки классов `Vertex` и `Graph` (менять можно только методы поиска):

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex<T> {
    private T value;
    private List<Vertex> adjacent = new ArrayList<>(); // список смежности

    public Vertex(T value) {
        this.value = value;
    }

    public List<Vertex> getAdjacent() {
        return adjacent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return value.equals(vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

```

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph<T> {
    private List<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> createVertex(T value) {
        Vertex<T> v = new Vertex<>(value);
        vertices.add(v);
        return v;
    }

    public void createEdge(Vertex<T> a, Vertex<T> b) {
        // добавляем их друг друга в их списки смежности
        // ВАШ КОД
    }

    public boolean isConnected(Vertex<T> a, Vertex<T> b) {
        return dfsFind(a, b, new HashSet<>()); // рекурсивный обход в глубину
    }

    // метод отвечает на вопрос, нашли ли мы обходом из v вершину target с учётом
    // посещённых вершин, которые записаны в visited
    private boolean dfsFind(Vertex<T> v, Vertex<T> target, Set<Vertex<T>> visited) {
        // если вершина в которую зашли (v) это та которую мы искали (target), то поиск закончен
        if (v.equals(target)) {
            return true; // нашли
        }
        visited.add(v); // запоминаем вершину которую посетили
        
        // ВАШ КОД
        // перебираем все смежные вершины у v
        // если такую вершину ещё не посещали, заходим рекурсивно в неё
        // если такой заход завершился нахождением target-а - выходим из метода с true

        return false; // ничего не нашли
    }

}

```

### Алгоритм

Для того чтобы определить, можно ли дойти из анкеты `a` до анкеты `b`, запустим обход в глубину из вершины `a`.
Если мы в его процессе наткнёмся на `b`, то дойти можно; иначе - нет.

