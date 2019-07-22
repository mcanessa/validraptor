<h1>Validraptor library</h1>

This library is intended to provide a way to extract validation logic from services
or other type of classes in able to have clearer code and concise method implementations.

<h2>Usage</h2>
There are 2 elements used:

-  The `@Validate` annotation to indicate what is to be validated.

-  The `Validator` interface to develop your own validators.

The `@Validate` annotation can be used at method and method's parameter level. When you put
the annotation at method level, this will validate the method's return object. If you use it
on a parameter, it will validate the method's parameter (obviously). The annotation must
receive a Class that implements the `Validator` interface. 

There are 2 modules, one that uses Spring-aop and the other that uses pure AspectJ.

<h2>Spring validraptor</h2>

<h3>Maven dependency</h3>
<b>***CURRENTYL NOT IN MAVEN CENTRAL***</b>

```xml
        <dependency>
            <groupId>com.github.mcanessa.validraptor</groupId>
            <artifactId>spring-validraptor</artifactId>
            <version>1.0.0</version>
        </dependency>
```
The only thing needed to be able to run this library is Spring. 
Add the dependency, develop your validations and it's ready to go.

Example:

```java
@Service
public class BankService {
    
    public String transfer(@Validate(TransferValidator.class) TransferRequest request) {
        doTransferStuff(request);
        return "OK";
    }
    
    @Override
    @Validate(ProcessResultValidator.class)
    public ProcessResult process(ProcessRequest request) {
        return doThingsToProcess(request);
    }
    /**
    .
    .
    .
    .
    .
    **/
}
```
```java
@Component
public class TransferValidator implements Validator<TransferRequest> {

    @Override
    public void validate(TransferRequest target) {
        if(validationsApply(target))
            throw new YourValidationException();
    }

}
```

```java
@Component
public class ProcessResultValidator implements Validator<ProcessResultValidator> {

    @Override
    public void validate(ProcessResultValidator target) {
        if(validationsApply(target))
            throw new YourValidationException();
    }

}
```
Keep in mind that this module has the spring-aop limitations. The validators and 
the classes where the annotation is used <b><u>must be spring beans</u></b>. 
This only works with calls between different beans, it doesn't work for inner calls.

<h2>AspectJ validraptor</h2>

<h3>Maven dependency</h3>

```xml
        <dependency>
			<groupId>com.github.mcanessa.validraptor</groupId>
			<artifactId>aspectj-validraptor</artifactId>
			<version>1.0.0</version>
		</dependency>
```

It's also needed the aspectj plugin to be able to weave the aspects used.

```xml
    <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>aspectj-maven-plugin</artifactId>
        <version>1.11</version>
        <configuration>
            <complianceLevel>1.8</complianceLevel>
            <source>1.8</source>
            <target>1.8</target>
            <showWeaveInfo>true</showWeaveInfo>
            <verbose>true</verbose>
            <aspectLibraries>
                <aspectLibrary>
                    <groupId>com.github.mcanessa.validraptor</groupId>
                    <artifactId>aspectj-validraptor</artifactId>
                </aspectLibrary>
            </aspectLibraries>
        </configuration>
        <executions>
            <execution>
                <goals>
                    <!-- use this goal to weave all your main classes -->
                    <goal>compile</goal>
                    <!-- use this goal to weave all your test classes -->
                    <goal>test-compile</goal>
                </goals>
            </execution>
        </executions>
        <dependencies>
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjtools</artifactId>
                <version>1.9.4</version>
            </dependency>
        </dependencies>
    </plugin>
```
<h2>IMPORTANT</h2>`
As of today, aspectj-maven-plugin version 1.11 doesn't work when its run with jdk 11.
If fix isn't uploaded to maven central, you can use the following snapshot build:

```xml
            <groupId>com.github.m50d</groupId>
            <artifactId>aspectj-maven-plugin</artifactId>
            <version>1.11.1</version>
```

Example:
The only difference with the spring-validraptor is that it doesn't have the
limitations of spring-aop and the validators and the classes where the annotation is used
<b><u>don't need to be</u></b> spring beans.

```java
public class BankService {
    
    public String transfer(@Validate(TransferValidator.class) TransferRequest request) {
        doTransferStuff(request);
        return "OK";
    }
    
    @Override
    @Validate(ProcessResultValidator.class)
    public ProcessResult process(ProcessRequest request) {
        return doThingsToProcess(request);
    }
    /**
    .
    .
    .
    .
    .
    **/
}
```

```java
public class TransferValidator implements Validator<TransferRequest> {

    @Override
    public void validate(TransferRequest target) {
        if(validationsApply(target))
            throw new YourValidationException();
    }

}
```

```java
public class ProcessResultValidator implements Validator<ProcessResultValidator> {

    @Override
    public void validate(ProcessResultValidator target) {
        if(validationsApply(target))
            throw new YourValidationException();
    }

}
```