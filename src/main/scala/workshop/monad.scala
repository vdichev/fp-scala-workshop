package workshop

import cats.Functor
// implement methods in terms of defined fundamental ones

trait Monad1[M[_]] extends Functor[M] {
  def unit[A](a: => A): M[A]
  def flatMap[A,B](ma: M[A])(f: A => M[B]): M[B]

  def map[A,B](ma: M[A])(f: A => B): M[B] = flatMap(ma)(a => unit(f(a)))
  def flatten[A](mma: M[M[A]]): M[A] = flatMap(mma)(x => x)
  def compose[A,B,C](f: A => M[B], g: B => M[C]): A => M[C] = a => flatten(map(f(a))(g))
}

trait Monad2[M[_]] extends Functor[M] {
  def unit[A](a: => A): M[A]
  def map[A,B](ma: M[A])(f: A => B): M[B]
  def flatten[A](mma: M[M[A]]): M[A]

  def flatMap[A,B](ma: M[A])(f: A => M[B]): M[B] = flatten(map(ma)(f))
}
