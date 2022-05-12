package dev.mateusneres.scaling.utils;

import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CharsetUtil {

    public static String convertToBinary(String input, String encoding) {
        byte[] encoded_input = Charset.forName(encoding)
                .encode(input)
                .array();
        return IntStream.range(0, encoded_input.length)
                .map(i -> encoded_input[i])
                .mapToObj(e -> Integer.toBinaryString(e ^ 255))
                .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
                .collect(Collectors.joining(" "));
    }

}
