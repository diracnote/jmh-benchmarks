# jmh-benchmarks

Some collections of jmh benchmarks for Java.

## Build

Build the package with Maven:

```bash
mvn package
```
## Benchmarks

### Reflection

Benchmark to test the cost between method call and reflection method invoke. Original from [nfrankel](https://github.com/nfrankel/reflection-performance), you can find the discussion [here](https://www.reddit.com/r/java/comments/4wkzck/performance_cost_of_reflection/).

### Differences from original case

 - excludes test for ReflectASM
 - replace Field.get() by Method.invoke()
 - simplify Person interface
 - change benchmark mode to Mode.AverageTime

### Run

```bash
java -jar reflection/target/benchmarks.jar
```

### Benchmark result

Check the file `reflection\reflection.benchmark` for the detail log of JMH.

```bash
Benchmark                                          Mode  Cnt   Score   Error  Units
JmhBenchmark.immutableInterfaceDirect              avgt  200   4.734 �� 0.035  ns/op
JmhBenchmark.immutableInterfaceReflection          avgt  200  22.316 �� 0.150  ns/op
JmhBenchmark.immutableInterfaceReflectionNoAccess  avgt  200  72.675 �� 0.383  ns/op
JmhBenchmark.immutableObjectDirect                 avgt  200   4.716 �� 0.020  ns/op
JmhBenchmark.immutableObjectReflection             avgt  200  19.552 �� 0.150  ns/op
JmhBenchmark.mutableInterfaceDirect                avgt  200   4.748 �� 0.025  ns/op
JmhBenchmark.mutableInterfaceReflection            avgt  200  22.057 �� 0.106  ns/op
JmhBenchmark.mutableInterfaceReflectionNoAccess    avgt  200  72.917 �� 0.395  ns/op
JmhBenchmark.mutableObjectDirect                   avgt  200   4.722 �� 0.021  ns/op
JmhBenchmark.mutableObjectReflection               avgt  200  19.541 �� 0.116  ns/op
```
