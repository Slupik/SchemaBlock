package io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * All rights reserved & copyright ©
 */
public class DefaultIO implements IOproxy {
    @Override
    public String readLine() {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void print(String print) {
        System.out.println(print);
    }
}
