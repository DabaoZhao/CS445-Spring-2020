#Configuration instructions

#Steps to install Java on the system
$ sudo apt update
$ sudo apt install openjdk-8-jdk

#Install maven to the system
$ sudo apt install maven

#Set maven and java path in the environment

#Run Junit Test
mvn install compile test

#Build and deployment instruction as follows:-
After maven installed, get into the directory of the project, then use command line: mvn spring-boot:run
The application will by default run on 8080 port
NO Database has been used


#Known bugs
1)   GET /rides/{rid1}/mesages and /accounts/{aid}/driver got errors when testing.

#Credits and acknowledgements
Maven and springboot for easy and smooth webapp creation
Open JAVA for smooth use of spring and java as REST API
