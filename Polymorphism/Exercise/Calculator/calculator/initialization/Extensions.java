package calculator.initialization;

import calculator.CalculationEngine;
import calculator.InputInterpreter;

public class Extensions {
    public static InputInterpreter buildInterpreter(CalculationEngine engine) {
        return new InputInterpreter(engine);
    }
}
