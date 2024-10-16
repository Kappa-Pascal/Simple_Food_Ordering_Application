package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.AllItems;
import model.Item;

// Represent JSON reader of AllItems
// The sample JsonSerializationDemo is modeled in this class
// Citation: “Build software better, together,” GitHub. 
//            https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderAllItems implements ReaderAllItems {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderAllItems(String source) {
        this.source = source;
    }

    // EFFECTS: reads AllItems from file and returns it;
    // throws IOException if an error occurs reading data from file    
    @Override
    public AllItems read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOrderList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    @Override
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses AllItems from JSON object and returns it
    @Override
    public AllItems parseOrderList(JSONObject jsonObject) {
        AllItems item = new AllItems();
        addItems(item, jsonObject);
        return item;
    }

    // MODIFIES: item
    // EFFECTS: parses AllItems from JSON object to multiple items and adds them to AllItems
    @Override
    public void addItems(AllItems item, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allItems");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(item, nextItem);
        }
    }

    // MODIFIES: item
    // EFFECTS: parses Item from JSON object and adds it to AllItems
    @Override
    public void addItem(AllItems items, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        //Category category = Category.valueOf(jsonObject.getString("category"));
        //Thingy thingy = new Thingy(name, category);
        double price = jsonObject.getDouble("price");
        int stockAmount = jsonObject.getInt("stockAmount");
        int orderAmount = jsonObject.getInt("orderAmount");
        Item item = new Item(name, price, stockAmount);
        item.setOrderAmount(orderAmount);
        items.addItem(item);        
    }

}
