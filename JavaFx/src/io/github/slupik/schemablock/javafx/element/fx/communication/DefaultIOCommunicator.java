package io.github.slupik.schemablock.javafx.element.fx.communication;

import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;

import java.util.Scanner;

/**
 * All rights reserved & copyright ©
 */
public class DefaultIOCommunicator implements IOCommunicable {

    //TODO change to program's console

    @Override
    public String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    @Override
    public void print(String value) {
        System.out.print(value);
    }

    @Override
    public void clearOutput() {
        System.out.println();
        System.out.println("======= CLEAR =======");
        System.out.println();
    }
}
