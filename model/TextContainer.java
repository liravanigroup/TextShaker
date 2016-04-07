package pl.com.bottega.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 05.04.16.
 */
public class TextContainer {

    String regExp = "[A-Za-zА-Яа-яąęłśźżóć]{4,}";
    String entryText;
    boolean isTransformed = false;

    public TextContainer(String entryText) {
        this.entryText = entryText;
    }

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }

    public String getWarpedText() {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(entryText);
        StringBuffer outTextBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(outTextBuffer, getOutWordWithMixedLetter(matcher.group()));
        }
        matcher.appendTail(outTextBuffer);
        return outTextBuffer.toString();
    }

    public String getOutWordWithMixedLetter(String wordToMix) {
        char[] inWord = wordToMix.toCharArray();
        return getImplodedOutWord(getCharArrayOfOutWordLetters(inWord, getNewCharIndexes(inWord)));
    }

    private List<Integer> getNewCharIndexes(char[] inWord) {
        Random random = new Random();
        List<Integer> charIndexes = new ArrayList<>();
        do {
            int newIndex = random.nextInt(inWord.length - 1);
            if (newIndex == 0) {
                continue;
            } else if (!charIndexes.contains(newIndex)) {
                charIndexes.add(newIndex);
            }
        } while (charIndexes.size() != inWord.length - 2);
        return charIndexes;
    }

    private char[] getCharArrayOfOutWordLetters(char[] inWord, List<Integer> charIndexes) {
        char[] outWord = new char[inWord.length];
        outWord[0] = inWord[0];
        outWord[outWord.length - 1] = inWord[inWord.length - 1];
        int nextChar = 1;
        for (int charIndex : charIndexes) {
            outWord[nextChar++] = inWord[charIndex];
        }
        return outWord;
    }

    private String getImplodedOutWord(char[] outWord) {
        StringBuilder buildWord = new StringBuilder();
        for (char character : outWord)
            buildWord.append(character);
        return buildWord.toString();
    }


}
