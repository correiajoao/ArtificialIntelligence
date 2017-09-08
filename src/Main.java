import Analyzer.SyntaxAnalyzer;
import Analyzer.Sentence.ComplexSentence;
import Analyzer.Sentence.Sentence;
import Analyzer.Sentence.SimpleSentence;
import Engine.InferenceEngine;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String args[]){
        Set<ComplexSentence> complexSentences = new HashSet<ComplexSentence>();
        Set<SimpleSentence> simpleSentences = new HashSet<SimpleSentence>();

        Scanner input = new Scanner(System.in);

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Olá, insira novas sentenças sempre com letras maiúsculas do alfabeto. Exemplo: A(>, v, ^)B ou A.");
        System.out.println("Para finalizar a inserção de novas sentenças insira: Z.");
        System.out.println("Siga as orientações do sistema.");
        System.out.println("----------------------------------------------------------------------------------------");

        do {
            System.out.println("Insira uma nova sentença na base de conhecimento:");
            String value = input.nextLine();

            if(value.equals("Z")){
                break;
            }

            Sentence sentence = SyntaxAnalyzer.analyze(value);

            if (sentence == null) {
                System.err.println("Erro: sentença inválida.");
                System.out.println(":(");
            } else if (sentence instanceof SimpleSentence) {
                simpleSentences.add((SimpleSentence) sentence);
            } else if (sentence instanceof ComplexSentence) {
                complexSentences.add((ComplexSentence) sentence);
            }
        }while(true);

        System.out.println("Insira o estado a ser inferido:");
        String goal = input.nextLine();

        Sentence sentenceGoal = SyntaxAnalyzer.analyze(goal);

        if (sentenceGoal == null) {
            System.err.println("Erro: sentença inválida.");
        }else{
           if(InferenceEngine.SearchFor(complexSentences, simpleSentences, sentenceGoal)){
               System.out.println("O estado final foi inferido com sucesso!");
           }else{
               System.out.println("Ops, o estado final não pôde ser inferido!");
           }
        }

    }
}
