package Analyzer.Sentence;

public class SingleSentence implements Sentence {
    private String atomOne;

    public SingleSentence(String atomOne) {
        this.atomOne = atomOne;
    }

    public String getAtomOne() {
        return atomOne;
    }

    public void setAtomOne(String atomOne) {
        this.atomOne = atomOne;
    }

    @Override
    public String toString() {
        return "SingleSentence{" +
                "atomOne='" + atomOne + '\'' +
                '}';
    }
}
