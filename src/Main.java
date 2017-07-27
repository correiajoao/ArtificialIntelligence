import Analyzer.SyntaxAnalyzer;
import Analyzer.Sentence.ComplexSentence;
import Analyzer.Sentence.Sentence;
import Analyzer.Sentence.SingleSentence;
import Engine.InferenceEngine;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String args[]){
        Set<ComplexSentence> complexSentences = new HashSet<ComplexSentence>();
        Set<SingleSentence> singleSentences = new HashSet<SingleSentence>();

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Insert a new sentence:");
            String value = input.nextLine();

            if(value.equals("X")){
                break;
            }

            Sentence sentence = SyntaxAnalyzer.analyze(value);

            if (sentence == null) {
                System.err.println("Error: Wrong instruction");
            } else if (sentence instanceof SingleSentence) {
                singleSentences.add((SingleSentence) sentence);
            } else if (sentence instanceof ComplexSentence) {
                complexSentences.add((ComplexSentence) sentence);
            }

        }while(true);

        //System.out.println(singleSentences.toString());
        //System.out.println(complexSentences.toString());

        System.out.println("Insert the goal:");
        String goal = input.nextLine();

        Sentence sentenceGoal = SyntaxAnalyzer.analyze(goal);

        if (sentenceGoal == null) {
            System.err.println("Error: Wrong instruction");
        }else{
            InferenceEngine.SearchFor(complexSentences,singleSentences, sentenceGoal);
        }

    }
}
