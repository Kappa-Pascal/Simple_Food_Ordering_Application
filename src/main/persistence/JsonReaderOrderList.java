package persistence;

import java.io.IOException;

import org.json.JSONObject;

import model.AllItems;

// Represent JSON reader of OrderList
// The sample JsonSerializationDemo is modeled in this class
// Citation: “Build software better, together,” GitHub. 
//            https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderOrderList implements ReaderAllItems {

    // EFFECTS: reads OrderList from file and returns it;
    // throws IOException if an error occurs reading data from file
    @Override
    public AllItems read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    @Override
    public String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses OrderList from JSON object and returns it
    @Override
    public AllItems parseWorkRoom(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: wr
    // EFFECTS: parses OrderList from JSON object and adds them to workroom
    @Override
    public void addThingies(AllItems wr, JSONObject jsonObject) {
        //stub

    }

    // MODIFIES: wr
    // EFFECTS: parses Order from JSON object and adds it to workroom
    @Override
    public void addThingy(AllItems wr, JSONObject jsonObject) {
        //stub
    }

}
