This is the readme for the project Proximity - https://github.com/nahojjjen/OOP-Project-TowerDefence/.
@author Johan Swanberg


______________________________________________________________________________________________________________

	 _____                          _               _   _           
 	|  __ \                        (_)             (_) | |          
 	| |__) |  _ __    ___   __  __  _   _ __ ___    _  | |_   _   _ 
 	|  ___/  | '__|  / _ \  \ \/ / | | | '_ ` _ \  | | | __| | | | |
 	| |      | |    | (_) |  >  <  | | | | | | | | | | | |_  | |_| |
 	|_|      |_|     \___/  /_/\_\ |_| |_| |_| |_| |_|  \__|  \__, |
 	                                                          __/ |
 	                                                         |___/ 
______________________________________________________________________________________________________________
Group members: 
	Johan Swanberg
	Hanna Römer
	Linda Evaldsson
	Simon Gislén

______________________________________________________________________________________________________________

Contents:
	1. Short description of readme
	2. Links
	3. Git structure
	4. Program structure

______________________________________________________________________________________________________________

1. Short description of readme
Running this project should be pretty straigtforward, simply naviate to the ProgramFiles folder
and run ./gradlew build, ./gradlew run, or ./gradlew test. You can also download the bytecode (.JAR)
from the project website, see the Links section.

This readme aims to give auxillary information, such as a short description of how we structured
the git repository, A normal libGdx application structure and how the program is structured.

______________________________________________________________________________________________________________

2. Links
Our project has a website for various information. www.foh-proximity.se
On the website you can find the following information about the project:

Game guide (How to play):....... http://www.foh-proximity.se/gameplay-guide/
Runnable jar (bytecode):........ http://www.foh-proximity.se/download/
Gitinspector history:........... http://foh-proximity.se/gitinspector/
Group work schedule:............ http://foh-proximity.se/schedule/

______________________________________________________________________________________________________________

3. Git structure

document........................ Contains RAD, SDD, Report and meeting protocols.
ProjectFiles.................... Contains source code and framework/gradle files
......Core...................... Contains source files
........... assets.............. Contains the project assets (images, music etc.)
........... src................. Contains the source code for application and tests
......Desktop................... Required by libGDX to create a desktop application
......Gradle.................... Gradle files

Root structure:
The git structure is divided in Documents and Project files. The ProjectFiles folder
is typical for a libGDX game. The ProjectFiles folder is divided into three sub-folders,
but only the Core folder contains source code, the rest of the files are files 
required by libGDX and gradle.

Source and assets:
The core folder containts the "assets" folder and the "src" folder, the assets folder 
contains only images and other non-code resources.

The src folder contains two folders, one for all test classes, and one for all program
classes. The structure for both folders are mostly the same, so for example a class 
	/edu/chl/proximity/Model/aClass.java
would have its test class in
    test/edu/chl/proximity/Model/aClass.java

The folder after /edu/chl/proximity/ is divided into four folders and a java class:
Controllers, Models, Viewers, Utilities and proximity.java
Proximity.java is the entry point of the application, the main class.
The model, view and controller folders contains the classes divided according to MVC.
The Utilities class contains classes which could be libraries & are very general.

The majority of the source code is within the Model folder. Within the Model folder, most models reside in
the Map folder, it contains all classes of objects which resides on the map. The majority of the game objects
reside on the map (for example, the enemies, the towers, the projectiles, etc.).

The service classes that adds a layer between dependency implementations and the project model reside within
the Utils folder. The utils folder also contains the settings file (player settings.)


______________________________________________________________________________________________________________


4. Program structure

4.1 Program initiation
The program starts the only class within the desktop folder, which in turn starts the proximity.java
class in the /core folder. Proximity.java contains the render() method which is called automatically 60 
times every second. This render method is the root of all method calls. On program start the 
proximity.java class opens a new MenuScreen and creates a new instance of Player.

4.2 Continous running of the program
The proximity.render will call the render method of whichever screen is currently active, for instance call 
MenuScreen or GameScreen render() method repeatedly. The programs three screens MenuScreen, GameScreen and 
GameOverScreenrender methods contains methods for ensuring the application scales correctly (camera.update())
and tells the current screens model to update.

The GameScreen controller updates 60 times per second in the gamescreen render method, but the MainScreen
and GameOverScreen controllers instead completely rely on listener to trigger controller events.

When changing screens (by pressing start on the main menu, losing a game or pressing the main menu button ingame)
the active game screen is changed, and the current screens render method takes over.

______________________________________________________________________________________________________________

5. Understanding by example:

The following text is a short explanation of how the program structure works during normal execution.
This example is pretty long and in-depth, it explains how the main game logic is updated during one 
frame. It does not contain any logic related to player input, only logic which is played automatically during 
a normal frame update. If you prefer, you can read the source code to get an understanding of the program, or
use this guide as complementary information.

The indentation represents how "deep" a method call is, the root method call has no indentation,
a method B called by method A has one indentation, and so on. The method calls are listed
in chronological order, as called by the application.

START:
The application timer method (proximity.java render()) tells the GameScreen render method to run.
	
	The GameScreen checks if the screen size has changed with the camera.update method, and
	sets the projection matrix accordingly.
	
	The viewer is called to tell the models to render

		The map model tells its known models to render

			The towers are told to render

			The projectiles are told to render

			The creeps are told to render

			The base is told to render

		The game panels are told to render

		The player hand (example: range indicators for selected towers) is told to render

		Any current game tips (popup menus) are told to render

		The particle manager is told to render
			
			All particles types are told to render, in a for loop.

	The main game controller (MainController.java) is told to update all handler controllers.
	This pretty much means that the controller goes through all models, and tells them to do stuff.

		The wave controller is told to update

			The wave controller creates creep objects and wave objects according to a 
			game timer & spawning algorithm.

		The hand controller is told to update

			Whatever the player is currently holding in his hand it told where
			the mouse cursor is.
			
			A check is made if a popup window should be created on the mouse location.

		The controllpanelcontroller is told to update
			The health indicator model is updated

			The resources indicator model is updated

			The player spell cooldown models are updated

			The player experiance indicator model is updated

		The mapController is told to update

			The map is told to update

				The towers are told to update (shoot, reload, etc)

				The creeps are told to move and rotate

				The spells are told about the map model 
				The spells are told to perform their effects

				The projectiles are told to move
				The projectiles are told to check for collisions
					The projectiles are told to perform collision logic
				The projectiles are told to remove themselves if outside screen

		

			The map resources are added

			The map experiance is added

			The map gets told to add any items created during this frame to the model

			The map gets told to remove any items destroyed during this frame from the model

			A check is made on the player health, and a game over screen is displayed if
			the player health is 0.
:END

//The frame update is done, and the chain call of methods will be called again the net time the 
//proximity.java render method is called. The loop starts over.


