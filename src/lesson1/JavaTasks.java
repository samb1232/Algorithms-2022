package lesson1;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    /**
     * Трудоёмкость программы T = O(log(n))
     * Ресурсоёмкость программы R = O(N).
     * В процессе программы составляется дерево из N элементов.
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        /**
         * Трудоёмкость программы T = O(log(N))
         * Ресурсоёмкость программы R = O(N).
         * В процессе программы составляется дерево из N элементов.
         */
        BufferedReader inputFile = Files.newBufferedReader(Paths.get(inputName));
        String line = inputFile.readLine();
        TimeTree tree = new TimeTree();

        while (line != null) {
            if (!line.matches("[01]\\d:[012345]\\d:[012345]\\d\\s[AP]M")) {
                throw new IllegalArgumentException("Wrong input");
            }
            String[] time = line.split("\\s");
            String[] nums = time[0].split(":");

            tree.addNode(nums, time[1]);

            line = inputFile.readLine();
        }

        BufferedWriter outputFile = Files.newBufferedWriter(Paths.get(outputName));

        tree.sortAndPrint(outputFile);

        outputFile.close();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) throws IOException {
        /**
         * Трудоёмкость программы T = O(log(N))
         * Ресурсоёмкость программы R = O(N).
         * В процессе программы составляется дерево из N элементов.
         */
        BufferedReader inputFile = Files.newBufferedReader(Paths.get(inputName));
        String line = inputFile.readLine();
        AddressTree tree = new AddressTree();
        String[] splitted;
        String[] address;

        while (line != null) {
            if (!line.matches("[А-ЯЁ][а-яё]*\\s[А-ЯЁ][а-яё]*\\s-\\s([А-Я][А-яЁа-яё-]*\\s)+\\d+")) {
                throw new IllegalArgumentException("Wrong input: " + line);
            }
            splitted = line.split("\\s-\\s");


            String[] bbbb = splitted[1].split(" ");
            tree.addNode(splitted[0], splitted[1].split(" "));

            line = inputFile.readLine();
        }

        BufferedWriter outputFile = Files.newBufferedWriter(Paths.get(outputName));

        tree.sortAndPrint(outputFile);

        outputFile.close();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
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
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        /**
         * Трудоёмкость программы T = O(N)
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
