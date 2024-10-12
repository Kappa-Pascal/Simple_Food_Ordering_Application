package persistence;

import java.io.FileNotFoundException;

import model.OrderList;

// Represent JSON writer of OrderList
// The sample JsonSerializationDemo is modeled in this class
// Citation: “Build software better, together,” GitHub. 
//            https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterOrderList implements WriterOrderList {
    
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    @Override
    public void open() throws FileNotFoundException {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of OrderList to file    
    @Override
    public void write(OrderList wr) {
        //stub

    }

    // MODIFIES: this
    // EFFECTS: closes writer
    @Override
    public void close() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    @Override
    public void saveToFile(String json) {
        //stub
    }

}
