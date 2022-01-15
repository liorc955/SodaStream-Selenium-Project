# SodaStream Automation Project

[![N|Solid](https://raw.githubusercontent.com/SeleniumHQ/www.seleniumhq.org/e846535b56af5f01151ec93f88785b86d3809564/src/main/webapp/images/originals/Selenium%20Logo%20Upright.svg)](https://www.selenium.dev/)



This is the first automation project I have built.
In this project, I took a manual test case excel file of SodaStream from Utest Platform and applied it to Selenium.
I used Java Selenium and TestNG.
This project implements POM and PageFactory Design.


## Tech

For this project, I used the following maven package:

- [TestNG](https://mvnrepository.com/artifact/org.testng/testng) - Testing framework for Java
- [Selenium Java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java) - Selenium automates browsers
- [Extent Reports](https://github.com/extent-framework/extentreports-java) - ExtentReports is an logger-style reporting library for automated tests

## Installation
First, you will need to install maven project:

https://maven.apache.org/install.html

-You can run this project with maven command line:

```sh
mvn test -DtestNg="Name of testng.xml"
```
e.g:
```sh
mvn test -DtestNg=OneTouchPageTest.xml
```
(make sure you are in the project folder before you run these commands)

-or with a regular java compiler like an eclipse.

## Attached resources
In the main folder of this project, there are all the HTML Extent Report files tests. Also, I attached a UML Diagram of this project using lucidchart and the excel test case file.
Note: There is a limited amount of using class and association objects in the free version of lucidchart, As a result, the file "Uml Diagram Pages and Objects Package1.pdf" is not fully completed.
