
import com.github.andreaslo.sudoku.SudokuSolver
import org.scalatest._

class SudokuSolverTest extends FlatSpec with Matchers {

  val notSolved1 = Vector(
    Vector(5, 3, 0, 0, 7, 0, 0, 0, 0),
    Vector(6, 0, 0, 1, 9, 5, 0, 0, 0),
    Vector(0, 9, 8, 0, 0, 0, 0, 6, 0),
    Vector(8, 0, 0, 0, 6, 0, 0, 0, 3),
    Vector(4, 0, 0, 8, 0, 3, 0, 0, 1),
    Vector(7, 0, 0, 0, 2, 0, 0, 0, 6),
    Vector(0, 6, 0, 0, 0, 0, 2, 8, 0),
    Vector(0, 0, 0, 4, 1, 9, 0, 0, 5),
    Vector(0, 0, 0, 0, 8, 0, 0, 7, 9)
  ).flatten

  val solved1 = Vector(
    Vector(5, 3, 4, 6, 7, 8, 9, 1, 2),
    Vector(6, 7, 2, 1, 9, 5, 3, 4, 8),
    Vector(1, 9, 8, 3, 4, 2, 5, 6, 7),
    Vector(8, 5, 9, 7, 6, 1, 4, 2, 3),
    Vector(4, 2, 6, 8, 5, 3, 7, 9, 1),
    Vector(7, 1, 3, 9, 2, 4, 8, 5, 6),
    Vector(9, 6, 1, 5, 3, 7, 2, 8, 4),
    Vector(2, 8, 7, 4, 1, 9, 6, 3, 5),
    Vector(3, 4, 5, 2, 8, 6, 1, 7, 9)
  ).flatten

  val notSolved2 = Vector(
    Vector(0, 2, 0, 0, 0, 0, 0, 1, 8),
    Vector(5, 3, 0, 4, 8, 0, 7, 0, 0),
    Vector(0, 0, 7, 0, 6, 0, 0, 0, 2),
    Vector(7, 5, 0, 0, 0, 0, 1, 0, 4),
    Vector(0, 4, 3, 0, 0, 0, 0, 7, 0),
    Vector(6, 1, 0, 0, 0, 0, 9, 0, 0),
    Vector(0, 0, 0, 8, 0, 0, 4, 3, 0),
    Vector(0, 0, 2, 3, 1, 0, 8, 0, 6),
    Vector(0, 0, 0, 0, 0, 0, 0, 9, 0)
  ).flatten

  val solved2 = Vector(
    Vector(4, 2, 6, 9, 7, 3, 5, 1, 8),
    Vector(5, 3, 1, 4, 8, 2, 7, 6, 9),
    Vector(8, 9, 7, 5, 6, 1, 3, 4, 2),
    Vector(7, 5, 9, 2, 3, 6, 1, 8, 4),
    Vector(2, 4, 3, 1, 9, 8, 6, 7, 5),
    Vector(6, 1, 8, 7, 4, 5, 9, 2, 3),
    Vector(1, 6, 5, 8, 2, 9, 4, 3, 7),
    Vector(9, 7, 2, 3, 1, 4, 8, 5, 6),
    Vector(3, 8, 4, 6, 5, 7, 2, 9, 1)
  ).flatten


  "A complete valid horizontal line" should "be valid" in {
    for (i <- 0 until 9) {
      SudokuSolver.isHorizontalValid(solved1, i) should be(true)
    }
  }

  "Zeros are unset and" should "be ignored in horizontal lines" in {
    for (i <- 0 until 9) {
      SudokuSolver.isHorizontalValid(notSolved1, i) should be(true)
    }
  }

