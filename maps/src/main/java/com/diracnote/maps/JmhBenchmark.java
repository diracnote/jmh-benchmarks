package com.diracnote.maps;

import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

@State(Scope.Thread)
public class JmhBenchmark {

    private static HashMap<Object, Object> hashMap = new HashMap<>(2048);

    private static ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>(2048);

    private static final Object[] CACHE = IntStream.range(0, 1024).mapToObj(value -> Integer.valueOf(value)).toArray();

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public Integer HashMap_PUT_Single_Thread() {
        hashMap.clear();
        for (Object cache : CACHE) {
            hashMap.put(cache, cache);
        }
        return 0;
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public Integer ConcurrentHashMap_PUT_Single_Thread() {
        concurrentHashMap.clear();
        for (Object cache : CACHE) {
            concurrentHashMap.put(cache, cache);
        }
        return 0;
    }

}
