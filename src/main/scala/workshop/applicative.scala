package workshop

// implement methods in terms of defined fundamental ones

trait Applicative1[F[_]] {
  def pure[A](a: A): F[A]
  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
  def map[A, B](fa: F[A])(f: A => B): F[B]

  def map2[A,B,C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = map(product(fa, fb))(f.tupled)
  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B] = map2(ff, fa)((f, a) => f(a))
}

trait Applicative[F[_]] {
  def pure[A](a: A): F[A]
  def map[A, B](fa: F[A])(f: A => B): F[B]
  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]

  def map2[A,B,C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = ap(map(fa)(f.curried))(fb)
  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] = map2(fa, fb)((a, b) => (a, b))
}
