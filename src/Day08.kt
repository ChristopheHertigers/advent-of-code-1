
class Holder8() {
    var maxX = 0;
    var maxY = 0
    val antennas = mutableMapOf<Char,MutableList<Pair<Int,Int>>>()
    val antinodes = mutableSetOf<Pair<Int,Int>>()

    fun init(input: List<String>) {
        input.forEachIndexed { y, value ->
            value.toCharArray().forEachIndexed { x, c ->
                if (c != '.') {
                    if (antennas.get(c) == null) {
                        antennas.put(c, mutableListOf())
                    }
                    antennas.get(c)?.add(Pair(x, y))
                }
            }
            maxX = value.toCharArray().size - 1
        }
        maxY = input.size - 1
    }

    fun compute2() {
        for (antenna in antennas) {
            val pairs = mutableSetOf<Pair<Pair<Int,Int>,Pair<Int,Int>>>()
            val allOfType = antenna.value.toMutableList()
            makeAntennaPairs(pairs, allOfType.get(0), allOfType.drop(1))
            for (pair in pairs) {
                computeForPairsPart2(pair.first, pair.second)
            }
        }
    }

    fun compute() {
        for (antenna in antennas) {
            val pairs = mutableSetOf<Pair<Pair<Int,Int>,Pair<Int,Int>>>()
            val allOfType = antenna.value.toMutableList()
            makeAntennaPairs(pairs, allOfType.get(0), allOfType.drop(1))
            for (pair in pairs) {
                computeForPairs(pair.first, pair.second)
            }
        }
    }

    fun makeAntennaPairs(set: MutableSet<Pair<Pair<Int,Int>,Pair<Int,Int>>>, el: Pair<Int,Int>, remaining: List<Pair<Int,Int>>) {
        if (remaining.isEmpty()) {
            return
        }
        remaining.forEach {
            set.add(Pair(el, it))
        }
        makeAntennaPairs(set, remaining.get(0), remaining.drop(1))
    }

    fun computeForPairs(antenna1: Pair<Int,Int>,antenna2: Pair<Int,Int>) {
        //println(antenna1) // 8,1    11,0
        //println(antenna2) // 5,2    2,3
        val firstX = antenna1.first+(antenna1.first - antenna2.first)
        val firstY = antenna1.second+(antenna1.second - antenna2.second)
        if (firstX in 0..maxX && firstY in 0..maxY) {
            antinodes.add(Pair(firstX, firstY))
        }
        val secondX = antenna2.first-(antenna1.first - antenna2.first)
        val secondY = antenna2.second-(antenna1.second - antenna2.second)
        if (secondX in 0..maxX && secondY in 0..maxY) {
            antinodes.add(Pair(secondX, secondY))
        }
    }

    fun computeForPairsPart2(antenna1: Pair<Int,Int>,antenna2: Pair<Int,Int>) {
        //println(antenna1) // 8,1    11,0
        //println(antenna2) // 5,2    2,3
        antinodes.add(antenna1)
        antinodes.add(antenna2)

        val deltaX = antenna1.first - antenna2.first
        val deltaY = antenna1.second - antenna2.second

        var firstX = antenna1.first+deltaX
        var firstY = antenna1.second+deltaY
        while (firstX in 0..maxX && firstY in 0..maxY) {
            antinodes.add(Pair(firstX, firstY))
            firstX += deltaX
            firstY += deltaY
        }

        var secondX = antenna2.first-deltaX
        var secondY = antenna2.second-deltaY
        while (secondX in 0..maxX && secondY in 0..maxY) {
            antinodes.add(Pair(secondX, secondY))
            secondX -= deltaX
            secondY -= deltaY
        }
    }

    fun print() {
        for (i in 0..maxY) {
            for (j in 0..maxX) {
                print(contains(Pair(j,i)))
            }
            println("")
        }
    }

    fun contains(pair: Pair<Int,Int>): Char {
        for (antenna in antennas) {
            if (antenna.value.contains(pair)) {
                return antenna.key
            }
        }
        if (antinodes.contains(pair)) {
            return '#'
        }
        return '.'
    }
}

fun main() {
    fun part1(input: List<String>): Long {
        val holder = Holder8()
        holder.init(input)
        holder.compute()
        //println(holder.antennas)
        //println(holder.antinodes)
        //holder.print()
        return holder.antinodes.size.toLong()
    }

    fun part2(input: List<String>): Long {
        val holder = Holder8()
        holder.init(input)
        holder.compute2()
        //holder.print()
        return holder.antinodes.size.toLong()
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("input_08")
    println(part1(input))
    println(part2(input))
}
