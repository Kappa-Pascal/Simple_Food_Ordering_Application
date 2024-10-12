package persistence;

import java.io.FileNotFoundException;

import model.AllItems;

// Represent all methods used to write JSON representation of AllItems
// The sample JsonSerializationDemo is modeled in this interface and its implementation
// Citation: “Build software better, together,” GitHub. 
//            https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface WriterAllItems {
    
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    void open() throws FileNotFoundException;

    // MODIFIES: this
    // EFFECTS: writes JSON representation of AllItems to file
    void write(AllItems wr);

    // MODIFIES: this
    // EFFECTS: closes writer
    void close();

    // MODIFIES: this
    // EFFECTS: writes string to file
    void saveToFile(String json);
}
