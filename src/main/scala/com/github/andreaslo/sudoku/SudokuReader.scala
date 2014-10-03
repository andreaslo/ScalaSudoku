package com.github.andreaslo.sudoku

import scala.io.Source

object SudokuReader {
  def readSudoku(input: Source): Seq[Int] = {
    val result = Vector.newBuilder[Int]
    for (line <- input.getLines() if line.length > 0) {
      line.filter((p: Char) => p.isDigit || p.isLetter).foreach((f: Char) => if (f.isLetter) result += 0 else result += f.asDigit)
    }
    result.result()
  }
}
