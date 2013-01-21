public interface LinkedListInterface {

    public Node initNode(int data);

    public String report(Node root);

    public void insert(Node parent, Node child);
    
    public void insertData(Node parent, int data);
    
    public void removeData(Node parent, int data);
    
    public int size(Node root);
    
    public boolean contains(Node root, int data);

}
