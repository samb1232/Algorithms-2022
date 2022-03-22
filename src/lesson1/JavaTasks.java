package lesson1;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * <p>
     * Сидоров Петр - Садовая 5
     * <p>
     * Иванов Алексей - Железнодорожная 7
     * <p>
     * Сидорова Мария - Садовая 5
     * <p>
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) throws IOException {
        /**
         * Трудоёмкость программы T = O(N*log(N)).
         * Ресурсоёмкость программы R = O(N).
         * Сортировка деревом. В процессе программы составляется дерево из N элементов.
         * Добавление элемента в дерево занимает O(log(N)).
         * Так как дерево не балансируется, трудоёмкость добавления элемента в дерево в худшем случае будет достигать O(N),
         * а трудоёмкость всей программы O(N^2).
         */
        try (BufferedReader inputFile = Files.newBufferedReader(Paths.get(inputName));
             BufferedWriter outputFile = Files.newBufferedWriter(Paths.get(outputName))) {
            String line = inputFile.readLine();
            AddressTree tree = new AddressTree();
            String[] splitted;
            String[] address;

            while (line != null) {
                if (!line.matches("[А-ЯЁ][а-яё]*\\s[А-ЯЁ][а-яё]*\\s-\\s([А-Я][А-яЁа-яё-]*\\s)+\\d+")) {
                    throw new IllegalArgumentException("Wrong input: " + line);
                }
                splitted = line.split("\\s-\\s");

                tree.addNode(splitted[0], splitted[1].split(" "));

                line = inputFile.readLine();
            }

            tree.sortAndPrint(outputFile);
        }
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * <p>
     * -12.6
     * <p>
     * 121.3
     * <p>
     * -98.4
     * <p>
     * 99.5
     * <p>
     * -12.6
     * <p>
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * <p>
     * -12.6
     * <p>
     * -12.6
     * <p>
     * 11.0
     * <p>
     * 24.7
     * <p>
     * 99.5
     * <p>
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        /**
         * Трудоёмкость программы T = O(n^3*log(n))
         * Ресурсоёмкость программы R = O(n)
         * Программа составляет массив из чисел формата Double и сортирует его методом Collection.sort.
         * Трудоёмкость добавления всех температур в массив O(n).
         * Трудоёмкость сортировки массива методом Collection.sort (сортировка слиянием) O(n*log(n)).
         * Трудоёмкость вывода элементов в файл O(n).
         * Общая трудоёмкость программы получается O(n^3*log(n)).
         */
        try (BufferedReader inputFile = Files.newBufferedReader(Paths.get(inputName));
             BufferedWriter outputFile = Files.newBufferedWriter(Paths.get(outputName))) {
            String line = inputFile.readLine();
            ArrayList<Double> list = new ArrayList();

            while (line != null) {
                list.add(Double.parseDouble(line));
                line = inputFile.readLine();
            }

            Collections.sort(list);

            for (Double num: list) {
                outputFile.write(num.toString());
                outputFile.newLine();
            }
        }
    }

        /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        /**
         * Трудоёмкость программы T = O(N).
         * Ресурсоёмкость программы R = O(1).
         *
         * Ресурсоёмкость программы не зависит от размера входных массивов.
         */
        int indexOne = 0;
        int indexTwo = first.length;
        for (int count = 0; count < second.length; count++) {
            if (indexTwo >= second.length ||
                    indexOne < first.length && first[indexOne].compareTo(second[indexTwo]) < 0) {
                second[count] = first[indexOne];
                indexOne++;
            } else {
                second[count] = second[indexTwo];
                indexTwo++;
            }
        }
    }
}
