# Sloppify

**Rudimentary backend clone of Spotify.**

This project is intended to put to use multiple programing concepts I learned during my self-taught journey into software development. It showcases my skills in using the Spring Boot framework to create a RESTful web API and a GUI desktop application that allows for administrator tasks.
The project is divided in two parts: 

  * **Sloppify:** Server side application where all the entities, controllers, services, repositories, security filters and configurations are defined.
  * **SloppifyAdminApp:** Desktop GUI that allows to upload a track a create new administrators.

They work together to try to emulate how a music streaming platform like Spotify would function for both an user and an administrator.

The following concepts are applied in this project:

  **Sloppify:**
  * Spring Boot framework and it's annotations.
  * REST architecture.
  * Http requests.
  * Web security using Spring Security.
  * Stateless session using JWTs.
  * MySQL database connection using Spring Data JPA.
  * Testing using JUnit5 with the Mockito library.
  * File handling.
  * AudioInputStreams. 

  **SloppifyAdminApp:**
  * Interactive GUI using Java Swing.
  * MySQL database connection using MySQL driver for Java.
  * File handling.
  * Password encryption using Spring Security.

## Setup

Both parts of this project contain an "application.properties.example" file in the resources folder. This file enumerates the needed properties for the application to work as intended, but it needs to be set up for it to work in your own machine.

![Readme_image_1](https://github.com/rijofeta/Sloppify/assets/126613798/9e166626-2f43-430e-a835-c2779b2bef31)
![Readme_image_2](https://github.com/rijofeta/Sloppify/assets/126613798/598d4730-306e-404f-ae11-ad2b39af8b71)

1. To begin the setup you first need to create a MySQL schema, where all the tables and their entities will be put into. The easiest way to do this is to create the schema directly using either the MySQL workbench or the MySQL command line. 
2. Substitute the database url, username and password examples in the "application.properties.example" files for your own database properties. The typical database url looks something like "jdbc:mysql://{_host_}:{_port_}/{_schema_}", where the brackets are substituted by the respective fields.
3. Create the folder where the track files and cover art files will be saved into.
4. Substitute the track storage path and cover art path examples for the paths of the folders you created.
5. Change the "application.properties.example" file name to "application.properties" so it acts as a .properties file.

With this, both parts of the application should be fully functional. 

**NOTES:** 
1. The red boxes mark the fields that should be changed. Everything else should be left as it is.
2. The field "spring.jpa.hibernate.ddl-auto" is set to "create-drop" which means that everytime the application is run, all the information saved inside the database is erased. This is set like this for developing purposes only.

## Walkthrough 

To start using the application, run the "SloppifyApplication" class in the "Sloppify" folder. This will define all the entities, controllers, services, repositories, security filters and configurations used by the server. With this you should be able to register, login, find track information and play a song using any API testing software.

But you'll have nothing to listen to if you don't add a song first. So let's do that. Run the "Main" class in the "SloppifyAdminApp" folder. This should open a GUI with two buttons, "Create User" and "Upload Track". Press the "Upload Track" button and you should have the following:

![Readme_image_3](https://github.com/rijofeta/Sloppify/assets/126613798/049001a9-70bf-4315-bc5b-245d59f32abb)

Go ahead and pick your favorite song, fill in the information, and press the upload button. If everything was done correctly, you should receive a "Track uploaded successfully" message. The track file and cover art should be in their defined storages and a new entry should be seen in the "sloppify_track" table of your MySQL database.

Now open your API testing software and register yourself by sending a POST request to localhost:8080/registration.

![Readme_image_4](https://github.com/rijofeta/Sloppify/assets/126613798/93412471-95d1-4f05-9df3-af2ec82a5f4d)

If everything goes right, you should be able to login. Send a POST request to localhost:8080/login. You'll recieve a token. This is the JWT you'll use to authorize each request you send.

![Readme_image_5](https://github.com/rijofeta/Sloppify/assets/126613798/6d1ee302-2c29-4d1b-bcf9-7492f3cbbd8b)

Now, to get information about a song, send a GET request to localhost:8080/track/{id} where {id} should be substituted by the song's id (check database). Dont forget to pass your JWT for authorization.

Finally, to listen to your jam, send a GET request to localhost:8080/track/{id}/play and the song should start playing!
