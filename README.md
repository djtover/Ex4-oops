# Ex4-oops
# **Project 4 - Pacman Game**
This project is a game that based on a map of Ariel, and on the map there are a few types of players.
1) The Player which is controlled either manually or automatically.
2) Fruit which are meant to be eaten.
3) Pacman which are on the board to help the player eat the Fruit.
4) Ghost which are chasing the Player
5) Block which are areas the Player cant go through.
The point of the game is for all the Fruit to be eaten. Every Packman and Player has a certain speed it could go and a radius that it could eat within.
The configuration of the game will be made ny one of these 9 examples. 
The structure of the project is:
Package GIS:
1) Class Fruit is a class that implements the Fruit in the game.
2) Class Packman is a class that implements the Packman in the game.
3) Class Game is a class that holds all the Fruit and Packman within the game.
4) Class Path is an structure that holds all the Fruit the Packman has eaten.
5) Class Map is the map of the game. It holds the file you would like to use as the picture for the GUI and also has
conversions like from pixels to coordinates and others.
6) Class Solution hold the game after it's run through the ShortestPathAlgo
7) Class ShortestPathAlgo is the algorithm that calculates the shortest path for all the Packman to go to so the Packmen eat 
the Fruit the fastest. 
The algorithm goes as follows:
a) While the amount of Fruits left is greater than 0 
b) Set ClosestTime to be infinty
c) Run through all the Packmen 
d) Run through all the Fruit
e) if the amount of time it takes form the Packman to the Fruit is less than ClosestTime + the amount of time the Packman has already passed
set ClosestTime to be that time
set ClosestFruit to be that Fruit
set ClosestPackman to be that Packman
f) After its run through all the Packman 
Set the ClosestPackman's time to be ClosestTime
Add ClosestFruit to that Packman's Path
Remove that Fruit from the ArrayList of Fruit

 
Package GUI:
1) The Game implemented in a GUI

Package FileFormat:
1) Class FromCsv is a class that is able to read from a csv file and make that into a type Game.
2) Class ToCsv is a class that is able to take a Game and convert it to a csv file.
3) Class ToKml is a class that is able to take a Game and convert it to a kml file.

Package Geom:
1) Interface coords_coverter is an interface that represents a basic coordinate system converter.
2) Class MyCoords is a class that implements the coords_converter interface.


How the Game Runs:

How the game works is that either you add a csv file that you would like to use for the game or the user could input the Packmen and Fruit themself and after that they click on Run and the Game will show that quickest way for the Packmen to eat the Fruit by running the ShortestPathAlgo. After the Game has been run then you could either save the game as a csv file or kml file by clicking on file then clicking on whichever format you would like to save it as.


![screenshot 135](https://user-images.githubusercontent.com/45014488/50423206-1fcbb200-085b-11e9-983e-99fa9feb3184.png)

![screenshot 136](https://user-images.githubusercontent.com/45014488/50423215-425dcb00-085b-11e9-8e0b-d92948854cbb.png)

![screenshot 137](https://user-images.githubusercontent.com/45014488/50423216-4c7fc980-085b-11e9-8454-8c46a9273493.png)


