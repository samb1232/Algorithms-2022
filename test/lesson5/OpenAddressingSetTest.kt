package lesson5

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OpenAddressingSetTest : AbstractOpenAddressingSetTest() {

    override fun <T : Any> create(bits: Int): MutableSet<T> {
        return OpenAddressingSet(bits)
    }

    @Test
    @Tag("Example")
    fun addTestJava() {
        doAddTest()
    }

    @Test
    @Tag("7")
    fun removeTestJava() {
        doRemoveTest()

        // My tests
        val hset = OpenAddressingSet<Int>(3)
        hset.add(5)
        assertTrue { hset.remove(5) }
        assertEquals(0, hset.size);
        assertFailsWith<NullPointerException> { hset.remove(null) }
    }

    @Test
    @Tag("5")
    fun iteratorTestJava() {
        doIteratorTest()
    }

    @Test
    @Tag("8")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()

        // My tests
        val hashSet = OpenAddressingSet<Int>(3)
        hashSet.add(5)
        hashSet.add(10)
        hashSet.add(15)
        hashSet.add(20)
        assertTrue { hashSet.contains(5) }
        val hsetIterator = hashSet.iterator()
        hsetIterator.next()
        hsetIterator.next()
        hsetIterator.remove()
        assertFalse { hashSet.contains(20) }
    }
}