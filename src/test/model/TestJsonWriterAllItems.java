package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import model.Item;
import persistence.JsonReaderAllItems;
import persistence.JsonWriterAllItems;

// Represent tests of JSON writer of AllItems
// The sample JsonSerializationDemo is modeled in this class
// Citation: “Build software better, together,” GitHub. 
//            https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class TestJsonWriterAllItems extends JsonTest {

    private JsonWriterAllItems writer;
    private JsonReaderAllItems reader;

    @Test
    void testWriterInvalidFile() {
        try {
            AllItems items = new AllItems();
            writer = new JsonWriterAllItems("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            AllItems items = new AllItems();
            writer = new JsonWriterAllItems("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(items);
            writer.close();

            reader = new JsonReaderAllItems("./data/testWriterEmptyWorkroom.json");
            items = reader.read();
            ArrayList<Item> empty = new ArrayList<>();
            assertEquals(empty,items.getAllItems());
            assertEquals(0,items.getAllItems().size());
        } catch (IOException e) {
            fail("IOException was not expected");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            AllItems items = new AllItems();
            //items.addThingy(new Thingy("saw", Category.METALWORK));
            //items.addThingy(new Thingy("needle", Category.STITCHING));
            items.addItem(new Item("w01", 50.0, 60));
            items.getAllItems().get(0).orderItem(5);
            items.addItem(new Item("w02", 60.0, 70));
            writer = new JsonWriterAllItems("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(items);
            writer.close();

            reader = new JsonReaderAllItems("./data/testWriterGeneralWorkroom.json");
            items = reader.read();
           // assertEquals("My work room", items.getName());
            ArrayList<Item> testItems = items.getAllItems();
            assertEquals(2, testItems.size());
            //checkThingy("saw", Category.METALWORK, items.get(0));
            //checkThingy("needle", Category.STITCHING, items.get(1));
            checkItem("w01",50.0,60 - 5,5,true,testItems.get(0));
            checkItem("w02",60.0,70,0,true,testItems.get(1));
        } catch (IOException e) {
            fail("IOException was not expected");
        }
    }
}
