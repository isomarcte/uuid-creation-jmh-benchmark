package io.isomarcte.uuid.jmh

import io.isomarcte.uuid._
import java.security._
import java.time._
import java.util._
import org.openjdk.jmh.annotations._

class UUIDBenchmark {
  import UUIDBenchmark._
  def updateState(state: UUIDBenchmarkState): Unit =
    state.seed = state.seed + 1

  @Benchmark
  def randomUUIDBenchmark(state: UUIDBenchmarkState): TestModel = {
    updateState(state)
    TestModel.random(
      state.seed.toString,
      Instant.ofEpochMilli(state.seed),
      UUID.nameUUIDFromBytes(state.seed.toString.getBytes(TestModel.charset))
    )
  }

  @Benchmark
  def digestUUIDBenchmarkMd5(state: UUIDBenchmarkState): TestModel = {
    updateState(state)
    TestModel.md5(
      state.seed.toString,
      Instant.ofEpochMilli(state.seed),
      UUID.nameUUIDFromBytes(state.seed.toString.getBytes(TestModel.charset))
    )
  }

  @Benchmark
  def digestUUIDBenchmarkSha1(state: UUIDBenchmarkState): TestModel = {
    updateState(state)
    TestModel.sha1(
      state.seed.toString,
      Instant.ofEpochMilli(state.seed),
      UUID.nameUUIDFromBytes(state.seed.toString.getBytes(TestModel.charset))
    )
  }

  @Benchmark
  def digestUUIDBenchmarkSha256(state: UUIDBenchmarkState): TestModel = {
    updateState(state)
    TestModel.sha256(
      state.seed.toString,
      Instant.ofEpochMilli(state.seed),
      UUID.nameUUIDFromBytes(state.seed.toString.getBytes(TestModel.charset))
    )
  }

  @Benchmark
  def digestUUIDBenchmarkMd5Shared(state: UUIDBenchmarkState): TestModel = {
    updateState(state)
    TestModel.withInstance(
      state.md5Md
    )(
      state.seed.toString,
      Instant.ofEpochMilli(state.seed),
      UUID.nameUUIDFromBytes(state.seed.toString.getBytes(TestModel.charset))
    )
  }

  @Benchmark
  def digestUUIDBenchmarkSha1Shared(state: UUIDBenchmarkState): TestModel = {
    updateState(state)
    TestModel.withInstance(
      state.sha1Md
    )(
      state.seed.toString,
      Instant.ofEpochMilli(state.seed),
      UUID.nameUUIDFromBytes(state.seed.toString.getBytes(TestModel.charset))
    )
  }

  @Benchmark
  def digestUUIDBenchmarkSha256Shared(state: UUIDBenchmarkState): TestModel = {
    updateState(state)
    TestModel.withInstance(
      state.sha256Md
    )(
      state.seed.toString,
      Instant.ofEpochMilli(state.seed),
      UUID.nameUUIDFromBytes(state.seed.toString.getBytes(TestModel.charset))
    )
  }
}

object UUIDBenchmark {
  @State(Scope.Thread)
  class UUIDBenchmarkState {
    @volatile
    var seed: Long = Long.MinValue
    var md5Md: MessageDigest = MessageDigest.getInstance("MD5")
    var sha1Md: MessageDigest = MessageDigest.getInstance("SHA-1")
    var sha256Md: MessageDigest = MessageDigest.getInstance("SHA-256")
  }
}
