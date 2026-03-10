package terminal

data class Cell(
    val ch: Char = ' ',
    val attributes: Attributes = Attributes()
)