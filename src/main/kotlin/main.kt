fun main() {
    println(calcCommission(cardType = "Visa", transfer = 12000))
//    println(calcCommission("Visa", transfer = 77_000))
//    println(calcCommission("Mastercard", transfer = 30_000))
//    println(calcCommission("Mastercard", 80_000, 50_000))
}

fun calcCommission(cardType: String = "Mir", monthlyTransfers: Int = 0, transfer: Int): String {
    val dailyLimit = 150_000
    val monthlyLimit = 600_000
    val limitMastercard = 75_000

    if (transfer > dailyLimit) {
        return "Превышен дневной лимит"
    }

    if (transfer + monthlyTransfers > monthlyLimit) {
        return "Превышен месячный лимит"
    }

    val commission = when (cardType) {
        "Mir" -> 0
        "Visa" -> {
            val calculated = (transfer * 0.0075).toInt()
            Math.max(calculated, 35)
        }
        "Mastercard" -> {
            if (monthlyTransfers < limitMastercard) {
                val amountOverLimit = (monthlyTransfers + transfer) - limitMastercard
                if (amountOverLimit > 0) {
                    (amountOverLimit * 0.006 + 20).toInt()
                } else {
                    0
                }
            } else {
                (transfer * 0.006 + 20).toInt()
            }
        }
        else -> return "Неверный ввод"
    }

    return if (commission == 0) {
        "Комиссия не взимается"
    } else {
        "Комиссия составит $commission руб."
    }
}
