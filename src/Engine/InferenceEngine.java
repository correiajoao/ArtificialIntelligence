package Engine;

import Analyzer.Sentence.ComplexSentence;
import Analyzer.Sentence.Sentence;
import Analyzer.Sentence.SimpleSentence;

import java.util.HashSet;
import java.util.Set;

public class InferenceEngine {
    public static boolean SearchFor(Set<ComplexSentence> complexSentencesSet, Set<SimpleSentence> simpleSentencesSet, Sentence conclusion){

            Boolean contaisModesPones = true;
            while (contaisModesPones){
                contaisModesPones = false;


                Set<SimpleSentence> resultFromModesPones = new HashSet<SimpleSentence>();
                for (ComplexSentence cs : complexSentencesSet) {
                    for (SimpleSentence ss : simpleSentencesSet) {
                        if (cs.getAtomOne().equals(ss.getAtomOne())) {
                            resultFromModesPones.add(new SimpleSentence(cs.getAtomTwo()));
                            contaisModesPones = true;
                        }
                    }
                }

                simpleSentencesSet.addAll(resultFromModesPones);
                if(simpleSentencesSet.contains(conclusion)) {
                    printResult(complexSentencesSet, simpleSentencesSet);
                    return true;
                }
            }

        printResult(complexSentencesSet, simpleSentencesSet);
        return false;
    }

    public static void printResult(Set<ComplexSentence> complexSentencesSet, Set<SimpleSentence> simpleSentencesSet){
        System.out.println("Resultado final da base de conhecimento: ");
        System.out.println(simpleSentencesSet.toString());
        System.out.println(complexSentencesSet.toString());
    }
}
