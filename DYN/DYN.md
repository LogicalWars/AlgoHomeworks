# Домашнее задание к занятию "Рекурсия"

## Цель задания

1. Научиться писать рекурсивные алгоритмы
2. Научиться оптимизировать рекурсию с помощью динамического программирования

------

## Материалы, которые пригодятся для выполнения задания

1. [Рекурсия в Java](https://javarush.com/groups/posts/1895-rekursija-v-java)
2. [Динамическое программирование для начинающих](https://tproger.ru/articles/dynprog-starters/)

------

## Задание 1 (обязательное)

Один любознательный студент завёл себе слишком много хобби - аж 10 штук.
Т.к. он любил их всех, он никогда не мог определиться, каким хобби он будет заниматься сегодня.

И он придумал правило, по которому будет выбирать себе хобби.

В самом начале он пишет два числа: день и месяц своего рождения, затем два числа - две половинки текущего года.
Например, если он родился 21го января и на дворе 2023й год, то первые четыре числа получаются 21 1 20 23.

Каждое следующее число получается по правилу: берётся предыдущий день и за два дня до него, перемножаются, берётся остаток от деления на 10, затем прибавляется 1, чтобы была нумерация с единицы.
Каждое такое число будет от 1 до 10 и являться номером хобби, которым он будет заниматься в этот день.

Пример расчёта:

| Номер дня | Число последовательности | Принцип расчёта |
| --- | --- | --- |
| x | 21 | день рождения |
| x | 1 | месяц рождения |
| x | 20 | первая половина записи года 2023 |
| x | 23 | вторая половина записи года 2023 |
| 1 | 4 | (23 * 1) % 10 + 1 |
| 2 | 1 | (4 * 20) % 10 + 1 |
| 3 | 4 | (1 * 23) % 10 + 1 |
| 4 | 7 | (4 * 4) % 10 + 1 |

Возможно, не все хобби попадутся таким жребием, но попробовать такую схему студент захотел, по крайней мере на какое-то время.

Ваша задача: написать рекурсивный алгоритм решения этой задачи.
Ниже вам будет дан итеративный способ решения.

После чего вам нужно будет ускорить рекурсию динамическим программированием (про это подробнее будет расписано ниже).


### Заготовка кода
Используйте этот код в качестве заготовки кода вашего проекта. Менять код в `main`, `compare` и `chooseHobbyIterative` нельзя.

```java
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        compare(1);
        compare(2);
        compare(5);
        compare(15);
    }

    public static void compare(int day) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = { 21, 1, 20, 23 };
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
        System.out.println();
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day) {
//        int prev = ??? // предыдущее значение
//        int prePrePrev = ??? // пре-пре-предыдущее значение
//        return prev * ...;
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day; d++) {
            int index = d + 4; // индексы дней в массиве сдвинуты на 4
            int prev = numbers.get(index - 1); // предыдущее значение
            int prePrePrev = numbers.get(index - 3); // пре-пре-предыдущее значение
            numbers.add((prev * prePrePrev) % 10 + 1);
        }

        return numbers.get(numbers.size() - 1);
    }
}
```

### Алгоритм

Каждый вызов сперва вычислит предыдущее значение, затем пре-пре-предыдущее.
Если вычисляемое выше значение является одним из стартовых чисел, то вычисление завершается простым взятием этого числа в качестве результата вычисления.
Иначе, если вычисляемое промежуточное значение - это номер хобби для одного из дней - алгоритм запускается рекурсивно для этого дня.

Поговорим теперь про ускорение рекурсии.
Если вы в начале вызова рекурсивного метода добавите строку вывода аргумента `System.out.println(">>> " + day);`, то увидите, что некоторые вызовы повторяются. Чтобы не пересчитывать в таких случаях заново, воспользуемся приёмами динамического программирования.

Заведите дополнительный параметр в `chooseHobbyRecursive` - массив для запоминания результатов вызовов чтобы переиспользовать эти данные при повторяющихся вызовах:
```java
public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory) {
    ...
}
```

Ячейки массива будем интерпретировать так: если в ячейке 0, то для этого индекса ещё рекурсии не было, а если не 0, то для этого индекса рекурсия уже была в это значение и есть посчитанный результат такого вызова.

При вызове в `compare` передайте туда массив из нулей соответствующего размера (тк при начале подсчёта мы ещё ничего не запомнили), это единственное изменение которое можно сделать в compare:
```java
        int recursive = chooseHobbyRecursive(startNumbers, day, new int[day]);
```

В начале рекурсивного вызова проверьте, нет ли в `memory` уже сохранённого значения для текущего значения параметра.
Если есть, завершите вызов вернув готовое значение.
Если нет, то выполните оставшуюся часть метода; не забудьте перед `return` сохранить посчитанное значение в `memory`.

Убедитесь, что вызовы теперь не повторяются (вывод `>>> day` переместите после проверки наличия готового ответа в `memory`).

Так, динамическое программирование заметно сократит количество повторяющихся рекурсивных вызовов, чем ускорит рекурсивный алгоритм.

Данная задача хорошо демонстрирует механики рекурсии и динамического программирования, хотя в данном случае написание итеративного нерекурсивного алгоритма не представляет большой сложности. Особенно динамическое программирование пригождается тогда, когда итеративный алгоритм совсем неочевиден и крайне сложен.

------