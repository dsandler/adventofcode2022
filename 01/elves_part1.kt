// https://adventofcode.com/2022/day/1

fun inputLines(): Sequence<String> = sequence {
    while (true)
        readLine()?.let { yield(it) } ?: break
}

fun elves(lines: Sequence<String>): Sequence<Int> = sequence {
    var calories = 0
    lines.forEach { line ->
        if (line.isEmpty()) {
            yield(calories)
            calories = 0
        } else {
            calories += line.toInt()
        }
    }
    // don't forget the last elf!
    yield(calories)
}

fun main() {
    println(elves(inputLines()).max())
}

// vim:ft=kotlin:sw=4:et:sts=4:
