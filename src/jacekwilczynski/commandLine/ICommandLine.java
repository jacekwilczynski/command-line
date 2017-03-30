/*
 * The MIT License
 *
 * Copyright 2017 Jacek Wilczynski <http://github.com/jacekwilczynski>.
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

import java.util.function.Consumer;

/**
 * <p>
 * An interface establishing the base architecture for a command-line user
 * interface.</p>
 * <p>
 * An implementation should provide a user interface and interpret user input
 * only to the extent necessary to determine and launch a command.</p>
 *
 * @param <CommandType> the class type of commands that will be used by the
 * implementation of this interface
 */
public interface ICommandLine<CommandType> {

    /**
     * Links given commands to this command line.
     *
     * @param commands commands to link
     */
    public void link(CommandType... commands);

    /**
     * Unlinks given commands from this command line.
     *
     * @param commands commands to unlink
     */
    public void unlink(CommandType... commands);

    /**
     * Returns true if the given commands are linked to this command line.
     *
     * @param commands commands to check
     * @return true if all commands are linked to this command line, false if
     * one or more is not
     */
    public boolean has(CommandType... commands);

    /**
     * Returns an array of the commands linked to this command line.
     *
     * @return array of commands
     */
    public CommandType[] getAll();

    /**
     * Performs an action on the given command objects. Can be used to launch
     * multiple commands or change their inner settings.
     *
     * @param action action to be performed. Can use a lambda expression. Does
     * not take any arguments or return any value.
     * @param commands the commands to perform the action on
     */
    public void apply(Consumer<CommandType> action, CommandType... commands);

    /**
     * Performs an action on all commands link to this command line. Can be used
     * to launch multiple commands or change their inner settings.
     *
     * @param action action to be performed. Can use a lambda expression. Does
     * not take any arguments or return any value.
     */
    default public void apply(Consumer<CommandType> action) {
        apply(action, getAll());
    }

    /**
     * Launches this command line.
     */
    public void run();

}
