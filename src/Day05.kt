import kotlin.math.abs

fun main() {
    fun checkEl(index: Int, input: List<Int>, orderedPairs: List<Pair<Int,Int>>): Boolean {
        for (i in 0..index - 1) {
            //println("checking "+i+"("+input.get(i)+") for "+index+"("+input.get(index)+") before :: " + orderedPairs.any { it.first == input.get(index) && it.second == input.get(i) })
            if (orderedPairs.any { it.first == input.get(index) && it.second == input.get(i) }) return false
        }
        for (i in index+1..input.size - 1) {
            //println("checking "+i+"("+input.get(i)+") for "+index+"("+input.get(index)+") after :: " + orderedPairs.any { it.first == input.get(i) && it.second == input.get(index) })
            if (orderedPairs.any { it.first == input.get(i) && it.second == input.get(index) }) return false
        }
        return true
    }

    fun check(input: List<Int>, orderedPairs: List<Pair<Int,Int>>) : Int {
        for (index in input.indices) {
            if (!checkEl(index, input, orderedPairs)) return 0
        }
        val middle = input.get(input.size/2)
        //println("valid sequence, middle "+middle+" "+input)
        return middle
    }

    fun sort(input: List<Int>, orderedPairs: List<Pair<Int, Int>>): Int {
        //println("sorting " + input)
        val mut = input.sortedWith({ o1, o2 -> if (orderedPairs.contains(Pair(o1,o2))) -1 else 1 })
        //println("sorted " + mut)
        val middle = mut.get(mut.size/2)
        return middle
    }

    fun part1(order:List<String>, input: List<String>): Int {
        val orderedPairs = order.map {
            val split = it.split("|")
            Pair(split[0].toInt(), split[1].toInt())
        }
        val list = input.map {
            val values = it.split(",").toList().map { it.toInt() }
            check(values, orderedPairs)
        }

        return list.reduce(Int::plus)
    }

    fun part2(order:List<String>, input: List<String>): Int {
        val orderedPairs = order.map {
            val split = it.split("|")
            Pair(split[0].toInt(), split[1].toInt())
        }
        val list = input.map {
            val values = it.split(",").toList().map { it.toInt() }
            if (check(values, orderedPairs) == 0) {
                sort(values, orderedPairs)
            } else {
                0
            }
        }

        return list.reduce(Int::plus)
    }

    // Read the input from the `src/Day01.txt` file.
    val order = readInput("input_05_order")
    val input = readInput("input_05")
    println(part1(order, input))
    println(part2(order, input))
}
