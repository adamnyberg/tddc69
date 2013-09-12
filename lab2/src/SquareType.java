import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.lang.Exception;

public enum SquareType {
    I, J, L, O, S, T, Z, OUTSIDE;
    private static final List<SquareType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();

    public static SquareType getSquareType(int index)  {
        return VALUES.get(index);
    }
}