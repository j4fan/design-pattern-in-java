package com.example.demo.banchmark;

import org.apache.commons.lang.StringUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 1)
@Measurement(iterations = 10)
public class StringContainsTest {

    private List<String> testString;
    private String keyword;
    private int n;
    private int[] next;


    @Setup(Level.Trial)
    public void init() {
        n = 10000;
        testString = new ArrayList<>();
        testString.add("ABCAABAABCDABCABABCAABAABCDABCABABCAABAABCDABCABABCAABAABCDABCABABCAABAABCDABCABABCDEABABABEAB");
        keyword = "ABCDEABABABEAB";
        next = MyKmp.getNext(keyword);
    }

    @Benchmark
    public void testViolentMatch() {
        for (int i = 0; i < n; i++) {
            for (String s : testString) {
                ViolentMatch.match(s, keyword);
            }
        }
    }

    @Benchmark
    public void testContains() {
        for (int i = 0; i < n; i++) {
            for (String s : testString) {
                s.contains(keyword);
            }
        }
    }

    @Benchmark
    public void testRegex() {
        Pattern pattern = Pattern.compile("(?<!\\S)" + keyword + "(?!\\S)");
        for (int i = 0; i < n; i++) {
            for (String s : testString) {
                Matcher matcher = pattern.matcher(s);
                matcher.find();
            }
        }
    }

    @Benchmark
    public void testApache() {
        for (int i = 0; i < n; i++) {
            for (String s : testString) {
                StringUtils.containsIgnoreCase(s, keyword);
            }
        }
    }


    @Benchmark
    public void testKMP() {
        for (int i = 0; i < n; i++) {
            for (String s : testString) {
                MyKmp.match(s, keyword, next);
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(StringContainsTest.class.getSimpleName()).build();
        new Runner(options).run();
    }

}
