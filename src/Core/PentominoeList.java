package Core;

import java.util.ArrayList;

public class PentominoeList  extends ArrayList<Pentominoes>{
    private ArrayList<Integer> amounts;

    /**
     * Constructor that creates a new empty array list.
     */
    public PentominoeList(){
        amounts = new ArrayList<>();
    }

    /**
     * Method that creates a copy of the current list.
     * 
     * @return - the new, identical pentominoe list
     */
    public PentominoeList copy(){
        PentominoeList newList = new PentominoeList();

        for(int i=0; i<size(); i++){
            newList.addWithAmount(get(i).copy());
        }

        return newList;
    }

    /**
     * Ads a new pentominoe to the list.
     * 
     * @param pentominoe - the pentominoe to be added
     */
    public void addWithAmount(Pentominoes pentominoe){
        this.amounts.add(pentominoe.getAmount());
        add(pentominoe);
    }

    /**
     * @return - total number of pentominoes to be used
     */
    public int getTotalSize(){
        int sum = 0;
        
        for(int i: amounts){
            sum += i;
        }

        return sum;
    }

    /**
     * @return - the array
     */
    public ArrayList<Pentominoes> getFullArray(){
        ArrayList<Pentominoes> list = new ArrayList<>();

        for (int i=0; i<size();i++){
            for (int j=0; j<amounts.get(i);j++){
                list.add(get(i).copy());
            }
        }

        return list;
    }
}