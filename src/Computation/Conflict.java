package Computation;

import java.util.Vector;

public class Conflict {
    private boolean[][] ConflictTable;
    private String[] CourseCode;
    private Graph ConflictGraph;
    private Coloring ColorAlgo;
    private int CourseNum;
    private int ProfNum;
    private int StudentNum;

    public Conflict()
    {
        ConflictGraph = null;
        ConflictTable = null;
    }

    public void SetCourseNum(int Num){
        CourseNum = Num;
        //Set the CourseNum by the number of courses will be processed
    }
    public void SetProfNum(int Num){
        ProfNum = Num;
        //Set the ProfNum by the number of Instructors will be processed
    }
    public void SetStdNum(int Num){
        StudentNum = Num;
        //Set the StdNum by the number of students will take courses.
    }
    public void SetCourseCode(String[] code){
        CourseCode = code;
    }

    public void InitConflit(){
        //Initialize the Class Variables
        ConflictTable = new boolean[CourseNum][CourseNum];
        ConflictGraph = new Graph();
        for(int i=0; i<CourseNum; i++)
            ConflictTable[i][i] = false;
    }

    public void SetConflict(String Course1, String Course2){
        //add Relation between 2 Courses
        int index1 = GetCourseIndex(Course1);
        int index2 = GetCourseIndex(Course2);
        ConflictTable[index1][index2] = true;
        ConflictTable[index2][index1] = true;
    }

    private int GetCourseIndex(String code) {
        //return the Index of the Course Code "code" in the Course Table
        for(int i=0; i<CourseNum; i++)
        {
            if(CourseCode[i].contentEquals(code))
                return i;
        }
        return -1;
    }

    public void SetConflictGraph() {
        ConflictGraph.clear();
        //add the courses to the Graph
        for(int i=0; i<CourseNum; i++)
        {
            ConflictGraph.AddNode(CourseCode[i],i);
        }
        //Set the relations between the courses
        ConflictGraph.SetEdges(ConflictTable);
    }

    public String[] GetCourseCode() {
        return CourseCode;
    }

    public boolean[][] GetConflict() {
        return ConflictTable;
    }

    void SetScheduele(int HallNum, String[] CArr, int[][] CData) {
        ColorAlgo = new Coloring(ConflictGraph,HallNum);
        ColorAlgo.InitConstraints(CArr,CData);
    }

    void SetFreeSlots(String[] CArr, Vector<Integer>[][] FreeSlot){
        //Set Each Course by the Free Slots assigned to it.
        for(int i=0; i<CArr.length; i++)
        {
            ColorAlgo.AddSlots(CArr[i], FreeSlot[i]);  
        }
    }

    boolean Execute() {
        return ColorAlgo.Execute(0);
    }

    String[][] GetTimeTable(int hall) {
        return ColorAlgo.GetTimeTable(hall);
    }
}
