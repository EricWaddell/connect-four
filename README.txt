This file contains usage instructions and descriptions of content.

USAGE:
java -jar connectFour.jar
Creates a new connect4 game with the number of players defined in Game.properties, or 2 players, 7 columns, and 6 rows if the file is missing or defined incorrectly.

CONTENT:
src/main/java
Code directory.

src/main/resources/Game.properties
A file containing in order, the number of players, the number of columns, and the number of rows to generate the game with.

src/test/java
Test directory, holds all tests.

README.txt
This readme

COMMENTS.txt
Various things I wanted to say about the code and organization. Does not contain source comments, those are on the source like they should be.

DESIGN.txt
Explanation for any design concepts. Comments are more personal thoughts (and would probably not exist in real production code because it would be things that end up going in slack, compared to this DESIGN which would end up in a design document or a wiki)