package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.Variable;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * All rights reserved & copyright ©
 */
public class StringChecker {

    @Test
    void checkMerge() throws InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException, VariableIsAlreadyDefinedException {
        Assertions.assertEquals("test"+" ", CodeParser.getValueToPrint("\"test\"+\" \""));
        Assertions.assertEquals("test"+" "+1, CodeParser.getValueToPrint("\"test\"+\" \"+1"));
        Assertions.assertEquals("test"+" "+"żółćęÓŻŻ€ "+1, CodeParser.getValueToPrint("\"test\"+\" \"+\"żółćęÓŻŻ€ \"+1"));

        CodeParser.getHeap().registerVariable(new Variable("valueI", ValueType.INT, "423"));
        Assertions.assertEquals("test"+" "+"żółćęÓŻŻ€ "+1+""+"423", CodeParser.getValueToPrint("\"test\"+\" \"+\"żółćęÓŻŻ€ \"+1+\"\"+valueI"));
        //TODO repair
//        Assertions.assertEquals("test"+" "+"żółćęÓŻŻ€ "+1+423, CodeParser.getValueToPrint("\"test\"+\" \"+\"żółćęÓŻŻ€ \"+1+valueI"));

        CodeParser.getHeap().registerVariable(new Variable("valueS", ValueType.STRING, "test"));
        Assertions.assertEquals("test"+" "+"żółćęÓŻŻ€ "+1+"test", CodeParser.getValueToPrint("\"test\"+\" \"+\"żółćęÓŻŻ€ \"+1+valueS"));
    }

    @Test
    void checkSpecialCharacters() throws InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException {
        Assertions.assertEquals("test"+"\n", CodeParser.getValueToPrint("\"test\"+\"\\n\""));

        Assertions.assertEquals("te\"st", CodeParser.getValueToPrint("\"te\\\"st\""));
        Assertions.assertEquals("te\"st"+"\""+"\n"+"", CodeParser.getValueToPrint("\"te\\\"st\"+\"\\\"\"+\"\\n\"+\"\""));
    }
}
