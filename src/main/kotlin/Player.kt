class Player {
    private var name: String = ""
    private var credits: Double = 0.0

    fun setName(name: String) {
        this.name = name
    }
    fun getName():String{
        return name
    }

    fun setCredits(credits: Double) {
        this.credits = credits
    }

    fun hasCredits(): Boolean {
        return credits > 0
    }

    fun getCredits(): Double {
        return credits
    }

    fun placeBet(betAmount: Double) {
        credits -= betAmount
    }

    fun addCredits(creditAmount: Double) {
        credits += creditAmount
    }
}