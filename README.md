# Urls
Has multiple exercises of servers and clients. Seen know the structure of URL until server and client remote.  
[Repository in GitHub here](https://github.com/JffMv/Urls)

## Getting Started

For clone this repository you use this command:
 ```
 git clone https://github.com/JffMv/Urls.git
 ```

### Prerequisites

Have installed:
maven 3.9.6
[Install Maven](https://maven.apache.org/download.cgi#Installation)


git 2.44
[Install Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

java
[Install Java](https://www.oracle.com/co/java/technologies/downloads/)


## Running the tests

This classes haven´t unit test

## Deployment

The process were building project maven with the command:

```
mvn archetype:generate -DgroupId=org.example -DartifactId=URLS -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

```

Then we have verify the class App.java and the pom.xml, run the project with:

```
mvn package

```

For generate documentation update the pom.xml add and later use "mvn package":

```
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <version>3.3.1</version>
                <configuration>
                    <reportOutputDirectory>${project.basedir}/documentation/javadocs</reportOutputDirectory>
                    <destDir>${project.basedir}/documentation/javadocs</destDir>
                </configuration>
                <executions>
                    <execution>
                        <id>generate-javadocs</id>
                        <goals>
                            <goal>javadoc</goal>
                            <goal>aggregate</goal>
                            <goal>aggregate-jar</goal>
                        </goals>
                        <configuration>
                            <reportOutputDirectory>${project.basedir}/documentation/javadocs</reportOutputDirectory>
                            <destDir>${project.basedir}/documentation/javadocs</destDir>
                        </configuration>
                    </execution>
                    <execution>
                        <id>generate-test-javadocs</id>
                        <goals>
                            <goal>test-javadoc</goal>
                            <goal>test-aggregate</goal>
                            <goal>test-aggregate-jar</goal>
                        </goals>
                        <configuration>
                            <destDir>${project.basedir}/documentation/testJavadocs</destDir>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

```
With the before plugins the documentation generate in the URLS/documentation/

### Server web (return the square of any number):
This return the square of any number:
- For both run programs location in the root of URLS
For run server:
```
java -cp "target/classes" org.example.EchoServer

```
For run client:
```
java -cp "target/classes" org.example.EchoClient

```

### Server web (return result of operations sin, cos, tan):
This return the sin, cos or tan of number. The input **HAVE** number and can change the operation write "fun:---". Where "---" is the function.
- For run program location in the root of URLS
For run server:
```
java -cp "target/classes" org.example.EchoServerWithOperations

```
For run client:
```
java -cp "target/classes" org.example.EchoClient

```

### Server web (return images and files html): 
This return images and files html save at directory **src/main/resource**
- For run program location in the root of URLS
For run server:
```
java -cp "target/classes" org.example.EchoServerSendImageDocument

```
For run client,
you have into to browser and put this url:
```
localhost:35000/xxxxxxxxx
```
you replace xxxxxxxxx for the name of the file. The files exist are:
- Among_Us_cover_art.jpg 
- proof.html

You can add more files to **src/main/resource** and then call for before URL channel. 

### Datagrams: 
We create a server and client where client request the date to server, however the client not depend of server. 
- For run both programs location in the root of URLS
For run client:
```
java -cp "target/classes" org.example.DatagramTimeClient

```
For run server:
```
java -cp "target/classes" org.example.DatagramTimeServer

```
### Remote (RMI): 
Are two classes basics work for a CHAT, both implements interfaces that inherit the remote.
- For run both programs location in the root of URLS
For run server:
```
java -cp "target/classes" org.example.EchoServerImplementationRemote

```
For run each client (you can have clients how you want, each in different console):
```
java -cp "target/classes" org.example.EchoClientImplementationRemote

```



Run the project seen console with where "Route/you/file.you" put the real location of the file:
```
java -cp target/Mean-SD-Lambda-DataGenerics-1.0-SNAPSHOT.jar edu.escuelaing.arsw.ASE.app.App "Route/you/file.you"

```

## Built With

* [Java](https://www.java.com/es/) - The language used
* [Maven](https://maven.apache.org/) - Dependency Management



## Authors

* **Yeferson Mesa**

## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details