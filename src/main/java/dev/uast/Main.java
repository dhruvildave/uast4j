package dev.uast;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Set<Scheme> fromSchemes =
            Set.of(Scheme.UAST, Scheme.RAW, Scheme.DEVANĀGARĪ, Scheme.IAST, Scheme.SLP, Scheme.GU, Scheme.OR, Scheme.TA, Scheme.TE, Scheme.ML, Scheme.KN);

    private static final Set<Scheme> toSchemes =
            Set.of(Scheme.UAST, Scheme.DEVANĀGARĪ, Scheme.IAST, Scheme.GU, Scheme.OR, Scheme.TA, Scheme.TE, Scheme.ML, Scheme.KN);

    public static void main(String[] args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("invalid number of arguments");
        }

        var to = Scheme.DEVANĀGARĪ;
        var from = Scheme.UAST;

        for (var i = 0; i < args.length; i += 2) {
            if (args[i + 1].equals("devanagari")) {
                args[i + 1] = "devanāgarī";
            }

            args[i + 1] = args[i + 1].toUpperCase();
            var s = Scheme.valueOf(args[i + 1]);
            if (args[i].equals("-to")) {
                if (!toSchemes.contains(s)) {
                    throw new IllegalArgumentException("invalid value for `to`");
                }
                to = s;
                continue;
            }

            if (args[i].equals("-from")) {
                if (!fromSchemes.contains(s)) {
                    throw new IllegalArgumentException("invalid value for `from`");
                }
                from = s;
                continue;
            }

            if (args[i].matches("-\\w+")) {
                throw new IllegalArgumentException("illegal argument passed " + args[i]);
            }
        }

        var transforms = UAST.convertor.get(from).get(to);
        try (var in = new Scanner(System.in, StandardCharsets.UTF_8);
                var out = new PrintWriter(System.out, true)) {
            while (in.hasNextLine()) {
                var x = in.nextLine();

                for (var l : x.split("\\n")) {
                    var s = l.split(" ");
                    var arr = new ArrayList<String>(s.length);
                    for (var w : s) {
                        for (var f : transforms) {
                            w = f.apply(w);
                        }
                        arr.add(w);
                    }
                    out.println(String.join(" ", arr));
                }
            }
        }
    }
}
