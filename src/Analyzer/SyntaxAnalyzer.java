package Analyzer;

import Analyzer.Sentence.Atom;
import Analyzer.Sentence.ComplexSentence;
import Analyzer.Sentence.Sentence;
import Analyzer.Sentence.SimpleSentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxAnalyzer {

    public static Sentence analyze(String input) {
        input = input.replace(" ", "");

        //Complex sentence
        String complex = "((^)((~){0,})([A-Z]{1})((([(>)(v)(^)])){1})((~){0,})([A-Z]){1}(\\z))";
        Pattern c = Pattern.compile(complex);

        //Single sentence
        String single = "((^)((~){0,})([A-Z]{1})(\\z))";
        Pattern s = Pattern.compile(single);

        //Create matcher object for complex instruction
        Matcher matchComplexSentence = c.matcher(input);

        // Create matcher object for single instruction
        Matcher matchSingleSentence = s.matcher(input);

        if (matchComplexSentence.find()) {
            String[] atom = input.split("[(>)(v)(^)]");

            Atom atom1 = new Atom(atom[0].replace("~",""), analyseNegation(atom[0]));
            Atom atom2 = new Atom(atom[1].replace("~",""), analyseNegation(atom[1]));

            return new ComplexSentence(atom1, atom2, analyseConnective(input));

        }else if(matchSingleSentence.find()){

            Atom atom1 = new Atom(input.replace("~",""), analyseNegation(input));
            return new SimpleSentence(atom1);

        }else{
            return null;
        }
    }

    private static Connective analyseConnective(String sentence){
        if(sentence.contains(">")){
            return  Connective.implies;
        }else  if(sentence.contains("^")){
            return Connective.and;
        }else if(sentence.contains("v")){
            return Connective.or;
        }
        return null;
    }

    private static boolean analyseNegation(String sentence){
        int counter = 0;
       for(int i = 0; i<sentence.length(); i++){
            if(sentence.charAt(i) == '~'){
                counter++;
            }
       }
        if(counter == 0){
            return false;
        }else if(counter % 2 == 0){
            return false;
        }else if(counter % 2 != 0){
            return true;
        }

        return false;
    }

}
