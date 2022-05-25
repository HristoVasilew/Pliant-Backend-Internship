package sequence;

public class Variables {
    private Integer M;
    private Integer N;

    public Variables(int M, int N) {
        this.setM(M);
        this.setN(N);
    }

    public Integer getM() {
        return M;
    }

    private void setM(Integer m) {
        validateVariables(m,'M');
        M = m;
    }

    public Integer getN() {
        return N;
    }

    private void setN(Integer n) {
        validateVariables(n,'N');
        N = n;
    }

    private void validateVariables(Integer number, Character symbol) {
        if (number < 1){
            throw new Error(String.format("%c cannot be negative or zero!",symbol));
        }
    }
}
