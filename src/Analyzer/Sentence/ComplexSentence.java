package Analyzer.Sentence;

import Analyzer.Connective;

public class ComplexSentence implements Sentence {
    private Atom atomOne;
    private Atom atomTwo;
    private Connective connective;

    public ComplexSentence(Atom atomOne, Atom atomTwo, Connective connective) {
        this.atomOne = atomOne;
        this.atomTwo = atomTwo;
        this.connective = connective;
    }

    public Atom getAtomOne() {
        return atomOne;
    }

    public void setAtomOne(Atom atomOne) {
        this.atomOne = atomOne;
    }

    public Atom getAtomTwo() {
        return atomTwo;
    }

    public void setAtomTwo(Atom atomTwo) {
        this.atomTwo = atomTwo;
    }

    public Connective getConnective() {
        return connective;
    }

    public void setConnective(Connective connective) {
        this.connective = connective;
    }

    @Override
    public int hashCode() {
        return atomOne.hashCode() + atomTwo.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof ComplexSentence)) {
            return false;
        }

        ComplexSentence sentence = (ComplexSentence) o;
        return sentence.getAtomOne().equals(atomOne) && sentence.getAtomTwo().equals(atomTwo) && sentence.getConnective() == this.connective;
    }

    @Override
    public String toString() {
        return  atomOne.toString()  + connective + atomTwo.toString();
    }
}
