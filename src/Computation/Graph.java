package Computation;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    private Map<String,Node> NodeSet;
    private boolean[][] Edges;
    private int Capacity;
    
    public Graph() {
        NodeSet = new HashMap<String, Node>();
        NodeSet.clear();
    }

    public Map<String,Node> GetNodeSet(){
        return NodeSet;
    }

    private boolean addnode(Node r_node){
        if(NodeSet.containsKey(r_node.GetCode()))
            return false;
        NodeSet.put(r_node.GetCode(), r_node);
        return true;
    }

    public void clear(){
        NodeSet.clear();
        Edges = null;
        Capacity = 0;
    }

    void AddNode(String Value,int ID) {
        Node Obj = new Node(Value);
        Obj.SetID(ID);
        Capacity++;
        addnode(Obj);
    }

    void SetEdges(boolean[][] Table){
        Edges = new boolean[Capacity][Capacity];
        Edges = Table;
    }

    void AddEdge(String Value1, String Value2){
        Node Obj1 = NodeSet.get(Value1);
        Node Obj2 = NodeSet.get(Value2);
        Edges[Obj1.GetID()][Obj2.GetID()] = true;
        Edges[Obj2.GetID()][Obj1.GetID()] = true;
    }

    int GetCapacity() {
        return Capacity;
    }

    String GetNode(int i) {
        String Code;
        for(int j=0; j<Capacity; j++)
        {
            Code = (String) NodeSet.keySet().toArray()[j];
            if(NodeSet.get(Code).GetID() == i)
                return Code;
        }
        return null;
    }

    boolean CheckDep(int ID1, int ID2){
        //Check the dependency between 2 Courses with ID1 & ID2
        return Edges[ID1][ID2];
    }
}
