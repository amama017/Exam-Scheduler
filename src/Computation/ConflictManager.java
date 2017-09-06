package Computation;

import java.util.Vector;

public class ConflictManager {

    private Conflict ConflictClass;

    public ConflictManager(){
        ConflictClass = new Conflict();
    }

    public void SetConflictData(int Course, int Inst, int Std){
        ConflictClass.SetCourseNum(Course);
        ConflictClass.SetProfNum(Inst);
        ConflictClass.SetStdNum(Std);
        ConflictClass.InitConflit();
    }

    public void SetArrayCourse(String[] code){
        ConflictClass.SetCourseCode(code);
    }

    public String[] GetArrayCourse(){
        return ConflictClass.GetCourseCode();
    }

    public void SetConflict(String Course1, String Course2){
        ConflictClass.SetConflict(Course1,Course2);
    }

    public boolean[][] GetConflictTable(){
        return ConflictClass.GetConflict();
    }

    public void SetConflictGraph() {
        ConflictClass.SetConflictGraph();
    }

    public boolean SetScheduele(int HallNum, String[] CArr, int[][] CData, Vector<Integer>[][] FreeSlot) {
        ConflictClass.SetScheduele(HallNum,CArr,CData);
        ConflictClass.SetFreeSlots(ConflictClass.GetCourseCode(), FreeSlot);
        return ConflictClass.Execute();
    }

    public String[][] GetTimeTable(int hall) {
        return ConflictClass.GetTimeTable(hall);
    }
}
