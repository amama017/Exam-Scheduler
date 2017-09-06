package Computation;

public class Node {

    private String Code;
    private int ID;

    Node(){};

    Node(String r_Code){Code = r_Code;};

    void SetCode(String r_Code){
        Code = r_Code;
    }

    public void SetID(int r_ID){
        ID = r_ID;
    }

    public String GetCode(){
        return Code;
    }

    public int GetID(){
        return ID;
    }
}
