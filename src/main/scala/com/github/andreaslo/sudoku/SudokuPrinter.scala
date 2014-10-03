package com.github.andreaslo.sudoku

object SudokuPrinter {

  def formatSudoku(sudoku: Seq[Int]): String = {
    val builder = new StringBuilder

    var indexCounter = 1
    for ((ele, idx) <- sudoku.zipWithIndex) {
      if (idx > 0 && idx % SudokuSolver.lineLength(sudoku) == 0 && indexCounter < SudokuSolver.squareSize(sudoku)) {
        indexCounter += 1
        builder.append(System.getProperty("line.separator"))
        builder.append(ele)
      } else if (idx > 0 && idx % SudokuSolver.lineLength(sudoku) == 0) {
        indexCounter = 1
        builder.append(System.getProperty("line.separator"))
        val numberOfBlocks = SudokuSolver.lineLength(sudoku) / SudokuSolver.squareSize(sudoku)
        for (_ <- 1 to SudokuSolver.lineLength(sudoku) + numberOfBlocks - 1) builder.append("-")
        builder.append(System.getProperty("line.separator"))
        builder.append(ele)
      } else if (idx > 0 && idx % SudokuSolver.squareSize(sudoku) == 0) {
        builder.append("|")
        builder.append(ele)
      } else {
        builder.append(ele)
      }
    }

    builder.toString
  }

}
