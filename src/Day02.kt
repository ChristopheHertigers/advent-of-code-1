import java.util.Collections.list
import kotlin.math.abs
import kotlin.math.max

fun main() {
    fun check(pairs: List<Pair<Int,Int>>) : Int {
        val order = pairs.all { pair -> pair.second > pair.first } || pairs.all { pair -> pair.second < pair.first }
        val diff = pairs.all { pair -> abs(pair.second - pair.first) >= 1 && abs(pair.second - pair.first) <= 3 }
        return if (order && diff) 1 else 0
    }

    fun part1(input: List<String>): Int {
        val list = input.map {
            val row = it.split(" ").toList().map { it.toInt() }
            val pairs = row.zipWithNext()
            check(pairs)
        }
        return list.reduce { acc, i -> acc + i}
    }

    fun part2(input: List<String>): Int {
        val list = input.map {
            val row = it.split(" ").toList().map { it.toInt() }
            val pairs = row.zipWithNext()
            var max = check(pairs)
            for (i in row.indices) {
                val p2 = row.toMutableList().apply {
                    removeAt(i)
                }.zipWithNext()
                max = max(max, check(p2))
            }
            max
        }
        return list.reduce { acc, i -> acc + i}
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("input_02")
    println(part1(input))
    println(part2(input))
}
