package Engine;

import Analyzer.Connective;
import Analyzer.Sentence.Atom;
import Analyzer.Sentence.ComplexSentence;
import Analyzer.Sentence.Sentence;
import Analyzer.Sentence.SimpleSentence;

import java.util.HashSet;
import java.util.Set;

public class InferenceEngine {
    private static Set<ComplexSentence> complexSet = new HashSet<ComplexSentence>();
    private static Set<SimpleSentence> simpleSet = new HashSet<SimpleSentence>();
    private static Sentence conclusion;


    //This method inserts in the complex senteces all its equivalences
    // P > Q <> ~P v Q
    // P ^ Q <> Q ^ P
    // P v Q <> Q v P
    public static void generateEquivalences() {
        boolean rule1 = true, rule2 = true, rule3 = true, rule4 = true;

        while(rule1 || rule2 || rule3 || rule4) {

            rule1 = false;
            Set<ComplexSentence> result = new HashSet<ComplexSentence>();
            for (ComplexSentence cs : complexSet) {
                if (cs.getConnective() == Connective.implies) {
                    Atom atom1 = new Atom(cs.getAtomOne().getSimbol(), true);
                    Atom atom2 = new Atom(cs.getAtomTwo().getSimbol(), false);
                    ComplexSentence equivalence = new ComplexSentence(atom1, atom2, Connective.or);
                    result.add(equivalence);

                    if(!complexSet.contains(equivalence))
                        rule1 = true;
                }
            }

            complexSet.addAll(result);

            //System.out.println("Equivalences");
            rule4 = false;
            result = new HashSet<ComplexSentence>();
            for (ComplexSentence cs : complexSet) {
                if(cs.getConnective() == Connective.or) {
                    Atom atom1 = new Atom(cs.getAtomOne().getSimbol(), !cs.getAtomOne().isNegation());
                    Atom atom2 = new Atom(cs.getAtomTwo().getSimbol(), cs.getAtomTwo().isNegation());

                    ComplexSentence equivalence = new ComplexSentence(atom1, atom2, Connective.implies);
                    result.add(equivalence);

                    if (!complexSet.contains(equivalence))
                        rule4 = true;
                }
            }


            complexSet.addAll(result);

            rule2 = false;
            result = new HashSet<ComplexSentence>();
            for (ComplexSentence cs : complexSet) {
                if (cs.getConnective() == Connective.and) {
                    Atom atom1 = new Atom(cs.getAtomOne().getSimbol(), cs.getAtomOne().isNegation());
                    Atom atom2 = new Atom(cs.getAtomTwo().getSimbol(), cs.getAtomTwo().isNegation());

                    ComplexSentence equivalence = new ComplexSentence(atom2, atom1, Connective.and);
                    result.add(equivalence);

                    if(!complexSet.contains(equivalence))
                        rule2 = true;
                }
            }

            complexSet.addAll(result);

            rule3 = false;
            result = new HashSet<ComplexSentence>();
            for (ComplexSentence cs : complexSet) {
                if (cs.getConnective() == Connective.or) {
                    Atom atom1 = new Atom(cs.getAtomOne().getSimbol(), cs.getAtomOne().isNegation());
                    Atom atom2 = new Atom(cs.getAtomTwo().getSimbol(), cs.getAtomTwo().isNegation());

                    ComplexSentence equivalence = new ComplexSentence(atom2, atom1, Connective.or);
                    result.add(equivalence);

                    if(!complexSet.contains(equivalence))
                        rule3 = true;
                }
            }

            complexSet.addAll(result);
        }

    }

    public static boolean modusPones(){
        boolean isApplied = false;

        Set<SimpleSentence> result = new HashSet<SimpleSentence>();
        for (ComplexSentence cs : complexSet) {
            for (SimpleSentence ss : simpleSet) {
                if (cs.getAtomOne().equals(ss.getAtomOne()) && cs.getConnective() == Connective.implies) {

                    //System.out.println("Modus pones:" + cs.toString() + ss.toString());

                    Atom atom1 = new Atom(cs.getAtomTwo().getSimbol(), cs.getAtomTwo().isNegation());
                    SimpleSentence inference = new SimpleSentence(atom1);
                    result.add(inference);

                    if(!simpleSet.contains(inference))
                        isApplied = true;
                }
            }
        }
        simpleSet.addAll(result);
        return isApplied;
    }

