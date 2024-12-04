
fun main() {

    fun part1(input: List<String>): Int {
        val list = input.map {

            val regex = """mul\(([0-9]{1,3}),([0-9]{1,3})\)""".toRegex()
            val map = regex.findAll(it).map {
                val (first, second) = it.destructured
                first.toInt() * second.toInt()
            }

            map.reduce { acc, i -> acc + i }
        }
        return list.reduce { acc, i -> acc + i}
    }

    fun part2(input: List<String>): Int {
        var disabled = false
        val list = input.map {
            val regex = """do\(\)|don't\(\)|mul\(([0-9]{1,3}),([0-9]{1,3})\)""".toRegex()
            val map = regex.findAll(it).map {
                if (it.value.startsWith("don")) {
                    disabled = true
                    //println(it.value + " 0")
                    0
                } else if (it.value.startsWith("do")) {
                    disabled = false
                    //println(it.value + " 0")
                    0
                } else {
                    val (first, second) = it.destructured
                    //println(it.value + " " + if (disabled) 0 else first.toInt() * second.toInt())
                    if (disabled) 0 else first.toInt() * second.toInt()
                }
            }

            map.reduce { acc, i -> acc + i }
        }
        return list.reduce { acc, i -> acc + i}
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("input_03")
    println(part1(input))
    println(part2(input))
}
