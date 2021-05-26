# SemesterprojektF21

## Intallation and usage guide

The following instructions are for use with Apache NetBeans IDE 12.3 and Java1.6

### Set up
1. Clone the repository
2. Open the project in NetBeans IDE. In the "Projects" window, right-click "org.sonatype.mcookbook.osgi-project (OSGi project)" and select "Clean and Build"
3. After successfully building the project, right-click on the project again and select: "Run Maven > pax : provision" to launch the game

### Using the game

- Use the arrow keys to control the character and space bar to attack the enemies. 

- To load and unload the component, use the Apache Felix gogo shell in the "Output" window of NetBeans.

- Type "lb" to see a list of installed bundles. 

- AttackSystem, Player, Room and Enemies are all loadable. Int the "Output" terminal, enter "stop" or "start" followed by the given bundle number.

- E.g. to unload the Enemy component, enter "stop 14" in the gogo shell.

- Exit the game by pressing on the exit button in the right hand corner of the window. 

### Testing

- To test the components with the implemented tests, right click the component to be teste in the projects window and select "Test"

- The components that have implemented testing are AttackSystem, Player and Enemey.
