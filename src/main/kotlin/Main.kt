fun main() {
    val player = Player()
    val ui = UI()
    val slotMachine = SlotMachine(player, ui)

    ui.printWelcomeMessage()
    println("Please enter your name: ")
    val name = readLine()
    ui.setPlayerName(name ?: "")

    slotMachine.play()
}