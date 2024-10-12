package persistence;

import java.io.FileNotFoundException;

import model.OrderList;

// Represent all methods used to write JSON representation of OrderList
// The sample JsonSerializationDemo is modeled in this interface and its implementation
// Citation: “Build software better, together,” GitHub. 
//            https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface WriterOrderList {

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    void open() throws FileNotFoundException;

    // MODIFIES: this
    // EFFECTS: writes JSON representation of OrderList to file
    void write(OrderList wr);

    // MODIFIES: this
    // EFFECTS: closes writer
    void close();

    // MODIFIES: this
    // EFFECTS: writes string to file
    void saveToFile(String json);
}
