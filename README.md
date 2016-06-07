# DatabasesFinalProject
This repository will be used to store our source code for our CPE365 final project. The project will revolve around the idea of fantasy football.

TO RUN THE PROGRAM:
   1. Run the build-all.mysql with the database of your choice.
   2. Create a file called input and format it as follows. This file will be used to establish a database connection
      line 1: n
      line 2: <database username>
      line 3: <database password>
      If the database on a Cal Poly UNIX Server...
      line 4: y
      Else (the host will default to 'localhost'):
      line 4: n
      line 5: <database name>
   3. Run the following command:
      ./run < input

SAMPLE INPUT FILE:
n
rootismyusername
thisismypassword
n
fantasyfootballdatabase 

