# WorkTime

A simple webapp for logging work logs and absence.

Using MySql database system to store the record. The access and modify of the records are based on Sql Sroted Procedures. All query's can be found in the worktime-sql module.

The Java representations of the database record are in the worktime-api module, where you can found simple POJO's that the app using.

The database connection and access are based on Spring Framework JDBC API and related codes can be found in the worktime-jdbc module.

The worktime-core contains the server and client side codes also. Use SpringBoot for fast and easy ApplicationServer management. Various Rest endpoint for serves the client's request. The client builded by mainly AngularJS with using W3.CSS designs and Angular Material's directive's.

For project management the Gradle build tool used. The various build.gradle files can found in every module directory.

The Unit test made by Spock Framework, whit it a very simple and easily understandable test speicification's written.

Also for the project there is an RFID IC which used by a Texas Instruments Tiva C Series TM4C1294 microcontroller and connects to the server by Ethernet.

###### Requirements for running the system:
- JDK 1.8
- Gradle
- MySql Database - run each query you got in the worktime-sql module, begin with the update_script/create_tables.sql

###### Run:
1. Navigate to the worktime-core directory and run a "gradle build" command.
2. Run the "java -jar build\libs\worktime-core-1.0.0-SNAPSHOT.jar"

###### Summary:
Language: Sql, Java, Groovy, Html, Javascript, CSS, C

Framework/API: Spring (SpringBoot, JDBC), AngularJS, Angular Material, W3.CSS, Spock

IDE/Tools: Netbeans, Gradle, Energia, MySql Workbench
