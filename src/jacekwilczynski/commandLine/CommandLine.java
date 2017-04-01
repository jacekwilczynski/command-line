/*
 * The MIT License
 *
 * Copyright 2017 Jacek.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package jacekwilczynski.commandLine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import static javafx.application.Platform.exit;

/**
 *
 *
 */
public class CommandLine implements ICommandLine {
    
    private final Scanner IN = new Scanner(System.in);

    private Map<String, CommandLibrary> libMap = new HashMap<>();
    private Set<CommandLibrary> libSet = new HashSet<>();

    @Override
    public void link(CommandLibrary... libraries) {
        for (CommandLibrary library : libraries) {
            libSet.add(library);
            libMap.put(library.getName(), library);
        }
    }

    @Override
    public void unlink(CommandLibrary... libraries) {
        for (CommandLibrary library : libraries) {
            libSet.remove(library);
            libMap.remove(library.getName(), library);
        }
    }

    @Override
    public boolean has(CommandLibrary... libraries) {
        return libSet.containsAll(Arrays.asList(libraries));
    }

    @Override
    public CommandLibrary[] getAll() {
        return (CommandLibrary[]) libSet.toArray();
    }

    @Override
    public void apply(Consumer<CommandLibrary> action, CommandLibrary... libraries) {
        for (CommandLibrary library : libraries) {
            action.accept(library);
        }
    }

    @Override
    public void run() {
        begin();
        do {
            prompt();
        } while (accept(getInput()));
        exit();
    }
    
    protected void begin() {
        System.out.println("Welcome to Command Line by Jacek Wilczynski!");
    }
    
    protected void prompt() {
        System.out.print("> ");
    }
    
    protected String getInput() {
        return IN.nextLine();
    }
    
    protected boolean accept(String line) {
        // TODO
        return true;
    }
    
    protected void exit() {
        System.out.println("Goodbye!");
    }

}
