package org.crtdev.aoc;

import org.crtdev.aoc.solution.Day10;

public class D10Runner {
    private static String EXERCISE = "d10";
    private static String EXAMPLE = EXERCISE + "ex";

    public static void main(String[] args) throws Exception {
        solve(EXAMPLE, 1); //35
        solve(EXAMPLE + "2", 1); //220
        solve(EXERCISE, 1); //1890
        solve(EXAMPLE, 2); //8
        solve(EXAMPLE + "2", 2); //19208
        solve(EXERCISE, 2); //49607173328384
    }

    public static void solve(String input, int part) throws Exception {
        Day10 solution = new Day10();
        solution.read(input);
        var p1 = part == 1 ? solution.solve() : solution.solve2();
        System.out.println(String.format("Part %d%s: %s", part, input.equals(EXAMPLE) ? "ex" : "", p1));
    }
}
