package Core;

import java.util.ArrayList;

public class ParcelList extends ArrayList<Parcels> {
    private ArrayList<Integer> amounts;

    /**
     * Constructor that creates a new empty array list.
     */
    public ParcelList() {
        amounts = new ArrayList<>();
    }

    /**
     * Method that creates a copy of the current list.
     * 
     * @return - the new, identical parcel list
     */
    public ParcelList copy() {
        ParcelList newList = new ParcelList();

        for (int i = 0; i < size(); i++) {
            newList.addWithAmount(get(i).copy());
        }

        return newList;
    }

    /**
     * Ads a new parcel to the list.
     * 
     * @param parcel - the parcel to be added
     */
    public void addWithAmount(Parcels parcel) {
        this.amounts.add(parcel.getAmount());
        add(parcel);
    }

    /**
     * @return - total number of parcels to be used
     */
    public int getTotalSize() {
        int sum = 0;

        for (int i : amounts) {
            sum += i;
        }

        return sum;
    }

    /**
     * @return - the array
     */
    public ArrayList<Parcels> getFullArray() {
        ArrayList<Parcels> list = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < amounts.get(i); j++) {
                list.add(get(i).copy());
            }
        }

        return list;
    }
}