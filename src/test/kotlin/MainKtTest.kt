import org.junit.Test
import kotlin.test.assertEquals

class MainKtTest {

    @Test
    fun testVisaCardCommission() {
        val result = calcCommission(cardType = "Visa", transfer = 12_000)
        assertEquals("Комиссия составит 90 руб.", result)
    }

    @Test
    fun testVisaCardCommissionBelowMinimum() {
        val result = calcCommission(cardType = "Visa", transfer = 4_000) // 4,000 * 0.0075 = 30, но минимальная комиссия 35
        assertEquals("Комиссия составит 35 руб.", result)
    }

    @Test
    fun testMirCardCommission() {
        val result = calcCommission(cardType = "Mir", transfer = 12_000)
        assertEquals("Комиссия не взимается", result)
    }

    @Test
    fun testMastercardCardCommissionNoFee() {
        val result = calcCommission(cardType = "Mastercard", monthlyTransfers = 50_000, transfer = 20_000)
        assertEquals("Комиссия не взимается", result)
    }

    @Test
    fun testExceedDailyLimit() {
        val result = calcCommission(cardType = "Visa", transfer = 200_000)
        assertEquals("Превышен дневной лимит", result)
    }

    @Test
    fun testExceedMonthlyLimit() {
        val result = calcCommission(cardType = "Visa", monthlyTransfers = 600_000, transfer = 1)
        assertEquals("Превышен месячный лимит", result)
    }

    @Test
    fun testInvalidCardType() {
        val result = calcCommission(cardType = "Unknown", transfer = 12_000)
        assertEquals("Неверный ввод", result)
    }

    @Test
    fun testLargeMastercardTransfer() {
        val result = calcCommission(cardType = "Mastercard", monthlyTransfers = 80_000, transfer = 100_000)
        assertEquals("Комиссия составит 620 руб.", result)
    }

    @Test
    fun testMastercardCardCommissionOverLimit() {
        val result = calcCommission(cardType = "Mastercard", monthlyTransfers = 60_000, transfer = 25_000)
        assertEquals("Комиссия составит 80 руб.", result)
    }

}