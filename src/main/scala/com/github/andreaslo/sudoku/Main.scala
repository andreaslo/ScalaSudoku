package com.github.andreaslo.sudoku

import java.io.File

import scala.io.Source

object Main {

  def main(args: Array[String]) {
    val inputFileOption = if (args.length > 0) Some(new File(args(0))) else None

    inputFileOption match {
      case Some(inputFile) => println(solve(inputFile))
      case None => println(getUsage)
    }
  }

  def getUsage: String = {
    """This program solves sudoku. It reads a sudoku from an input file and prints the output to the console.
      |Please specify the input file as first parameter.
      |
      |Digits from the input file are read as sudoku digits, characters are interpreted as missing fields, whitespaces and special
      |characters are ignored. Thus valid input is for example:
      |
      |040723000000009003802050079400205960500060004029804005780040102300500000000178030
      |
      |or
      |
      |010380050
      |030500100
      |006041090
      |582100006
      |900060005
      |600004823
      |060490200
      |008007030
      |090032060
      |
      |or
      |
      |010|380|050
      |030|500|100
      |006|041|090
      |-----------
      |582|100|006
      |900|060|005
      |600|004|823
      |-----------
      |060|490|200
      |008|007|030
      |090|032|060
      |
      | """.stripMargin
  }

  def solve(inputFile: File): String = {
    if (!inputFile.exists()) {
      s"File ${inputFile.getName} not found"
    } else {
      SudokuPrinter.formatSudoku(SudokuSolver.solve(SudokuReader.readSudoku(Source.fromFile(inputFile))))
    }
  }

}
