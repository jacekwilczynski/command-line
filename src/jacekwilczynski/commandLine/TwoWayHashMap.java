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

import com.sun.prism.PhongMaterial.MapType;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jacek
 */
public class TwoWayHashMap<FirstType, SecondType> {

    public Map<FirstType, SecondType> a = new HashMap<>();
    public Map<SecondType, FirstType> b = new HashMap<>();

    public boolean[] put(FirstType keyValue1, SecondType keyValue2) {
        boolean[] result = new boolean[2];
        result[0] = a.put(keyValue1, keyValue2) == null;
        result[1] = b.put(keyValue2, keyValue1) == null;
        return result;
    }

}
