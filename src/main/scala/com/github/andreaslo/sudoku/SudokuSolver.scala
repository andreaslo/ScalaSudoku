package com.github.andreaslo.sudoku

object SudokuSolver {

  def isLineValid(line: Seq[Int]): Boolean = {
    def testMethod(line: Seq[Int], checkVals: Seq[Boolean]): Boolean = line match {
      case 0 +: tail => testMethod(tail, checkVals)
      case ele +: tail => if (checkVals(ele - 1)) false else testMethod(tail, checkVals.updated(ele - 1, true))
      case Nil => true
    }
    testMethod(line, List.fill[Boolean](line.length)(false))
  }

  def isHorizontalValid(sudoku: Seq[Int], lineNum: Int): Boolean = {
    val lineStart = lineLength(sudoku) * lineNum
    val lineEnd = lineStart + lineLength(sudoku)
    isLineValid(sudoku.slice(lineStart, lineEnd))
  }

  def getVerticalLineValues(sudoku: Seq[Seq[Int]], lineNum: Int): Seq[Int] = {
    sudoku.foldLeft(Vector[Int]()) { (r, h) => r :+ h(lineNum)}
  }

  def isVerticalValid(sudoku: Seq[Int], lineNum: Int): Boolean = {
    val verticalList = for (i <- 0 until lineLength(sudoku)) yield sudoku(i * lineLength(sudoku) + lineNum)
    isLineValid(verticalList)
  }

  def lineLength(sudoku: Seq[Int]): Int = if (sudoku.length == 81) 9 else Math.sqrt(sudoku.length).toInt

  def squareSize(sudoku: Seq[Int]): Int = if (lineLength(sudoku) == 9) 3 else Math.sqrt(lineLength(sudoku)).toInt

  def getSquareValues(sudoku: Seq[Int], x: Int, y: Int): Seq[Int] = {
    val sSize = squareSize(sudoku)
    val squareX = Math.ceil(x / sSize).toInt
    val squareY = Math.ceil(y / sSize).toInt
    val horizontalFirst = lineLength(sudoku) * squareX * sSize // Index of the first field in the first horizontal line of the block
    val blockStart = horizontalFirst + sSize * squareY

    //    println(s"x: $x, y: $y, ll: $lineLength, squareSize: $squareSize, squareX: $squareX, squareY: $squareY, horizontalFirst: $horizontalFirst, blockStart: $blockStart")

    for (i <- 0 until sSize; j <- 0 until sSize) yield {
      //      println(s"i: $i, j: $j")
      //      println(blockStart + i + j * lineLength)
      sudoku(blockStart + i + j * lineLength(sudoku))
    }
  }

  def isSquareValid(sudoku: Seq[Int], x: Int, y: Int): Boolean = {
    isLineValid(getSquareValues(sudoku, x, y))
  }

  def isFieldValid(sudoku: Seq[Int], field: Int): Boolean = {
    val horizontalLine = Math.ceil(field / lineLength(sudoku)).toInt
    val verticalLine = field % lineLength(sudoku)

    isHorizontalValid(sudoku, horizontalLine) && isVerticalValid(sudoku, verticalLine) && isSquareValid(sudoku, horizontalLine, verticalLine)
  }

  def solve(sudoku: Seq[Int]): Seq[Int] = {
    def solveHelper(workingList: Seq[Int]): Seq[Int] = workingList match {
      case ele +: tail => if (ele == 0) {
        val workingField = sudoku.length - workingList.length
        for (i <- 1 to lineLength(sudoku)) {
          val newTempSudoku = sudoku.updated(workingField, i)
          if (isFieldValid(newTempSudoku, workingField)) {
            val solved = solve(newTempSudoku)
            if (solved != Nil) {
              return solved
            }
          }
        }
        Nil
      } else {
        solveHelper(tail)
      }
      case Nil => Nil
    }
    def solved(sudoku: Seq[Int]): Boolean = sudoku.contains(0)

    if (!solved(sudoku)) {
      sudoku
    } else {
      solveHelper(sudoku)
    }
  }

}
