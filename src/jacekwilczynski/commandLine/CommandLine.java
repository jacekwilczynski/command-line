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
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 *
 *
 */
public abstract class CommandLine implements ICommandLine<Command> {

    private boolean caseSensitive;
    private BiPredicate<String, String> compareMethod;
    private Map<String, Command> linkMap;
    private Set<Command> linkSet;

    {
        setCaseSensitive(false);
        linkMap = new HashMap<>();
        linkSet = new HashSet<>();
    }

    public final void setCaseSensitive(boolean value) {
        caseSensitive = value;
        if (caseSensitive) {
            compareMethod = (s1, s2) -> s1.equals(s2);
        } else {
            compareMethod = (s1, s2) -> s1.equalsIgnoreCase(s2);
        }
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    @Override
    public void link(Command... commands) {
        for (Command command : commands) {
            linkSet.add(command);
            linkMap.put(command.getName(), command);
        }
    }

    @Override
    public void unlink(Command... commands) {
        for (Command command : commands) {
            if (has(command)) {
                linkSet.remove(command);
                linkMap.remove(command.getName());
            }
        }
    }

    @Override
    public boolean has(Command... commands) {
        return linkSet.containsAll(Arrays.asList(commands));
    }

    public boolean has(String... commands) {
        return linkMap.keySet().containsAll(Arrays.asList(commands));
    }

    @Override
    public Command[] getAll() {
        return (Command[]) linkSet.toArray();
    }

    @Override
    public void apply(Consumer<Command> action, Command... commands) {
        for (Command command : commands) {
            action.accept(command);
        }
    }

    @Override
    public void run() {
        initialize();
        do {
            prompt();
        } while (accept(getInput()));
        exit();
    }

    protected boolean areEqual(String s1, String s2) {
        return compareMethod.test(s1, s2);
    }

    protected boolean launch(String command, String[] args) {
        if (has(command)) {
            linkMap.get(command).launch(args);
            return true;
        } else {
            return false;
        }
    }

    protected abstract void initialize();

    protected abstract void prompt();

    protected abstract String getInput();

    protected abstract boolean accept(String input);

    protected abstract void exit();

}
