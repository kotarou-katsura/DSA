import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

class Huffman {
    static int charIndex = 0;

    public static void printCode(HuffmanNode root, String s, char[] charArray, String[] codeArray) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            charArray[charIndex] = root.character;
            codeArray[charIndex] = s;
            int asciiCode = root.character;
            System.out.println(root.character + ": " + s + " (ASCII: " + asciiCode + ")");
            charIndex++;
        }
        printCode(root.left, s + "0", charArray, codeArray);
        printCode(root.right, s + "1", charArray, codeArray);
    }

    public static int findCharIndex(char[] array, char target) {
        if (array.length == 0) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) return i;
        }
        return -1;
    }

    public static void encoder(String filePath) {
        int n = 0;
        char[] charArray = new char[94];
        int[] charFrequencies = new int[94];
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int charValue;
            while ((charValue = reader.read()) != -1) {
                char target = (char) charValue;
                text.append(target);
                int index = findCharIndex(charArray, target);
                if (index != -1) charFrequencies[index] += 1;
                else {
                    charArray[n] = target;
                    charFrequencies[n] = 1;
                    n++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error: Unable to read the file. Details: " + e.getMessage());
            return;
        }
        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator());
        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.character = charArray[i];
            hn.data = charFrequencies[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }
        HuffmanNode root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.character = '-';
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }
        char[] newCharArray = new char[n];
        String[] code = new String[n];
        printCode(root, "", newCharArray, code);
        try (FileWriter writer = new FileWriter("test.txt")) {
            StringBuilder encodedText = new StringBuilder();
            StringBuilder informationFC = new StringBuilder();
            for (int i = 0; i < newCharArray.length; i++) {
                switch (newCharArray[i]) {
                    case ' ': {
                        informationFC.append("\\s");
                        break;
                    }
                    case '\n': {
                        informationFC.append("\\n");
                        break;
                    }
                    case '\t': {
                        informationFC.append("\\t");
                        break;
                    }
                    case '\r': {
                        informationFC.append("\\r");
                        break;
                    }
                    case '\b': {
                        informationFC.append("\\b");
                        break;
                    }
                    case '\f': {
                        informationFC.append("\\f");
                        break;
                    }
                    case '\"': {
                        informationFC.append("\\\"");
                        break;
                    }
                    case '\'': {
                        informationFC.append("\\'");
                        break;
                    }
                    case '\\': {
                        informationFC.append("\\\\");
                        break;
                    }
                    default: {
                        informationFC.append(newCharArray[i]);
                        break;
                    }
                }
                informationFC.append(" ").append(charFrequencies[findCharIndex(charArray, newCharArray[i])]).append(",");
            }
            for (int i = 0; i < text.length(); i++) {
                char currentChar = text.charAt(i);
                int index = -1;
                for (int j = 0; j < newCharArray.length; j++) {
                    if (newCharArray[j] == currentChar) {
                        index = j;
                        break;
                    }
                }
                encodedText.append(code[index]);
            }
            writer.write(informationFC.toString());
            writer.write("\n");
            writer.write(encodedText.toString());
            System.out.println("Success: File has been encoded and written successfully.");
        } catch (IOException e) {
            System.err.println("Error: Unable to write to the file. Details: " + e.getMessage());
        }
    }

    public static void decoder(String filePath) {
        boolean isFirstNewLine = true;
        int n = 0;
        char[] charArray = new char[94];
        int[] charFrequencies = new int[94];
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int charValue;
            while ((charValue = reader.read()) != -1) {
                char target = (char) charValue;
                if (charValue == '\\') {
                    target = (char) reader.read();
                    switch (target) {
                        case 's': {
                            target = ' ';
                            break;
                        }
                        case 'n': {
                            target = '\n';
                            break;
                        }
                        case 't': {
                            target = '\t';
                            break;
                        }
                        case 'r': {
                            target = '\r';
                            break;
                        }
                        case 'b': {
                            target = '\b';
                            break;
                        }
                        case 'f': {
                            target = '\f';
                            break;
                        }
                        case '\"': {
                            target = '\"';
                            break;
                        }
                        case '\'': {
                            target = '\'';
                            break;
                        }
                        case '\\': {
                            target = '\\';
                            break;
                        }
                        default: {
                            System.err.println("Warning: Unrecognized escape sequence: \\" + target);
                            break;
                        }
                    }
                    charValue = target;
                    String number = "";
                    int peekCharValue = reader.read();
                    if (peekCharValue == ' ') {
                        charArray[n] = target;
                        charFrequencies[n] = 0;
                        do {
                            peekCharValue = reader.read();
                            if (peekCharValue == ' ' || peekCharValue == ',') break;
                            else number += (char) peekCharValue;
                        } while (true);
                        charFrequencies[n] = Integer.parseInt(number);
                        n++;
                    }
                } else if (isFirstNewLine && charValue >= 33 && charValue <= 126) {
                    String number = "";
                    int peekCharValue = reader.read();
                    if (peekCharValue == ' ') {
                        charArray[n] = target;
                        charFrequencies[n] = 0;
                        do {
                            peekCharValue = reader.read();
                            if (peekCharValue == ' ' || peekCharValue == ',') break;
                            else number += (char) peekCharValue;
                        } while (true);
                        charFrequencies[n] = Integer.parseInt(number);
                        n++;
                    }
                } else {
                    if (isFirstNewLine) isFirstNewLine = false;
                    else {
                        text.append(target);
                        isFirstNewLine = false;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error: Unable to read the file. Details: " + e.getMessage());
            return;
        }
        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator());
        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.character = charArray[i];
            hn.data = charFrequencies[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }
        HuffmanNode root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.character = '-';
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }
        char[] newCharArray = new char[n];
        String[] code = new String[n];
        printCode(root, "", newCharArray, code);
        try (FileWriter writer = new FileWriter("test.txt")) {
            StringBuilder decodedText = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                boolean flag = false;
                String temp = String.valueOf(text.charAt(i));
                do {
                    for (int j = 0; j < n; j++) {
                        if (Objects.equals(code[j], temp)) {
                            flag = true;
                            decodedText.append(newCharArray[j]);
                            break;
                        }
                    }
                    if (!flag) temp += String.valueOf(text.charAt(++i));
                } while (!flag);
            }
            writer.write(decodedText.toString());
            System.out.println("Success: File has been decoded and written successfully.");
        } catch (IOException e) {
            System.err.println("Error: Unable to write to the file. Details: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "test.txt";
        encoder(filePath);
//        decoder(filePath);
    }
}

class HuffmanNode {
    int data;
    char character;
    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        if (x.data != y.data) return x.data - y.data;
        return x.character - y.character;
    }
}
