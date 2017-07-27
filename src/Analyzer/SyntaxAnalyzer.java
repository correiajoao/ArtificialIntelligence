package Analyzer;

import Analyzer.Sentence.ComplexSentence;
import Analyzer.Sentence.Sentence;
import Analyzer.Sentence.SimpleSentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxAnalyzer {

    public static Sentence analyze(String input) {
        input = input.replace(" ", "");

        //Complex sentence
        String complex = "((^)([A-Z]{1})((->){1})([A-Z]){1}(\\z))";
        Pattern c = Pattern.compile(complex);

        //Single sentence
        String single = "((^)([A-Z]{1})(\\z))";
        Pattern s = Pattern.compile(single);

        //Create matcher object for complex instruction
        Matcher matchComplexSentence = c.matcher(input);

        // Create matcher object for single instruction
        Matcher matchSingleSentence = s.matcher(input);

        if (matchComplexSentence.find()) {
            String[] atom = input.split("->");
            if (atom.length == 2 && (atom[0].equals(atom[1]))) {
                return null;
            } else {
                return new ComplexSentence(atom[0], atom[1], "->");
            }
        }else if(matchSingleSentence.find()){
            return new SimpleSentence(input);
        }else{
            return null;
        }
    }
}
