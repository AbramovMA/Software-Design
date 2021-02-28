package game;

public class Sequence {

    //we need to compare it to the input from the buffer
    //need to make sure it progresses, when needed
    //visuals???

    /*
    what function needs to be made, so we could use this properly:
        First: value from Matrix has to be already input in buffer before calling this it,
        cause it makes more sense.
        Initial value of:
            (Globally in Main!!!!!: )
                iSeq == 0;
                success should be false
                fail should be false
                passSeq is false

        This function has to be launched each time after interaction with the button:
            we have a string from the button; iSeq (in seq function is i) = which sequence is being compared
            to in the list; current sequence; size of the sequence; booleans: success, fail and pass(to move onto
            next code of the sequence; buffer;
            These are included, when launching the function, so it would work, as intended

        After this function
         for some stuff, logic and visual:
            if(success == true){
                    Game Complete!!! sign is popping up and game is finished. Player wins.
                }
            else if(fail == true){  // can be used for if the timer has ran out or smth.
                    GAME OVER!!!! sign is popping up and game is finished. Player loses.
                }
            else if(pass == true){
                    Visuals of the sequence are changed, where the highlight would change depending on iSeq.
                }
            else{
                    nothing. Game is keep going
                }
     */





    /*
    TO DO:
        - unprivatize sizes of buffer (plz)
        - input it into the main, so the whole thing would work together

     */
    public boolean sequenceProgression(int i, String[] sequence, String input, boolean success, boolean fail,
                                       Buffer buffer, boolean pass){

        String[] currentSequence = sequence;

        if(i == (sequence.length - 1)){
            if(input == currentSequence[i]){
                success = true;
                return success;
            }
            else{
                if(buffer.is_full()){
                    fail = true;
                    return fail; // we trigger Game Over
                    // this is when the game should end, cause he didn't pass the last
                    // value of the sequence
                }
                pass = false;
                // if not, then nothing really
            }
        }
        else{
            if(input == currentSequence[i]){
                i++;
                pass = true;
            }
            else{
                pass = false;
                //nothing really. Player has to chose another one
            }
        }
        return pass;
    }



}
