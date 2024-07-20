# SimpleInflux
A small and simple InfluxDB API for Java made for writing data to InfluxDB.\
Other endpoints are not supported and are not planned to be added.

The InfluxDB `v2` api is used.

## Usage
### Opening a connection
To write data, you first need to create a new `InfluxDBConnection`. The constructor takes `ConnectionProperties` which can be created like this:
```java
ConnectionProperties properties = ConnectionProperties.builder()
        .address("http://localhost")
        .bucket("bucket")
        .organization("organization")
        .apiToken("apiToken")
        .build();
```
After setting all the required properties, you can create the connection:
```java
InfluxDBConnection connection = new InfluxDBConnection(properties);
```

You can also do the setup in one line:
```java
InfluxDBConnection connection = ConnectionProperties.builder()
        .build()
        //Set the properties
        .createConnection();
```

### Writing data
Creating data points is done by creating a `DataBuilder`:
```java
DataBuilder data = DataBuilder
        .create("measurement")
        .field("field1", 1)
        .field("field2", 2D)
        .field("field3", "test")
        .tag("tag1", "value1")
        .tag("tag2", "value2")
        .timestamp(System.currentTimeMillis());
```
Writing the data is done by calling `write` on the connection:
```java
try {
    connection.write(data);
} catch (IOException e) {
    //An IOException is thrown if the connection fails or the data is invalid
    e.printStackTrace();
}
```

For every `write` call a new connection is opened and closed. Writing multiple data points is done by passing them as a vararg or an iterable:
```java
//vararg
connection.write(data1, data2, data3);

//iterable
connection.write(List.of(data1, data2, data3));
```

## Including in your project
Check out [maven central](https://mvnrepository.com/artifact/net.lenni0451/SimpleInflux) or [my maven server](https://maven.lenni0451.net/#/releases/net/lenni0451/SimpleInflux) for the latest version.

### Gradle
```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "net.lenni0451:SimpleInflux:x.x.x"
}
```

### Maven
```xml
<dependency>
  <groupId>net.lenni0451</groupId>
  <artifactId>SimpleInflux</artifactId>
  <version>x.x.x</version>
</dependency>
```
