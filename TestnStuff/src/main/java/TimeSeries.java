import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TimeSeries {

    protected String filePath;
    protected ArrayList<Double> timeSeries = new ArrayList<>();

    public TimeSeries(String filePath) {
        this.filePath = filePath;
        setupTimeSeries();
    }

    private void setupTimeSeries() {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        String filePath = this.filePath;
        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;

            //Get observations
            JSONArray obsArr = (JSONArray) jsonObject.get("observations");

            obsArr.forEach(obs -> parseObservation((JSONObject) obs));

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private void parseObservation(JSONObject obs) {
        JSONArray obsArr = (JSONArray) obs.get("values");
        timeSeries.add( (Double) obsArr.get(0));
    }

    public ArrayList<Double> getTimeSeries() {
        return timeSeries;
    }
}
