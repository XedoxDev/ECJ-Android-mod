package org.xedox.javac;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class OptionsBuilder {
    private String javaVersion;
    private Path outputDirectory;
    private Path sourceDirectory;
    private String sourcePath;
    private List<String> classpath;
    private List<String> options;

    public String getJavaVersion() {
        return this.javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public Path getOutputDirectory() {
        return this.outputDirectory;
    }

    public void setOutputDirectory(Path outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public Path getSourceDirectory() {
        return this.sourceDirectory;
    }

    public void setSourceDirectory(Path sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public String getSourcePath() {
        return this.sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public List<String> getClasspath() {
        if (this.classpath == null) {
            this.classpath = new ArrayList<>();
        }
        return this.classpath;
    }

    public void setClasspath(List<String> classpath) {
        this.classpath = classpath;
    }

    public void addClasspath(String path) {
        getClasspath().add(path);
    }

    public void addAllClasspath(List<String> paths) {
        getClasspath().addAll(paths);
    }

    public List<String> getOptions() {
        if (this.options == null) {
            this.options = new ArrayList<>();
        }
        return this.options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void addOption(String option) {
        getOptions().add(option);
    }

    public void addAllOptions(List<String> options) {
        getOptions().addAll(options);
    }

    public String[] build() {
        List<String> args = new ArrayList<>();

        if (javaVersion != null) {
            args.add("-" + javaVersion);
        }

        if (outputDirectory != null) {
            args.add("-d");
            args.add(outputDirectory.toString());
        }

        if (sourceDirectory != null) {
            args.add("-sourcepath");
            args.add(sourceDirectory.toString());
        } else if (sourcePath != null) {
            args.add("-sourcepath");
            args.add(sourcePath);
        }
        if (classpath != null && !classpath.isEmpty()) {
            args.add("-classpath");
            args.add(String.join(System.getProperty("path.separator"), classpath));
        }

        if (options != null) {
            args.addAll(options);
        }

        return args.toArray(new String[0]);
    }
}
