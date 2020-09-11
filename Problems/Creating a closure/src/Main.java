import java.util.function.UnaryOperator;

class PrefixSuffixOperator {

    public static final String PREFIX = "__pref__";
    public static final String SUFFIX = "__suff__";

    public static UnaryOperator<String> operator = x -> {
    String aux = x.trim();
    return PREFIX + aux + SUFFIX;
    };
    // write your code here
}