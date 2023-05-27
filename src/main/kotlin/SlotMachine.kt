import java.io.File
import javax.sound.sampled.AudioSystem

class SlotMachine(private val player: Player, private val ui: UI) {
    private val symbols = arrayOf("🍒", "🍇", "🍊", "🔔", "💎", "7️⃣")
    private val symbolMultiplier = mapOf(
        "🍒" to 0.2,
        "🍇" to 0.4,
        "🍊" to 0.6,
        "🔔" to 0.8,
        "💎" to 1.0,
        "7️⃣" to 1.2
    )
    private val board: Array<Array<String>> = Array(3) { Array(5) { "" } }

    fun play() {
        ui.printWelcomeMessage()
        player.setName(ui.getPlayerName())
        player.setCredits(ui.getPlayerCredits())

        while (player.hasCredits()) {
            ui.printCurrentStatus(player.getCredits())

            if (ui.wantToPlayAgain()) {

                val betAmount = ui.getBetAmount()
                if (player.placeBet(betAmount)) {
                    player.placeBet(betAmount)
                    spin()
                    val winAmount = calculateWin()
                    val totalWin = winAmount * betAmount
                    player.addCredits(totalWin)
                    ui.printSpinResult(board, totalWin)
                    playSpinSound()
                }
             else {
                println("Insufficient credits. Please place a valid bet.")
                continue
            }
            } else {
                break
            }
        }

        ui.printGoodbyeMessage()
    }

    private fun spin() {
        generateBoard()
    }

    private fun generateBoard() {
        for (i in board.indices) {
            for (j in board[i].indices) {
                board[i][j] = symbols.random()
            }
        }
    }

    private fun calculateWin(): Double {
        var totalWin = 0.0
        for (i in 0..2) {
            val row = board[i]
            if (row[0] == row[1]) {
                val symbol = row[0]
                if (symbol == "🍒") {
                    totalWin += symbolMultiplier[symbol]!!
                } else if (row[1] == row[2]) {
                    val count = row.count { it == symbol }
                    totalWin += symbolMultiplier[symbol]!! * count
                }
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            val symbol = board[0][0]
            val count = board.count { it[0] == symbol }
            totalWin += symbolMultiplier[symbol]!! * count
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            val symbol = board[2][0]
            val count = board.count { it[2] == symbol }
            totalWin += symbolMultiplier[symbol]!! * count
        }
        return totalWin
    }

    private fun playSpinSound() {
        val soundFile = File("spin_sound.wav")
        val audioInputStream = AudioSystem.getAudioInputStream(soundFile)
        val clip = AudioSystem.getClip()
        clip.open(audioInputStream)
        clip.start()
    }
}