This is a simple jmh benchmark comparing creating `java.util.UUID` values using either derived or random data.

The TL;DR is, using only JDK library primitives, if you create a new `MessageDigest` each time you need to create a `UUID` value then using MD5 or SHA-1 hashing is faster than `UUID.randomUUID`, but random is faster than SHA-256 generation.

If you share the instance of `MessageDigest`, rather than re-create it, all deterministic methods are faster than random generation.

Full results are in the `results/` folder.

|Benchmark                                                           |Mode  |Threads |Samples |Score          |Score Error (99.9%) |Unit  |
| ------------------------------------------------------------------ | ---- | ------ | -------| ------------- | ------------------ | ---- |
|io.isomarcte.uuid.jmh.UUIDBenchmark.digestUUIDBenchmarkMd5          |thrpt |14      |50      |1029726.868709 |25070.778880        |ops/s |
|io.isomarcte.uuid.jmh.UUIDBenchmark.digestUUIDBenchmarkMd5Shared    |thrpt |14      |50      |1392467.867144 |43813.309841        |ops/s |
|io.isomarcte.uuid.jmh.UUIDBenchmark.digestUUIDBenchmarkSha1         |thrpt |14      |50      |698416.789480  |12767.231542        |ops/s |
|io.isomarcte.uuid.jmh.UUIDBenchmark.digestUUIDBenchmarkSha1Shared   |thrpt |14      |50      |1595305.292869 |61465.904377        |ops/s |
|io.isomarcte.uuid.jmh.UUIDBenchmark.digestUUIDBenchmarkSha256       |thrpt |14      |50      |675192.567710  |9515.900212         |ops/s |
|io.isomarcte.uuid.jmh.UUIDBenchmark.digestUUIDBenchmarkSha256Shared |thrpt |14      |50      |1471128.651168 |47989.000330        |ops/s |
|2io.isomarcte.uuid.jmh.UUIDBenchmark.randomUUIDBenchmark            |thrpt |14      |50      |896512.094796  |11097.132359        |ops/s |
