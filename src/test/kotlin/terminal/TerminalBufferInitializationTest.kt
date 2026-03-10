package terminal

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class TerminalBufferInitializationTest {
    @Test
    fun defaultTextStyleFlagsAreFalse() {
        val style = TextStyle()

        assertFalse(style.bold)
        assertFalse(style.italic)
        assertFalse(style.underline)
    }

    @Test
    fun defaultAttributesUseDefaultColorsAndStyle() {
        val attributes = Attributes()

        assertEquals(TerminalColor.DEFAULT, attributes.foreground)
        assertEquals(TerminalColor.DEFAULT, attributes.background)
        assertEquals(TextStyle(), attributes.style)
    }

    @Test
    fun blankLineHasExpectedWidthAndSpaces() {
        val line = Line.blank(5)

        assertEquals(5, line.size())
        assertEquals("     ", line.toPlainString())
    }

    @Test
    fun lineSetCellUpdatesContent() {
        val line = Line.blank(4)

        line.setCell(2, Cell('X', Attributes()))

        assertEquals("  X ", line.toPlainString())
        assertEquals('X', line.getCell(2).ch)
    }

    @Test
    fun lineFillReplacesAllCells() {
        val line = Line.blank(4)
        val attributes = Attributes(foreground = TerminalColor.RED)

        line.fill('a', attributes)

        assertEquals("aaaa", line.toPlainString())
        for (i in 0 until 4) {
            assertEquals('a', line.getCell(i).ch)
            assertEquals(attributes, line.getCell(i).attributes)
        }
    }
}