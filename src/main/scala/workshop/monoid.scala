package workshop

import cats.kernel.Semigroup

trait ListMonoid[A] extends Semigroup[A] {
  def zero: A

  def combineAll(l: List[A]): A = ???
  def foldMap[B](l: List[B])(f: B => A): A = ???
}
