# Cron Parser
A Java application for parsing cron expressions and displaying the schedule at which they will run.

## Build Instructions
### Requirements

- Java JDK 17 or later
- Maven 4.0.0 or later

### Installation
To build the project, run the following Maven command:</br>

<b>mvn clean install</b></br>

This command compiles the project and packages it into a runnable .jar file.


## Running
To run the application, use the following command in your terminal, replacing {JAR_FILE_PATH} with the path to your generated .jar file, and {cron_expression} with your cron expression:

<b>java -jar {JAR_FILE_PATH} "{cron_expression}"</b>

for ex:

java -jar CronParser-1.0-SNAPSHOT-jar-with-dependencies.jar  "*/15 0 1,15 * 1-5 /usr/bin/find"

## Expected Result
Given the example cron expression */15 0 1,15 * 1-5 /usr/bin/find, the application will output:

minute        0 15 30 45</br>
hour          0</br>
day of month  1 15</br>
month         1 2 3 4 5 6 7 8 9 10 11 12</br>
day of week   1 2 3 4 5</br>
command       /usr/bin/find

This result breaks down the cron expression into its component parts, showing the schedule on which the command will run.