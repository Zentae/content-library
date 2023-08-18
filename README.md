# content-library

Lightweight library that helps content management.

## How to include content-library in your project.

````xml
<dependency>
    <groupId>io.zentae.contentlibrary</groupId>
    <artifactId>content-library</artifactId>
    <version>2.5.0</version>
    <scope>compile</scope>
</dependency>
````

## Extensions
In the following steps I will show how to setup an Extension.
<br>
You need to provide what type of data you want to serialize or deserialize.
### Json
````java
Extension<String> extension = new JsonExtension<>();
````
### Yaml
````java
Extension<String> extension = new YamlExtension<>();
````
### Serialization
For the serialization you'll need to provide a file where the data will be stored 
<br>
as well as the data that needs to be stored.
````java
File file = new File("some file path");
extension.serialize(file, "Some data");
````
### Deserialisation
For the deserialization you only need to provide the file where the stored data is.
````java
String deserializedString = extension.deserialize(file, String.class);
````
## Directories
Directories is a very useful class that will be subject to evolution.
<br>
This class extends directly from java.File but adds some useful methods.
### Get files
This method returns all the files within this directory.
````java
    Directory directory = new Directory("some directory path");
    directory.getFiles().forEach(file ->
        System.out.println(file.getAbsolutePath()));
````
### Copy files
This methods allows you to copy several files into the following directory.
````java
    File[] files = new File[5];
    Directory directory = new Directory("some directory path");
    directory.copyFiles(files);
````
## Wrappers
Wrappers are indeed very useful when it comes to context or to wrap regardless the type of the data.
<br>
They can store whatever object regardless their type. Good for generic stuff.
### Generic wrapper
This wrapper is useful for single generic data wrapping.
````java
# The data that needs to be stored.
GamePlayer gamePlayer = null;
# Init object wrapper.
GenericWrapper<GamePlayer> genericWrapper = new GenericWrapper();
# Set the single generic parameter.
genericWrapper.setParameter(gamePlayer);
# Get the stored parameter.
GamePlayer storedGamePlayer = genericWrapper.getParameter();
````
### Map wrapper
This map is also generic it can store objects regardless their type.
````java
# The data that needs to be stored.
Object data = null;
# Init map wrapper.
MapWrapper mapWrapper = new MapWrapper();
# Put the data into the map.
mapWrapper.putParameter("key", data);
# Get the said data.
Object storedData = mapWrapper.getParameter("key", Object.class);
````