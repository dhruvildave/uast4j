package dev.uast;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var x = "ūrdhvamūlam adhaḥśākham 15";

        for (var i : new String[] {"devanagari", "gu", "te", "ta", "kn", "ml", "or", "io"}) {
            var fs = UAST.convertor.get("iast").get(i);

            if (fs == null) {
                System.out.println(i + " not found");
                continue;
            }

            for (var l : x.split("\\n")) {
                var s = l.split(" ");
                var arr = new ArrayList<String>(s.length);
                for (var w : s) {
                    for (var f : fs) {
                        w = f.apply(w);
                    }
                    arr.add(w);
                }
                System.out.println(String.join(" ", arr));
            }
        }
    }
}
