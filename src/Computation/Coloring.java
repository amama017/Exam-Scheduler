package Computation;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Coloring {

    private Graph Domain;
    private Map<String,CNode> Constraints;
    private String[][][] TimeTable;
    private int Capacity;
    private int HallNum;
    private int TotalCost;

    Coloring(){
        Domain = new Graph();
        Domain.clear();
        Constraints = new HashMap<String, CNode>();
        Constraints.clear();
        TimeTable = null;
        HallNum = 0;
        TotalCost = 0;
        Capacity = 0;
    }

    Coloring(Graph Courses, int hall){
        Domain = Courses;
        Constraints = new HashMap<String, CNode>();
        HallNum = hall;
        TimeTable = new String[hall][5][9];
        TotalCost = 0;
        Capacity = 0;
    }

    public void InitConstraints(String[] CArr,int[][] CourseData){
        for(int i=0; i<Domain.GetCapacity(); i++)
        {
            AddConstraint(CArr[i],HallNum,CourseData[i][0],CourseData[i][1]);
        }
    }

    public void AddConstraint(String Code, int hall, int Lec, int Lab) {
        CNode Obj = new CNode(Code,hall);
        Obj.SetID(Domain.GetNodeSet().get(Code).GetID());
        Obj.SetLab(Lab);
        Obj.SetLec(Lec);
        Capacity++;
        AddCNode(Obj);
    }

    private boolean AddCNode(CNode r_node){
        if(Constraints.containsKey(r_node.GetCode()))
            return false;
        Constraints.put(r_node.GetCode(), r_node);
        return true;
    }

    public boolean Execute(int current) {
        if(Finished(Constraints))
            return true;
        if(current >= Capacity)
            current = 0;
        else if(current < 0)
            current = Capacity-1;
        CNode CurrentCourse = Constraints.get(Domain.GetNode(current));
        if(!CurrentCourse.IsLecFinish())
        {
            int lec = CurrentCourse.GetLec();
            if(CurrentCourse.GetNumSlots() < lec+CurrentCourse.GetLab())
            {
                return false;
            }
            int[] SlotData = new int[3];
            SlotData = CurrentCourse.GetFreeSlot();
            int daynum = SlotData[0];
            int slotnum = SlotData[1];
            int hallnum = SlotData[2];
            SetSlot(CurrentCourse.GetCode(),daynum,slotnum,hallnum);
            Propagation(CurrentCourse.GetID(), daynum, slotnum, hallnum);
            CurrentCourse.SetLec(lec-1);
            if(lec <= 1)
                CurrentCourse.FinishLec(true);
            if(Execute(current+1))
            {
                return true;
            }
            else
            {
                CurrentCourse.SetLec(lec);
                if(lec >= 1)
                    CurrentCourse.FinishLec(false);
                RemoveSlot(daynum,slotnum,hallnum);
                UndoPropagation(CurrentCourse.GetID(), daynum, slotnum, hallnum);
                for(int j=0; j<HallNum; j++)
                    if(Constraints.get(Domain.GetNode(CurrentCourse.GetID())).Check(daynum, slotnum, j))
                    {
                        CurrentCourse.removeSlot(slotnum, hallnum, daynum);
                        CurrentCourse.disable(daynum, slotnum, hallnum);
                    }
                if(CurrentCourse.GetNumSlots() >= lec+CurrentCourse.GetLab())
                    return Execute(current);
                else
                {
                    CurrentCourse.addSlot(slotnum, hallnum, daynum);
                    return false;
                }
            }
        }
        else if(!CurrentCourse.IsLabFinish())
        {
            int lab = CurrentCourse.GetLab();
            if(CurrentCourse.GetNumSlots() < lab+CurrentCourse.GetLec())
            {
                return false;
            }
            int[] SlotData = new int[3];
            SlotData = CurrentCourse.GetFreeSlot();
            int daynum = SlotData[0];
            int slotnum = SlotData[1];
            int hallnum = SlotData[2];
            SetSlot((CurrentCourse.GetCode()+" Lab"),daynum,slotnum,hallnum);
            Propagation(CurrentCourse.GetID(), daynum, slotnum, hallnum);
            CurrentCourse.SetLab(lab-1);
            if(lab <= 1)
                CurrentCourse.FinishLab(true);
            if(Execute(current+1))
            {
                return true;
            }
            else
            {
                CurrentCourse.SetLab(lab);
                if(lab >= 1)
                    CurrentCourse.FinishLab(false);
                RemoveSlot(daynum,slotnum,hallnum);
                UndoPropagation(CurrentCourse.GetID(), daynum, slotnum, hallnum);
                for(int j=0; j<HallNum; j++)
                    if(Constraints.get(Domain.GetNode(CurrentCourse.GetID())).Check(daynum, slotnum, j))
                    {
                        CurrentCourse.removeSlot(slotnum, hallnum, daynum);
                        CurrentCourse.disable(daynum, slotnum, hallnum);
                    }
                if(CurrentCourse.GetNumSlots() >= lab+CurrentCourse.GetLec())
                    return Execute(current);
                else
                {
                    CurrentCourse.addSlot(slotnum, hallnum, daynum);
                    return false;
                }
            }
        }
        else
        {
            return Execute(current-1);
        }
    }

    void AddSlots(String Code, Vector<Integer>[] Slots) {
        //Assign the FreeTime for Course of Code "Code"
        CNode Course = Constraints.get(Code);
        for(int j=0; j<Slots.length; j++)
            if(Slots[j] != null)
                for(int k=0; k<Slots[j].size(); k++)
                    for(int i=0; i<HallNum; i++)
                        Course.addSlot(Slots[j].elementAt(k), i, j);
    }

    private boolean Finished(Map<String, CNode> Constraints) {
        //Check if all the Courses has finished Schedueling.
        for(int i=0; i<Constraints.size(); i++)
            if(!Constraints.get(Domain.GetNode(i)).IsLabFinish() || !Constraints.get(Domain.GetNode(i)).IsLecFinish())
                return false;
        return true;
    }

    private void SetSlot(String CourseCode, int daynum, int slotnum, int hallnum) {
        //Set the Slot of time "Slotnum" in day "daynum" in "hallnum" table by CourseCode
        TimeTable[hallnum][daynum][slotnum] = CourseCode;
    }

    private void RemoveSlot(int daynum, int slotnum, int hallnum) {
        //Remove the Slot of time "Slotnum" in day "daynum" in "hallnum" table
        TimeTable[hallnum][daynum][slotnum] = null;
    }

    private void Propagation(int ID, int daynum, int slotnum, int hallnum) {
        for(int i=0; i<Constraints.size(); i++)
        {
            if(CheckDependency(i,ID) || (i == ID))
            {
                //if the Course is dependent remove the Slot form all Halls Tables
                for(int j=0; j<HallNum; j++)
                    if(Constraints.get(Domain.GetNode(i)).Check(daynum, slotnum, j))
                    {
                        Constraints.get(Domain.GetNode(i)).disable(daynum,slotnum,j);
                        Constraints.get(Domain.GetNode(i)).removeSlot(slotnum, j, daynum);
                    }
            }
            else
                if(Constraints.get(Domain.GetNode(i)).Check(daynum, slotnum, hallnum))
                {
                    Constraints.get(Domain.GetNode(i)).disable(daynum,slotnum,hallnum);
                    Constraints.get(Domain.GetNode(i)).removeSlot(slotnum, hallnum, daynum);
                }
        }
    }

    private void UndoPropagation(int ID, int daynum, int slotnum, int hallnum) {
        for(int i=0; i<Constraints.size(); i++)
        {
            if(CheckDependency(i,ID) || (i==ID))
            {
                for(int j=0; j<HallNum; j++)
                    if(Constraints.get(Domain.GetNode(i)).Check(daynum, slotnum, j))
                    {
                        Constraints.get(Domain.GetNode(i)).enable(daynum,slotnum,j);
                        Constraints.get(Domain.GetNode(i)).addSlot(slotnum, j, daynum);
                    }
            }
            else
                if(Constraints.get(Domain.GetNode(i)).Check(daynum, slotnum, hallnum))
                {
                    Constraints.get(Domain.GetNode(i)).enable(daynum,slotnum,hallnum);
                    Constraints.get(Domain.GetNode(i)).addSlot(slotnum, hallnum, daynum);
                }
        }
    }

    private boolean CheckDependency(int i, int ID) {
        return Domain.CheckDep(i, ID);
    }

    String[][] GetTimeTable(int hall) {
        return TimeTable[hall];
    }
}
