package si.zad2.csp;

import java.util.List;
import java.util.stream.Collectors;

public class Variable {
    int row;
    int column;
    int value;
    List<Integer> domain;
    boolean isSingleton;

    public Variable(int row, int column, int value, List<Integer> domain) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.domain = domain;
        if(domain.size()==1) isSingleton=true;
    }

    public Variable(Variable other){
        this.row = other.row;
        this.column = other.column;
        this.value = other.value;
        this.domain = other.domain;
        this.isSingleton = other.isSingleton;
    }

    public void removeFromDomain(int elem){
        if(!isSingleton && elem!=value)
        domain = domain.stream().filter(d->d!=elem).collect(Collectors.toList());
    }



    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Integer> getDomain() {
        return domain;
    }

    public void setDomain(List<Integer> domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "row=" + row +
                ", column=" + column +
                ", value=" + value +
                ", domain=" + domain +
                '}';
    }
}
