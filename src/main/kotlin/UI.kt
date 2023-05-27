class UI {
    fun printWelcomeMessage() {
        println("Welcome to Slizzing Hot!")
    }

    fun getPlayerName(): String {
        println("Please enter your name: ")
        return readLine() ?: ""
    }

    fun getPlayerCredits(): Double {
        println("Hello, ${getPlayerName()}, how much money do you want to play with?")
        return readLine()?.toDoubleOrNull() ?: 0.0
    }

    fun printCurrentStatus(credits: Double) {
        println("You have $credits credits.")
    }

    fun wantToPlayAgain(): Boolean {
        println("Press enter to spin or type 'quit' to exit.")
        val input = readLine() ?: ""
        return input != "quit"
    }

    fun getBetAmount(): Double {
        println("How much do you want to bet?")
        return readLine()?.toDoubleOrNull() ?: 0.0
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

    fun printGoodbyeMessage() {
        println("You have no more credits left. Thanks for playing!")
    }
}