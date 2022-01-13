package Core;

import java.util.ArrayList;

public class ParcelList extends ArrayList<Parcels>{
    private ArrayList<Integer> amounts;

    /**
     * 
     */
    public ParcelList(){
        amounts = new ArrayList<>();
    }

    /**
     * 
     * @return
     */
    public ParcelList copy(){
        ParcelList newList = new ParcelList();
        for(int i=0; i<size(); i++){
            newList.addWithAmount(get(i).copy());
        }
        return newList;
    }

    /**
     * 
     * @param parcel
     */
    public void addWithAmount(Parcels parcel){
        this.amounts.add(parcel.getAmount());
        add(parcel);
    }

    /**
     * 
     * @param index
     */
    public void removeParcel(int index){
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
    public ArrayList<Parcels> getFullArray(){
        ArrayList<Parcels> list = new ArrayList<>();
        for (int i=0; i<size();i++){
            for (int j=0; j<amounts.get(i);j++){
                list.add(get(i).copy());
            }
        }
        return list;
    }
}
/*{

    public void add(Parcels parcels, int i) {
    }

    public ArrayList<Parcels> getFullArray() {
        return null;
    }

    public int getTotalSize() {
        return 0;
    }
    
}
/*
class Node {
        private int[][][] parcel;
        private int value;
        private Node next;

        public Node(int[][][] p, int v){
            parcel = p;
            value = v;
            next = null;
        }

        public int[][][] getParcel() {
            return parcel;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setParcel(int[][][] p) {
            parcel = p;
        }

        public void setNext(Node n) {
            next = n;
        }
    }
    
    Node head;
    Node tail;
    int size;

    /**
     * 
     * @param p     - the first parcel
     *
    public ParcelList(int[][][] p, int v) {
        head = new Node(p, v);
        tail = head;
        size = 1;
    }

    // public Parcels getFirstParce

    public int getSize() {
        return size;
    }

    public int getvalueHead() {
        return head.getValue();
    }

    public void addParcel(int[][][] p, int v) {
        Node n = new Node(p, v);
        tail.setNext(n);
        tail = n;
        size++;
    }

    public int[][][] removeFirstParcel() {
        if(head == null) {
            return null;
        }
        int[][][] p = head.getParcel();
        head = head.getNext();
        size--;
        return p;
    }

    public Node getTail() {
        return tail;
    }
*/