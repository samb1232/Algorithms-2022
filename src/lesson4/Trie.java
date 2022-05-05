package lesson4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Префиксное дерево для строк
 */
public class Trie extends AbstractSet<String> implements Set<String> {

    private static class Node {
        SortedMap<Character, Node> children = new TreeMap<>();
    }

    private final Node root = new Node();

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root.children.clear();
        size = 0;
    }

    private String withZero(String initial) {
        return initial + (char) 0;
    }

    @Nullable
    private Node findNode(String element) {
        Node current = root;
        for (char character : element.toCharArray()) {
            if (current == null) return null;
            current = current.children.get(character);
        }
        return current;
    }

    @Override
    public boolean contains(Object o) {
        String element = (String) o;
        return findNode(withZero(element)) != null;
    }

    @Override
    public boolean add(String element) {
        Node current = root;
        boolean modified = false;
        for (char character : withZero(element).toCharArray()) {
            Node child = current.children.get(character);
            if (child != null) {
                current = child;
            } else {
                modified = true;
                Node newChild = new Node();
                current.children.put(character, newChild);
                current = newChild;
            }
        }
        if (modified) {
            size++;
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        String element = (String) o;
        Node current = findNode(element);
        if (current == null) return false;
        if (current.children.remove((char) 0) != null) {
            size--;
            return true;
        }
        return false;
    }

    /**
     * Итератор для префиксного дерева
     * <p>
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     * <p>
     * Сложная
     */
    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new TrieIterator();
    }


    public class TrieIterator implements Iterator<String> {

        Stack<String> stack = new Stack<>();
        String top = null;

        public TrieIterator() {
            feelStack(root, "");
        }

        private void feelStack(Node node, String word) {
            /**
             * Трудоёмкость алгоритма T = O(n)
             * Ресурсоёмкость алгоритма R = O(n)
             * Рекурсивно добавляем в стек все слова
             */


            for (char character : node.children.keySet()) {
                if (character != (char) 0) feelStack(node.children.get(character), word + character);
                else stack.push(word);
            }
        }

        @Override
        public boolean hasNext() {
            /**
             * Трудоёмкость алгоритма T = O(1)
             * Ресурсоёмкость алгоритма R = O(1)
             */
            return !stack.isEmpty();
        }

        @Override
        public String next() {
            /**
             * Трудоёмкость алгоритма T = O(1)
             * Ресурсоёмкость алгоритма R = O(1)
             */
            if (!hasNext()) throw new NoSuchElementException();
            top = stack.pop();
            return top;
        }

        @Override
        public void remove() {
            /**
             * Трудоёмкость алгоритма T = O(log(n))
             * Ресурсоёмкость алгоритма R = O(1)
             */
            if (top == null) throw new IllegalStateException();
            Trie.this.remove(top);
            top = null;
        }
    }
}