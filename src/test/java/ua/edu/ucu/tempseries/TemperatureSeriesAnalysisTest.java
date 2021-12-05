package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }


    @Test
    public void testSDWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;

        // call tested method
        double actualResult = seriesAnalysis.deviation();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSDWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.deviation();
    }

    @Test
    public void testSD() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.74165738;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMaxMinWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 2.0;

        // call tested method
        double minActualResult = seriesAnalysis.min();
        double maxActualResult = seriesAnalysis.max();

        // compare expected result with actual result
        assertEquals(expResult, minActualResult, 0.00001);
        assertEquals(expResult, maxActualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxMinWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.max();
        seriesAnalysis.min();
    }

    @Test
    public void testMaxMin() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double minExpResult = -5.0;
        double maxExpResult = 5.0;

        double minActualResult = seriesAnalysis.min();
        double maxActualResult = seriesAnalysis.max();

        assertEquals(minExpResult, minActualResult, 0.00001);
        assertEquals(maxExpResult, maxActualResult, 0.00001);
    }

    @Test
    public void testFindWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 2.0;

        // call tested method
        double[] lessArr = seriesAnalysis.findTempsLessThen(3);

        // compare expected result with actual result
        assertArrayEquals(new double []{2.0}, lessArr, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.findTempsLessThen(0);
    }

    @Test
    public void testFind() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] lessArrExp = new double []{-5.0, 1.0};
        double[] greaterArrExp = new double []{3.0, 5.0};

        double[] lessArrAct = seriesAnalysis.findTempsLessThen(2);
        double[] greaterArrAct = seriesAnalysis.findTempsGreaterThen(2.0);

        assertArrayEquals(lessArrExp, lessArrAct, 0.00001);
        assertArrayEquals(greaterArrExp, greaterArrAct, 0.00001);
    }


    @Test
    public void testFindClosestWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 2.0;

        // call tested method
        double closest = seriesAnalysis.findTempClosestToValue(temperatureSeries[0]);

        // compare expected result with actual result
        assertEquals(expResult, closest, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindClosestWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.max();
        seriesAnalysis.min();
    }

    @Test
    public void testFindClosest() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double closestToZeroExp = 1.0;
        double closestToTwoExp = 3.0;

        double closestToZeroAct = seriesAnalysis.findTempClosestToZero();
        double closestToTwoAct = seriesAnalysis.findTempClosestToValue(2.0);

        assertEquals(closestToZeroExp, closestToZeroAct, 0.00001);
        assertEquals(closestToTwoExp, closestToTwoAct, 0.00001);
    }


    @Test
    public void testSummary() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics summary = new TempSummaryStatistics(seriesAnalysis.average(), seriesAnalysis.deviation(),
                seriesAnalysis.min(), seriesAnalysis.max());

        double averageExp = 1.0;
        double sdExp = 3.7416573867739413;
        double minExp = -5.0;
        double maxExp = 5.0;

        assertEquals(averageExp, summary.avgTemp, 0.00001);
        assertEquals(sdExp, summary.devTemp, 0.00001);
        assertEquals(minExp, summary.minTemp, 0.00001);
        assertEquals(maxExp, summary.maxTemp, 0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        int count = seriesAnalysis.addTemps(temperatureSeries);

        assertEquals(4, count);
    }
}



