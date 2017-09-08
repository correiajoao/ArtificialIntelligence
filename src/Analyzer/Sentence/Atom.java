package Analyzer.Sentence;

public class Atom {
    private String simbol;
    private boolean negation;

    public Atom(String simbol, boolean negation) {
        this.simbol = simbol;
        this.negation = negation;
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

    public boolean isNegation() {
        return negation;
    }

    public void setNegation(boolean negation) {
        this.negation = negation;
    }

    @Override
    public int hashCode() {
        return simbol.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Atom)) {
            return false;
        }

        Atom atom = (Atom) o;
        return atom.getSimbol().equals(simbol) && atom.isNegation() == negation;
    }

    @Override
    public String toString() {
        return "{'" + simbol + '\'' +
                ", negation=" + negation +
                '}';
    }
}
