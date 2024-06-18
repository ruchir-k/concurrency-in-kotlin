import java.io.File
import kotlin.random.Random
import kotlin.time.measureTime

fun generateRandomIntegers(): List<Int> {
    return List(5000000) { Random.nextInt() }
}

fun createFileWithIntegers(filename:String, integers: List<Int>) {
    File("src/main/resources/$filename").writeText(integers.joinToString(separator = "\n"))
}

fun readFile(filename: String): List<Int> {
    return File("src/main/resources/$filename").readLines().map { it.toInt() }
}



fun main() {

//    for (i in 1..3) {
//        createFileWithIntegers("file$i.txt", generateRandomIntegers())
//    }

    val allIntegers = mutableListOf<Int>()

    val readingTime = measureTime {
        for (i in 1..3) {
            allIntegers.addAll(readFile("file$i.txt"))
        }
    }

    val sortingTime = measureTime {
        allIntegers.sort()
    }

    println("reading time: $readingTime")
    println("sorting time: $sortingTime")

    createFileWithIntegers("sorted.txt", allIntegers)
}