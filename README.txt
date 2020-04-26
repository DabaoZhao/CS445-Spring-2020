#Configuration instructions

#Steps to install Java on the system
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer

#Install maven to the system
$ sudo apt-get install maven

#Set maven and java path in the environment


#Build and deployment instruction as follows:-
Open the project into Intellij IDE and set JDK .
It is a boot application which has inbuilt webapp and tomcat to run the jar image on server.
Run the src/main/java/org.pop.rs/RideApplication as a java application, the application will by default run on 8080 port
NO Database has been used


#Copyright and licensing instructions
License: GNU GENERAL PUBLIC LICENSE v3.0


#Known bugs
1)   GET /rides/{rid1}/mesages and /accounts/{aid}/driver got errors when testing.

#Credits and acknowledgements
Maven and springboot for easy and smooth webapp creation
Open JAVA for smooth use of spring and java as REST API
