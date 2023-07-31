package dev.uast;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var x = "ūrdhvamūlam adhaḥśākham";

        var fs = UAST.convertor.get("iast").get("devanagari");
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
        System.out.println(UAST.convertor);
    }
}
