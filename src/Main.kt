import java.math.BigInteger
import java.security.SecureRandom


fun main() {
    println(generatePrimeNumber(50, 20))
}

fun generatePrimeNumber(bitLength: Int, k: Int): BigInteger {
    val random = SecureRandom()
    var a: BigInteger
    do {
        a = BigInteger(bitLength, random)
    } while (!millerRabinTest(a, k))
    return a
}

fun randomBigInteger(number: BigInteger): BigInteger {

    val random = SecureRandom()
    var a: BigInteger
    do {
        a = BigInteger(number.bitLength(), random)
    } while (a < 2.toBigInteger() || a >= number - 2.toBigInteger())
    return a
}

fun millerRabinTest(number: BigInteger, k: Int): Boolean {
    if (number == 2.toBigInteger() || number == 3.toBigInteger())
        return true

    if (number < 2.toBigInteger() || number.rem(2.toBigInteger()) == 0.toBigInteger())
        return false

    var d = number.minus(1.toBigInteger())

    var s = 0

    while (d.rem(2.toBigInteger()) == 0.toBigInteger()) {
        d /= 2.toBigInteger()
        s++
    }

    for (i in 0..k) {
        val a = randomBigInteger(number)
        var x = a.modPow(d, number)
        if (x == 1.toBigInteger() || x == (number - 1.toBigInteger()))
            continue
        for (r in 1..s) {
            x = x.modPow(2.toBigInteger(), number)
            if (x == 1.toBigInteger())
                return false
            if (x == (number - 1.toBigInteger()))
                break
        }
        if (x != (number - 1.toBigInteger()))
            return false
    }
    return true
}