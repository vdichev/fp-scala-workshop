package workshop


import cats.syntax.apply._
import cats.syntax.either._


object EitherExample {
  type FormData = Map[String, String]
  def getValue(name: String)(data: FormData): Either[String,String] =
    data.get(name).toRight(s"$name not specified")
  def parseInt(name: String)(data: String): Either[String,Int] =
    Either.catchOnly[NumberFormatException](data.toInt).leftMap(_ => s"$name must be an integer")
  def nonNegative(name: String)(data: Int): Either[String,Int] =
    data.asRight[String].ensure(s"$name must be non-negative")(_ >= 0)
  def nonBlank(name: String)(data: String): Either[String,String] =
    data.asRight[String].ensure(s"$name cannot be blank")(_.nonEmpty)
  def readName(data: FormData): Either[String,String] =
    getValue("name")(data).flatMap(nonBlank("name"))
  def readAge(data: FormData): Either[String,Int] =
    getValue("age")(data).flatMap(parseInt("age")).flatMap(nonNegative("age"))
  def readUser(data: FormData): Either[String,User] =
    for {
      name <- readName(data)
      age <- readAge(data)
    } yield User(name, age)

}
