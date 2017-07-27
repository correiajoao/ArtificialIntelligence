package Engine;

import Analyzer.Sentence.ComplexSentence;
import Analyzer.Sentence.Sentence;
import Analyzer.Sentence.SingleSentence;

import javax.swing.text.StyledEditorKit;
import java.util.HashSet;
import java.util.Set;

public class InferenceEngine {
    public static boolean SearchFor(Set<ComplexSentence> complexSentencesSet, Set<SingleSentence> singleSentencesSet, Sentence conclusion){

            Set<SingleSentence> resultFromModesPones = new HashSet<SingleSentence>();
            for (ComplexSentence cs : complexSentencesSet) {
                for (SingleSentence ss : singleSentencesSet) {
                    if (cs.getAtomOne().equals(ss.getAtomOne())) {
                        resultFromModesPones.add(new SingleSentence(cs.getAtomTwo()));
                    }
                }
            }

            singleSentencesSet.addAll(resultFromModesPones);

            System.out.println(complexSentencesSet.toString());
            System.out.println(singleSentencesSet.toString());

        return false;
    }
}
