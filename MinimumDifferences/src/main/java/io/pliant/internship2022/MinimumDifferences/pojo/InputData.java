package io.pliant.internship2022.MinimumDifferences.pojo;

import java.util.Arrays;

public class InputData {
    private Integer n;
    private int[] pnt;
    private int[] pwe;

    public InputData() {
    }

    public InputData(Integer n, int[] pnt, int[] pwe) {
        this.n = n;
        this.pnt = pnt;
        this.pwe = pwe;
    }

    public Integer getN() {
        return n;
    }

    public InputData setN(Integer n) {
        this.n = n;
        return this;
    }

    public int[] getPnt() {
        return pnt;
    }

    public InputData setPnt(int[] pnt) {
        this.pnt = pnt;
        return this;
    }

    public int[] getPwe() {
        return pwe;
    }

    public InputData setPwe(int[] pwe) {
        this.pwe = pwe;
        return this;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "n=" + n +
                ", pnt=" + Arrays.toString(pnt) +
                ", pwe=" + Arrays.toString(pwe) +
                '}';
    }
}
