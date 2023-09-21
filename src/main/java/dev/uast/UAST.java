package dev.uast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class UAST {
    public static final Map<Scheme, Map<Scheme, List<Function<String, String>>>> convertor =
            builder();

    private static Map<Scheme, Map<Scheme, List<Function<String, String>>>> builder() {
        var builder = new HashMap<LangList, Map<FuncList, Function<String, String>>>();

        for (var i : LangList.values()) {
            builder.put(
                    i,
                    Map.ofEntries(
                            Map.entry(FuncList.HandleUnicode, createHandleUnicode(i)),
                            Map.entry(FuncList.DataFunction, createDataFunction(i)),
                            Map.entry(FuncList.ScriptToDevanagari, createScriptFunction(i))));
        }

        return Map.ofEntries(
                Map.entry(
                        Scheme.RAW,
                        Map.ofEntries(
                                Map.entry(
                                        Scheme.IAST,
                                        List.of(
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode))),
                                Map.entry(
                                        Scheme.DEVANĀGARĪ,
                                        List.of(
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::IASTToUAST,
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.SA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.UAST,
                                        List.of(
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::IASTToUAST)),
                                Map.entry(
                                        Scheme.GU,
                                        List.of(
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::IASTToUAST,
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.GU)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.OR,
                                        List.of(
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::IASTToUAST,
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.OR)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.KN,
                                        List.of(
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::IASTToUAST,
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.KN)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.ML,
                                        List.of(
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::IASTToUAST,
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.ML)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TA,
                                        List.of(
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::IASTToUAST,
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TE,
                                        List.of(
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::IASTToUAST,
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TE)
                                                        .get(FuncList.DataFunction))))),
                Map.entry(
                        Scheme.UAST,
                        Map.ofEntries(
                                Map.entry(
                                        Scheme.DEVANĀGARĪ,
                                        List.of(
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.SA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.IAST,
                                        List.of(
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::dataToIAST)),
                                Map.entry(
                                        Scheme.GU,
                                        List.of(
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.GU)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.OR,
                                        List.of(
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.OR)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TA,
                                        List.of(
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TE,
                                        List.of(
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TE)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.KN,
                                        List.of(
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.KN)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.ML,
                                        List.of(
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.ML)
                                                        .get(FuncList.DataFunction))))),
                Map.entry(
                        Scheme.DEVANĀGARĪ,
                        Map.ofEntries(
                                Map.entry(Scheme.UAST, List.of(UAST::devanagariToUAST)),
                                Map.entry(
                                        Scheme.IAST,
                                        List.of(
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::dataToIAST)),
                                Map.entry(
                                        Scheme.GU,
                                        List.of(
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.GU)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.OR,
                                        List.of(
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.OR)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.KN,
                                        List.of(
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.KN)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TE,
                                        List.of(
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TE)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TA,
                                        List.of(
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.ML,
                                        List.of(
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.ML)
                                                        .get(FuncList.DataFunction))))),
                Map.entry(
                        Scheme.SLP,
                        Map.ofEntries(
                                Map.entry(Scheme.IAST, List.of(UAST::SLPToIAST)),
                                Map.entry(Scheme.UAST, List.of(UAST::SLPToIAST, UAST::IASTToUAST)),
                                Map.entry(
                                        Scheme.DEVANĀGARĪ,
                                        List.of(
                                                UAST::SLPToIAST,
                                                UAST::IASTToUAST,
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.SA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.GU,
                                        List.of(
                                                UAST::SLPToIAST,
                                                UAST::IASTToUAST,
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.GU)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.OR,
                                        List.of(
                                                UAST::SLPToIAST,
                                                UAST::IASTToUAST,
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.OR)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.KN,
                                        List.of(
                                                UAST::SLPToIAST,
                                                UAST::IASTToUAST,
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.KN)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TA,
                                        List.of(
                                                UAST::SLPToIAST,
                                                UAST::IASTToUAST,
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TE,
                                        List.of(
                                                UAST::SLPToIAST,
                                                UAST::IASTToUAST,
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TE)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.ML,
                                        List.of(
                                                UAST::SLPToIAST,
                                                UAST::IASTToUAST,
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.ML)
                                                        .get(FuncList.DataFunction))))),
                Map.entry(
                        Scheme.IAST,
                        Map.ofEntries(
                                Map.entry(Scheme.UAST, List.of(UAST::IASTToUAST)),
                                Map.entry(
                                        Scheme.DEVANĀGARĪ,
                                        List.of(
                                                UAST::IASTToUAST,
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.SA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.GU,
                                        List.of(
                                                UAST::IASTToUAST,
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.GU)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.OR,
                                        List.of(
                                                UAST::IASTToUAST,
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.OR)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.ML,
                                        List.of(
                                                UAST::IASTToUAST,
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.ML)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TA,
                                        List.of(
                                                UAST::IASTToUAST,
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.KN,
                                        List.of(
                                                UAST::IASTToUAST,
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.KN)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TE,
                                        List.of(
                                                UAST::IASTToUAST,
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TE)
                                                        .get(FuncList.DataFunction))))),
                Map.entry(
                        Scheme.GU,
                        Map.ofEntries(
                                Map.entry(
                                        Scheme.DEVANĀGARĪ,
                                        List.of(
                                                builder.get(LangList.GU)
                                                        .get(FuncList.ScriptToDevanagari))),
                                Map.entry(
                                        Scheme.UAST,
                                        List.of(
                                                builder.get(LangList.GU)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST)),
                                Map.entry(
                                        Scheme.IAST,
                                        List.of(
                                                builder.get(LangList.GU)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::dataToIAST)),
                                Map.entry(
                                        Scheme.OR,
                                        List.of(
                                                builder.get(LangList.GU)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.OR)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.KN,
                                        List.of(
                                                builder.get(LangList.GU)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.KN)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TE,
                                        List.of(
                                                builder.get(LangList.GU)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TE)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TA,
                                        List.of(
                                                builder.get(LangList.GU)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.ML,
                                        List.of(
                                                builder.get(LangList.GU)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.ML)
                                                        .get(FuncList.DataFunction))))),
                Map.entry(
                        Scheme.ML,
                        Map.ofEntries(
                                Map.entry(
                                        Scheme.DEVANĀGARĪ,
                                        List.of(
                                                builder.get(LangList.ML)
                                                        .get(FuncList.ScriptToDevanagari))),
                                Map.entry(
                                        Scheme.UAST,
                                        List.of(
                                                builder.get(LangList.ML)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST)),
                                Map.entry(
                                        Scheme.IAST,
                                        List.of(
                                                builder.get(LangList.ML)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::dataToIAST)),
                                Map.entry(
                                        Scheme.OR,
                                        List.of(
                                                builder.get(LangList.ML)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.OR)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.KN,
                                        List.of(
                                                builder.get(LangList.ML)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.KN)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TE,
                                        List.of(
                                                builder.get(LangList.ML)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TE)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TA,
                                        List.of(
                                                builder.get(LangList.ML)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.GU,
                                        List.of(
                                                builder.get(LangList.ML)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.GU)
                                                        .get(FuncList.DataFunction))))),
                Map.entry(
                        Scheme.OR,
                        Map.ofEntries(
                                Map.entry(
                                        Scheme.DEVANĀGARĪ,
                                        List.of(
                                                builder.get(LangList.OR)
                                                        .get(FuncList.ScriptToDevanagari))),
                                Map.entry(
                                        Scheme.UAST,
                                        List.of(
                                                builder.get(LangList.OR)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST)),
                                Map.entry(
                                        Scheme.IAST,
                                        List.of(
                                                builder.get(LangList.OR)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::dataToIAST)),
                                Map.entry(
                                        Scheme.ML,
                                        List.of(
                                                builder.get(LangList.OR)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.ML)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.KN,
                                        List.of(
                                                builder.get(LangList.OR)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.KN)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TE,
                                        List.of(
                                                builder.get(LangList.OR)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TE)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TA,
                                        List.of(
                                                builder.get(LangList.OR)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.GU,
                                        List.of(
                                                builder.get(LangList.OR)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.GU)
                                                        .get(FuncList.DataFunction))))),
                Map.entry(
                        Scheme.KN,
                        Map.ofEntries(
                                Map.entry(
                                        Scheme.DEVANĀGARĪ,
                                        List.of(
                                                builder.get(LangList.KN)
                                                        .get(FuncList.ScriptToDevanagari))),
                                Map.entry(
                                        Scheme.UAST,
                                        List.of(
                                                builder.get(LangList.KN)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST)),
                                Map.entry(
                                        Scheme.IAST,
                                        List.of(
                                                builder.get(LangList.KN)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::dataToIAST)),
                                Map.entry(
                                        Scheme.ML,
                                        List.of(
                                                builder.get(LangList.KN)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.ML)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.OR,
                                        List.of(
                                                builder.get(LangList.KN)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.OR)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TE,
                                        List.of(
                                                builder.get(LangList.KN)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TE)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TA,
                                        List.of(
                                                builder.get(LangList.KN)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.GU,
                                        List.of(
                                                builder.get(LangList.KN)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.GU)
                                                        .get(FuncList.DataFunction))))),
                Map.entry(
                        Scheme.TA,
                        Map.ofEntries(
                                Map.entry(
                                        Scheme.DEVANĀGARĪ,
                                        List.of(
                                                builder.get(LangList.TA)
                                                        .get(FuncList.ScriptToDevanagari))),
                                Map.entry(
                                        Scheme.UAST,
                                        List.of(
                                                builder.get(LangList.TA)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST)),
                                Map.entry(
                                        Scheme.IAST,
                                        List.of(
                                                builder.get(LangList.TA)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::dataToIAST)),
                                Map.entry(
                                        Scheme.ML,
                                        List.of(
                                                builder.get(LangList.TA)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.ML)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.OR,
                                        List.of(
                                                builder.get(LangList.TA)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.OR)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TE,
                                        List.of(
                                                builder.get(LangList.TA)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TE)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TE)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.KN,
                                        List.of(
                                                builder.get(LangList.TA)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.KN)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.GU,
                                        List.of(
                                                builder.get(LangList.TA)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.GU)
                                                        .get(FuncList.DataFunction))))),
                Map.entry(
                        Scheme.TE,
                        Map.ofEntries(
                                Map.entry(
                                        Scheme.DEVANĀGARĪ,
                                        List.of(
                                                builder.get(LangList.TE)
                                                        .get(FuncList.ScriptToDevanagari))),
                                Map.entry(
                                        Scheme.UAST,
                                        List.of(
                                                builder.get(LangList.TE)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST)),
                                Map.entry(
                                        Scheme.IAST,
                                        List.of(
                                                builder.get(LangList.TE)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.SA)
                                                        .get(FuncList.HandleUnicode),
                                                UAST::dataToIAST)),
                                Map.entry(
                                        Scheme.ML,
                                        List.of(
                                                builder.get(LangList.TE)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.ML)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.ML)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.OR,
                                        List.of(
                                                builder.get(LangList.TE)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.OR)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.OR)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.TA,
                                        List.of(
                                                builder.get(LangList.TE)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.TA)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.TA)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.KN,
                                        List.of(
                                                builder.get(LangList.TE)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.KN)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.KN)
                                                        .get(FuncList.DataFunction))),
                                Map.entry(
                                        Scheme.GU,
                                        List.of(
                                                builder.get(LangList.TE)
                                                        .get(FuncList.ScriptToDevanagari),
                                                UAST::devanagariToUAST,
                                                builder.get(LangList.GU)
                                                        .get(FuncList.HandleUnicode),
                                                builder.get(LangList.GU)
                                                        .get(FuncList.DataFunction))))));
    }

    private static Function<String, String> createHandleUnicode(LangList langList) {
        var langDict = new HashMap<>(Data.unicodeMap);
        langDict.putAll(
                switch (langList) {
                    case SA -> Map.ofEntries(
                            Map.entry("0", "०"),
                            Map.entry("1", "१"),
                            Map.entry("2", "२"),
                            Map.entry("3", "३"),
                            Map.entry("4", "४"),
                            Map.entry("5", "५"),
                            Map.entry("6", "६"),
                            Map.entry("7", "७"),
                            Map.entry("8", "८"),
                            Map.entry("9", "९"),
                            Map.entry("om", "ॐ"),
                            Map.entry("'", "ऽ"));
                    case GU -> Map.ofEntries(
                            Map.entry("0", "૦"),
                            Map.entry("1", "૧"),
                            Map.entry("2", "૨"),
                            Map.entry("3", "૩"),
                            Map.entry("4", "૪"),
                            Map.entry("5", "૫"),
                            Map.entry("6", "૬"),
                            Map.entry("7", "૭"),
                            Map.entry("8", "૮"),
                            Map.entry("9", "૯"),
                            Map.entry("om", "ॐ"),
                            Map.entry("'", "ઽ"));
                    case KN -> Map.ofEntries(
                            Map.entry("0", "೦"),
                            Map.entry("1", "೧"),
                            Map.entry("2", "೨"),
                            Map.entry("3", "೩"),
                            Map.entry("4", "೪"),
                            Map.entry("5", "೫"),
                            Map.entry("6", "೬"),
                            Map.entry("7", "೭"),
                            Map.entry("8", "೮"),
                            Map.entry("9", "೯"),
                            Map.entry("om", "ಓಂ"),
                            Map.entry("'", "ಽ"));
                    case TE -> Map.ofEntries(
                            Map.entry("0", "౦"),
                            Map.entry("1", "౧"),
                            Map.entry("2", "౨"),
                            Map.entry("3", "౩"),
                            Map.entry("4", "౪"),
                            Map.entry("5", "౫"),
                            Map.entry("6", "౬"),
                            Map.entry("7", "౭"),
                            Map.entry("8", "౮"),
                            Map.entry("9", "౯"),
                            Map.entry("'", "ఽ"),
                            Map.entry("om", "ఓం"));
                    case ML -> Map.ofEntries(
                            Map.entry("0", "൦"),
                            Map.entry("1", "൧"),
                            Map.entry("2", "൨"),
                            Map.entry("3", "൩"),
                            Map.entry("4", "൪"),
                            Map.entry("5", "൫"),
                            Map.entry("6", "൬"),
                            Map.entry("7", "൭"),
                            Map.entry("8", "൮"),
                            Map.entry("9", "൯"),
                            Map.entry("'", "ഽ"),
                            Map.entry("om", "ഓം"));
                    case TA -> Map.ofEntries(
                            Map.entry("0", "௦"),
                            Map.entry("1", "௧"),
                            Map.entry("2", "௨"),
                            Map.entry("3", "௩"),
                            Map.entry("4", "௪"),
                            Map.entry("5", "௫"),
                            Map.entry("6", "௬"),
                            Map.entry("7", "௭"),
                            Map.entry("8", "௮"),
                            Map.entry("9", "௯"),
                            Map.entry("'", "𑌽"),
                            Map.entry("om", "𑍐"));
                    case OR -> Map.ofEntries(
                            Map.entry("0", "୦"),
                            Map.entry("1", "୧"),
                            Map.entry("2", "୨"),
                            Map.entry("3", "୩"),
                            Map.entry("4", "୪"),
                            Map.entry("5", "୫"),
                            Map.entry("6", "୬"),
                            Map.entry("7", "୭"),
                            Map.entry("8", "୮"),
                            Map.entry("9", "୯"),
                            Map.entry("om", "ଓଁ"),
                            Map.entry("'", "ଽ"));
                });

        return (String uast) -> {
            var str = new ArrayList<String>(uast.length());
            for (var i : uast.toLowerCase().strip().replaceAll("^\\\\+|\\\\+$", "").toCharArray()) {
                str.add(String.valueOf(i));
            }

            var arr = new ArrayList<String>(str.size());

            for (var i = 0; i < str.size(); ) {
                var curr = str.get(i);

                if (curr.equals("/")) {
                    var charList = new ArrayList<String>(2);

                    for (var j = i + 1; j < str.size(); j++) {
                        curr = str.get(j);

                        if (curr.equals("/")) {
                            i = j;
                            break;
                        }

                        if (j == str.size() - 1) {
                            i = j;
                        }

                        charList.add(curr);
                    }

                    var v = String.join("", charList);
                    if (langDict.containsKey(v)) {
                        arr.add(langDict.get(v));
                    }

                    i++;
                    continue;
                }

                arr.add(curr);
                i++;
            }

            return Normalizer.normalize(String.join("", arr), Normalizer.Form.NFC);
        };
    }

    private static Function<String, String> createScriptFunction(LangList lang) {
        var obj = Data.scripts.get(lang);

        return (String s) -> {
            var arr = new ArrayList<String>(s.length());

            for (var v : Normalizer.normalize(s, Normalizer.Form.NFC).toCharArray()) {
                var l = String.valueOf(v);

                if (obj.containsKey(l)) {
                    arr.add(obj.get(l));
                    continue;
                }

                if (Data.allowedSymbols.contains(l)) {
                    arr.add(l);
                }
            }

            return Normalizer.normalize(String.join("", arr), Normalizer.Form.NFC);
        };
    }

    private static String dataToIAST(String data) {
        data =
                Normalizer.normalize(data, Normalizer.Form.NFC)
                        .replaceAll("[\\[\\]{}^~@#$%&*_;.<>\\n\\v\\t\\r\\f]", "");

        var ans = new ArrayList<String>(data.length());

        for (var split : data.split("\\\\")) {
            if (split.equals("ॐ")) {
                ans.add("oṃ");
                continue;
            }

            var saDict = Data.charDict.get(LangList.SA);
            if (saDict.get(LangMap.NUMBERS).containsKey(split)) {
                ans.add(saDict.get(LangMap.NUMBERS).get(split));
                continue;
            }

            if (saDict.get(LangMap.MISC).containsKey(split)) {
                ans.add(saDict.get(LangMap.MISC).get(split));
                continue;
            }

            if (split.equals("ḥ") || split.equals("ṃ") || split.equals("ã")) {
                ans.add(split);
                continue;
            }

            var str = new ArrayList<String>(split.length());
            for (var v : split.toCharArray()) {
                str.add(String.valueOf(v));
            }

            var arr = new ArrayList<String>(str.size());
            for (var i = 0; i < str.size(); ) {
                var curr = str.get(i);

                if (curr.equals("'")) {
                    // arr.push("॑");
                    i++;
                    continue;
                }

                if (curr.equals("`")) {
                    // arr.push("॒");
                    i++;
                    continue;
                }

                if (Data.allowedSymbols.contains(curr)) {
                    arr.add(curr);
                    i++;
                    continue;
                }

                var next = "";
                if (i + 1 < str.size()) {
                    next = str.get(i + 1);
                }

                if (next.equals("ḥ") || next.equals("ṃ") || next.equals("ã")) {
                    if (saDict.get(LangMap.CONSONANTS).containsKey(curr)) {
                        arr.add(curr + "a" + next);
                    } else {
                        arr.add(curr + next);
                    }

                    i += 2;
                    continue;
                }

                if (saDict.get(LangMap.VOWELS).containsKey(curr)) {
                    arr.add(curr);
                    i++;
                    continue;
                }

                if (i == str.size() - 1) {
                    if (curr.equals("ḥ") || curr.equals("ṃ") || curr.equals("ã")) {
                        arr.add(curr);
                    } else {
                        arr.add(curr + "a");
                    }

                    i++;
                    continue;
                }

                if (Data.unAspiratedConstants.contains(curr) && next.equals("h")) {
                    var last = "";

                    if (i + 2 < str.size()) {
                        last = str.get(i + 2);
                    }

                    if (!saDict.get(LangMap.VOWELSIGNS).containsKey(last)) {
                        arr.add(curr + next + "a");
                        i += 2;
                        continue;
                    }

                    if (last.equals("ḥ") || last.equals("ṃ") || last.equals("ã")) {
                        arr.add(curr + next + "a" + last);
                        i += 3;
                        continue;
                    }

                    if (last.equals("-")) {
                        i += 3;
                    } else {
                        i += 2;
                    }

                    arr.add(curr + next);
                    continue;
                }

                if (next.equals("-")) {
                    arr.add(curr);
                    i += 2;
                    continue;
                }

                if (saDict.get(LangMap.VOWELSIGNS).containsKey(next)) {
                    arr.add(curr);
                    i++;
                    continue;
                }

                if (curr.equals("ḥ") || curr.equals("ṃ") || curr.equals("ã")) {
                    arr.add(curr);
                    i++;
                    continue;
                }

                if (Data.iastAllowed.contains(curr)) {
                    i++;
                    continue;
                }

                arr.add(curr + "a");
                i++;
            }

            ans.add(String.join("", arr));
        }

        return Normalizer.normalize(String.join("", ans), Normalizer.Form.NFC);
    }

    private static String IASTToUAST(String data) {
        data =
                Normalizer.normalize(data, Normalizer.Form.NFC)
                        .replaceAll("[\\[\\]{}^~@#$%&*\\-_;<>]", "");

        var str = new ArrayList<String>();
        for (var v : data.toCharArray()) {
            str.add(String.valueOf(v));
        }

        var arr = new ArrayList<String>(str.size());
        var saDict = Data.charDict.get(LangList.SA);

        for (var i = 0; i < str.size(); ) {
            var curr = str.get(i);

            var next = "";
            if (i + 1 < str.size()) {
                next = str.get(i + 1);
            }

            if (saDict.get(LangMap.CONSONANTS).containsKey(curr)) {
                if (Data.unAspiratedConstants.contains(curr)) {
                    if (next.equals("a") && (i + 2 < str.size() && str.get(i + 2).equals("h"))) {
                        arr.add(curr + "\\");
                        i += 2;
                        continue;
                    }

                    if (next.equals("h")) {
                        var last = "";
                        if (i + 2 < str.size()) {
                            last = str.get(i + 2);
                        }

                        if (saDict.get(LangMap.CONSONANTS).containsKey(last)) {
                            arr.add(curr + next + "-");
                            i += 2;
                            continue;
                        }

                        if (last.equals("a")) {
                            if (i + 3 < str.size()) {
                                last = str.get(i + 3);
                            }

                            if (last.equals("i") || last.equals("u")) {
                                arr.add(curr + next + "a" + last);
                                i += 4;
                                continue;
                            }

                            i += 3;
                        } else {
                            i += 2;
                        }

                        arr.add(curr + next);
                        continue;
                    }
                }

                if (next.equals("a")) {
                    var last = "";
                    if (i + 2 < str.size()) {
                        last = str.get(i + 2);
                    }

                    if (last.equals("i") || last.equals("u")) {
                        arr.add(curr + "a" + last);
                        i += 3;
                        continue;
                    }

                    arr.add(curr);
                    i += 2;
                    continue;
                }

                if (saDict.get(LangMap.CONSONANTS).containsKey(next)
                        || (next.equals(".") || next.equals("..") || next.equals("'"))
                        || i == str.size() - 1) {
                    arr.add(curr + "-");
                    i++;
                    continue;
                }

                arr.add(curr);
                i++;
                continue;
            }

            if (curr.equals("a") && (next.equals("i") || next.equals("u"))) {
                arr.add(curr + next + "\\");
                i += 2;
                continue;
            }

            if (saDict.get(LangMap.VOWELS).containsKey(curr)
                    && saDict.get(LangMap.CONSONANTS).containsKey(next)) {
                arr.add(curr + "\\");
                i++;
                continue;
            }

            arr.add(curr);
            i++;
        }

        var ans = new ArrayList<String>(arr.size());
        for (var k = 0; k < arr.size(); k++) {
            var curr = arr.get(k);

            var hasDash = curr.contains("-");

            curr = curr.replaceAll("\\\\", "");
            curr = curr.replaceAll("-", "");

            for (var j :
                    new String[] {"\\.", "'", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}) {
                if (curr.equals(".") && (k + 1 < arr.size() && arr.get(k + 1).equals("."))) {
                    curr = curr.replaceAll(curr, "\\\\/../\\\\");
                    k++;
                    continue;
                }

                curr = curr.replaceAll(j, "\\\\/" + j + "/\\\\");
            }

            var val = new StringBuilder(curr);
            if (Data.unAspiratedConstants.contains(curr)
                    && k + 1 < arr.size()
                    && arr.get(k + 1).equals("h")) {
                val.append("a");
            }

            if (hasDash) {
                val.append("-");
            }

            if (saDict.get(LangMap.VOWELS).containsKey(curr)) {
                val.append("\\");
            }

            ans.add(val.toString());
        }

        if (!ans.isEmpty()
                && !str.isEmpty()
                && saDict.get(LangMap.CONSONANTS).containsKey(ans.get(ans.size() - 1))
                && !str.get(str.size() - 1).equals("a")) {
            ans.add("-");
        }

        var fin = new ArrayList<String>(ans.size());

        for (var v : String.join("", ans).toCharArray()) {
            var l = String.valueOf(v);
            if (Data.iastDataDict.containsKey(l)) {
                fin.add("/" + Data.iastDataDict.get(l) + "/");
            } else {
                fin.add(l);
            }
        }

        return Normalizer.normalize(String.join("", fin), Normalizer.Form.NFC);
    }

    private static Function<String, String> createDataFunction(LangList lang) {
        var obj = Data.charDict.get(lang);

        return (String data) -> {
            var ans = new ArrayList<String>(data.length());

            for (var split : data.split("\\\\")) {
                if (obj.get(LangMap.MISC).containsKey(split)
                        || obj.get(LangMap.NUMBERS).containsKey(split)) {
                    ans.add(split);
                    continue;
                }

                if (obj.get(LangMap.VOWELS).containsKey(split)) {
                    ans.add(obj.get(LangMap.VOWELS).get(split));
                    continue;
                }

                var str = new ArrayList<String>(split.length());
                for (var v : split.toCharArray()) {
                    str.add(String.valueOf(v));
                }

                var arr = new ArrayList<String>(str.size());
                for (var i = 0; i < str.size(); ) {
                    var curr = str.get(i);

                    if (lang == LangList.SA) {
                        if (curr.equals("'")) {
                            arr.add("॑");
                            i++;
                            continue;
                        }

                        if (curr.equals("`")) {
                            arr.add("॒");
                            i++;
                            continue;
                        }
                    }

                    if (Set.of(",", "?", "!", "\"", ":", "(", ")", "=").contains(curr)) {
                        arr.add(curr);
                        i++;
                        continue;
                    }

                    if (Data.unAspiratedConstants.contains(curr)) {
                        var consonant = "";
                        if (i + 1 < str.size() && str.get(i + 1).equals("h")) {
                            consonant = String.join("", str.subList(i, i + 2));
                            i += 2;
                        } else {
                            consonant = curr;
                            i++;
                        }

                        if (obj.get(LangMap.CONSONANTS).containsKey(consonant)) {
                            arr.add(obj.get(LangMap.CONSONANTS).get(consonant));
                        }

                        continue;
                    }

                    if (obj.get(LangMap.CONSONANTS).containsKey(curr)) {
                        arr.add(obj.get(LangMap.CONSONANTS).get(curr));
                    }

                    var vowel = "";
                    if (curr.equals("a")
                            && (i + 1 < str.size()
                                    && (str.get(i + 1).equals("i")
                                            || str.get(i + 1).equals("u")))) {
                        vowel = String.join("", str.subList(i, i + 2));
                        i += 2;
                    } else {
                        vowel = curr;
                        i++;
                    }

                    if (obj.get(LangMap.VOWELSIGNS).containsKey(vowel)) {
                        arr.add(obj.get(LangMap.VOWELSIGNS).get(vowel));
                    }
                }

                ans.add(String.join("", arr));
            }

            return Normalizer.normalize(String.join("", ans), Normalizer.Form.NFC);
        };
    }

    private static String devanagariToUAST(String data) {
        var str = new ArrayList<String>();
        for (var v : Normalizer.normalize(data, Normalizer.Form.NFC).toCharArray()) {
            str.add(String.valueOf(v));
        }

        var arr = new ArrayList<String>(str.size());

        for (var i = 0; i < str.size(); i++) {
            var curr = str.get(i);

            var next = "";
            if (i + 1 < str.size()) {
                next = str.get(i + 1);
            }

            if (curr.equals("॑")) {
                arr.add("\\'");
                continue;
            }

            if (curr.equals("॒")) {
                arr.add("\\`");
                continue;
            }

            var val = Data.devanagariDataDict.getOrDefault(curr, curr);
            var nextVal = Data.devanagariDataDict.getOrDefault(next, next);
            var checkVowel = Data.charDict.get(LangList.SA).get(LangMap.VOWELS).containsValue(curr);
            var checkConsonant =
                    Data.charDict.get(LangList.SA).get(LangMap.CONSONANTS).containsValue(next);

            if (checkVowel && checkConsonant) {
                arr.add(val + "\\");
                continue;
            }

            if (Data.unAspiratedConstants.contains(val) && nextVal.equals("h")) {
                arr.add(val + "a");
                continue;
            }

            arr.add(val);
        }

        return Normalizer.normalize(String.join("", arr), Normalizer.Form.NFC);
    }

    private static String SLPToIAST(String data) {
        final var slpDataDict = Data.slpDataDict;
        var str = new ArrayList<String>(data.length());

        for (var v : data.toCharArray()) {
            var k = String.valueOf(v);
            if (slpDataDict.containsKey(k)) {
                str.add(slpDataDict.get(k));
            }
        }

        return Normalizer.normalize(String.join("", str), Normalizer.Form.NFC);
    }
}
