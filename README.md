# Android ECJ Compiler

A modified version of ECJ (Eclipse Compiler for Java) `3.14.0` adapted for Android development.

**WARNING**: You need to add java.base.jar to classpath, else compiler won't find basic classes (Example: java.lang.Object, java.io.File etc)

## Key Features

- **Android-compatible**: Removed `Runtime.Version` dependency which isn't available in Android SDK
- **Dual usage modes**:
  - Use as a drop-in replacement for standard ECJ
  - Use the simplified API for easier integration
- **Full ECJ functionality**: Supports all standard Java compilation features

## Usage

### Basic Compilation

```java
import org.xedox.javac.Javac;
import org.xedox.javac.JavacOptionsBuilder;

Javac compiler = new Javac();
boolean success = compiler.compile(
    JavacOptionsBuilder.create()
        .release("17")
        .destination("out/")
        .classpath("path/to/java.base.jar")
        .addSrc("src/Main.java")
        .build()
);
```

### Using OptionsBuilder

```java
import org.xedox.javac.Javac;
import org.xedox.javac.JavacOptionsBuilder;
import java.nio.file.Paths;

String[] args = JavacOptionsBuilder.create()
    .source("1.8")
    .target("1.8")
    .destination(Paths.get("out/"))
    .sourcepath(Paths.get("src/"))
    .classpath("libs/dependency.jar")
    .option("-Xlint:unchecked")
    .addSrc("src/Main.java")
    .build();

Javac compiler = new Javac();
boolean success = compiler.compile(args);
```

### Advanced Configuration

```java
import org.xedox.javac.Javac;
import org.xedox.javac.JavacOptionsBuilder;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

// Custom output/error handlers
Javac compiler = new Javac(
    new PrintWriter(customOutStream),
    new PrintWriter(customErrStream)
);

// Compile multiple files
List<File> sources = Arrays.asList(
    new File("src/Main.java"),
    new File("src/Utils.java")
);

boolean success = compiler.compile(
    JavacOptionsBuilder.create()
        .release("17")
        .destination("out/")
        .classpath("path/to/java.base.jar")
        .addSrc(sources.stream().map(File::getPath).toList())
        .build()
);
```