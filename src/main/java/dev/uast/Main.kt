package dev.uast

import java.io.PrintWriter
import java.nio.charset.StandardCharsets
import java.util.*

private val fromSchemes: Set<Scheme> = setOf(
    Scheme.UAST,
    Scheme.RAW,
    Scheme.DEVANĀGARĪ,
    Scheme.IAST,
    Scheme.SLP,
    Scheme.GU,
    Scheme.OR,
    Scheme.TA,
    Scheme.TE,
    Scheme.ML,
    Scheme.KN
)

private val toSchemes: Set<Scheme> = setOf(
    Scheme.UAST, Scheme.DEVANĀGARĪ, Scheme.IAST, Scheme.GU, Scheme.OR, Scheme.TA, Scheme.TE, Scheme.ML, Scheme.KN
)

fun main(args: Array<String>) {
    require(args.size % 2 == 0) {
        "invalid number of arguments"
    }

    var to = Scheme.DEVANĀGARĪ
    var from = Scheme.UAST

    for (i in args.indices step 2) {
        if (args[i + 1] == "devanagari") {
            args[i + 1] = "devanāgarī"
        }

        args[i + 1] = args[i + 1].uppercase()
        val s = Scheme.valueOf(args[i + 1])
        if (args[i] == "-to") {
            require(toSchemes.contains(s)) {
                "invalid value for `to`"
            }
            to = s
            continue
        }

        if (args[i] == "-from") {
            require(fromSchemes.contains(s)) {
                "invalid value for `from`"
            }
            from = s
            continue
        }

        require(!args[i].matches("-\\w+".toRegex())) {
            "illegal argument passed ${args[i]}"
        }
    }

    val transforms = UAST.convertor[from]!![to]!!
    Scanner(System.`in`, StandardCharsets.UTF_8).use { input ->
        PrintWriter(
            System.out, true
        ).use { output ->
            output.println("`from`: $from")
            output.println("`to`: $to")
            while (input.hasNextLine()) {
                val x = input.nextLine()

                for (l in x.split("\n")) {
                    val s = l.split(" ")
                    val arr = mutableListOf<String>()
                    for (w in s) {
                        var i = w
                        for (f in transforms) {
                            i = f.apply(i)
                        }
                        arr.add(i)
                    }
                    output.println(arr.joinToString(" "))
                }
            }
        }
    }
}
