# Android FlightGear Joystick

**Technical info:**  
Flight Gear settings : --telnet=socket,in,10,127.0.0.1,6400,tcp  
Note: 6400 is just a recommended port, and can be chosen freely by the user.

## Description
<!-- The client runs the android application on either an emulator or an Android phone, and runs FlightGear on a computer. When the user wants to connect the FlightGear, they enter the IP address of the computer running the FlightGear, as well as the port chosen in the FlightGear settings. In the app click the "Fly!" button and in the FlightGear click "autostart" (located in the tab with the name of the chosen plane).
The app connects to the FlightGear and the user can now control the flight using the joystick, rudder, and throttle. -->
This is an Android app that a user can use to connect to a computer running FlightGear. Once connected, the user can control the plane using the joystick, rudder, and throttle in the app.


## Installation And Running application
1.Before running our app you need to download and install the FlightGear application. [You can find a download link here.](https://www.flightgear.org/download/ "FlightGear Download")  
2.Download Android Studio.
3.Download the repo on your computer and run the app.


The client runs the android application on either an emulator or an Android phone, and runs FlightGear on a computer. When the user wants to connect the FlightGear, they enter the IP address of the computer running the FlightGear, as well as the port chosen in the FlightGear settings. In the app click the "Fly!" button and in the FlightGear click "autostart" (located in the tab with the name of the chosen plane).
<!-- The app connects to the FlightGear and the user can now control the flight using the joystick, rudder, and throttle. -->
The app connects to the FlightGear and the user can now control the flight!
<!-- For more information about FlightGear, you can find here
Android Studio
Flight Gear -->

## Four Main Parts:
1. Main Activity  
2. Joystick  
3. ViewModel  
4. Model  

### 1. Main Activity
The Main Activity(view) contains a reference to the joystick, input fields, and seekbars.
Here we define Listeners which are used to listen to the control elements and send the appropriate data to the ViewModel.

### 2. Joystick
File that includes all the details about the current status of the joystick.
Includes small circle with border of big circle.
Processes every touch event on the joystick.

### 3. ViewModel
The ViewModel is the connection between the Model and the View.
It takes the data from the View and sends it to the Model after calculations if needed.

### 4. Model
The Model includes connection to the server of the FlightGear.
In addition, the Model is responsible for sending the updated flight data to the FlightGear.
The Model closes the connection when the user close the app.





#### How it looks:
<img src="https://github.com/ArielDrellich/Android-FlightGear-Joystick/blob/master/Images/1.jpeg" width="250" height="500">

<img src="https://github.com/ArielDrellich/Android-FlightGear-Joystick/blob/master/Images/2.jpeg" width="250" height="500">


## Documentation
Here you can find the UML contains partial information of the central classes. UML represents the various connections between the classes and the most important information found in each class. UML can be found here.

![image](https://github.com/ArielDrellich/Android-FlightGear-Joystick/blob/master/UML.png)

## Video
Here you can find a link to our demo video - https://www.youtube.com/watch?v=KJTP_ruWYMw&ab_channel=Sapir
