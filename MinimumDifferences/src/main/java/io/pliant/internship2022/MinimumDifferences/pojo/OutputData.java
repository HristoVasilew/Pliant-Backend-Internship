package io.pliant.internship2022.MinimumDifferences.pojo;

import jdk.jfr.Name;

public class OutputData {

    private Integer dMin;
    private Integer sumPnt;
    private Integer sumPwe;

    public OutputData() {
    }

    public OutputData(Integer n, Integer sumPnt, Integer sumPwe) {
        this.dMin = n;
        this.sumPnt = sumPnt;
        this.sumPwe = sumPwe;
    }

    public Integer getN() {
        return dMin;
    }

    public OutputData setN(Integer n) {
        this.dMin = n;
        return this;
    }

    public Integer getSumPnt() {
        return sumPnt;
    }

    public OutputData setSumPnt(Integer sumPnt) {
        this.sumPnt = sumPnt;
        return this;
    }

    public Integer getSumPwe() {
        return sumPwe;
    }

    public OutputData setSumPwe(Integer sumPwe) {
        this.sumPwe = sumPwe;
        return this;
    }

    @Override
    public String toString() {
        return "{ \"d.min\": " + dMin +
                ", \"s.pnt\": " + sumPnt +
                ", \"s.pwe\":"  + sumPwe +
                "}";
    }


}
