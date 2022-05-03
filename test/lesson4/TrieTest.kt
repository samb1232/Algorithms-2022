package lesson4

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TrieTest : AbstractTrieTest() {

    override fun create(): MutableSet<String> =
        Trie()

    @Test
    @Tag("Example")
    fun generalTestJava() {
        doGeneralTest()
    }

    @Test
    @Tag("7")
    fun iteratorTestJava() {
        doIteratorTest()
    }

    @Test
    @Tag("8")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()

        //My test
        val trie = Trie()
        trie.add("hello")
        trie.add("world")
        trie.add("help")
        trie.add("helper")
        trie.add("hello")
        val trieIterator = trie.iterator()
        while (trieIterator.hasNext()) {
            trieIterator.next()
            trieIterator.remove()
        }
        assertEquals(1, trie.size, "Not all elements are deleted")
    }

}