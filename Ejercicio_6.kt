class Song(
    val title: String, 
    val artist: String, 
    val publicationYear: Int,
    val totalReproductions: Int    
) {
    val isPopular: Boolean
        get() = totalReproductions >= 1000

    fun printDescription() {
        println("$title, intepretada por $artist, se lanzó en $publicationYear.")
    }
}

fun main() {
    val songOne = Song("Fire", "BTS", 2016, 1500)
    songOne.printDescription()

    println("¿Es ${songOne.title} popular? ${songOne.isPopular}")
}