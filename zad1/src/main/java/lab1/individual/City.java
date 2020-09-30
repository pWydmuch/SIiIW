package lab1.individual;

import java.util.Objects;

public class City {

    private int number;
    private double x;
    private double y;


    public City() {
    }

    public City(int number, double x, double y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }


    public double countDistanceBetweenCities(City b){
        double xDistance = Math.pow(getX()-b.getX(),2);
        double yDistance = Math.pow(getY()-b.getY(),2);
        return Math.sqrt( xDistance + yDistance);
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "lab1.individual.City{" +
                "number=" + number +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return number == city.number;

    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
