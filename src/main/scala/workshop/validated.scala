package workshop

import cats.data.NonEmptyList
import cats.data.{Validated,ValidatedNel}
import cats.syntax.apply._
import cats.syntax.option._
import cats.syntax.validated._

case class User(name: String, age: Int)

object ValidatedExample {
  type FormData = Map[String, String]
  def getValue(name: String)(data: FormData): ValidatedNel[String,String] =
    data.get(name).toValidNel(s"$name not specified")
  def parseInt(name: String)(data: String): ValidatedNel[String,Int] =
    Validated.catchOnly[NumberFormatException](data.toInt).leftMap(_ => NonEmptyList.of(s"$name must be an integer"))
  def nonNegative(name: String)(data: Int): ValidatedNel[String,Int] =
    data.validNel.ensure(NonEmptyList.of(s"$name must be non-negative"))(_ >= 0)
  def nonBlank(name: String)(data: String): ValidatedNel[String,String] =
    data.validNel.ensure(NonEmptyList.of(s"$name cannot be blank"))(_.nonEmpty)
  def readName(data: FormData): ValidatedNel[String,String] =
    getValue("name")(data).andThen(nonBlank("name"))
  def readAge(data: FormData): ValidatedNel[String,Int] =
    getValue("age")(data).andThen(parseInt("age")).andThen(nonNegative("age"))
  def readUser(data: FormData): ValidatedNel[String,User] =
    (readName(data), readAge(data)).mapN(User.apply)
  // You need the "-Ypartial-unification" scalac flag in order for `mapN` to work
}
