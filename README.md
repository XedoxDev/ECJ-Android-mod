# Android ECJ Compiler

A modified version of ECJ (Eclipse Compiler for Java) `3.14.0` adapted for Android development.

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

Javac compiler = new Javac();
boolean success = compiler.compile("-17", "-d", "out/", "src/Main.java");
```

### Using OptionsBuilder

```java
import org.xedox.javac.OptionsBuilder;
import java.nio.file.Paths;

String[] args = new OptionsBuilder()
    .withJavaVersion("1.8")
    .withOutputDirectory(Paths.get("out/"))
    .withSourceDirectory(Paths.get("src/"))
    .addClasspath("libs/dependency.jar")
    .addOption("-Xlint:unchecked")
    .addSourceFile("src/Main.java")
    .build();

boolean success = compiler.compile(args);
```

### Advanced Configuration

```java
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

boolean success = compiler.compileFromFiles(sources, additionalOptions);
```