package game;

import javafx.scene.text.Text;

import java.util.Arrays;

final public class Buffer {
    final private int max_size;    // maximum amount of value the buffer can store
    private int size;              // current amount of value the buffer can store
    final private String[] values; // the values stored in the buffer

    Text contents;

    /**
     * Constructor for a buffer with a predefined size `capacity`
     **/
    public Buffer(int capacity){
        max_size = capacity;
        size = 0;
        values = new String[max_size];
        Arrays.fill(values, "");

        contents = new Text("Probably buffer");
        contents.setX(0);
        contents.setY(0);

        update();
    }

    /**
     * Adds `new_value` to the end of the buffer
     * Return value: 1 on success
     *               0 on failure
     **/
    final public boolean add_value(String new_value){
        if (size < max_size) {
            values[size] = new_value;
            size++;
            update();
            return true;
        }else
            return false;
    }

    /**
     * Returns 1 if the buffer reached its maximum capacity, 0 otherwise
     **/
    final public boolean is_full(){
         return size == max_size;
    }

    /**
     * Checks if a given `sequence` can be constructed by adding more values into the buffer
     * Return value: 1 on success
     *               0 on failure
     **/
    private boolean is_reachable(String[] sequence) {
        int length = sequence.length;

        int sequence_index = 0;
        for (int i = 0; i < size; ++i){
            if (sequence_index == length)
                break;
            if (sequence[sequence_index].equals(values[i]))
                ++sequence_index;
        }

        int sequence_done = sequence_index;
        int sequence_left = length - sequence_done;
        int buffer_left = max_size - size;

        return sequence_left <= buffer_left;
    }

    public boolean unreachable(String[] sequence) {
        return !is_reachable(sequence);
    }

    /**
     * Updates the buffer on screen
     **/
    public void update(){
        String text = "Buffer: " + String.join(" ", values);
        contents.setText(text);
    }
}
