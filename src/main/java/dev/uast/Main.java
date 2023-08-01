package dev.uast;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static final Set<String> fromSchemes =
            Set.of("uast", "raw", "devanagari", "iast", "slp", "gu", "or", "ta", "te", "ml", "kn");

    static final Set<String> toSchemes =
            Set.of("uast", "devanagari", "iast", "gu", "or", "ta", "te", "ml", "kn");

    public static void main(String[] args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("invalid number of arguments");
        }

        var to = "devanagari";
        var from = "uast";

        for (var i = 0; i < args.length; i += 2) {
            if (args[i].equals("-to")) {
                if (!toSchemes.contains(args[i + 1])) {
                    throw new IllegalArgumentException("invalid value for `to`");
                }
                to = args[i + 1];
                continue;
            }

            if (args[i].equals("-from")) {
                if (!fromSchemes.contains(args[i + 1])) {
                    throw new IllegalArgumentException("invalid value for `from`");
                }
                from = args[i + 1];
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
