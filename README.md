# SpellChecker

This program takes in either a text file or reads from standard input. It checks if every individual word is in the English dictionary. If it is not, the program looks for actual words based on the following common mistakes:
 
1. Deletion
    "delletion" --> "deletion"
2. Insertion
    "inserion" --> "insertion"
3. Substitution
    "sebstitution" --> "substitution"
4. Transposition
    "tranpsosition" --> "transposition"
5. Split
    "splitcheck" --> "split check"
    
To Run:
  - download and compile SpellChecker.java and SpellDictionary.java
  - run SpellChecker.java, pass in either a text file or word in standard input
  - test.txt is a good first file for testing the program 
