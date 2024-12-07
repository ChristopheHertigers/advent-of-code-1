
class Holder7(input: String) {
    val result = input.split(":")[0].toLong()
    val numbers = input.split(":")[1].trim().split(" ").map { it.toLong() }

    fun checkSum(sum: Long, partial: Long, remainingNumbers: List<Long>): Boolean {
        if (sum < partial) return false
        if (remainingNumbers.isEmpty()) return sum == partial
        return checkSum(sum, partial * remainingNumbers[0], remainingNumbers.subList(1, remainingNumbers.size))
                || checkSum(sum, partial + remainingNumbers[0], remainingNumbers.subList(1, remainingNumbers.size))
    }

    fun checkSumPart2(sum: Long, partial: Long, remainingNumbers: List<Long>): Boolean {
        if (sum < partial) return false
        if (remainingNumbers.isEmpty()) return sum == partial
        return checkSumPart2(sum, partial * remainingNumbers[0], remainingNumbers.subList(1, remainingNumbers.size))
                || checkSumPart2(sum, partial + remainingNumbers[0], remainingNumbers.subList(1, remainingNumbers.size))
                || checkSumPart2(sum,  "${partial}${remainingNumbers[0]}".toLong(), remainingNumbers.subList(1, remainingNumbers.size))
    }
}

fun main() {
    fun part1(input: List<String>): Long {
        var result:Long = 0
        for (s in input) {
            val holder = Holder7(s)
            if (holder.checkSum(holder.result, holder.numbers[0], holder.numbers.subList(1, holder.numbers.size))) {
                result += holder.result
            }
        }
        return result
    }

    fun part2(input: List<String>): Long {
        var result:Long = 0
        for (s in input) {
            val holder = Holder7(s)
            if (holder.checkSumPart2(holder.result, holder.numbers[0], holder.numbers.subList(1, holder.numbers.size))) {
                result += holder.result
            }
        }
        return result
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("input_07")
    println(part1(input))
    println(part2(input))
}