  "A horizontal line with multiple same entries" should "be invalid" in {
    val invalidSudoku = Vector(
      Vector(2, 2, 3, 4, 5, 6, 7, 8, 9),
      Vector(9, 3, 4, 5, 6, 7, 8, 9, 1),
      Vector(3, 4, 5, 6, 3, 8, 9, 1, 2),
      Vector(4, 5, 6, 7, 4, 9, 1, 2, 3),
      Vector(5, 6, 7, 1, 9, 1, 2, 3, 4),
      Vector(0, 5, 0, 0, 0, 0, 0, 0, 5),
      Vector(0, 8, 8, 1, 2, 3, 4, 5, 6),
      Vector(8, 9, 1, 2, 3, 3, 5, 6, 7),
      Vector(9, 7, 2, 3, 4, 5, 6, 7, 8)
    ).flatten

    for (i <- 0 until 9) {
      SudokuSolver.isHorizontalValid(invalidSudoku, i) should be(false)
    }
  }

  "A complete valid vertical line" should "be valid" in {
    for (i <- 0 until 9) {
      SudokuSolver.isVerticalValid(solved1, i) should be(true)
    }
  }

  "Zeros are unset and" should "be ignored in vertical lines" in {
    for (i <- 0 until 9) {
      SudokuSolver.isVerticalValid(notSolved1, i) should be(true)
    }
  }

  "An invalid vertical line" should "be invalid" in {
    val invalidSudoku = Vector(
      Vector(2, 0, 9, 4, 5, 0, 7, 8, 9),
      Vector(2, 0, 4, 5, 6, 7, 8, 0, 1),
      Vector(3, 4, 5, 6, 0, 8, 9, 0, 2),
      Vector(4, 5, 6, 7, 8, 0, 5, 0, 3),
      Vector(5, 6, 7, 8, 0, 1, 2, 0, 4),
      Vector(6, 7, 8, 9, 1, 2, 3, 0, 5),
      Vector(7, 8, 9, 1, 2, 3, 4, 0, 6),
      Vector(8, 9, 1, 1, 4, 1, 5, 0, 7),
      Vector(9, 5, 2, 3, 4, 5, 6, 8, 9)
    ).flatten
    for (i <- 0 until 9) {
      SudokuSolver.isVerticalValid(invalidSudoku, i) should be(false)
    }
  }

  "A valid square" should "be valid" in {
    SudokuSolver.isSquareValid(solved1, 3, 3) should be(true)
  }

  "An invalid square" should "be invalid" in {
    val invalidSudoku = Vector(
      Vector(1, 2, 3, 4, 5, 6, 7, 8, 9),
      Vector(2, 3, 4, 5, 6, 7, 8, 9, 1),
      Vector(3, 4, 5, 6, 7, 8, 9, 1, 2),
      Vector(4, 5, 6, 7, 8, 9, 1, 2, 3),
      Vector(5, 6, 7, 8, 9, 1, 2, 3, 4),
      Vector(6, 7, 8, 9, 1, 2, 3, 4, 5),
      Vector(7, 8, 9, 1, 2, 3, 4, 5, 6),
      Vector(8, 9, 1, 2, 3, 4, 5, 6, 7),
      Vector(9, 1, 2, 3, 4, 5, 6, 7, 8)
    ).flatten
    SudokuSolver.isSquareValid(invalidSudoku, 7, 7) should be(false)
  }

  "An valid incomplete sudoku" should "be solved" in {
    SudokuSolver.solve(notSolved1) should be(solved1)
    SudokuSolver.solve(notSolved2) should be(solved2)
  }

  "An unsolvable sudoku" should "be Nil" in {
    val unsolvable = Vector(
      Vector(5, 3, 0, 0, 7, 0, 0, 0, 0),
      Vector(6, 0, 0, 1, 9, 5, 0, 0, 0),
      Vector(0, 9, 8, 0, 0, 0, 0, 6, 0),
      Vector(8, 0, 0, 0, 6, 0, 0, 0, 3),
      Vector(4, 0, 0, 8, 0, 3, 0, 0, 1),
      Vector(7, 0, 0, 0, 2, 0, 0, 0, 6),
      Vector(0, 6, 0, 0, 0, 0, 2, 8, 0),
      Vector(0, 0, 0, 4, 1, 9, 0, 9, 5), // 2 times 9, second from right is wrong
      Vector(0, 0, 0, 0, 8, 0, 0, 7, 9)
    ).flatten
    SudokuSolver.solve(unsolvable) should be(Nil)
  }

}