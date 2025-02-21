This program is the submission of the first assignment of the course 
TEPR60 (Test automation and programming) of the Software tester program at EC Utbildning.

The purpose of the program is to translate Morse Code to English, and vice versa.

The program consists of three classes:
  - Test: program is developed via TDD as one of the assignment requirements is to write at least five JUnit test cases
  - Logic: a class that translates input either to English or Morse Code
  - Main: a class that reads in from the terminal and writes out the translation

Furthermore, the program is required to have at least two cases of error handling to deal with two things 
a user can do that goes beyond the usage of the system. 

The program is implemented for translation of international Morse Code to the letters A to Z, and vice vera.
Short signals are represented by period (.) and long signals by hyphen (-), for instance F = [..-.]
The program handles translation of multiple letter, but seperation of words is not a requirement.

[.... . .-.. .-.. --- .----- .-. .-.. -..]  = HELLOWORLD

HELLO WORLD = [.... . .-.. .-.. --- .----- .-. .-.. -..]

The choice of data structure for representation of the Morse Code is free.
