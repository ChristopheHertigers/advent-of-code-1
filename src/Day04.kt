
fun main() {

    fun part1(input: List<String>): Int {
        val map = input.map { it.toList() }
        //println(map)
        var total = 0
        for (y in map.indices) {
            val row = map.get(y)
            for (x in row.indices) {
                if (row.get(x) == 'X') {
                    //println(""+x+","+y)

                    // search up
                    if (y >= 3) {
                        var word = ""
                        for (i in y downTo y-3) {
                            word += map.get(i).get(x)
                        }
                        if (word == "XMAS") {
                            //println("match "+x+","+y+" up")
                            total += 1
                        }
                    }
                    // search down
                    if (y <= map.size - 4) {
                        var word = ""
                        for (i in y..y+3) {
                            word += map.get(i).get(x)
                        }
                        if (word == "XMAS") {
                            //println("match "+x+","+y+" down")
                            total += 1
                        }
                    }

                    // search left
                    if (x >= 3) {
                        var word = ""
                        for (i in x downTo x-3) {
                            word += map.get(y).get(i)
                        }
                        if (word == "XMAS") {
                            //println("match "+x+","+y+" left")
                            total += 1
                        }
                    }

                    // search right
                    if (x <= row.size - 4) {
                        var word = ""
                        for (i in x..x+3) {
                            word += map.get(y).get(i)
                        }
                        if (word == "XMAS") {
                            //println("match "+x+","+y+" right")
                            total += 1
                        }
                    }

                    // diag up left
                    if (y >= 3 && x >= 3) {
                        var word = ""
                        for (i in 0..3) {
                            word += map.get(y-i).get(x-i)
                        }
                        if (word == "XMAS") {
                            //println("match "+x+","+y+" up left")
                            total += 1
                        }
                    }

                    // diag up right
                    if (y >= 3 && x <= row.size - 4) {
                        var word = ""
                        for (i in 0..3) {
                            word += map.get(y-i).get(x+i)
                        }
                        if (word == "XMAS") {
                            //println("match "+x+","+y+" up right")
                            total += 1
                        }
                    }

                    // diag down left
                    if (y <= map.size - 4 && x >= 3) {
                        var word = ""
                        for (i in 0..3) {
                            word += map.get(y+i).get(x-i)
                        }
                        if (word == "XMAS") {
                            //println("match "+x+","+y+" down left")
                            total += 1
                        }
                    }

                    // diag down right
                    if (y <= map.size - 4 && x <= row.size - 4) {
                        var word = ""
                        for (i in 0..3) {
                            word += map.get(y+i).get(x+i)
                        }
                        if (word == "XMAS") {
                            //println("match "+x+","+y+" down right")
                            total += 1
                        }
                    }

                }
            }
        }

        return total
    }

    fun part2(input: List<String>): Int {
        val map = input.map { it.toList() }
        var total = 0
        for (y in map.indices) {
            val row = map.get(y)
            for (x in row.indices) {
                if (row.get(x) == 'A') {
                    if (y >= 1 && x >= 1 && y <= map.size - 2 && x <= row.size - 2) {
                        val word1 = "" + map.get(y-1).get(x-1) + map.get(y).get(x) + map.get(y+1).get(x+1)
                        val word2 = "" + map.get(y-1).get(x+1) + map.get(y).get(x) + map.get(y+1).get(x-1)
                        if ((word1 == "MAS" || word1 == "SAM")
                            && (word2 == "MAS" || word2 == "SAM")) {
                            total += 1
                        }
                    }
                }
            }
        }
        return total
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("input_04")
    println(part1(input))
    println(part2(input))
}
