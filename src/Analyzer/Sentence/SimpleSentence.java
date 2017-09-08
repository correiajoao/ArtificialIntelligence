package Analyzer.Sentence;

public class SimpleSentence implements Sentence {
    private Atom atomOne;

    public SimpleSentence(Atom atomOne) {
        this.atomOne = atomOne;
    }

    public Atom getAtomOne() {
        return atomOne;
    }

    public void setAtomOne(Atom atomOne) {
        this.atomOne = atomOne;
    }

    @Override
    public int hashCode() {
        return atomOne.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof SimpleSentence)) {
            return false;
        }

        SimpleSentence sentence = (SimpleSentence) o;
        return sentence.getAtomOne().equals(atomOne);
    }

    @Override
    public String toString() {
        return  atomOne.toString() ;
    }
}
