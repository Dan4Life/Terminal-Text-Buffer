package terminal

import kotlin.test.Test
import kotlin.test.assertEquals

class TerminalBufferScrollbackTest {

    @Test
    fun scrollingMovesTopLineIntoScrollback() {
        val buffer = TerminalBuffer(5, 2, 10)

        val line0 = buffer.getScreenLine(0)
        val line1 = buffer.getScreenLine(1)

        line0.fill('A')
        line1.fill('B')

        buffer.javaClass.getDeclaredMethod("scrollUp", Int::class.java)
            .apply { isAccessible = true }
            .invoke(buffer, 1)

        assertEquals(1, buffer.getScrollbackSize())
        assertEquals("BBBBB", buffer.getScreenLine(0).toPlainString())
        assertEquals("     ", buffer.getScreenLine(1).toPlainString())
    }

    @Test
    fun scrollbackDoesNotExceedMaxSize() {
        val buffer = TerminalBuffer(3, 2, 2)

        repeat(5) {
            buffer.getScreenLine(0).fill('A')
            buffer.getScreenLine(1).fill('B')

            buffer.javaClass.getDeclaredMethod("scrollUp", Int::class.java)
                .apply { isAccessible = true }
                .invoke(buffer, 1)
        }

        assertEquals(2, buffer.getScrollbackSize())
    }
}