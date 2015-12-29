package eu.timepit.refined

import eu.timepit.refined.api.Validate
import eu.timepit.refined.generic.Eval
import org.scalacheck.Prop._
import org.scalacheck.Properties

class GenericValidateSpecJvm extends Properties("GenericValidate") {

  type IsEven = Eval[W.`"(x: Int) => x % 2 == 0"`.T]

  property("Eval.isValid") = forAll { (i: Int) =>
    Validate[Int, IsEven].isValid(i) ?= (i % 2 == 0)
  }

  property("Eval.showExpr") = secure {
    Validate[Int, IsEven].showExpr(0) ?= "(x: Int) => x % 2 == 0"
  }

  property("Eval.refineM") = secure {
    refineMV[IsEven](2).get == 2
  }
}
