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
    ???
  def parseInt(name: String)(data: String): ValidatedNel[String,Int] =
    ???
  def nonNegative(name: String)(data: Int): ValidatedNel[String,Int] =
    ???
  def nonBlank(name: String)(data: String): ValidatedNel[String,String] =
    ???
  def readName(data: FormData): ValidatedNel[String,String] =
    ???
  def readAge(data: FormData): ValidatedNel[String,Int] =
    ???
  def readUser(data: FormData): ValidatedNel[String,User] = ???
}
