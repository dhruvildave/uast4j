package dev.uast

import java.text.Normalizer

class UAST {
    companion object {
        val convertor = builder()

        private fun builder(): Map<Scheme, Map<Scheme, List<(String) -> String>>> {
            val builder = mutableMapOf<LangList, Map<FuncList, (String) -> String>>()

            LangList.entries.forEach {
                builder[it] = mapOf(
                    FuncList.HandleUnicode to createHandleUnicode(it),
                    FuncList.DataFunction to createDataFunction(it),
                    FuncList.ScriptToDevanāgarī to createScriptFunction(it)
                )
            }

            return mapOf(
                Scheme.RAW to mapOf(
                    Scheme.IAST to listOf(
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!
                    ),

                    Scheme.DEVANĀGARĪ to listOf(
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        UAST::IASTToUAST,
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.SA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.UAST to listOf(
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!, UAST::IASTToUAST
                    ),

                    Scheme.GU to listOf(
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        UAST::IASTToUAST,
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        builder[LangList.GU]!![FuncList.DataFunction]!!
                    ),

                    Scheme.OR to listOf(
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        UAST::IASTToUAST,
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        builder[LangList.OR]!![FuncList.DataFunction]!!
                    ),

                    Scheme.KN to listOf(
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        UAST::IASTToUAST,
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        builder[LangList.KN]!![FuncList.DataFunction]!!
                    ),

                    Scheme.ML to listOf(
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        UAST::IASTToUAST,
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        builder[LangList.ML]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TA to listOf(
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        UAST::IASTToUAST,
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TE to listOf(
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        UAST::IASTToUAST,
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TE]!![FuncList.DataFunction]!!
                    )
                ),

                Scheme.UAST to mapOf(
                    Scheme.DEVANĀGARĪ to listOf(
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.SA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.IAST to listOf(
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!, UAST::dataToIAST
                    ),

                    Scheme.GU to listOf(
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        builder[LangList.GU]!![FuncList.DataFunction]!!
                    ),

                    Scheme.OR to listOf(
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        builder[LangList.OR]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TA to listOf(
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TE to listOf(
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TE]!![FuncList.DataFunction]!!
                    ),

                    Scheme.KN to listOf(
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        builder[LangList.KN]!![FuncList.DataFunction]!!
                    ),

                    Scheme.ML to listOf(
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        builder[LangList.ML]!![FuncList.DataFunction]!!
                    )
                ),

                Scheme.DEVANĀGARĪ to mapOf(
                    Scheme.UAST to listOf(UAST::devanāgarīToUAST),

                    Scheme.IAST to listOf(
                        UAST::devanāgarīToUAST, builder[LangList.SA]!![FuncList.HandleUnicode]!!, UAST::dataToIAST
                    ),

                    Scheme.GU to listOf(
                        UAST::devanāgarīToUAST,
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        builder[LangList.GU]!![FuncList.DataFunction]!!
                    ),

                    Scheme.OR to listOf(
                        UAST::devanāgarīToUAST,
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        builder[LangList.OR]!![FuncList.DataFunction]!!
                    ),

                    Scheme.KN to listOf(
                        UAST::devanāgarīToUAST,
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        builder[LangList.KN]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TE to listOf(
                        UAST::devanāgarīToUAST,
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TE]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TA to listOf(
                        UAST::devanāgarīToUAST,
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.ML to listOf(
                        UAST::devanāgarīToUAST,
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        builder[LangList.ML]!![FuncList.DataFunction]!!
                    )
                ),

                Scheme.SLP to mapOf(
                    Scheme.IAST to listOf(UAST::SLPToIAST),

                    Scheme.UAST to listOf(UAST::SLPToIAST, UAST::IASTToUAST),

                    Scheme.DEVANĀGARĪ to listOf(
                        UAST::SLPToIAST,
                        UAST::IASTToUAST,
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.SA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.GU to listOf(
                        UAST::SLPToIAST,
                        UAST::IASTToUAST,
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        builder[LangList.GU]!![FuncList.DataFunction]!!
                    ),

                    Scheme.OR to listOf(
                        UAST::SLPToIAST,
                        UAST::IASTToUAST,
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        builder[LangList.OR]!![FuncList.DataFunction]!!
                    ),

                    Scheme.KN to listOf(
                        UAST::SLPToIAST,
                        UAST::IASTToUAST,
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        builder[LangList.KN]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TA to listOf(
                        UAST::SLPToIAST,
                        UAST::IASTToUAST,
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TE to listOf(
                        UAST::SLPToIAST,
                        UAST::IASTToUAST,
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TE]!![FuncList.DataFunction]!!
                    ),

                    Scheme.ML to listOf(
                        UAST::SLPToIAST,
                        UAST::IASTToUAST,
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        builder[LangList.ML]!![FuncList.DataFunction]!!
                    )
                ),

                Scheme.IAST to mapOf(
                    Scheme.UAST to listOf(UAST::IASTToUAST),

                    Scheme.DEVANĀGARĪ to listOf(
                        UAST::IASTToUAST,
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.SA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.GU to listOf(
                        UAST::IASTToUAST,
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        builder[LangList.GU]!![FuncList.DataFunction]!!
                    ),

                    Scheme.OR to listOf(
                        UAST::IASTToUAST,
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        builder[LangList.OR]!![FuncList.DataFunction]!!
                    ),

                    Scheme.ML to listOf(
                        UAST::IASTToUAST,
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        builder[LangList.ML]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TA to listOf(
                        UAST::IASTToUAST,
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.KN to listOf(
                        UAST::IASTToUAST,
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        builder[LangList.KN]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TE to listOf(
                        UAST::IASTToUAST,
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TE]!![FuncList.DataFunction]!!
                    )
                ),

                Scheme.GU to mapOf(
                    Scheme.DEVANĀGARĪ to listOf(
                        builder[LangList.GU]!![FuncList.ScriptToDevanāgarī]!!
                    ),

                    Scheme.UAST to listOf(
                        builder[LangList.GU]!![FuncList.ScriptToDevanāgarī]!!, UAST::devanāgarīToUAST
                    ),

                    Scheme.IAST to listOf(
                        builder[LangList.GU]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        UAST::dataToIAST
                    ),

                    Scheme.OR to listOf(
                        builder[LangList.GU]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        builder[LangList.OR]!![FuncList.DataFunction]!!
                    ),

                    Scheme.KN to listOf(
                        builder[LangList.GU]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        builder[LangList.KN]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TE to listOf(
                        builder[LangList.GU]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TE]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TA to listOf(
                        builder[LangList.GU]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.ML to listOf(
                        builder[LangList.GU]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        builder[LangList.ML]!![FuncList.DataFunction]!!
                    )
                ),

                Scheme.ML to mapOf(
                    Scheme.DEVANĀGARĪ to listOf(
                        builder[LangList.ML]!![FuncList.ScriptToDevanāgarī]!!
                    ),

                    Scheme.UAST to listOf(
                        builder[LangList.ML]!![FuncList.ScriptToDevanāgarī]!!, UAST::devanāgarīToUAST
                    ),

                    Scheme.IAST to listOf(
                        builder[LangList.ML]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        UAST::dataToIAST
                    ),

                    Scheme.OR to listOf(
                        builder[LangList.ML]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        builder[LangList.OR]!![FuncList.DataFunction]!!
                    ),

                    Scheme.KN to listOf(
                        builder[LangList.ML]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        builder[LangList.KN]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TE to listOf(
                        builder[LangList.ML]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TE]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TA to listOf(
                        builder[LangList.ML]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.GU to listOf(
                        builder[LangList.ML]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        builder[LangList.GU]!![FuncList.DataFunction]!!
                    )
                ),

                Scheme.OR to mapOf(
                    Scheme.DEVANĀGARĪ to listOf(
                        builder[LangList.OR]!![FuncList.ScriptToDevanāgarī]!!
                    ),

                    Scheme.UAST to listOf(
                        builder[LangList.OR]!![FuncList.ScriptToDevanāgarī]!!, UAST::devanāgarīToUAST
                    ),

                    Scheme.IAST to listOf(
                        builder[LangList.OR]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        UAST::dataToIAST
                    ),

                    Scheme.ML to listOf(
                        builder[LangList.OR]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        builder[LangList.ML]!![FuncList.DataFunction]!!
                    ),

                    Scheme.KN to listOf(
                        builder[LangList.OR]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        builder[LangList.KN]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TE to listOf(
                        builder[LangList.OR]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TE]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TA to listOf(
                        builder[LangList.OR]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.GU to listOf(
                        builder[LangList.OR]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        builder[LangList.GU]!![FuncList.DataFunction]!!
                    )
                ),

                Scheme.KN to mapOf(
                    Scheme.DEVANĀGARĪ to listOf(
                        builder[LangList.KN]!![FuncList.ScriptToDevanāgarī]!!
                    ),

                    Scheme.UAST to listOf(
                        builder[LangList.KN]!![FuncList.ScriptToDevanāgarī]!!, UAST::devanāgarīToUAST
                    ),

                    Scheme.IAST to listOf(
                        builder[LangList.KN]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        UAST::dataToIAST
                    ),

                    Scheme.ML to listOf(
                        builder[LangList.KN]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        builder[LangList.ML]!![FuncList.DataFunction]!!
                    ),

                    Scheme.OR to listOf(
                        builder[LangList.KN]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        builder[LangList.OR]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TE to listOf(
                        builder[LangList.KN]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TE]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TA to listOf(
                        builder[LangList.KN]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.GU to listOf(
                        builder[LangList.KN]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        builder[LangList.GU]!![FuncList.DataFunction]!!
                    )
                ),

                Scheme.TA to mapOf(
                    Scheme.DEVANĀGARĪ to listOf(
                        builder[LangList.TA]!![FuncList.ScriptToDevanāgarī]!!
                    ),

                    Scheme.UAST to listOf(
                        builder[LangList.TA]!![FuncList.ScriptToDevanāgarī]!!, UAST::devanāgarīToUAST
                    ),

                    Scheme.IAST to listOf(
                        builder[LangList.TA]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        UAST::dataToIAST
                    ),

                    Scheme.ML to listOf(
                        builder[LangList.TA]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        builder[LangList.ML]!![FuncList.DataFunction]!!
                    ),

                    Scheme.OR to listOf(
                        builder[LangList.TA]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        builder[LangList.OR]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TE to listOf(
                        builder[LangList.TA]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.TE]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TE]!![FuncList.DataFunction]!!
                    ),

                    Scheme.KN to listOf(
                        builder[LangList.TA]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        builder[LangList.KN]!![FuncList.DataFunction]!!
                    ),

                    Scheme.GU to listOf(
                        builder[LangList.TA]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        builder[LangList.GU]!![FuncList.DataFunction]!!
                    )
                ),

                Scheme.TE to mapOf(
                    Scheme.DEVANĀGARĪ to listOf(
                        builder[LangList.TE]!![FuncList.ScriptToDevanāgarī]!!
                    ),

                    Scheme.UAST to listOf(
                        builder[LangList.TE]!![FuncList.ScriptToDevanāgarī]!!, UAST::devanāgarīToUAST
                    ),

                    Scheme.IAST to listOf(
                        builder[LangList.TE]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.SA]!![FuncList.HandleUnicode]!!,
                        UAST::dataToIAST
                    ),

                    Scheme.ML to listOf(
                        builder[LangList.TE]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.ML]!![FuncList.HandleUnicode]!!,
                        builder[LangList.ML]!![FuncList.DataFunction]!!
                    ),

                    Scheme.OR to listOf(
                        builder[LangList.TE]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.OR]!![FuncList.HandleUnicode]!!,
                        builder[LangList.OR]!![FuncList.DataFunction]!!
                    ),

                    Scheme.TA to listOf(
                        builder[LangList.TE]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.TA]!![FuncList.HandleUnicode]!!,
                        builder[LangList.TA]!![FuncList.DataFunction]!!
                    ),

                    Scheme.KN to listOf(
                        builder[LangList.TE]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.KN]!![FuncList.HandleUnicode]!!,
                        builder[LangList.KN]!![FuncList.DataFunction]!!
                    ),

                    Scheme.GU to listOf(
                        builder[LangList.TE]!![FuncList.ScriptToDevanāgarī]!!,
                        UAST::devanāgarīToUAST,
                        builder[LangList.GU]!![FuncList.HandleUnicode]!!,
                        builder[LangList.GU]!![FuncList.DataFunction]!!
                    )
                )
            )
        }

        private fun createDataFunction(lang: LangList): (String) -> String {
            val obj = charDict[lang]!!

            return fun(data: String): String {
                val ans = mutableListOf<String>()

                for (split in data.split("\\\\")) {
                    if (obj[LangMap.MISC]?.contains(split) == true || obj[LangMap.MISC]?.contains(split) == true) {
                        ans.add(split)
                        continue
                    }

                    if (obj[LangMap.VOWELS]?.contains(split) == true) {
                        ans.add(obj[LangMap.VOWELS]!![split]!!)
                        continue
                    }

                    val str = split.map { it.toString() }.toList()

                    val arr = mutableListOf<String>()

                    var i = 0
                    while (i < str.size) {
                        val curr = str[i]

                        if (lang == LangList.SA) {
                            if (curr == "'") {
                                arr.add("॑")
                                i++
                                continue
                            }

                            if (curr == "`") {
                                arr.add("॒")
                                i++
                                continue
                            }
                        }

                        if (curr in setOf(",", "?", "!", "\"", ":", "(", ")", "=")) {
                            arr.add(curr)
                            i++
                            continue
                        }

                        if (curr in unAspiratedConstants) {
                            var consonant: String
                            if (i + 1 < str.size && str[i + 1] == "h") {
                                consonant = str.subList(i, i + 2).joinToString("")
                                i += 2
                            } else {
                                consonant = curr
                                i++
                            }


                            arr.add(obj[LangMap.CONSONANTS]!![consonant]!!)
                            continue
                        }

                        if (obj[LangMap.CONSONANTS]?.contains(curr) == true) {
                            arr.add(obj[LangMap.CONSONANTS]!![curr]!!)
                        }

                        var vowel: String
                        if (curr == "a" && (i + 1 < str.size && (str[i + 1] == "i" || str[i + 1] == "u"))) {
                            vowel = str.subList(i, i + 2).joinToString("")
                            i += 2
                        } else {
                            vowel = curr
                            i++
                        }

                        if (obj[LangMap.VOWELSIGNS]?.contains(vowel) == true) {
                            arr.add(obj[LangMap.VOWELSIGNS]!![vowel]!!)
                        }
                    }

                    ans.add(arr.joinToString(""))
                }

                return Normalizer.normalize(ans.joinToString(""), Normalizer.Form.NFC)
            }
        }

        private fun createHandleUnicode(langList: LangList): (String) -> String {
            val langDict = unicodeMap.toMutableMap()
            langDict.putAll(
                when (langList) {
                    LangList.SA -> mapOf<String, String>(
                        "0" to "०",
                        "1" to "१",
                        "2" to "२",
                        "3" to "३",
                        "4" to "४",
                        "5" to "५",
                        "6" to "६",
                        "7" to "७",
                        "8" to "८",
                        "9" to "९",
                        "om" to "ॐ",
                        "'" to "ऽ"
                    )

                    LangList.GU -> mapOf<String, String>(
                        "0" to "૦",
                        "1" to "૧",
                        "2" to "૨",
                        "3" to "૩",
                        "4" to "૪",
                        "5" to "૫",
                        "6" to "૬",
                        "7" to "૭",
                        "8" to "૮",
                        "9" to "૯",
                        "om" to "ॐ",
                        "'" to "ઽ"
                    )

                    LangList.KN -> mapOf<String, String>(
                        "0" to "೦",
                        "1" to "೧",
                        "2" to "೨",
                        "3" to "೩",
                        "4" to "೪",
                        "5" to "೫",
                        "6" to "೬",
                        "7" to "೭",
                        "8" to "೮",
                        "9" to "೯",
                        "om" to "ಓಂ",
                        "'" to "ಽ"
                    )

                    LangList.TE -> mapOf<String, String>(
                        "0" to "౦",
                        "1" to "౧",
                        "2" to "౨",
                        "3" to "౩",
                        "4" to "౪",
                        "5" to "౫",
                        "6" to "౬",
                        "7" to "౭",
                        "8" to "౮",
                        "9" to "౯",
                        "'" to "ఽ",
                        "om" to "ఓం"
                    )

                    LangList.ML -> mapOf<String, String>(
                        "0" to "൦",
                        "1" to "൧",
                        "2" to "൨",
                        "3" to "൩",
                        "4" to "൪",
                        "5" to "൫",
                        "6" to "൬",
                        "7" to "൭",
                        "8" to "൮",
                        "9" to "൯",
                        "'" to "ഽ",
                        "om" to "ഓം"
                    )

                    LangList.TA -> mapOf<String, String>(
                        "0" to "௦",
                        "1" to "௧",
                        "2" to "௨",
                        "3" to "௩",
                        "4" to "௪",
                        "5" to "௫",
                        "6" to "௬",
                        "7" to "௭",
                        "8" to "௮",
                        "9" to "௯",
                        "'" to "𑌽",
                        "om" to "𑍐"
                    )

                    LangList.OR -> mapOf<String, String>(
                        "0" to "୦",
                        "1" to "୧",
                        "2" to "୨",
                        "3" to "୩",
                        "4" to "୪",
                        "5" to "୫",
                        "6" to "୬",
                        "7" to "୭",
                        "8" to "୮",
                        "9" to "୯",
                        "om" to "ଓଁ",
                        "'" to "ଽ"
                    )
                }
            )

            return fun(uast: String): String {
                val str = uast.lowercase().trim().replace("^\\\\+|\\\\+$".toRegex(), "").map { it.toString() }.toList()

                val arr = mutableListOf<String>()

                var i = 0
                while (i < str.size) {
                    var curr = str[i]

                    if (curr == "/") {
                        val charList = ArrayList<String>(2)

                        for (j in i + 1 until str.size) {
                            curr = str[j]

                            if (curr == "/") {
                                i = j
                                break
                            }

                            if (j == str.size - 1) {
                                i = j
                            }

                            charList.add(curr)
                        }

                        val v = charList.joinToString("")
                        if (v in langDict) {
                            arr.add(langDict[v]!!)
                        }

                        i++
                        continue
                    }

                    arr.add(curr)
                    i++
                }

                return Normalizer.normalize(arr.joinToString(""), Normalizer.Form.NFC)
            }
        }

        private fun createScriptFunction(lang: LangList): (String) -> String {
            val obj = scripts[lang] ?: scripts[LangList.SA]!!

            return fun(s: String): String {
                val arr = mutableListOf<String>()
                for (v in Normalizer.normalize(s, Normalizer.Form.NFC)) {
                    val l = v.toString()

                    if (l in obj) {
                        arr.add(obj[l]!!)
                        continue
                    }

                    if (l in allowedSymbols) {
                        arr.add(l)
                    }
                }

                return Normalizer.normalize(arr.joinToString(""), Normalizer.Form.NFC)
            }
        }

        private fun devanāgarīToUAST(data: String): String {
            val str = Normalizer.normalize(data, Normalizer.Form.NFC).map { it.toString() }.toList()

            val arr = mutableListOf<String>()
            for (i in str.indices) {
                val curr = str[i]
                val next = if (i + 1 < str.size) {
                    str[i + 1]
                } else {
                    ""
                }

                if (curr == "॑") {
                    arr.add("\\'")
                    continue
                }
                if (curr == "॒") {
                    arr.add("\\`")
                    continue
                }

                val v = devanāgarīDataDict[curr] ?: curr
                val nextV = devanāgarīDataDict[next] ?: next
                val checkVowel = charDict[LangList.SA]!![LangMap.VOWELS]!!.containsValue(curr)
                val checkConsonant = charDict[LangList.SA]!![LangMap.CONSONANTS]!!.containsValue(next)

                if (checkVowel && checkConsonant) {
                    arr.add("${v}\\")
                    continue
                }

                if (v in unAspiratedConstants && nextV == "h") {
                    arr.add("${v}a")
                    continue
                }

                arr.add(v)
            }

            return Normalizer.normalize(arr.joinToString(""), Normalizer.Form.NFC)
        }

        private fun dataToIAST(data: String): String {
            val andy = Normalizer.normalize(data, Normalizer.Form.NFC)
                .replace("[\\[\\]{}^~@#$%&*_;.<>\\n\\v\\t\\r\\f]".toRegex(), "")
            val ans = mutableListOf<String>()

            for (split in andy.split("\\\\")) {
                if (split == "ॐ") {
                    ans.add("oṃ")
                    continue
                }

                val saDict = charDict[LangList.SA]!!
                if (split in saDict[LangMap.NUMBERS]!!) {
                    ans.add(saDict[LangMap.NUMBERS]!![split]!!)
                    continue
                }

                if (split in saDict[LangMap.MISC]!!) {
                    ans.add(saDict[LangMap.MISC]!![split]!!)
                    continue
                }

                if (split == "ḥ" || split == "ṃ" || split == "ã") {
                    ans.add(split)
                    continue
                }


                val str = split.map { it.toString() }.toList()

                val arr = mutableListOf<String>()
                var i = 0
                while (i < str.size) {
                    val curr = str[i]

                    if (curr == "'") {
                        // arr.push("॑");
                        i++
                        continue
                    }

                    if (curr == "`") {
                        // arr.push("॒");
                        i++
                        continue
                    }

                    if (curr in allowedSymbols) {
                        arr.add(curr)
                        i++
                        continue
                    }

                    val next = if (i + 1 < str.size) {
                        str[i + 1]
                    } else {
                        ""
                    }

                    if (next == "ḥ" || next == "ṃ" || next == "ã") {
                        if (curr in saDict[LangMap.CONSONANTS]!!) {
                            arr.add("${curr}a${next}")
                        } else {
                            arr.add("${curr}${next}")
                        }

                        i += 2
                        continue
                    }

                    if (curr in saDict[LangMap.VOWELS]!!) {
                        arr.add(curr)
                        i++
                        continue
                    }

                    if (i == str.size - 1) {
                        if (curr == "ḥ" || curr == "ṃ" || curr == "ã") {
                            arr.add(curr)
                        } else {
                            arr.add("${curr}a")
                        }

                        i++
                        continue
                    }

                    if (unAspiratedConstants.contains(curr) && next == "h") {
                        val last = if (i + 2 < str.size) {
                            str[i + 2]
                        } else {
                            ""
                        }

                        if (last !in saDict[LangMap.VOWELSIGNS]!!) {
                            arr.add("${curr}${next}a")
                            i += 2
                            continue
                        }

                        if (last == "ḥ" || last == "ṃ" || last == "ã") {
                            arr.add(curr + next + "a" + last)
                            i += 3
                            continue
                        }

                        i += if (last == "-") {
                            3
                        } else {
                            2
                        }

                        arr.add("${curr}${next}")
                        continue
                    }

                    if (next == "-") {
                        arr.add(curr)
                        i += 2
                        continue
                    }

                    if (next in saDict[LangMap.VOWELSIGNS]!!) {
                        arr.add(curr)
                        i++
                        continue
                    }

                    if (curr == "ḥ" || curr == "ṃ" || curr == "ã") {
                        arr.add(curr)
                        i++
                        continue
                    }

                    if (curr !in iastAllowed) {
                        i++
                        continue
                    }

                    arr.add("${curr}a")
                    i++
                }

                ans.add(arr.joinToString(""))
            }

            return Normalizer.normalize(ans.joinToString(""), Normalizer.Form.NFC)
        }

        private fun IASTToUAST(data: String): String {
            val andy =
                Normalizer.normalize(data, Normalizer.Form.NFC).replace("[\\[\\]{}^~@#$%&*\\-_;<>]".toRegex(), "")

            val str = andy.map { it.toString() }.toList()

            val arr = mutableListOf<String>()
            val saDict = charDict[LangList.SA]!!

            var i = 0
            while (i < str.size) {
                val curr = str[i]

                val next = if (i + 1 < str.size) {
                    str[i + 1]
                } else {
                    ""
                }

                if (curr in saDict[LangMap.CONSONANTS]!!) {
                    if (unAspiratedConstants.contains(curr)) {
                        if (next == "a" && (i + 2 < str.size && str[i + 2] == "h")) {
                            arr.add("${curr}\\")
                            i += 2
                            continue
                        }

                        if (next == "h") {
                            var last = if (i + 2 < str.size) {
                                str[i + 2]
                            } else {
                                ""
                            }

                            if (last in saDict[LangMap.CONSONANTS]!!) {
                                arr.add("${curr}${next}-")
                                i += 2
                                continue
                            }

                            if (last == "a") {
                                if (i + 3 < str.size) {
                                    last = str[i + 3]
                                }

                                if (last == "i" || last == "u") {
                                    arr.add("${curr}${next}a${last}")
                                    i += 4
                                    continue
                                }

                                i += 3
                            } else {
                                i += 2
                            }

                            arr.add("${curr}${next}")
                            continue
                        }
                    }

                    if (next == "a") {
                        var last = ""
                        if (i + 2 < str.size) {
                            last = str[i + 2]
                        }

                        if (last == "i" || last == "u") {
                            arr.add("${curr}a${last}")
                            i += 3
                            continue
                        }

                        arr.add(curr)
                        i += 2
                        continue
                    }

                    if (next in saDict[LangMap.CONSONANTS]!! || (next == "." || next == ".." || next == "'") || i == str.size - 1) {
                        arr.add("${curr}-")
                        i++
                        continue
                    }

                    arr.add(curr)
                    i++
                    continue
                }

                if (curr == "a" && (next == "i" || next == "u")) {
                    arr.add("${curr}${next}\\")
                    i += 2
                    continue
                }

                if (curr in saDict[LangMap.VOWELS]!! && next in saDict[LangMap.CONSONANTS]!!) {
                    arr.add("${curr}\\")
                    i++
                    continue
                }

                arr.add(curr)
                i++
            }

            val ans = mutableListOf<String>()
            var k = 0
            while (k < arr.size) {
                var curr = arr[k]

                val hasDash = "-" in curr

                curr = curr.replace("\\\\".toRegex(), "")
                curr = curr.replace("-".toRegex(), "")

                for (j in arrayOf("\\.", "'", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9")) {
                    if (curr == "." && (k + 1 < arr.size && arr[k + 1] == ".")) {
                        curr = curr.replace(curr.toRegex(), "\\\\/../\\\\")
                        k++
                        continue
                    }

                    curr = curr.replace(j.toRegex(), "\\\\/${j}/\\\\")
                }

                val v = mutableListOf(curr)
                if (curr in unAspiratedConstants && k + 1 < arr.size && arr[k + 1] == "h") {
                    v.add("a")
                }

                if (hasDash) {
                    v.add("-")
                }

                if (curr in saDict[LangMap.VOWELS]!!) {
                    v.add("\\")
                }

                ans.add(v.joinToString(""))
                k++
            }

            if (ans.isNotEmpty() && str.isNotEmpty() && ans.last() in saDict[LangMap.CONSONANTS]!! && str.last() != "a") {
                ans.add("-")
            }

            val fin = mutableListOf<String>()

            for (v in ans.joinToString("")) {
                val l = v.toString()
                fin.add(
                    if (l in iastDataDict) {
                        "/${iastDataDict[l]}/"
                    } else {
                        l
                    }
                )
            }

            return Normalizer.normalize(fin.joinToString(""), Normalizer.Form.NFC)
        }

        private fun SLPToIAST(data: String): String {
            val str = mutableListOf<String>()

            str.addAll(data.map { it.toString() }.filter { it in slpDataDict })

            return Normalizer.normalize(str.joinToString(" "), Normalizer.Form.NFC)
        }
    }
}
