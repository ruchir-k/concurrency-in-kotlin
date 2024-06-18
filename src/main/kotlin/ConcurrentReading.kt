import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlin.time.measureTime
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


@OptIn(DelicateCoroutinesApi::class)
suspend fun main() = runBlocking {

    val allIntegers = mutableListOf<Int>()

    val readingTime = measureTime {
        val defrreds = (1..3).map { i ->
            GlobalScope.async {
                readFile("file$i.txt")
            }
        }
        defrreds.forEach { deferred ->
            allIntegers.addAll(deferred.await())
        }
    }

    val sortingTime = measureTime {
        allIntegers.sort()
    }

    println("reading time: $readingTime")
    println("sorting time: $sortingTime")

    createFileWithIntegers("sorted.txt", allIntegers)
}