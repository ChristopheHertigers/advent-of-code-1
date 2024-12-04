import kotlin.math.abs

fun main() {
    fun part(input: List<String>, index: Int): List<Int> {
        return input.map {
            it.split("   ")[index].trim().toInt()
        }
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("input_01")
    val list1 = part(input, 0).sorted()
    val list2 = part(input, 1).sorted()

    val result = list1.zip(list2).map {
        abs(it.first - it.second)
    }

    val sum = result.reduce { acc, i -> acc + i }

    println(list1)
    println(list2)
    println(result)
    println(sum)

    val result2 = list1.toMutableList().map {
        val value: Int = it
        val count = list2.count { it == value }
        value * count
    }
    val sum2 = result2.reduce { acc, i -> acc + i }

    println(result2)
    println(sum2)
}
