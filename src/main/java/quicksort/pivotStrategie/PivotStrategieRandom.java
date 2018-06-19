package quicksort.pivotStrategie;

import quicksort.SchluesselWertPaar;

import java.util.Random;

/**
 * @author ${user} on ${date}
 */
public class PivotStrategieRandom implements PivotStrategie{
    @Override
    public <T extends Comparable<T>, U> int getIndex(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {

        int pivotIndex;
        Random random = new Random();

        do{

            pivotIndex = random.nextInt(iRechts+1);

        } while(pivotIndex <= iLinks);

        return pivotIndex;
    }
}