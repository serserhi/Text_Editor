import java.util.function.*;


class Operator {

    public static LongBinaryOperator binaryOperator = (x, y) ->
    {long i = 1;
    while (x <= y) {
        i = i * x;
        x++;
    }
        return i;
    };
}