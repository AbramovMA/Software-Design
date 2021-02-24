package game;

final public class Buffer {
    final private int max_size;
    private int size;
    final private String[] values;

    public Buffer(int capacity){
        max_size = capacity;
        size = 0;
        values = new String[max_size];
    }

    final public boolean add_value(String s){
        if (size < max_size) {
            values[size] = s;
            size++;
            return true;
        }else
            return false;
    }

    final public boolean is_full(){
        return size == max_size;
    }

    private boolean contains(String[] seq){
        int length = seq.length;
        for (int i = 0; i < size - length + 1; ++i){
            boolean found = true;
            for (int j = 0; j < length; ++j)
                if (!values[i + j].equals(seq[j])) {
                    found = false;
                    break;
                }

            if (found)
                return true;
        }
        return false;
    }

    final public boolean contains_goal_sequence(){
        String[] goal = new String[0]; // TO DO: change to goal_sequence.get_sequence()
        return contains(goal);
    }

    final public void show(){}
}
