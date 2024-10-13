package persistence;

import model.AllItems;
import model.Item;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.io.*;

// Represent JSON writer of AllItems
// The sample JsonSerializationDemo is modeled in this class
// Citation: “Build software better, together,” GitHub. 
//            https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterAllItems implements WriterAllItems {
    private static final int tab = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterAllItems(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    @Override
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of AllItems to file
    @Override
    public void write(AllItems items) {
        JSONObject json = new JSONObject();
        json.put("allItems", itemsToJson(items.getAllItems()));
        saveToFile(json.toString(tab));
    }

    // MODIFIES: this
    // EFFECTS: closes writer    
    @Override
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file    
    @Override
    public void saveToFile(String json) {
        writer.print(json);
        
    }

    // EFFECTS: returns items in this AllItems as a JSON array
    public JSONArray itemsToJson(ArrayList<Item> items) {
        JSONArray jsonArray = new JSONArray();
        for (Item t : items) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

}
