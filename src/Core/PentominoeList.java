package Core;

import java.util.ArrayList;

public class PentominoeList  extends ArrayList<Pentominoes>{
    private ArrayList<Integer> amounts;

    /**
     * 
     */
    public PentominoeList(){
        amounts = new ArrayList<>();
    }

    /**
     * 
     * @return
     */
    public PentominoeList copy(){
        PentominoeList newList = new PentominoeList();
        for(int i=0; i<size(); i++){
            newList.addWithAmount(get(i).copy());
        }
        return newList;
    }

    /**
     * 
     * @param pentominoe
     */
    public void addWithAmount(Pentominoes pentominoe){
        this.amounts.add(pentominoe.getAmount());
        add(pentominoe);
    }

    /**
     * 
     * @param index
     */
    public void removePentominoe(int index){
        if(amounts.get(index) == 1){
            remove(index);
            amounts.remove(index);
        }
        else{
            amounts.set(index, amounts.get(index)-1);
        }
    }

    /**
     * 
     * @return
     */
    public int getTotalSize(){
        int sum = 0;
        for(int i: amounts){
            sum += i;
        }
        return sum;
    }

    /**
     * 
     * @return
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