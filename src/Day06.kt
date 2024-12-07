import kotlin.math.abs

enum class Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    fun turn(): Direction {
        if (this.ordinal == entries.size - 1) { return UP }
        return entries[ordinal+1]
    }
}

class Room(val input: List<List<Int>>, var direction: Direction) {
    val area = input.toMutableList().map { it.toMutableList() }
    var currentX = -1
    var currentY = -1

    fun findStart() {
        area.forEachIndexed { y, list ->
            list.forEachIndexed { x, v ->
                if (v == 1) {
                    currentY = y
                    currentX = x
                }
            }
        }
    }

    fun upcoming(): Int {
        if (direction == Direction.UP) {
            if (currentY == 0) return -1
            return area[currentY-1][currentX]
        } else if (direction == Direction.RIGHT) {
            if (currentX+1 == area[currentY].size) return -1
            return area[currentY][currentX+1]
        } else if (direction == Direction.DOWN) {
            if (currentY+1 == area.size) return -1
            return area[currentY+1][currentX]
        } else {
            if (currentX == 0) return -1
            return area[currentY][currentX-1]
        }
    }

    fun moveOne(): Boolean {
        if (direction == Direction.UP) {
            if (currentY == 0
                || area[currentY-1][currentX] == 5
                || area[currentY-1][currentX] == 9) return false
            currentY--
        } else if (direction == Direction.RIGHT) {
            if (currentX+1 == area[currentY].size
                || area[currentY][currentX+1] == 5
                || area[currentY][currentX+1] == 9) return false
            currentX++
        } else if (direction == Direction.DOWN) {
            if (currentY+1 == area.size
                || area[currentY+1][currentX] == 5
                || area[currentY+1][currentX] == 9) return false
            currentY++
        } else {
            if (currentX == 0
                || area[currentY][currentX-1] == 5
                || area[currentY][currentX-1] == 9) return false
            currentX--
        }
        area[currentY][currentX] = 1
        return true
    }

    fun move(): Boolean {
        var leavingArea = false
        findStart()
//        println("start $currentX, $currentY")
        var currentSum = 0
        var foundNewObstacle = 0

        while (!leavingArea && foundNewObstacle < 20) {
            if (!moveOne()) {
                if (upcoming() == 9 || upcoming() == 5) {
                    direction = direction.turn()
                    if (currentSum == sum()) {
                        foundNewObstacle++
                    } else {
                        currentSum = sum()
                        foundNewObstacle = 0
                    }
                } else if (upcoming() == -1) {
                    leavingArea = true
                }
            }
        }

        return leavingArea
    }

    fun print() {
        area.forEach {
            println(it.joinToString(" "))
        }
    }

    fun sum(): Int {
        return area.map { it.map { if (it == 9 || it == 5) 0 else it }.reduce(Int::plus) }.reduce(Int::plus)
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val list = input.map {
            it.toCharArray().map {
                if (it == '.') 0 else if (it == '#') 9 else 1
            }
        }

        val room = Room(list, Direction.UP)

        room.move()

        return room.sum()
    }

    fun part2(input: List<String>): Int {
        val list = input.map {
            it.toCharArray().map {
                if (it == '.') 0 else if (it == '#') 9 else 1
            }
        }

        var newLocations = 0
//        var i=0
        for (i in list.indices) {
            println("trying $i")
            for (j in list[i].indices) {
//            for (j in 0..1) {
                val room = Room(list, Direction.UP)
                if (room.area[i][j] == 0) {
                    room.area[i][j] = 5
//                    room.print()
//                    println()
                    if (!room.move()) {
                        newLocations++
                    }
                }
            }
        }

        return newLocations
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("input_06")
    println(part1(input))
    println(part2(input))
}
