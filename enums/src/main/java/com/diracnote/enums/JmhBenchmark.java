package com.diracnote.enums;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class JmhBenchmark {

    private static final String CODE = "JUNE";

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public Month Enums_Access_By_Iterator() {
        return Month.getByArrayCode(CODE);
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public Month Enums_Access_By_Map() {
        return Month.getByMapCode(CODE);
    }

}
