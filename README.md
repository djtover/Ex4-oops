# **Project 4 - Pacman Game**

## **Understanding the Game**
This project is a game that based on a map of Ariel, and on the map there are a few types of players.
1) The Player which is controlled either manually or automatically.
2) Fruit which are meant to be eaten.
3) Pacman which are on the board to help the player eat the Fruit.
4) Ghost which are chasing the Player
5) Block which are areas the Player cant go through.
The point of the game is for all the Fruit to be eaten. Every Packman and Player has a certain speed it could go and a radius that it could eat within.

## **Game Setup**
The configuration of the game will be made by one of these 9 examples.

![screenshot 163](https://user-images.githubusercontent.com/45014488/50998648-c89f3000-1530-11e9-8a43-861682d8a869.png)

Once you have chosen then press on Menu and click on Player as seen in the picture below.

![screenshot 164](https://user-images.githubusercontent.com/45014488/50998928-9fcb6a80-1531-11e9-94a6-34bc897d34fa.png)

Once you have clicked on Player then put the Player wherever you want on the board.
After that you have either two options:
1)Run in Manual Mode which means you get to control the Player.

2)Run in Automatic Mode which means the Player will go automatically to eat the Fruit.

After you choose which Mode you want the game will start and this is how the game will look.

![screenshot 165](https://user-images.githubusercontent.com/45014488/50999151-2e3fec00-1532-11e9-89fe-6af1f2631deb.png)


## **Class Structure**

### **Package MyGIS**

1) Class Fruit is a class that implements the Fruit in the game.

2) Class Packman is a class that implements the Packman in the game.

3) Class Player is a class that implements the Player in the game

4) Class Game is a class that holds all the players within the game.

5) Class Path is an structure that holds all the Fruit the Player has eaten.

6) Class Map is the map of the game. It holds the file you would like to use as the picture for the GUI and also has
conversions like from pixels to coordinates and others.
 
### **Package Algo**
1) Class GameAlgo is the algorithm the Player runs in Automatic Mode. Description of the algorithm can be seen in the wiki page.

 
### **Package GUI**
1) The Game implemented in a GUI

2) DrawBoard is a Thread the updates the board each time before its should be drawn again


### **Package FileFormat**
1) FromBoard is the how the game is read from a csv file


### **Package Geom**
1) Interface coords_coverter is an interface that represents a basic coordinate system converter.

2) Class MyCoords is a class that implements the coords_converter interface.


## **Comparison of Scores Between Automatic Mode and Manual Mode**

### **Best Score in Example 8**
In example 8 my best score was 45.6. I started from the top eating a few Fruit there but making sure to leave a path for the Pacman there to go on and I make make my way eating 3 more till I get to the all the Fruit on the left and eat those and then the game ended.

### **Differences in Scores Between Automatic Mode and Manual Mode**
As you can see in the photo below some times Automatic Mode does better than Manual Mode and sometimes the other way around.

Based on my understanding of the game when Automatic Mode does better then it means the optimal way is the greedy way which is how Automatic Mode works on, and when you try to do the greedy way when playing manual it is very hard to make sure you are constantly on the optimal way. 

When Manual Mode does better then it means the player has to consider the Pacmen and where to move to make sure 2 Pacmen or a player and a Pacman aren't going toward the same Fruit but rather splitting up to cover more ground.

![screenshot 174](https://user-images.githubusercontent.com/45014488/51083603-bb976200-1725-11e9-8123-6e66fe5cdf3b.png)
