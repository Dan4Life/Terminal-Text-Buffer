package terminal

class Line private constructor(
    private val width: Int,
    private val cells: MutableList<Cell>
) {
    companion object {
        fun blank(width: Int): Line {
            require(width > 0)
            return Line(width, MutableList(width) { Cell() })
        }
    }

    fun copy(): Line = Line(width, cells.toMutableList())

    fun size(): Int = width

    fun getCell(column: Int): Cell {
        require(column in 0 until width)
        return cells[column]
    }

    fun setCell(column: Int, cell: Cell) {
        require(column in 0 until width)
        cells[column] = cell
    }

    fun fill(ch: Char = ' ', attributes: Attributes = Attributes()) {
        for (i in 0 until width) {
            cells[i] = Cell(ch, attributes)
        }
    }

    fun toPlainString(): String {
        return buildString(width) {
            for (cell in cells) {
                append(cell.ch)
            }
        }
    }
}