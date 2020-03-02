# Rule 30 Android App

### Implementation

For implementation, I started with running rule 30 on a single array of either 1's or 0's. I then moved on to create an 
object called a World which contains a 2 dimensional array of either 1's or 0's and created a method for running rule 30 on the points in that world.
The world is supposed to represent a coordinate system with the rows representing each generation and each row representing a generation's elements.
To initialize a world, you put in the number of generations and the number of elements that are in each generation and then choose between 3 different ways
of generating the points in the world. The world's generate method just creates a world with all elements having a zero value.
The world's generate random method creates a world with every element being randomly chosen as 1 or 0. The generate random method can also take
a percentage that represents the frequency that 1 is randomly selected. 

I also added another option for running rule 30 starting from the center out.

### Usage

At the beginning you are presented with a blank screen that contains a single point. To view options, click the FAB and a menu will open up.

#### Options:
* Execute Rule 30 - executes rule 30 on the elements of the world.
* Execute Rule 30 from Center Out - executes rule 30 from the center out on the elements of the world. Note: Performing rule 30 center out with single point 
will create a blank world because the initial world is has a single dot in the top center, but this method starts from the center. Reset the world 
to start over if this happens.
* Randomize World - creates a world with a 20% chance that 1's are chosen.
* Shuffle World - shuffles the world's points randomly. Note: Will not work with a world that only contains a single point.
* Reset World - resets the world to the initial world.

If you lose the world, reset the camera to bring it back in focus.

### Visualization

For visualizing the results, I created a custom view that holds a list of WorldPoints, 
each of which contain x and y coordinates and an optional point size representing an element with the value of 1. 
I used data binding to update the view's list when an action is performed on the world. When the view's list is updated, 
the view is invalidated and redrawn. For each draw of the view, circles are added for each point. The initial world that is created starts
with a WorldPoint in the center of the top row, this point is larger for easier viewing. You can also zoom and pan on the view and 
to reset the positioning of the view, you just click the reset camera button.  

