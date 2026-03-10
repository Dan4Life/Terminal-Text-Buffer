package terminal

data class Attributes(
    val foreground: TerminalColor = TerminalColor.DEFAULT,
    val background: TerminalColor = TerminalColor.DEFAULT,
    val style: TextStyle = TextStyle()
)