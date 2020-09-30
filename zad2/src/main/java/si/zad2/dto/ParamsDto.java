package si.zad2.dto;


import si.zad2.algorithms.Algorithm;

public class ParamsDto {
    int solutionsNr = 0;
    long firstSolutionTime;
    int firsNodesNr;
    int firstBacktracksNr;
    long allSolutionsTime;
    int allNodesNr;
    int allBacktracksNr;

    public ParamsDto(Algorithm algorithm) {
        solutionsNr=algorithm.getSolutionsNr();
        firstSolutionTime = algorithm.getFirstSolutionTime();
        firsNodesNr = algorithm.getFirsNodesNr();
        firstBacktracksNr = algorithm.getFirstBacktracksNr();
        allSolutionsTime = algorithm.getAllSolutionsTime();
        allNodesNr = algorithm.getAllNodesNr();
        allBacktracksNr = algorithm.getAllBacktracksNr();
    }

    public int getSolutionsNr() {
        return solutionsNr;
    }

    public void setSolutionsNr(int solutionsNr) {
        this.solutionsNr = solutionsNr;
    }

    public long getFirstSolutionTime() {
        return firstSolutionTime;
    }

    public void setFirstSolutionTime(long firstSolutionTime) {
        this.firstSolutionTime = firstSolutionTime;
    }

    public int getFirsNodesNr() {
        return firsNodesNr;
    }

    public void setFirsNodesNr(int firsNodesNr) {
        this.firsNodesNr = firsNodesNr;
    }

    public int getFirstBacktracksNr() {
        return firstBacktracksNr;
    }

    public void setFirstBacktracksNr(int firstBacktracksNr) {
        this.firstBacktracksNr = firstBacktracksNr;
    }

    public long getAllSolutionsTime() {
        return allSolutionsTime;
    }

    public void setAllSolutionsTime(long allSolutionsTime) {
        this.allSolutionsTime = allSolutionsTime;
    }

    public int getAllNodesNr() {
        return allNodesNr;
    }

    public void setAllNodesNr(int allNodesNr) {
        this.allNodesNr = allNodesNr;
    }

    public int getAllBacktracksNr() {
        return allBacktracksNr;
    }

    public void setAllBacktracksNr(int allBacktracksNr) {
        this.allBacktracksNr = allBacktracksNr;
    }

    @Override
    public String toString() {
        return "ParamsDto{" +
                "solutionsNr=" + solutionsNr +
                ", firstSolutionTime=" + firstSolutionTime +
                ", firsNodesNr=" + firsNodesNr +
                ", firstBacktracksNr=" + firstBacktracksNr +
                ", allSolutionsTime=" + allSolutionsTime +
                ", allNodesNr=" + allNodesNr +
                ", allBacktracksNr=" + allBacktracksNr +
                '}';
    }
}
