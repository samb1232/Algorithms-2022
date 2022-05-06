package lesson5;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class OpenAddressingSet<T> extends AbstractSet<T> {

    private final int bits;

    private final int capacity;

    private final Object[] storage;

    private int size = 0;

    private final Object DD = new Object();

    private int startingIndex(Object element) {
        return element.hashCode() & (0x7FFFFFFF >> (31 - bits));
    }

    public OpenAddressingSet(int bits) {
        if (bits < 2 || bits > 31) {
            throw new IllegalArgumentException();
        }
        this.bits = bits;
        capacity = 1 << bits;
        storage = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка, входит ли данный элемент в таблицу
     */
    @Override
    public boolean contains(Object o) {
        int index = startingIndex(o);
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                return true;
            }
            index = (index + 1) % capacity;
            current = storage[index];
        }
        return false;
    }

    /**
     * Добавление элемента в таблицу.
     * <p>
     * Не делает ничего и возвращает false, если такой же элемент уже есть в таблице.
     * В противном случае вставляет элемент в таблицу и возвращает true.
     * <p>
     * Бросает исключение (IllegalStateException) в случае переполнения таблицы.
     * Обычно Set не предполагает ограничения на размер и подобных контрактов,
     * но в данном случае это было введено для упрощения кода.
     */
    @Override
    public boolean add(T t) {
        int startingIndex = startingIndex(t);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null && current != DD) {
            if (current.equals(t)) {
                return false;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) {
                throw new IllegalStateException("Table is full");
            }
            current = storage[index];
        }
        storage[index] = t;
        size++;
        return true;
    }

    /**
     * Удаление элемента из таблицы
     * <p>
     * Если элемент есть в таблице, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     * <p>
     * Спецификация: {@link Set#remove(Object)} (Ctrl+Click по remove)
     * <p>
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        /**
         * Трудоёмкость программы T = O(capacity) в худшем случае. O(1) в среднем случае.
         * В худшем случае придётся проходить по всей таблице в поиске нужного элемента. Трудоёмкость этой операции O(capacity)
         * Ресурсоёмкость программы R = O(1)
         */
        if (o == null) throw new NullPointerException();
        int startingIndex = startingIndex(o);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                storage[index] = DD;
                size--;
                return true;
            }
            index = (index + 1) % capacity;
            current = storage[index];
            if (index == startingIndex) {
                break;
            }
        }
        return false;
    }

    /**
     * Создание итератора для обхода таблицы
     * <p>
     * Не забываем, что итератор должен поддерживать функции next(), hasNext(),
     * и опционально функцию remove()
     * <p>
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     * <p>
     * Средняя (сложная, если поддержан и remove тоже)
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new OpenAddressingSetIterator();
    }

    private class OpenAddressingSetIterator implements Iterator<T> {

        int iterCounter = 0;
        int index = 0;
        T current = null;


        @Override
        public boolean hasNext() {
            /**
             * Трудоёмкость алгоритма T = O(1)
             * Ресурсоёмкость алгоритма R = O(1)
             */
            return iterCounter < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            /**
             * Трудоёмкость алгоритма T = O(n)
             * Ресурсоёмкость алгоритма R = O(1)
             */
            if (!hasNext()) throw new NoSuchElementException();
            while (storage[index] == DD || storage[index] == null) {
                index++;
            }
            current = (T) storage[index];
            index++;
            iterCounter++;
            return current;
        }

        @Override
        public void remove() {
            /**
             * Трудоёмкость алгоритма T = O(1)
             * Ресурсоёмкость алгоритма R = O(1)
             */
            if (current == null) throw new IllegalStateException();
            storage[index - 1] = DD;
            size--;
            iterCounter--;
        }
    }
}
