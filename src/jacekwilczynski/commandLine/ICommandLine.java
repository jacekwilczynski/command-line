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
 * An interface delineating the external behavior of Command Line classes.</p>
 * <p>
 * An implementation should provide a user interface and interpret user input
 * only to the extent necessary to determine and launch a command.</p>
 *
 */
public interface ICommandLine {

    /**
     * Links given command libraries to this command line.
     *
     * @param libraries commands to link
     */
    public void link(CommandLibrary... libraries);

    /**
     * Unlinks given command libraries from this command line.
     *
     * @param libraries commands to unlink
     */
    public void unlink(CommandLibrary... libraries);

    /**
     * Returns true if the given command libraries are linked to this command
     * line.
     *
     * @param libraries command libraries to look for
     * @return true if all the libraries are linked to this command line, false
     * if one or more is not
     */
    public boolean has(CommandLibrary... libraries);

    /**
     * Returns an array of the command libraries linked to this command line.
     *
     * @return array of command libraries
     */
    public CommandLibrary[] getAll();

    /**
     * Performs an action on the given command libraries. Useful for changing
     * parse settings of multiple libraries in one go.
     *
     * @param action action to be performed. Can use a lambda expression. Does
     * not take any arguments or return any value.
     * @param libraries the libraries to perform the action on
     */
    public void apply(Consumer<CommandLibrary> action, CommandLibrary... libraries);

    /**
     * Performs an action on all libraries linked to this command line. Useful
     * for changing parse settings of all libraries in one go.
     *
     * @param action action to be performed. Can use a lambda expression. Does
     * not take any arguments or return any value.
     */
    default public void apply(Consumer<CommandLibrary> action) {
        apply(action, getAll());
    }

    /**
     * Launches this command line.
     */
    public void run();

}
