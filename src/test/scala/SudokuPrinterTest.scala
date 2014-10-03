import java.io.{PrintWriter, BufferedWriter, FileWriter}
import com.github.andreaslo.sudoku.SudokuPrinter
import org.scalatest._

class SudokuPrinterTest extends FlatSpec with Matchers {


  "An unsolvable sudoku" should "be Nil" in {
    val sudoku = Vector(
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

    val expected = """534|678|912
                     |672|195|348
                     |198|342|567
                     |-----------
                     |859|761|423
                     |426|853|791
                     |713|924|856
                     |-----------
                     |961|537|284
                     |287|419|635
                     |345|286|179""".stripMargin

    SudokuPrinter.formatSudoku(sudoku) should be(expected)
  }

}