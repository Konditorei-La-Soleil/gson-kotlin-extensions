package moe.lasoleil.gson.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.io.reader

class CharSequenceReaderTest {

    @Test
    fun `read single characters`() {
        val reader = "abc".reader()
        Assertions.assertEquals('a'.code, reader.read())
        Assertions.assertEquals('b'.code, reader.read())
        Assertions.assertEquals('c'.code, reader.read())
        Assertions.assertEquals(-1, reader.read()) // end of stream
    }

    @Test
    fun `read into buffer`() {
        val reader = "abcdef".reader()
        val buffer = CharArray(4)
        val count = reader.read(buffer, 0, 4)
        Assertions.assertEquals(4, count)
        Assertions.assertArrayEquals(charArrayOf('a', 'b', 'c', 'd'), buffer)
    }

    @Test
    fun `skip characters`() {
        val reader = "abcdef".reader()
        val skipped = reader.skip(3)
        Assertions.assertEquals(3, skipped)
        Assertions.assertEquals('d'.code, reader.read())
    }

    @Test
    fun `mark and reset works`() {
        val reader = "hello".reader()
        Assertions.assertEquals('h'.code, reader.read())
        reader.mark(10)
        Assertions.assertEquals('e'.code, reader.read())
        Assertions.assertEquals('l'.code, reader.read())
        reader.reset()
        Assertions.assertEquals('e'.code, reader.read()) // back to mark
    }

    @Test
    fun `ready and markSupported returns true`() {
        val reader = "test".reader()
        Assertions.assertTrue(reader.ready())
        Assertions.assertTrue(reader.markSupported())
    }

    @Test
    fun `read with buffer when already at end returns -1`() {
        val reader = "".reader()
        val buffer = CharArray(5)
        val result = reader.read(buffer, 0, 5)
        Assertions.assertEquals(-1, result)
    }

    @Test
    fun `close does not throw`() {
        val reader = "data".reader()
        Assertions.assertDoesNotThrow {
            reader.close()
        }
    }
}