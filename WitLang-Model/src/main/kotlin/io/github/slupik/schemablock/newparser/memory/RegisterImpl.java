package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.newparser.memory.element.Memoryable;

import javax.inject.Inject;
import java.util.Stack;

/**
 * All rights reserved & copyright ©
 */
public class RegisterImpl extends Stack<Memoryable> implements Register {

    @Inject
    public RegisterImpl() {
    }

}
