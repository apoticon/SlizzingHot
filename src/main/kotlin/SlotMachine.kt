import java.io.File
import javax.sound.sampled.AudioSystem
import kotlin.math.round

class SlotMachine(private val player: Player, private val ui: UI) {
    private val symbols = arrayOf("🍒", "🍇", "🍊", "🔔", "💎", "7️⃣")
    val winTable = mapOf(
        "🍒" to mapOf(2 to 5, 3 to 20, 4 to 50, 5 to 200),
        "🍇" to mapOf(3 to 50, 4 to 200, 5 to 500),
        "🍊" to mapOf(3 to 20, 4 to 50, 5 to 200),
        "🔔" to mapOf(3 to 20, 4 to 50, 5 to 200),
        "💎" to mapOf(3 to 10, 4 to 50, 5 to 250),
        "7️⃣" to mapOf(3 to 100, 4 to 1000, 5 to 5000)
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
        val symbolInstances = mutableMapOf<String, Int>()
        var totalWin = 0.0

        for (i in 0..2) {
            val row = board[i]
            if (row[0] == row[1]) {
                val symbol = row[0]
                if (symbol == "🍒") {
                    symbolInstances[symbol] = symbolInstances.getOrDefault(symbol, 0) + 2
                } else if (row[1] == row[2]) {
                    symbolInstances[symbol] = symbolInstances.getOrDefault(symbol, 0) + 3
                }
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            val symbol = board[0][0]
            symbolInstances[symbol] = symbolInstances.getOrDefault(symbol, 0) + 3
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            val symbol = board[2][0]
            symbolInstances[symbol] = symbolInstances.getOrDefault(symbol, 0) + 3
        }

        for ((symbol, instances) in symbolInstances) {
            val reward = winTable[symbol]?.getOrDefault(instances, 0) ?: 0
            totalWin += reward
        }

        totalWin = roundToOneDecimal(totalWin)
        return totalWin
    }
    private fun roundToOneDecimal(value: Double): Double {
        return round(value * 10.0) / 10.0
    }
    private fun playSpinSound() {
        val soundFile = File("spin_sound.wav")
        val audioInputStream = AudioSystem.getAudioInputStream(soundFile)
        val clip = AudioSystem.getClip()
        clip.open(audioInputStream)
        clip.start()
    }
}