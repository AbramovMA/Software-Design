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
     * Checks if a given `sequence` is contained in the buffer
     * Return value: 1 on success
     *               0 on failure
     **/
    private boolean contains(String[] sequence){
        int length = sequence.length;
        for (int i = 0; i < size - length + 1; ++i){
            boolean found = true;
            for (int j = 0; j < length; ++j)
                if (!values[i + j].equals(sequence[j])) {
                    found = false;
                    break;
                }

            if (found)
                return true;
        }
        return false;
    }

    /**
     * Checks if the goal sequence is contained in the buffer
     * Return value: 1 on success
     *               0 on failure
     **/
    final public boolean contains_goal_sequence(){
        String[] goal = new String[0]; // TODO: change to goal_sequence.get_sequence()
        return contains(goal);
    }

    /**
     * Updates the buffer on screen
     **/
    public void update(){
        String text = "Buffer: " + String.join(" ", values);
        contents.setText(text);
    }
}
