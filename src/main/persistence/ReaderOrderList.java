package persistence;

import java.io.IOException;

import org.json.JSONObject;

import model.OrderList;

// Represent all methods used to read JSON representation of OrderList
// The sample JsonSerializationDemo is modeled in this interface and its implementation
// Citation: “Build software better, together,” GitHub. 
//            https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface ReaderOrderList {

    // EFFECTS: reads OrderList from file and returns it;
    // throws IOException if an error occurs reading data from file
    OrderList read() throws IOException;

    // EFFECTS: reads source file as string and returns it
    String readFile(String source) throws IOException;

    // EFFECTS: parses OrderList from JSON object and returns it
    OrderList parseOrderList(JSONObject jsonObject);

    // MODIFIES: wr
    // EFFECTS: parses OrderList from JSON object and adds them to workroom
    void addOrderLists(OrderList wr, JSONObject jsonObject);

    // MODIFIES: wr
    // EFFECTS: parses Order from JSON object and adds it to workroom
    void addOrderList(OrderList wr, JSONObject jsonObject);

}