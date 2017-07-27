package Analyzer.Sentence;

public class ComplexSentence implements Sentence {
    private String atomOne;
    private String atomTwo;
    private String operator;

    public ComplexSentence(String atomOne, String atomTwo, String operator) {
        this.atomOne = atomOne;
        this.atomTwo = atomTwo;
        this.operator = operator;
    }

    public String getAtomOne() {
        return atomOne;
    }

    public void setAtomOne(String atomOne) {
        this.atomOne = atomOne;
    }

    public String getAtomTwo() {
        return atomTwo;
    }

    public void setAtomTwo(String atomTwo) {
        this.atomTwo = atomTwo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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
        return sentence.getAtomOne().equals(atomOne) && sentence.getAtomTwo().equals(atomTwo);
    }

    @Override
    public String toString() {
        return  atomOne  + operator + atomTwo;
    }
}
