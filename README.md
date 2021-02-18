Using ATC/Instructions
Importing and running the Project
The ATC project will function on your system using eclipse. To ensure that the project is being imported correctly follow these instructions.
1.	Download Eclipse. The latest version of eclipse is provided here https://www.eclipse.org/downloads/.
2.	Once eclipse is installed, open the application, and find a suitable workspace to work in.
3.	Now click file, then import in the top left corner.
 
 ![image](https://user-images.githubusercontent.com/48979024/108304978-1d352080-7177-11eb-92c3-765b9bf89c0a.png)
 
4.	A window will pop with the different import options. Select “Projects from Git”.
 
 ![image](https://user-images.githubusercontent.com/48979024/108305013-2cb46980-7177-11eb-895e-3c1290a307cd.png)
 
5.	Now click on “Clone URI”
 
 ![image](https://user-images.githubusercontent.com/48979024/108305046-3b9b1c00-7177-11eb-8a15-8a53551aa3a3.png)
 
6.	After this, the source git repository will be brought up. In the URI section paste in the following link https://github.com/RafaelDolores/EECS2311_GROUP5_TAB_TO_MUSICXML.
 
 ![image](https://user-images.githubusercontent.com/48979024/108305069-481f7480-7177-11eb-8438-f14a1bb83b6f.png)
 
7.	Now at the bottom, for Authentication enter in your GitHub Username and password. Clicking the “Store in Secure Store” will ensure eclipse will save your credentials for future use.
 
 ![image](https://user-images.githubusercontent.com/48979024/108305158-671e0680-7177-11eb-9c3d-390e2517701a.png)
 
8.	Once you click next, the different branches will appear on screen. For this project to work the master branch must be imported.
 
 ![image](https://user-images.githubusercontent.com/48979024/108305190-7735e600-7177-11eb-8466-ca36b97f5faa.png)
 
9.	Once that is done, click next and now you must select a suitable directory to save this project.

![image](https://user-images.githubusercontent.com/48979024/108305213-8321a800-7177-11eb-98f5-53d30ef88b35.png)

10. 	After this is completed, click next and then finish. The project is successfully imported.
11.	To run the project open “Tab_TO_XML”, then click on “src/main/java”.
12.	Now open the package called “Interface” and right click on “Runner.java”. Then click “run as java application”.
13.	The Project is now functional.
 
 ![image](https://user-images.githubusercontent.com/48979024/108305236-8d43a680-7177-11eb-828c-591d351e2a92.png)
 
NOTE: If you get the warning “Error: JavaFX runtime components are missing”, then you must import Javafx separately. Follow these steps to resolve this problem.
1.	Download Javafx from https://gluonhq.com/products/javafx/ or any other website you prefer, and extract it in a location.
2.	In Eclipse go to Window, preferences, Java, Build path, User Library.
3.	Click New and then enter “javafx”.
4.	Now click on it and then click on add external JARS.
5.	Now open the javafx folder and click on bins.
6.	Now highlight all the jar files except the zip file at the bottom.
7.	Click apply.
8.	Now right click on the project and go to build path and then configure build path.
9.	Click on class path and then click add library.
10.	Click on user library and then select javafx.
11.	Apply the following.
12.	The last step is to click on “run configuration” from the run drop down list.
13.	You will then see “Arguments” and after clicking this “VM Arguments” at the bottom.
14.	 In that field type in the path to the javafx folder.
15.	As an example --module-path"C:\Users\moham\Downloads\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml.
16.	Click apply. Now the problem should be solved.
