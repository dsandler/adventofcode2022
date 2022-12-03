// https://adventofcode.com/2022/day/1

fun log(s: String) {
    //println(s)
}

fun inputLines(): Sequence<String> = sequence {
    while (true)
        readLine()?.let { yield(it) } ?: break
}

enum class Shape {
    ROCK {
        override fun score() = 1
    }, PAPER {
        override fun score() = 2
    }, SCISSORS {
        override fun score() = 3
    };
    abstract fun score(): Int
}

fun String.toShape(): Shape {
    return when (this) {
        "A" -> Shape.ROCK
        "X" -> Shape.ROCK
        "B" -> Shape.PAPER
        "Y" -> Shape.PAPER
        "C" -> Shape.SCISSORS
        "Z" -> Shape.SCISSORS
        else -> throw IllegalArgumentException()
    }
}

val SCORE_LOSS = 0
val SCORE_DRAW = 3
val SCORE_WIN = 6

fun scoreRound(me: Shape, them: Shape): Int {
    if (me == them) return SCORE_DRAW
    return when(me) {
        Shape.ROCK -> if (them == Shape.SCISSORS) SCORE_WIN else SCORE_LOSS
        Shape.PAPER -> if (them == Shape.ROCK) SCORE_WIN else SCORE_LOSS
        Shape.SCISSORS -> if (them == Shape.PAPER) SCORE_WIN else SCORE_LOSS
    }
}

fun score(lines: Sequence<String>): Int {
    var score = 0
    lines.forEach { line ->
        log(">> ${line}")
        val theirShape = line.substring(0,1).toShape()
        val myShape = line.substring(2,3).toShape()
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
