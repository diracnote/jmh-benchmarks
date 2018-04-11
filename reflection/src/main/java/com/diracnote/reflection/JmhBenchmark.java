package com.diracnote.reflection;

import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class JmhBenchmark {

    private static final Person iImmutable = new ImmutablePerson("John Doe");

    private static final Person iMutable = new MutablePerson("John Doe");

    private static final ImmutablePerson oImmutable = new ImmutablePerson("John Doe");

    private static final MutablePerson oMutable = new MutablePerson("John Doe");

    private static final Method INTERFACE_METHOD;

    private static final Method INTERFACE_METHOD_NOACCESS;

    private static final Method OBJECT_IMMUTABLE_METHOD;

    private static final Method OBJECT_MUTABLE_METHOD;

    static {
        try {
            INTERFACE_METHOD = Person.class.getMethod("getName");
            INTERFACE_METHOD.setAccessible(true);

            INTERFACE_METHOD_NOACCESS = Person.class.getMethod("getName");

            OBJECT_IMMUTABLE_METHOD = ImmutablePerson.class.getMethod("getName");
            OBJECT_IMMUTABLE_METHOD.setAccessible(true);

            OBJECT_MUTABLE_METHOD = MutablePerson.class.getMethod("getName");
            OBJECT_MUTABLE_METHOD.setAccessible(true);
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Immutable_Interface_Direct() {
        return iImmutable.getName();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Mutable_Interface_Direct() {
        return iMutable.getName();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Immutable_Object_Direct() {
        return oImmutable.getName();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Mutable_Object_Direct() {
        return oMutable.getName();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Immutable_Interface_Reflection() throws Exception {
        return (String) INTERFACE_METHOD.invoke(iImmutable);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Mutable_Interface_Reflection() throws Exception {
        return (String) INTERFACE_METHOD.invoke(iMutable);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Immutable_Interface_Reflection_WithoutSetAccessible() throws Exception {
        return (String) INTERFACE_METHOD_NOACCESS.invoke(iImmutable);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Mutable_Interface_Reflection_WithoutSetAccessible() throws Exception {
        return (String) INTERFACE_METHOD_NOACCESS.invoke(iMutable);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Immutable_Object_Reflection() throws Exception {
        return (String) OBJECT_IMMUTABLE_METHOD.invoke(oImmutable);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Mutable_Object_Reflection() throws Exception {
        return (String) OBJECT_MUTABLE_METHOD.invoke(oMutable);
    }

}
