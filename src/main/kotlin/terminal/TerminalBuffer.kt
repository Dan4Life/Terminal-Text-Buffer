package terminal

class TerminalBuffer(
    val width: Int,
    val height: Int,
    private val scrollbackMaxSize: Int
) {

    init {
        require(width > 0)
        require(height > 0)
        require(scrollbackMaxSize >= 0)
    }

    private val screen: MutableList<Line> =
        MutableList(height) { Line.blank(width) }

    private val scrollback: ArrayDeque<Line> = ArrayDeque()

    private var cursorRow: Int = 0
    private var cursorCol: Int = 0

    private var currentAttributes: Attributes = Attributes()

    fun getCursorRow(): Int = cursorRow

    fun getCursorColumn(): Int = cursorCol

    fun setCursorPosition(column: Int, row: Int) {
        cursorCol = column.coerceIn(0, width - 1)
        cursorRow = row.coerceIn(0, height - 1)
    }

    fun moveCursorUp(n: Int = 1) {
        require(n >= 0)
        cursorRow = (cursorRow - n).coerceAtLeast(0)
    }

    fun moveCursorDown(n: Int = 1) {
        require(n >= 0)
        cursorRow = (cursorRow + n).coerceAtMost(height - 1)
    }

    fun moveCursorLeft(n: Int = 1) {
        require(n >= 0)
        cursorCol = (cursorCol - n).coerceAtLeast(0)
    }

    fun moveCursorRight(n: Int = 1) {
        require(n >= 0)
        cursorCol = (cursorCol + n).coerceAtMost(width - 1)
    }

    fun setAttributes(attributes: Attributes) {
        currentAttributes = attributes
    }

    fun getCurrentAttributes(): Attributes {
        return currentAttributes
    }

    fun getScreenHeight(): Int = height

    fun getScreenWidth(): Int = width

    fun getScrollbackSize(): Int = scrollback.size

    fun getTotalLineCount(): Int {
        return scrollback.size + height
    }

    internal fun getScreenLine(row: Int): Line {
        require(row in 0 until height)
        return screen[row]
    }

    internal fun pushLineToScrollback(line: Line) {
        if (scrollbackMaxSize == 0) return

        scrollback.addLast(line.copy())

        while (scrollback.size > scrollbackMaxSize) {
            scrollback.removeFirst()
        }
    }
}