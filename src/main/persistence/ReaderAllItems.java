package persistence;

import java.io.IOException;

import org.json.JSONObject;

import model.AllItems;

// Represent all methods used to read JSON representation of AllItems
// The sample JsonSerializationDemo is modeled in this interface and its implementation
// Citation: “Build software better, together,” GitHub. 
//            https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface ReaderAllItems {

    // EFFECTS: reads AllItems from file and returns it;
    // throws IOException if an error occurs reading data from file
    AllItems read() throws IOException;

    // EFFECTS: reads source file as string and returns it
    String readFile(String source) throws IOException;

    // EFFECTS: parses AllItems from JSON object and returns it
    AllItems parseOrderList(JSONObject jsonObject);

    // MODIFIES: wr
    // EFFECTS: parses AllItems from JSON object and adds them to workroom
    void addItems(AllItems wr, JSONObject jsonObject);

    // MODIFIES: wr
    // EFFECTS: parses Item from JSON object and adds it to workroom
    void addItem(AllItems wr, JSONObject jsonObject);

}
