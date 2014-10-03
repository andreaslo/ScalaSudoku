# ScalaSudoku

ScalaSudoku is a sudoku solver written in Scala. I created it in order to get started with Scala. 

## Run it

The project uses sbt as build tool. Run the test cases:

    sbt test
    
Run the program:

    sbt run $filename

Create an executable:

    sbt package
    
## Usage

The program reads a sudoku from an input file, solves it and prints the output to the console. Please specify the input file as first parameter.

Digits from the input file are read as sudoku digits, zeros and characters are interpreted as missing fields, whitespaces and special characters are ignored. Thus valid input files are for example:

040723000000009003802050079400205960500060004029804005780040102300500000000178030

or

010380050
030500100
006041090
582100006
900060005
600004823
060490200
008007030
090032060

or

010|380|050
030|500|100
006|041|090
-----------
582|100|006
900|060|005
600|004|823
-----------
060|490|200
008|007|030
090|032|060