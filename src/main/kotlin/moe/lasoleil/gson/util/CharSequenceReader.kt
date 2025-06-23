package moe.lasoleil.gson.util

import java.io.Reader

/**
 * Returns a [Reader] that reads from this [CharSequence].
 *
 * It has the following features:
 * - **Not** thread-safe.
 * - Read directly from the original [CharSequence], which means if it's changed later,
 * the [Reader] will be changed at the same time.
 * - Supports the `mark()` and `reset()` operations.
 * - Always returns `true` for `ready()`.
 * - The `skip(n)` method skips up to `n` characters or until the end of the sequence.
 * - The `close()` method is a no-op; the reader does not manage external resources.
 *
 * @see java.io.StringReader
 * @see java.io.Reader
 */
fun CharSequence.reader(): Reader = object : Reader() {
    private var pos = 0
    private var mark = 0

    override fun read(): Int {
        return if (pos < length) this@reader[pos++].code else -1
    }

    override fun read(cbuf: CharArray, off: Int, len: Int): Int {
        if (pos >= length) return -1
        val n = minOf(len, length - pos)
        for (i in 0 until n) {
            cbuf[off + i] = this@reader[pos++]
        }
        return n
    }

    override fun skip(n: Long): Long {
        val skip = minOf(n, (length - pos).toLong()).coerceAtLeast(0)
        pos += skip.toInt()
        return skip
    }

    override fun ready(): Boolean = true

    override fun markSupported(): Boolean = true

    override fun mark(readAheadLimit: Int) {
        mark = pos
    }

    override fun reset() {
        pos = mark
    }

    override fun close() {}
}
