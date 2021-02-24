import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TimeSeries {

    protected String filePath;
    protected ArrayList<Point> timeSeries = new ArrayList<>();

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
        Point p = new Point();
        JSONArray obsArr = (JSONArray) obs.get("values");
        p.setLocation((Double) obs.get("time"),(Double) obsArr.get(0));
        timeSeries.add(p);
    }

    public ArrayList<Point> getTimeSeries() {
        return timeSeries;
    }
}