    public static boolean andIntroduction(){
        boolean isApplied = false;

        Set<ComplexSentence> result = new HashSet<ComplexSentence>();
        for (SimpleSentence ss : simpleSet) {
            for (SimpleSentence ssa : simpleSet) {
                Atom atom1 = new Atom(ss.getAtomOne().getSimbol(), ss.getAtomOne().isNegation());
                Atom atom2 = new Atom(ssa.getAtomOne().getSimbol(), ssa.getAtomOne().isNegation());

                ComplexSentence inference = new ComplexSentence(atom1, atom2, Connective.and);
                result.add(inference);

                if(!complexSet.contains(inference))
                    isApplied = true;
            }
        }
        complexSet.addAll(result);
        return isApplied;
    }

    public static boolean andElimination(){
        boolean isApplied = false;

        Set<SimpleSentence> result = new HashSet<SimpleSentence>();
        for (ComplexSentence cs : complexSet) {
            if(cs.getConnective() == Connective.and){

            Atom atom1 = new Atom(cs.getAtomOne().getSimbol(), cs.getAtomOne().isNegation());
            SimpleSentence inference = new SimpleSentence(atom1);
            result.add(inference);

            if(!simpleSet.contains(inference))
                    isApplied = true;
            }
        }
        simpleSet.addAll(result);
        return isApplied;
    }

    public static void doubleNegation(){
        //This rule is guaranted by the SyntaxAnalyser
    }

    public static boolean unitResolution(){
        boolean isApplied = false;

        Set<SimpleSentence> result = new HashSet<SimpleSentence>();
        for (ComplexSentence cs : complexSet) {
            for (SimpleSentence ss : simpleSet) {
                if (cs.getAtomTwo().getSimbol().equals(ss.getAtomOne().getSimbol()) && cs.getAtomTwo().isNegation() != ss.getAtomOne().isNegation() && cs.getConnective() == Connective.or) {

                    Atom atom1 = new Atom(cs.getAtomOne().getSimbol(), cs.getAtomOne().isNegation());
                    SimpleSentence inference = new SimpleSentence(atom1);
                    result.add(inference);

                    if(!simpleSet.contains(inference))
                        isApplied = true;
                }
            }
        }
        simpleSet.addAll(result);
        return isApplied;
    }

    public static boolean resolution(){
        boolean isApplied = false;

        Set<ComplexSentence> result = new HashSet<ComplexSentence>();
        for (ComplexSentence cs : complexSet) {
            for (ComplexSentence csa : complexSet) {
                if (cs.getAtomTwo().getSimbol().equals(csa.getAtomOne().getSimbol()) && cs.getAtomTwo().isNegation() != csa.getAtomOne().isNegation() && cs.getConnective() == Connective.or) {

                    Atom atom1 = new Atom(cs.getAtomOne().getSimbol(), cs.getAtomOne().isNegation());
                    Atom atom2 = new Atom(csa.getAtomTwo().getSimbol(), cs.getAtomOne().isNegation());

                    ComplexSentence inference = new ComplexSentence(atom1, atom2, Connective.or);
                    result.add(inference);

                    if(!complexSet.contains(inference))
                        isApplied = true;
                }
            }
        }
        complexSet.addAll(result);
        return isApplied;
    }

    public static boolean SearchFor(Set<ComplexSentence> complexSentencesSet, Set<SimpleSentence> simpleSentencesSet, Sentence conclusion){
        boolean modusPones = true, andIntroduction = true, andElimination = true, unitResolution = true, resolution = true;

        complexSet = complexSentencesSet;
        simpleSet = simpleSentencesSet;

        //while (modusPones || andElimination || andIntroduction || unitResolution || resolution){
        while (modusPones || andElimination || andIntroduction || unitResolution || resolution ){
            generateEquivalences();

            modusPones = modusPones();
            andIntroduction = andIntroduction();
            andElimination = andElimination();
            unitResolution = unitResolution();
            resolution = resolution();

            if(conclusion instanceof ComplexSentence){
                if(complexSet.contains((ComplexSentence) conclusion)){
                    printResult();
                    return true;
                }
            }else if(conclusion instanceof SimpleSentence){
                if(simpleSet.contains((SimpleSentence) conclusion)){
                    printResult();
                    return true;
                }
            }
        }

        printResult();
        return false;
    }

    public static void printResult(){
        System.out.println("Resultado final da base de conhecimento: ");
        System.out.println("Sentenças simples: " + simpleSet.toString());
        System.out.println("Sentenças complexas: " +complexSet.toString());
    }
}
