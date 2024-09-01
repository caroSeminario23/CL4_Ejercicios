import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "off"
        protected set

    open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }

    open fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    fun increaseSpeakerVolume() {
        if (deviceStatus == "on") {
            speakerVolume++
            println("Speaker volume increased to $speakerVolume.")
        } else {
            println("Cannot increase speaker volume. $name is turned off.")
        }
    }

    fun decreaseVolume() {
        if (deviceStatus == "on") {
            speakerVolume--
            println("Speaker volume decreased to $speakerVolume.")
        } else {
            println("Cannot decrease speaker volume. $name is turned off.")
        }
    }

    fun nextChannel() {
        if (deviceStatus == "on") {
            channelNumber++
            println("Channel number increased to $channelNumber.")
        } else {
            println("Cannot change channel. $name is turned off.")
        }
    }

    fun previousChannel() {
        if (deviceStatus == "on") {
            channelNumber--
            println("Channel number decreased to $channelNumber.")
        } else {
            println("Cannot change channel. $name is turned off.")
        }
    }

    override fun turnOn() {
        super.turnOn()
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness() {
        if (deviceStatus == "on") {
            brightnessLevel++
            println("Brightness increased to $brightnessLevel.")
        } else {
            println("Cannot increase brightness. $name is turned off.")
        }
    }

    fun decreaseBrightness() {
        if (deviceStatus == "on") {
            brightnessLevel--
            println("Brightness decreased to $brightnessLevel.")
        } else {
            println("Cannot decrease brightness. $name is turned off.")
        }
    }

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Smart Light turned off")
    }
}

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        if (smartTvDevice.deviceStatus == "off") {
            deviceTurnOnCount++
            smartTvDevice.turnOn()
        } else {
            println("Cannot turn on the TV. The TV is already turned on.")
        }
    }

    fun turnOffTv() {
        if (smartTvDevice.deviceStatus == "on") {
            deviceTurnOnCount--
            smartTvDevice.turnOff()
        } else {
            println("Cannot turn off the TV. The TV is already turned off.")
        }
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun decreaseTvVolume() {
        smartTvDevice.decreaseVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun changeTvChannelToPrevious() {
        smartTvDevice.previousChannel()
    }

    fun turnOnLight() {
        if (smartLightDevice.deviceStatus == "off") {
            deviceTurnOnCount++
            smartLightDevice.turnOn()
        } else {
            println("Cannot turn on the light. The light is already turned on.")
        }
    }

    fun turnOffLight() {
        if (smartLightDevice.deviceStatus == "on") {
            deviceTurnOnCount--
            smartLightDevice.turnOff()
        } else {
            println("Cannot turn off the light. The light is already turned off.")
        }
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun decreaseLightBrightness() {
        smartLightDevice.decreaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }

    fun printSmartTvInfo() {
        smartTvDevice.printDeviceInfo()
    }

    fun printSmartLightInfo() {
        smartLightDevice.printDeviceInfo()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    val smartHome = SmartHome(
        smartTvDevice = SmartTvDevice("Android TV", "Entertainment"),
        smartLightDevice = SmartLightDevice("Google Light", "Utility")
    )

    smartHome.turnOnTv()
    smartHome.increaseTvVolume()
    smartHome.decreaseTvVolume()
    smartHome.changeTvChannelToNext()
    smartHome.changeTvChannelToPrevious()
    smartHome.printSmartTvInfo()
    
    smartHome.turnOnLight()
    smartHome.increaseLightBrightness()
    smartHome.decreaseLightBrightness()
    smartHome.printSmartLightInfo()

    smartHome.turnOffAllDevices()
}