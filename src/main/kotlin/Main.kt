fun main() {
    val player = Player()
    val ui = UI()
    val slotMachine = SlotMachine(player, ui)
    slotMachine.play()
}