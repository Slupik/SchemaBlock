package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandDeclareVarImpl;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandHeapVariableImpl;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandHeapVirArrImpl;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation.ByteCommandOperationImpl;
import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.utils.CodeUtils;
import io.github.slupik.schemablock.newparser.utils.TextUtils;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class Declaration {

    static List<ByteCommand> compile(ValueType type, List<Token> parts) throws NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ValueTooBig {
        List<ByteCommand> compiled = new ArrayList<>();

        List<Token> partialDeclaration = new ArrayList<>();//ex. int a, b;
        int arrayNestLvl = 0;
        for (Token part : parts) {
            if (CodeUtils.isArrayStart(part)) arrayNestLvl++;
            if (CodeUtils.isArrayEnd(part)) arrayNestLvl--;

            if (",".equals(part.getData()) && arrayNestLvl == 0) {
                compiled.addAll(compilePart(type, partialDeclaration));
                partialDeclaration.clear();
            } else {
                partialDeclaration.add(part);
            }
        }

        if (partialDeclaration.size() > 0) {
            compiled.addAll(compilePart(type, partialDeclaration));
        }

        return compiled;
    }

    private static List<ByteCommand> compilePart(ValueType type, List<Token> parts) throws NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ValueTooBig {
        List<ByteCommand> compiled = new ArrayList<>();

        Token tokenWithName = null;
        if (isNameOfVariable(parts.get(0).getData())) {
            tokenWithName = parts.get(0);
        }
        for (int i = 0; i < parts.size(); i++) {
            Token current = parts.get(i);
            if ((CodeUtils.isArrayEnd(current) && CodeUtils.getArrayNestLvl(current) == 0) || CodeUtils.isEmptyArrayBrackets(current)) {
                if (parts.size() > i + 1) {
                    if (isNameOfVariable(parts.get(i + 1).getData())) {
                        tokenWithName = parts.get(i + 1);
                    }
                } else {
                    if (tokenWithName == null) {
                        throw new NameForDeclarationCannotBeFound(parts.get(0).getLine(), parts.get(0).getPos());
                    } else {
                        break;
                    }
                }
            }
        }
        if (tokenWithName == null) {
            throw new NameForDeclarationCannotBeFound(parts.get(0).getLine(), parts.get(0).getPos());
        }

        List<List<Token>> dimensions = new ArrayList<>();
        List<Token> actDimension = new ArrayList<>();
        int nestLevel = -1;
        for (Token checked : parts) {
            if (checked.equals(tokenWithName)) {
                continue;
            }

            if (CodeUtils.isArrayStart(checked)) {
                nestLevel = CodeUtils.getArrayNestLvl(checked);
                continue;
            } else if (CodeUtils.isArrayEnd(checked)) {
                nestLevel = CodeUtils.getArrayNestLvl(checked) - 1;
                if (nestLevel == -1) {
                    dimensions.add(actDimension);
                    actDimension = new ArrayList<>();
                }
                continue;
            }

            if (nestLevel > -1) {//CodeUtils.isSpecialText(checked.getData()) || TextUtils.isNumber(checked.getData())
                actDimension.add(checked);
                continue;
            }

            if (nestLevel == -1 && CodeUtils.isEmptyArrayBrackets(checked)) {
                dimensions.add(actDimension);
                actDimension = new ArrayList<>();
            }
        }

        compiled.add(new ByteCommandDeclareVarImpl(
                tokenWithName.getLine(),
                tokenWithName.getPos(),
                type,
                tokenWithName.getData(),
                dimensions.size()
        ));
        compiled.add(new ByteCommandHeapVariableImpl(
                tokenWithName.getLine(),
                tokenWithName.getPos(),
                tokenWithName.getData()
        ));

        int lastSize = compiled.size();
        for (int i = dimensions.size() - 1; i >= 0; i--) {
            compiled.addAll(LineCompilator.getCompiledLine(dimensions.get(i)));
        }

        if (lastSize != compiled.size()) {
            compiled.add(new ByteCommandHeapVirArrImpl(
                    tokenWithName.getLine(),
                    tokenWithName.getPos(),
                    type,
                    dimensions.size(),
                    true
            ));
            compiled.add(new ByteCommandOperationImpl(
                    tokenWithName.getLine(),
                    tokenWithName.getPos(),
                    "="
            ));
        }

        return compiled;
    }

    private static boolean isNameOfVariable(String data) {
        return !CodeUtils.isSpecialText(data) && !TextUtils.isNumber(data) && !CodeUtils.isEmptyArrayBrackets(data)
                && !CodeUtils.isArrayStart(data) && !CodeUtils.isArrayEnd(data);
    }
}
