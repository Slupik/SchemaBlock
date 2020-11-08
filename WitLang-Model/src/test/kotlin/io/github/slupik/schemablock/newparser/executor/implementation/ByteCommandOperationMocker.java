package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandOperation;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandBase;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandOperationMocker {

    public static ByteCommandOperation mockByteCommandOperation() {
        return new MockedByteCommandOperation();
    }

    public static class MockedByteCommandOperation extends ByteCommandBase implements ByteCommandOperation {

        public MockedByteCommandOperation() {
            super(-1, -1, null);
        }

        @Override
        public String getSymbol() {
            return null;
        }
    }

}
