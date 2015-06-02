# JavaPlugs
Small set of Java tweaks without any dependencies

## Content

### CrossLock & AppLock
Using idea of file locking to somehow synchronize code in different Java instances (or applications)

**CrossLock** allows you to use lock-like functionality between different instances.

**AppLock** is based on ClossLock and allows you to run only one Java application instance at time.

Just look into javadocs and tests.

## Adding to your project

For now this package is available only via https://jitpack.io/

### Gradle
```
repositories {
  maven {
    url "https://jitpack.io"
  }
}

dependencies {
  compile 'com.github.javaplugs:javaplugs:0.1'
}
```

### Maven
```
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>

<dependency>
  <groupId>com.github.javaplugs</groupId>
  <artifactId>javaplugs</artifactId>
  <version>0.1</version>
</dependency>
```
