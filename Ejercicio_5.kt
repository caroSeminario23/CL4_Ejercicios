fun main() {
    val firstMeasurement = 27.0
    val secondMeasurement = 350.0
    val thirdMeasurement = 10.0

    printFinalTemperature(firstMeasurement, "Celsius", "Fahrenheit", celsiusToFahrenheit)
    printFinalTemperature(secondMeasurement, "Kelvin", "Celsius", kelvinToCelsius)
    printFinalTemperature(thirdMeasurement, "Fahrenheit", "Kelvin", fahrenheitToKelvin)
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

val celsiusToFahrenheit: (Double) -> Double = { celsius ->
    celsius * 9 / 5 + 32
}

val kelvinToCelsius: (Double) -> Double = { kelvin ->
    kelvin - 273.15
}

val fahrenheitToKelvin: (Double) -> Double = { fahrenheit ->
    (fahrenheit - 32) * 5 / 9 + 273.15
}