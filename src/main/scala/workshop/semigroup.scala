package workshop

import cats.kernel.Semigroup

object SemigroupExercise {
  // implement semigroup instances for Int and Option
  implicit object IntSemigroup extends Semigroup[Int] {
    def combine(x: Int, y: Int) = x + y
  }

  implicit def optionSemigroup[A](implicit m: Semigroup[A]): Semigroup[Option[A]] =
    new Semigroup[Option[A]] {
      def combine(o1: Option[A], o2: Option[A]): Option[A] =
        o1 match {
          case None => o2
          case Some(x) => o2 match {
            case None => o1
            case Some(y) => Some(m.combine(x, y))
          }
        }
    }

}
