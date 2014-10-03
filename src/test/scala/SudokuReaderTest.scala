import com.github.andreaslo.sudoku.SudokuReader
import org.scalatest._

import scala.io.Source

class SudokuReaderTest extends FlatSpec with Matchers {

  "A valid multiline input file with whitespaces" should "be read" in {
    val source = Source.fromURL(getClass.getResource("/test_sudoku_1.txt"))
    val read = SudokuReader.readSudoku(source)

    val expected = Vector(
      Vector(8, 0, 0, 4, 0, 1, 0, 0, 7),
      Vector(6, 4, 0, 2, 0, 3, 8, 0, 0),
      Vector(2, 0, 5, 9, 0, 0, 0, 0, 0),
      Vector(1, 3, 0, 7, 0, 0, 6, 0, 0),
      Vector(0, 5, 0, 0, 3, 0, 0, 1, 0),
      Vector(0, 0, 8, 0, 0, 4, 0, 3, 5),
      Vector(0, 0, 0, 0, 0, 6, 9, 0, 4),
      Vector(0, 0, 2, 3, 0, 7, 0, 8, 1),
      Vector(5, 0, 0, 1, 0, 2, 0, 0, 6)
    ).flatten

    read should be(expected)
  }

  "A valid multiline input file without whitespaces" should "be read" in {
    val source = Source.fromURL(getClass.getResource("/test_sudoku_2.txt"))
    val read = SudokuReader.readSudoku(source)

    val expected = Vector(
      Vector(0, 1, 0, 3, 8, 0, 0, 5, 0),
      Vector(0, 3, 0, 5, 0, 0, 1, 0, 0),
      Vector(0, 0, 6, 0, 4, 1, 0, 9, 0),
      Vector(5, 8, 2, 1, 0, 0, 0, 0, 6),
      Vector(9, 0, 0, 0, 6, 0, 0, 0, 5),
      Vector(6, 0, 0, 0, 0, 4, 8, 2, 3),
      Vector(0, 6, 0, 4, 9, 0, 2, 0, 0),
      Vector(0, 0, 8, 0, 0, 7, 0, 3, 0),
      Vector(0, 9, 0, 0, 3, 2, 0, 6, 0)
    ).flatten

    read should be(expected)
  }

  "A valid single line input file without whitespaces" should "be read" in {
    val source = Source.fromURL(getClass.getResource("/test_sudoku_3.txt"))
    val read = SudokuReader.readSudoku(source)

    val expected = Vector(
      Vector(0, 4, 0, 7, 2, 3, 0, 0, 0),
      Vector(0, 0, 0, 0, 0, 9, 0, 0, 3),
      Vector(8, 0, 2, 0, 5, 0, 0, 7, 9),
      Vector(4, 0, 0, 2, 0, 5, 9, 6, 0),
      Vector(5, 0, 0, 0, 6, 0, 0, 0, 4),
      Vector(0, 2, 9, 8, 0, 4, 0, 0, 5),
      Vector(7, 8, 0, 0, 4, 0, 1, 0, 2),
      Vector(3, 0, 0, 5, 0, 0, 0, 0, 0),
      Vector(0, 0, 0, 1, 7, 8, 0, 3, 0)
    ).flatten

    read should be(expected)
  }


}