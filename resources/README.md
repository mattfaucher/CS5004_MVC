# CS5004 Assigment 7: The view
#  UML DIAGRAM : *https://app.creately.com/diagram/AeU3o8eT0dw/view*

### Changes from the pervious Assigment.
# in this assigment we are focusing on making the three different ways to out put the view. The first refactor that we did we going back to our model and added modification to declear shape and create shape and how it is vital to our view making process. Also in our ModelImpl we added the checkTransformationOverlap that we missed out on the self-evaluation. In this methood we check if any of the transfromation is overlaping.Forexample , it should not be moving forward and moving forward and moving backwards at the same time.This hlpes us to keep track of the shapes and transformation  and by adding this conponet we were able to elemanate some bugs that were happening in the our view making process.



<ins> *** CHANGES IN SHAPE PACKAGE **</ins>

**Size** => the Size has been changed to better work with the tweening calculator since the calculator takes should be precise and having an int for the calculator is always making the view output missplacing on the canvas.


**Position** => the Position has also been refactored to better work with the tweening calculation method that calculating the position on the canvas. Using int is making the view miscalculating since it round up the values.


<ins> *** CHANGES TRANSFORMATION**  </ins>

**TIME** => in this package , we have added a Boolean method called  is between that takes in a tick that will return a ture or false. this method will assisnt us to calculat the view retuslt. The tick is between the start and end time value.


**TIME** => In time class we also have added the method call getDifferences . it will return the difference from the start time and the end time that will be passed in the our tween calculator.



<ins> *** NEW CLASS** </ins>
**CANVAS** => this is a new calss that we added to our model that keep tracks of the canvas size and its position. we have geters and seters in this class to effectively get the canvas information to set up our Jfram panel and draw object on the canvas.



<ins> *** IModel /modelImpl** </ins>

**Imodel** => we have implemented the getStateatAtick method and in our modelImpl and and also added remove shape method to assist us in the later project to remove the shapes that are added to the shape list and and the transformation class.

**ModelImpl** => we have many modification in this class include added the method call check for transofmationOverlap and also implemented the getShapeAtTick class which were able to calculate the shape which at that particualr tick and return to the list that has all the shape in the list.


<ins> *** Util** </ins>

**AnimationBulderImpl** => a class that implements the given starter code to propoly read it from a file and add the animation to pupulate our model with correct information which then we could use to add modtion and other implementation . In this class we set the bound declear the shape and also add the motion to to the builder.



<ins> *** view** </ins> 

**Iview*** => this is the ivew interfact that has all the neassary method that a view needs to output a correct view type for the user when being called.

**AbstractView** => This will be where we have the genreal view funcitonality to build the view types and the view and we have three view being implemented text view that just return a text basesd view SVG is a SVG formated view which can be ran at the browers and graphical view is where we use built in java class to show what's being processed.


<ins> *** EASYANIMATOR** </ins>
**EasyAnimator*** => this is the main class where we takes in pares command areguments and start initialize our program .All semester we have been using Junit test to test run our program , but using main in this program will run our program and read from the txt files and show the view based on which view the customers needs. 


<ins> *** added all test** </ins>
**Test*** => tested all the function being implemented and ran all test passes. 