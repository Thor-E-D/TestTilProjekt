
public class Test {

    public static void main(String[] args) {
        TimeSeries timeSeries = new TimeSeries("C:\\Users\\thore\\Desktop\\4 semester\\Data\\OneDim\\1Breaks_1K.json");
        timeSeries.getTimeSeries().forEach(obs -> System.out.println(obs));
    }

}
