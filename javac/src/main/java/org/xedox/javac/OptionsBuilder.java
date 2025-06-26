package org.xedox.javac;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class OptionsBuilder {
    
    protected final List<String> args = new ArrayList<>();
    
    public List<String> getArgs() {
        return args;
    }
    
    public void arg(String... argument) {
        for(String arg : argument) {
        	this.args.add(arg);
        }
    }
    
    public void arg(List<String> arguments) {
        this.args.addAll(arguments);
    }
    
    public String[] build() {
        return this.args.toArray(new String[0]);
    }

    public String buildCmd() {
    	return String.join(" ", args);
    }
}