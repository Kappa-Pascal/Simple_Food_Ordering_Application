package model;

import org.junit.jupiter.api.Test;

import persistence.JsonReaderAllItems;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReaderAllItems extends JsonTest {

    JsonReaderAllItems reader;

    @Test
    void testReaderNonExistenceFile() {
        reader = new JsonReaderAllItems("./data/noSuchFile.json");
        try {
            AllItems items = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAllItem() {
        reader = new JsonReaderAllItems("./data/TestReaderEmptyAllItems.json");
        try {
            AllItems items = reader.read();
            ArrayList<Item> empty = new ArrayList<>();
            assertEquals(0, items.getAllItems().size());
            assertEquals(empty, items.getAllItems());
        } catch (IOException e) {
            fail("IOException not expected");
        }
    }

    @Test
    void testReaderGeneralAllItem() {
        reader = new JsonReaderAllItems("./data/TestReaderGeneralAllItems.json");
        try {
            AllItems items = reader.read();
            assertEquals(3, items.getAllItems().size());
            assertEquals("i02",items.getAllItems().get(1).getName());
            checkItem("i01", 10.0, 20, 0, false, items.getAllItems().get(0));
        } catch (IOException e) {
            fail("IOException not expected");
        }
    }
}