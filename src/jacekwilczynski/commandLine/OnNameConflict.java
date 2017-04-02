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
import java.util.function.Predicate;

/**
 *
 * @author Jacek
 */
public class OnNameConflict implements Predicate<String> {

    public Behavior behavior;

    public OnNameConflict(Behavior value) {
        behavior = value;
    }

    @Override
    public boolean test(String name) {
        switch (behavior) {
            case PRINT_SKIP:
                System.out.println("Link \"" + name + "\" already present. New link not added.");
            case SKIP:
                return false;
            case PRINT_OVERRIDE:
                System.out.println("Link \"" + name + "\" already present. Overriding.");
            case OVERRIDE:
                return true;
            case ASK:
                System.out.println("Link \"" + name + "\" already present. Override? (y/n)");
                return getYesOrNo();
            case THROW_EXCEPTION:
                throw new RuntimeException("Link \"" + name + "\" already present.");
            default:
                throw new AssertionError(behavior.name());
        }
    }

    private boolean getYesOrNo() {
        Scanner IN = new Scanner(System.in);
        String input;
        while (true) {
            input = IN.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                return true;
            }
            if (input.equalsIgnoreCase("N")) {
                return false;
            }
        }
    }

    public static enum Behavior {
        SKIP, PRINT_SKIP, OVERRIDE, PRINT_OVERRIDE, ASK, THROW_EXCEPTION;
    }
}
