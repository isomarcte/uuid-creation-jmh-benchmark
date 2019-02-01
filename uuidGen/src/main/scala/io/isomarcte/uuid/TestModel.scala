package io.isomarcte.uuid

import java.security._
import java.time._
import java.util._
import java.nio.charset._

final case class TestModel(digest: UUID, string: String, instant: Instant, uuid: UUID)

object TestModel {
  val charset: Charset = StandardCharsets.UTF_8

  def sha256(
    string: String,
    instant: Instant,
    uuid: UUID
  ): TestModel = {
    val md: MessageDigest = MessageDigest.getInstance("SHA-256")
    md.update(string.getBytes(charset))
    md.update(instant.toString().getBytes(charset))
    TestModel(
      digest = UUID.nameUUIDFromBytes(md.digest(uuid.toString().getBytes)),
      string = string,
      instant = instant,
      uuid = uuid
    )
  }

  def sha1(
    string: String,
    instant: Instant,
    uuid: UUID
  ): TestModel = {
    val md: MessageDigest = MessageDigest.getInstance("SHA-1")
    md.update(string.getBytes(charset))
    md.update(instant.toString().getBytes(charset))
    TestModel(
      digest = UUID.nameUUIDFromBytes(md.digest(uuid.toString().getBytes)),
      string = string,
      instant = instant,
      uuid = uuid
    )
  }

  def md5(
    string: String,
    instant: Instant,
    uuid: UUID
  ): TestModel = {
    val md: MessageDigest = MessageDigest.getInstance("MD5")
    md.update(string.getBytes(charset))
    md.update(instant.toString().getBytes(charset))
    TestModel(
      digest = UUID.nameUUIDFromBytes(md.digest(uuid.toString().getBytes)),
      string = string,
      instant = instant,
      uuid = uuid
    )
  }

  def withInstance(
    md: MessageDigest
  )(
    string: String,
    instant: Instant,
    uuid: UUID
  ): TestModel = {
    md.reset
    md.update(string.getBytes(charset))
    md.update(instant.toString().getBytes(charset))
    TestModel(
      digest = UUID.nameUUIDFromBytes(md.digest(uuid.toString().getBytes)),
      string = string,
      instant = instant,
      uuid = uuid
    )
  }

  def random(
    string: String,
    instant: Instant,
    uuid: UUID
  ): TestModel =
    TestModel(UUID.randomUUID(), string, instant, uuid)
}
