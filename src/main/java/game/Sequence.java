package game;

public class Sequence {

    //visuals???

    public int sequenceProgression(int i, String[] sequence, String input,
                                       Buffer buffer, int pass){
    // 0- nothing, 1 - pass, 2 - success, 3 - fail
        String[] currentSequence = new String[sequence.length];
        currentSequence = sequence;
        if(i == (sequence.length - 1)){
            if(input == currentSequence[i]){
                pass = 2; // success
            }
            else{
                if(buffer.is_full()){
                    pass = 3; //fail
                    // we trigger Game Over
                    // this is when the game should end, cause he didn't pass the last
                    // value of the sequence
                }
                else{
                    pass = 0; // nothing
                }
                // if not, then nothing really
            }
        }
        else{
            if(buffer.is_full()){
                pass = 3;

            }
            else if(input == currentSequence[i]){
                //i++;
                pass = 1; // pass

            }
            else{
                pass = 0; // nothing
                //nothing really. Player has to chose another one
            }
        }
        return pass;
    }



}
