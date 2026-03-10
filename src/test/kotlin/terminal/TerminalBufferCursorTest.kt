package terminal

import kotlin.test.Test
import kotlin.test.assertEquals

class TerminalBufferCursorTest {

    @Test
    fun cursorStartsAtOrigin() {
        val buffer = TerminalBuffer(10, 5, 100)

        assertEquals(0, buffer.getCursorRow())
        assertEquals(0, buffer.getCursorColumn())
    }

    @Test
    fun setCursorClampsToScreenBounds() {
        val buffer = TerminalBuffer(10, 5, 100)

        buffer.setCursorPosition(50, 50)

        assertEquals(4, buffer.getCursorRow())
        assertEquals(9, buffer.getCursorColumn())
    }

    @Test
    fun moveCursorUpStopsAtTop() {
        val buffer = TerminalBuffer(10, 5, 100)

        buffer.moveCursorUp(5)

        assertEquals(0, buffer.getCursorRow())
    }

    @Test
    fun moveCursorDownStopsAtBottom() {
        val buffer = TerminalBuffer(10, 5, 100)

        buffer.moveCursorDown(100)

        assertEquals(4, buffer.getCursorRow())
    }

    @Test
    fun moveCursorLeftStopsAtLeftEdge() {
        val buffer = TerminalBuffer(10, 5, 100)

        buffer.moveCursorLeft(10)

        assertEquals(0, buffer.getCursorColumn())
    }

    @Test
    fun moveCursorRightStopsAtRightEdge() {
        val buffer = TerminalBuffer(10, 5, 100)

        buffer.moveCursorRight(100)

        assertEquals(9, buffer.getCursorColumn())
    }

    @Test
    fun attributesCanBeUpdated() {
        val buffer = TerminalBuffer(10, 5, 100)

        val attrs = Attributes(foreground = TerminalColor.RED)

        buffer.setAttributes(attrs)

        assertEquals(attrs, buffer.getCurrentAttributes())
    }
}