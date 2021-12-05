package ua.edu.ucu.tempseries;
import java.util.InputMismatchException;
import static java.lang.Math.abs;

public class TemperatureSeriesAnalysis {
    private int capacity = 1;
    private int size = 0;
    private double[] tempSeriesArray = new double[capacity];

    public void increaseCapacity() {
        capacity *= 2;
        double[] newTempSeriesArr = new double[capacity];
        if (size >= 0) System.arraycopy(tempSeriesArray, 0, newTempSeriesArr, 0, size);
        tempSeriesArray = newTempSeriesArr;
    }

    public void addItem(double item) {
        if (size == capacity) {
            increaseCapacity();
        }
        tempSeriesArray[size] = item;
        size++;
    }

    public TemperatureSeriesAnalysis() {}

    public void lessLowest(double[] arr) {
        for (double val: arr) {
            if (val < -273) {
                throw new InputMismatchException("All temperature values should be at least -273");
            }
        }
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        lessLowest(temperatureSeries);
        for (double temperature : temperatureSeries) {
            addItem(temperature);
        }
    }

    public double average() {
        if (size != 0) {
            double average = 0;
            for (double temperature : tempSeriesArray) {
                average += temperature;
            }
            return average / size;
        }
        throw new IllegalArgumentException("Temperature list must not be empty");
    }

    public double deviation() {
        if (size != 0) {
            double sampleAverage = average();
            double sd = 0;
            for (Double temperature : tempSeriesArray) {
                sd += (temperature - sampleAverage) * (temperature - sampleAverage);
            }
            return Math.sqrt(sd / size);
        }
        throw new IllegalArgumentException("Temperature list must not be empty");
    }

    public double minMax(int isMax) {
        if (size != 0) {
            double greatest = tempSeriesArray[0];
            double smallest = tempSeriesArray[0];
            for (int i = 1; i < size; i++) {
                double next = tempSeriesArray[i];
                if (next < smallest) {
                    smallest = next;
                }
                if (greatest < next) {
                    greatest = next;
                }
            }
            if (isMax == 1) {
                return greatest;
            }
            return smallest;
        }
        throw new IllegalArgumentException("Temperature list must not be empty");
    }

    public double min() {
        return minMax(0);
    }

    public double max() {
        return minMax(1);
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (size != 0) {
            int closestIdx = 0;
            double closest = abs(tempSeriesArray[0] - tempValue);
            for (int i = 1; i < size; i++) {
                double next = abs(tempSeriesArray[i] - tempValue);
                if (next < closest) {
                    closest = next;
                    closestIdx = i;
                }
            }
            return tempSeriesArray[closestIdx];
        }
        throw new IllegalArgumentException("Temperature list must not be empty");
    }

    public double[] findTemps(int greater, double tempValue) {
        double[] tempList = new double[size];
        int elNum = 0;
        if (size != 0) {
            for (double next : tempSeriesArray) {
                if (greater == 0) {
                    if (next < tempValue) {
                        tempList[elNum] = next;
                        elNum++;
                    }
                }
                if (greater == 1) {
                    if (next > tempValue) {
                        tempList[elNum] = next;
                        elNum++;
                    }
                }
            }
            double [] correctTempList = new double[elNum];
            System.arraycopy(tempList, 0, correctTempList, 0, elNum);
            return correctTempList;
        }
        throw new IllegalArgumentException("Temperature list must not be empty");
    }

    public double[] findTempsLessThen(double tempValue) {
        return findTemps(0, tempValue);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return findTemps(1, tempValue);
    }

    public TempSummaryStatistics summaryStatistics() {
        if (size != 0) {
            return new TempSummaryStatistics(average(), deviation(), min(), max());
        }
        throw new IllegalArgumentException("Temperature list must not be empty");
    }

    public int addTemps(double... temps) {
        lessLowest(temps);
        for (double temp : temps) {
            addItem(temp);
        }
        return size;
    }
}
