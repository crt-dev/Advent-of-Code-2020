package org.crtdev.aoc.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Day10 {

    private List<Integer> input;
    private long permutationCount = 0;
    private Map<Integer, Long> memo = new HashMap();

    public void read(String fileName) throws IOException {
        this.input = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(String.format("%s.txt", fileName));
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(Integer.parseInt(line));
            }
        }
    }

    public int solve() {
        this.input.add(0);
        Collections.sort(this.input);
        var map = new HashMap<Integer, Integer>();
        int builtInAdaptor = this.input.stream().mapToInt(t -> t).max().getAsInt() + 3;
        this.input.add(builtInAdaptor);

        for (int i = 1; i < this.input.size(); i++) {
            int diff = this.input.get(i) - this.input.get(i - 1);
            int count = map.containsKey(diff) ? map.get(diff) : 0;
            map.put(diff, count + 1);
        }

        return map.get(1) * map.get(3);
    }

    public long countOptions(int p) {
        if (p == this.input.size() - 1) {
            return 1;
        } else if (this.memo.containsKey(p)) {
            return this.memo.get(p);
        }

        long count = 0;
        for (int i = p + 1; i < this.input.size(); i++) {
            if (this.input.get(i) - this.input.get(p) <= 3) {
                count += this.countOptions(i);
            } else {
                break;
            }
        }
        this.memo.put(p, count);
        return count;
    }

    public long solve2() {
        this.input.add(0);
        Collections.sort(this.input);
        int builtInAdaptor = this.input.stream().mapToInt(t -> t).max().getAsInt() + 3;
        this.input.add(builtInAdaptor);
        return this.countOptions(0);
    }

    public long bruteForce() {
        this.input.add(0);
        Collections.sort(this.input);
        int builtInAdaptor = this.input.stream().mapToInt(t -> t).max().getAsInt() + 3;
        this.input.add(builtInAdaptor);
        getOptions(0, "(0)", String.format("%s", builtInAdaptor));
        return this.permutationCount;
    }

    public void getOptions(int startpos, String history, String terminationValue) {
        int v = this.input.get(startpos);
        int i = startpos + 1;

        while (i < this.input.size()) {
            int dv = this.input.get(i);
            int diff = dv - v;
            if (diff >= 1 && diff <= 3) {
                this.getOptions(i, String.format("%s, %d", history, dv), terminationValue);
            } else {
                break;
            }
            i++;
        }

        if (history.endsWith(terminationValue)) {
            this.permutationCount++;
            if (this.permutationCount % 1000000 == 0) {
                System.out.println(String.format("Added %f M perms", (double)this.permutationCount / 1E6));
            }
        }
    }
}