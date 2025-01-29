Database - Programming Assignment 1 – Individual Only

 

The goal of this assignment is using Java or C, build an application that will build and manipulate a file that deals with problems similar to those of a Database Management System (DBMS).  You will be making use of the RandomAccessFile class in Java or similar support that is found in C or Python for working with files.

 

The first part of the application will allow new items to be added to the file called "fun.dat".  Specifications are as follows:

Each item should be added to the end of the file. It should not delete the old file or create a whole new file.  It should only add to the existing file.
The file is a text file, with the information stored as readable text.
Each item added has three components: Color, Zip Code, and State
When adding the item each component has a set size: Color is 8 characters, Zip Code is 5 characters, and State is 2 characters.
Color is text name of a color, for example “Orange”. If a color text name doesn’t fit, e.g. Light Green, it gets truncated. So “Light Green” becomes “Light Gr”
Zip Code is a five digit number from 00000 to 99999.
State is just the abbreviation of the name of the state and is only 2 characters.  For example, Pennsylvania is just PA.
If any component is too small, then extra spaces should be added so it is still the required number of characters.  For example, the Color "Red" becomes "Red     " <- 5 spaces added.
 

The second part of the application will allow for changes to the file.  The specifications are as follows:

The user will specify which entry to be modified.  The first item in the file is considered entry number 1.
The user will specify which components are being changed and the new values.  For example, the user could state he wants to change only the Color and then provide the new value of "Yellow".
The application will go to that point in the file and overwrite ONLY the old information.  For example, if the user is only updating the Zip Code information for entry number 3, then the Color and State information should not get modified in any way.
You should not be creating a brand-new file, but only modifying the information in the existing file.  Both Java and C allow for this.
The fixed size of the components in an entry are still the same (Color is 8 characters, Zip Code is 5 characters, and State is 2 characters)
