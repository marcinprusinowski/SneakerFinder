# SneakerFinder
Web application for searching shoes in the best sneaker shops in Poland.

[SneakerFinder](https://agile-savannah-97721.herokuapp.com/)

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
- Install [Java ( 11 at least )](https://www.codejava.net/java-se/download-and-install-java-11-openjdk-and-oracle-jdk) 
- Install [Maven ( 3.6.3 at least ) ](https://maven.apache.org/install.html)

**Once you installed Java and Maven, open the terminal and get copy of this application on your local machine:**
```
git clone https://github.com/marcinprusinowski/SneakerFinder.git
```
**Go to the root directory of the project and build it using:**
```
mvn package
```
**After successful build, you can finally run the application with:**
```
java -jar ./target/thymleaf-shoefinder-0.0.1-SNAPSHOT.war
```
**Application runs on a port 8080.**
```
Enjoy ! :)
```
## Build With:
- [SpringBoot](https://spring.io/projects/spring-boot) - JavaEE framework.
- [Maven](https://maven.apache.org/) - Used to manage the Project's build.
- [Thymleaf](https://www.thymeleaf.org/) - Server-side Template engine for web and standalone environments.
- [Jsoup](https://jsoup.org/) - Java library for manipulating HTML Document.


## Idea ?
I am a little bit of a sneaker freak and I used to look for sneakers a lot. I have allways had 25 carts opened at once in my web browser and it was a one huge mess, but there was no other options to compare them in a fast way. Then I thought it would be nice if such SneakerFinder exists, which search for the sneakers through the best and the most popular sneaker shops in Poland.
