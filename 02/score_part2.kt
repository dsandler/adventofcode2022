// https://adventofcode.com/2022/day/2

fun log(s: String) {
    println(s)
}

fun inputLines(): Sequence<String> = sequence {
    while (true)
        readLine()?.let { yield(it) } ?: break
}

enum class Shape {
    ROCK {
        override fun score() = 1
        override fun beats() = SCISSORS
        override fun beater() = PAPER
    }, PAPER {
        override fun score() = 2
        override fun beats() = ROCK
        override fun beater() = SCISSORS
    }, SCISSORS {
        override fun score() = 3
        override fun beats() = PAPER
        override fun beater() = ROCK
    };
    abstract fun score(): Int
    abstract fun beats(): Shape
    abstract fun beater(): Shape
}

fun String.toShape(): Shape {
    return when (this) {
        "A" -> Shape.ROCK
        "B" -> Shape.PAPER
        "C" -> Shape.SCISSORS
        else -> throw IllegalArgumentException()
    }
}

enum class Result {
    WIN {
        override fun score() = 6
    }, DRAW {
        override fun score() = 3
    }, LOSS {
        override fun score() = 0
    };
    abstract fun score(): Int
}

fun String.toResult(): Result {
    return when (this) {
        "X" -> Result.LOSS
        "Y" -> Result.DRAW
        "Z" -> Result.WIN
        else -> throw IllegalArgumentException()
    }
}

fun scoreRound(me: Shape, them: Shape): Int {
    if (me == them) return Result.DRAW.score()
    else if (me == them.beater()) return Result.WIN.score()
    else return Result.LOSS.score()
}

fun score(lines: Sequence<String>): Int {
    var score = 0
    lines.forEach { line ->
        log(">> ${line}")
        val theirShape = line.substring(0,1).toShape()
        val result = line.substring(2,3).toResult()
        val myShape = when(result) {
            Result.DRAW -> theirShape
            Result.WIN -> theirShape.beater()
            Result.LOSS -> theirShape.beats()
        }
        log("   ${theirShape} (them) vs ${myShape} (me)")
        log("   SHAPE SCORE: ${myShape.score()}")
        log("   ROUND SCORE: ${scoreRound(myShape, theirShape)}")
        score += myShape.score() + scoreRound(myShape, theirShape)
    }
    return score
}

fun main() {
    println(score(inputLines()))
}

// vim:ft=kotlin:sw=4:et:sts=4:
