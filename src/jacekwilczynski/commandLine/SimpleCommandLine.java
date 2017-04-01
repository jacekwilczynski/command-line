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

import java.util.Scanner;

/**
 *
 * @author Jacek
 */
public class SimpleCommandLine extends CommandLine {

    private static final Scanner IN = new Scanner(System.in);

    @Override
    protected void initialize() {
        System.out.println("Welcome to Simple Command Line by Jacek Wilczynski.\n");
    }

    @Override
    protected void prompt() {
        System.out.print("> ");
    }

    @Override
    protected String getInput() {
        return IN.nextLine();
    }

    @Override
    protected boolean accept(String input) {
        String[] words = input.split("[ ]");
        String command = words[0];
        if (areEqual(command, "exit")) {
            return false;
        }
        String[] args = new String[words.length - 1];
        System.arraycopy(words, 1, args, 0, words.length - 1);

        if (!launch(command, args)) {
            System.out.println("Command " + command + " not found.");
        }
        return true;
    }

    @Override
    protected void exit() {
        System.out.println("Goodbye!");
    }

}
