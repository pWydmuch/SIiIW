package lab1.dto;

public class FormDto {
    String instance;
    String selection;
    String cross;
    String mutation;
    int popSize;
    int genNr;
    int tourSize;
    double crossProb;
    double mutProb;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getCross() {
        return cross;
    }

    public void setCross(String cross) {
        this.cross = cross;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public int getPopSize() {
        return popSize;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public int getGenNr() {
        return genNr;
    }

    public void setGenNr(int genNr) {
        this.genNr = genNr;
    }

    public int getTourSize() {
        return tourSize;
    }

    public void setTourSize(int tourSize) {
        this.tourSize = tourSize;
    }

    public double getCrossProb() {
        return crossProb;
    }

    public void setCrossProb(double crossProb) {
        this.crossProb = crossProb;
    }

    public double getMutProb() {
        return mutProb;
    }

    public void setMutProb(double mutProb) {
        this.mutProb = mutProb;
    }

    @Override
    public String toString() {
        return "FormDto{" +
                "instance='" + instance + '\'' +
                ", selection='" + selection + '\'' +
                ", cross='" + cross + '\'' +
                ", mutation='" + mutation + '\'' +
                ", popSize=" + popSize +
                ", genNr=" + genNr +
                ", tourSize=" + tourSize +
                ", crossProb=" + crossProb +
                ", mutProb=" + mutProb +
                '}';
    }
}
