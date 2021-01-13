package io.github.slupik.schemablock.javafx.element.fx.communication;

import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;

import java.util.Scanner;

/**
 * All rights reserved & copyright ©
 */
public class DefaultIOCommunicator implements IOCommunicable {

    private Scanner scanner = new Scanner(System.in);

    //TODO change to execution's console

    @Override
    public String getInput() {
        scanner = new Scanner(System.in);
        return scanner.next();
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

    @Override
    public void stop() {
        scanner.close();
    }
}
