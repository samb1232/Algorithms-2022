package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.util.Arrays;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) {
        /**
         * Трудоёмкость программы T = O(N*M), где N - длина первой строки, M - длина второй строки.
         * Ресурсоемкость программы R = O(1)
         *
         * В цикле N раз проходит внутренний цикл M раз.
         * Ресурсоёмкость программы зависит от размера максимальной подстроки.
         * В худшем случае это будет O(K), где K - min(M, N). То есть минимум из first или second.
         */
        int count;
        String maxSubstring = "";
        StringBuilder substring = new StringBuilder();
        for (int i = 0; i < first.length(); i++) {
            count = 0;
            if (maxSubstring.length() > first.length() - i) {
                break;
            }
            for (int j = 0; j < second.length(); j++) {
                if (i + count < first.length() && first.charAt(i + count) == second.charAt(j)) {
                    substring.append(second.charAt(j));
                    count++;
                } else {
                    count = 0;
                    if (substring.length() > maxSubstring.length()) {
                        maxSubstring = substring.toString();
                    }
                    substring.setLength(0);
                }
            }
        }
        return maxSubstring;
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        /**
         * Алгоритм Решето́ Эратосфе́на
         * Трудоёмкость алгоритма T = O(N(log(log(N)))
         * Ресурсоёмкость алгоритма R = O(N)
         * В программе мы создаём булевой массив, у которого длина равна limit
         */
        if (limit <= 1) return 0;
        boolean[] nums = new boolean[limit + 1];
        int count = 0;
        nums[0] = true;
        nums[1] = true; // изменять 0 и 1 элементы массива необязательно, но так красивее демонстрируется решение

        for (int i = 2; i <= limit; i++) {
            if (!nums[i]) {
                count++;
                if (i * i <= limit) {
                    for (long j = (long) i * i; j <= limit; j += i) {
                        nums[(int) j] = true;
                    }
                }
            }
        }
        return count;
    }
}
