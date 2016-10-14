# SystemsSoftware
Collection of Java, SIC, SIC/XE programs written in my Introduction to System Software class

In this file I will outline the contents of each Project file as well as the purpose of the project.

Intro to Systems Software Project 1
      Date: 09/22/2016
      
      The purpose of this project is to implement the symbol table that will be used by the assembler during pass1 and pass2. It will be constructed as an efficient hashing table.
      
      Project1.java file: This file contains the program and the implementation of the program.
      
      Input.txt file: The input file consists of strings, some with numbers and some without numbers. The ones with numbers will be assigned to a spot within the array. The ones without numbers are searched for within the array.
      
      Ouput.txt file: The output file is generated as a result of teh placement of the words from teh input file. Errors are also printed if any do occur.
      
      Makefile file: The makefile compiles the Project1 file and also runs the other file as an input
      
      
Intro to Systems Software Project 2

      Date: 10/16/2016

      The purpose of this project is to create a SIC or SIC/XE project that will have 10 integers
      initiaized in memory via WORD storage.

      project2 file: This file contains the SIC/XE program that reverses the order of the numbers

      sicModified.doc: This file contains the modified sic.log which shows the original list of
      integers and the reversed list of integers.

      Procedure for running SIC/XE or SIC programs:

      The command SICASM must be entered first and then it will prompt you for the name of the file

      After entering the name of the file, command line will return saying that it created two
      files: project2. obj and project2.lst.

      Then the command SICSIM must be entered. A prompt will appear asking you to A)ccept the files
      or R)ename the files.
      Enter 'R'
      Hit enter past loader until you get to DEVF1 and then enter project2.obj
      Continue hitting enter until the prompt appears again. 

      Enter 'A', this will accept the program2.obj file
      Another prompt will be displayed.

      Enter 'S', this will start the project2.obj file
      Enter 'R', this will run the project2.obj file. Continue entering 'R' until it says that
      it has attempted to read past the file.

      Enter 'D, 100-200', this will dump the data stored in memory and will show the integers
      entered initially and the integers reversed. 
