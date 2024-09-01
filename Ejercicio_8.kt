open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(var isFolded: Boolean = true): Phone() {
    override fun switchOn() {
        if (isFolded == false) {
            isScreenLightOn = true
            println("Switching on the phone.")
        } else {
            println("Cannot switch on the phone because it is folded.")
        }
    }

    fun fold() {
        if (isFolded) {
            println("The phone is already folded.")
        } else {
            isFolded = true
            isScreenLightOn = false
            println("Folding the phone.")
        }
    }

    fun unfold() {
        if (isFolded) {
            isFolded = false
            println("Unfolding the phone.")
        } else {
            println("The phone is already unfolded.")
        }
    }
}

fun main() {
    val phone = FoldablePhone()

    phone.switchOn()
    phone.checkPhoneScreenLight()
    phone.fold()
    phone.switchOn()
    phone.unfold()
    phone.switchOn()
    phone.checkPhoneScreenLight()
    phone.fold()
    phone.checkPhoneScreenLight()
}