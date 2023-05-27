
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UITest {
    private lateinit var ui: UI

    @BeforeAll
    fun setUp() {
        ui = UI()
    }

    @Test
    fun getPlayerCredits_ValidInput_ReturnsRoundedCredits() {
        val input = "123.456"
        val result = ui.getPlayerCreditsTestable(input)
        assertEquals(123.5, result, 0.0)
    }

    @Test
    fun getPlayerCredits_InvalidInput_ReturnsZero() {
        val input = "abc"
        val result = ui.getPlayerCreditsTestable(input)
        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun getBetAmount_ValidInput_ReturnsRoundedBetAmount() {
        val input = "12.345"
        val result = ui.getBetAmountTestable(input)
        assertEquals(12.3, result, 0.0)
    }

    @Test
    fun getBetAmount_InvalidInput_ReturnsZero() {
        val input = "xyz"
        val result = ui.getBetAmountTestable(input)
        assertEquals(0.0, result, 0.0)
    }
}