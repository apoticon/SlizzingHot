import kotlin.math.round
class UI {
    private var playerName: String = ""

    fun printWelcomeMessage() {
        println("Welcome to Slizzing Hot!")
    }

    fun getPlayerName(): String {
        return playerName
    }

    fun setPlayerName(name: String) {
        playerName = name
    }
    fun getPlayerCredits(): Double {
        println("Hello, how much money do you want to play with?")
        var credits: Double
        while (true) {
            val input = readLine()
            try {
                credits = input?.toDouble()?.let { roundToOneDecimal(it) } ?: throw NumberFormatException()
                if (credits < 0) {
                    throw NumberFormatException()
                }
                break
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter a number or a non-negative number of credits.")
            }
        }
        return credits
    }

    fun getBetAmount(): Double {
        println("How much do you want to bet?")
        var betAmount: Double
        while (true) {
            val input = readLine()
            try {
                betAmount = input?.toDouble()?.let { roundToOneDecimal(it) } ?: throw NumberFormatException()
                betAmount /=2
                if (betAmount < 0) {
                    throw NumberFormatException()
                }
                break
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter a number or a non-negative bet amount.")
            }
        }
        return betAmount
    }

    fun printCurrentStatus(credits: Double) {
        println("You have $credits credits.")
    }

    fun wantToPlayAgain(): Boolean {
        println("Press enter to spin or type 'quit' to exit.")
        val input = readLine() ?: ""
        return input != "quit"
    }

    fun printSpinResult(board: Array<Array<String>>, totalWin: Double) {
        println("You won $totalWin credits!")
        println("Board:")
        for (row in board) {
            for (symbol in row) {
                print("[$symbol]")
            }
            println()
        }
    }
    private fun roundToOneDecimal(value: Double): Double {
        return (round(value * 10.0) / 10.0)
    }
    fun printGoodbyeMessage() {
        println("You have no more credits left. Thanks for playing!")
    }

    fun getPlayerCreditsTestable(input: String): Double {
        return  getPlayerCredits()
    }

    fun getBetAmountTestable(input: String): Double {
        return getBetAmount()
    }
}