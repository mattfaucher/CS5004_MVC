# CS5004_MVC - Simple Animator Project
# UML DIAGRAM: *https://app.creately.com/diagram/AeU3o8eT0dw/view*

![UML Diagram for Model](https://github.com/mattfaucher/CS5004_MVC/blob/main/res/CS5004_MVC_UML_MODEL.png)




<ins>***INTERFACES***</ins>

**IModel** => Protocol for user facing methods that will be directly interacting with the Controller/View.

**IShape** => Protocol for all shapes that can be animated.

**ITransformations** => Protocol for all types of transformations.

<ins>***ENUMS***</ins>

**TypeOfTransformation** => Houses the allotted types of transformations available to the application.

**TypeOfShape** => Houses the allotted types of shapes available to the application.


<ins>***ABSTRACT CLASSES***</ins>

**AbstractShape** => Implements IShape, contains all code common to all shapes including the attributes that are common among all shapes. Reduces code redundancy. AbstractShape is abstract and cannot be instantiated.

**AbstractTransformations** => Implements ITransformations, contains code common to all tranformation types. Reduces code redundancy. AbstractTransformations can be instantiated since the show transformation only requires the attributes within this class.


<ins>***CONCRETE CLASSES***</ins>

**ModelImpl** => Implements IModel. This class uses a HashMap as well as a LinkedList to store the shapes that have been created by the user and to store the List of Transformations that the user wants to take place. Implements the functionality for what a user will be able to accomplish.

**Position** => Class to be used for composition as an attribute of Shape types. Stores the X and Y coordinates and allows for access to these values.

**Size** => Class to be used for composition as an attribute of Shape types. Stores the width and height and allows for access to these values.

**Colors** => Class to be used for composition as an attribute of Shape types. Stores the red, green and blue values (RGB) and allows for access to these values.

**Time** => Class to be used for composition as an attribute of Transformation types. Stores the start and end time of a given animation and allows for access to these values.

**Rectangle** => Concrete type of IShape that represents a Rectangle. Extends the AbstractShape class, inheriting all attributes and methods.

**Oval** => Concrete type of IShape that represents an Oval. Extends the AbstractShape class, inheriting all attributes and methods.

**Move** => Concrete type of ITransformations. Extends the AbstractTransformations class, inheriting all attributes and methods. Stores an additional attribute Position and allows for retrieval of this attribute.

**Scale** => Concrete type of ITransformations. Extends the AbstractTransformations class, inheriting all attributes and methods. Stores an additional attribute Size and allows for retrieval of this attribute.

**ChangeColor** => Concrete type of ITransformations. Extends the AbstractTransformations class, inheriting all attributes and methods. Stores an additional attribute Color and allows for retrieval of this attribute.
