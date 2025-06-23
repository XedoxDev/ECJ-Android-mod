package org.xedox.javac;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.jdt.internal.compiler.batch.Main;

public class Javac {

    private PrintWriter outWriter;
    private PrintWriter errWriter;
    private boolean systemExitEnabled;

    public Javac() {
        this(new PrintWriter(System.out), new PrintWriter(System.err));
    }

    public Javac(PrintWriter outWriter, PrintWriter errWriter) {
        this.outWriter = outWriter;
        this.errWriter = errWriter;
        this.systemExitEnabled = false;
    }

    public boolean compile(String... options) {
        Main compiler = new Main(outWriter, errWriter, systemExitEnabled, null, null);
        return compiler.compile(options);
    }

    public boolean compile(List<String> options) {
        return compile(options.toArray(new String[0]));
    }

    public boolean compileFromFiles(List<File> sourceFiles, List<String> additionalOptions) {
        List<String> options = new ArrayList<>(additionalOptions);
        sourceFiles.forEach(file -> options.add(file.getPath()));
        return compile(options);
    }
}
