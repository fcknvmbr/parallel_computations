
fun random(lowerBound: Double, upperBound: Double): Double {
    return Math.random() * (upperBound - lowerBound) + lowerBound
}

fun sigmoid(value: Double) =
        1.0 / (1.0 + Math.exp(-value))

fun sigmoidDerivative(value: Double) =
        value * (1 - value)