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
    require(args.size % 2 == 0) { "invalid number of arguments" }

    var to = Scheme.DEVANĀGARĪ
    var from = Scheme.UAST

    var i = 0
    while (i < args.size) {
        if (args[i + 1] == "devanagari") {
            args[i + 1] = "devanāgarī"
        }

        args[i + 1] = args[i + 1].uppercase()
        val s = Scheme.valueOf(args[i + 1])
        if (args[i] == "-to") {
            require(toSchemes.contains(s)) { "invalid value for `to`" }
            to = s
            i += 2
            continue
        }

        if (args[i] == "-from") {
            require(fromSchemes.contains(s)) { "invalid value for `from`" }
            from = s
            i += 2
            continue
        }

        require(!args[i].matches("-\\w+".toRegex())) { "illegal argument passed " + args[i] }
        i += 2
    }

    val transforms = UAST.convertor[from]!![to]!!
    Scanner(System.`in`, StandardCharsets.UTF_8).use { `in` ->
        PrintWriter(
            System.out, true
        ).use { out ->
            out.println("`from`: $from")
            out.println("`to`: $to")
            while (`in`.hasNextLine()) {
                val x = `in`.nextLine()

                for (l in x.split("\\n".toRegex()).dropLastWhile { it.isEmpty() }) {
                    val s = l.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                    val arr = ArrayList<String>(s.size)
                    for (w in s) {
                        var ij = w
                        for (f in transforms) {
                            ij = f.apply(ij)
                        }
                        arr.add(ij)
                    }
                    out.println(java.lang.String.join(" ", arr))
                }
            }
        }
    }
}
