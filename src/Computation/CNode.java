package Computation;

import java.util.Vector;

public class CNode {
    
    private String Code;
    private int ID;
    private int Day;
    private int Lecnum;
    private int Labnum;
    private Vector<Integer>[][] Slots;
    private boolean[][][] TakenSlots;
    private boolean lecfinished;
    private boolean labfinished;

    CNode(){
        Slots = null;
        Day = 5;
        ID = -1;
        Lecnum = 0;
        Labnum = 0;
        lecfinished = false;
        labfinished = false;
    }

    CNode(String r_Code, int hall){
        Code = r_Code;
        Day = 5;
        //number of days = 5
        Slots = new Vector[hall][Day];
        TakenSlots = new boolean[hall][Day][9];
        for(int i=0; i<hall; i++)
            for(int j=0; j<Day; j++)
                Slots[i][j] = new Vector();
        ID = -1;
        Lecnum = 0;
        Labnum = 0;
        lecfinished = false;
        labfinished = false;
    }

    public Vector<Integer>[][] GetSlots(){
        //return the Set of free Slots for the Course
        return Slots;
    }

    public void addSlot(int S, int hall, int day){
        //add a slot to the freetime space
        Slots[hall][day].add(S);
    }

    public void removeSlot(int S, int hall, int day){
        //remove a slot from freetime space of the upper data
        int index = Slots[hall][day].indexOf(S);
        Slots[hall][day].removeElementAt(index);
    }

    public boolean Check(int daynum, int slotnum, int hallnum){
        if(Search(daynum, slotnum, hallnum))
            return true;
        return false;
    }

    public boolean Search(int daynum, int slotnum, int hallnum){
        for(int i=0; i<Slots[hallnum][daynum].size(); i++)
        {
            if(Slots[hallnum][daynum].elementAt(i) == slotnum)
                return true;
        }
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public void SetCode(String r_Code){
        Code = r_Code;
    }

    public void SetID(int r_ID){
        ID = r_ID;
    }

    public void SetLec(int r_Lec){
        Lecnum = r_Lec;
        if(Lecnum <= 0)
            FinishLec(true);
    }

    public void SetLab(int r_Lab){
        Labnum = r_Lab;
        if(Labnum <= 0)
            FinishLab(true);
    }

    public void FinishLec(boolean Status){
        lecfinished = Status;
    }

    public void FinishLab(boolean Status){
        labfinished = Status;
    }

    public void disable(int daynum, int slotnum, int hallnum){
        TakenSlots[hallnum][daynum][slotnum] = true;
    }

    public void enable(int daynum, int slotnum, int hallnum){
        TakenSlots[hallnum][daynum][slotnum] = false;
    }

    public String GetCode(){
        return Code;
    }

    public int GetID(){
        return ID;
    }

    public int GetLec(){
        return Lecnum;
    }

    public int GetLab(){
        return Labnum;
    }

    public boolean IsLecFinish(){
        return lecfinished;
    }

    public boolean IsLabFinish(){
        return labfinished;
    }

    int GetNumSlots() {
        //Get the total number of free slots available in the time space of the course
        int Sum = 0;
        for(int i=0; i<Slots.length; i++)
            for(int j=0; j<Slots[i].length; j++)
                Sum += Slots[i][j].size();
        return Sum;
    }

    int[] GetFreeSlot() {
        //return the first Free Slot
        int[] found = new int[3];
        for(int z=0; z<3; z++)
            found[z] = -1;
        for(int i=0; i<Day; i++)
        {
            for(int j=0; j<Slots.length; j++)
            {
                if(!Slots[j][i].isEmpty())
                {
                    found[0] = i;
                    found[1] = Slots[j][i].firstElement();
                    found[2] = j;
                    return found;
                }
            }
        }
        return found;
    }
    // </editor-fold>
}
