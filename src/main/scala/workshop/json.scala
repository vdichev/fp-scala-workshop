package workshop

trait JsonSerializer[A] {
  def toJson(a: A): String
}

object JsonSerializer {
  def toJson[A](a: A)(implicit js: JsonSerializer[A]): String =
    js.toJson(a)

  implicit val intSerializable =
    new JsonSerializer[Int] {
      def toJson(a: Int): String = a.toString
    }

  implicit def listSerializer[A: JsonSerializer]: JsonSerializer[List[A]] =
    new JsonSerializer[List[A]] {
      def toJson(a: List[A]): String =
        (a map (v => JsonSerializer.toJson(v))).mkString("[", ", ", "]")
    }
}

