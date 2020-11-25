package io.github.slupik.schemablock.view.logic.communication;

import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;

import java.util.Scanner;

/**
 * All rights reserved & copyright ©
 */
public class DefaultIOCommunicator implements IOCommunicable {

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
    public void clear() {
        System.out.println();
        System.out.println("======= CLEAR =======");
        System.out.println();
    }

    @Override
    public void printAlgorithmError(String text) {
        System.err.println(text);
    }

    @Override
    public void printProgramError(String text) {
        System.err.println(text);
    }
}
