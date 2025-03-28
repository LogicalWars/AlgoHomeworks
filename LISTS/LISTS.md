# Домашнее задание к занятию "Динамический массив. Списки. Деки"

## Цель задания

1. Попрактиковаться в связных структурах данных
2. Реализовать свой связный список


------

## Материалы, которые пригодятся для выполнения задания

1. [Реализация односвязных списков в Java](https://javascopes.com/implementing-singly-linked-lists-in-java-gbh-adebd552/)

------

## Задание 1 (обязательное)

Давайте реализуем стек на основе односвязного стека.

### Заготовка кода
Используйте этот код в качестве заготовки кода вашего проекта. Менять код в `main` нельзя.
В комментариях указан в точности тот вывод на консоль, который ожидается от вашей реализации стека.


```java
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        LinkedStack stack = new LinkedStack();

        System.out.println(stack); // EMPTY

        stack.push(5);
        System.out.println(stack); // 5

        stack.push(15);
        System.out.println(stack); // 15 -> 5

        stack.push(25);
        System.out.println(stack); // 25 -> 15 -> 5

        System.out.println(stack.pop()); // 25
        System.out.println(stack); // 15 -> 5

        System.out.println(stack.pop()); // 15
        System.out.println(stack); // 5

        System.out.println(stack.pop()); // 5
        System.out.println(stack); // EMPTY
    }

}
```

Значения стека будем хранить в обёртке, где помимо значения будет указатель на ниже в стеке элемент, в самом же стеке будем хранить указатель на самый верхний элемент.
```java
public class Node {
    private int value;
    private Node prev; // ссылка на обёртку предыдущего элемента

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getPrev() {
        return prev;
    }

    /** Связывание узла с предыдущим, т.е. выставление узлу какой узел является к нему предыдущим */
    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
```

Заготовка вашего класса стека с теми методами, что вам предстоит реализовать выглядит так:
```java
public class LinkedStack {
    private Node tail; // ссылка на последний добавленный узел (обёртку)
    private int size; // размер стека, т.е. количество элементов в нём

    public void push(int value) {
        Node node = new Node(value); // создаём новый узел
        if (tail != null) { // если в стеке уже есть элементы
            node.setPrev(tail); // связываем новый узел с последним
        }
        tail = node; // назначаем новый узел последним узлом
        size++; // увеличиваем счётчик элементов
    }

    public int pop() {
        // ваш код
        // возьмите value из последнего узла
        // назначьте предыдущий к последнему узлу последним узлом
    }

    public int getSize() {
        // ваш код
        // верните размер стека
    }

    public boolean isEmpty() {
        // ваш код
        // верните ответ на вопрос, не пустой ли стек
    }

    public String toString() {
        // если есть элементы, пройдитесь по связному списку,
        // выводя элементы.
        // вывод должен быть в точности как в комментариях к main
        // при этом этот метод не должен менять стек!
    }
}

```