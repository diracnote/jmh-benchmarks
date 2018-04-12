package com.diracnote.reflection;

import org.openjdk.jmh.annotations.*;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
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

    private static final Field OBJECT_MUTABLE_FIELD;

    private static final Unsafe UNSAFE;
    private static final long FIELD_OFFSET;

    static {
        try {
            INTERFACE_METHOD = Person.class.getMethod("getName");
            INTERFACE_METHOD.setAccessible(true);

            INTERFACE_METHOD_NOACCESS = Person.class.getMethod("getName");

            OBJECT_IMMUTABLE_METHOD = ImmutablePerson.class.getMethod("getName");
            OBJECT_IMMUTABLE_METHOD.setAccessible(true);

            OBJECT_MUTABLE_METHOD = MutablePerson.class.getMethod("getName");
            OBJECT_MUTABLE_METHOD.setAccessible(true);

            OBJECT_MUTABLE_FIELD = MutablePerson.class.getDeclaredField("name");
            OBJECT_MUTABLE_FIELD.setAccessible(true);

            UNSAFE = getUnsafe();
            FIELD_OFFSET = UNSAFE.objectFieldOffset(OBJECT_MUTABLE_FIELD);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Unsafe getUnsafe() {
        try {
            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            return (Unsafe) singleoneInstanceField.get(null);
        } catch (Exception ex) {
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

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Mutable_Object_Field_Reflection() throws Exception {
        return (String) OBJECT_MUTABLE_FIELD.get(oMutable);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public String Unsafe_getObject() throws Exception {
        return (String) UNSAFE.getObjectVolatile(oMutable, FIELD_OFFSET);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public boolean Unsafe_Ensure() throws Exception {
        return OBJECT_MUTABLE_FIELD.getDeclaringClass().isAssignableFrom(oMutable.getClass());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public Class<?> Instance_getClass() {
        return oMutable.getClass();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public Object[] New_Object_Array() {
        return new Object[]{};
    }

}
