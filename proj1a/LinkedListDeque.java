public class LinkedListDeque <T> {
    private  class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;

    private  IntNode(IntNode pren,IntNode n){
        prev=pren;
        next=n;
    }
    private  IntNode(T i,IntNode pren, IntNode n) {
            prev=pren;
            item = i;
            next = n;
        }
    }
    private IntNode sentinel;
    private IntNode last;
    private int size;
    public LinkedListDeque() {
        sentinel = new IntNode(null,null);
        last=sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new IntNode(null,null);
        sentinel.next = new IntNode(x, sentinel,null);
        last=sentinel.next;
        size = 1;
    }
    public void addFirst(T item){
        IntNode q=sentinel.next;
        IntNode p;
        if(q!=null) {
            p = new IntNode(item, sentinel, q);
            q.prev=p;
        }
        else
            p=new IntNode(item,sentinel,null);
        sentinel.next=p;
        last=p;
        size++;
    }
    public void addLast(T item){
        IntNode p=new IntNode(item,last,null);
        last.next=p;
        last=p;
        size++;
    }
    public boolean isEmpty(){
        return last==sentinel;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        if(last!=sentinel){
            IntNode p=sentinel.next;
            while(p!=null){
                System.out.println(p.item);
                p=p.next;
            }
        }
    }
    public T removeFirst(){
        IntNode renode=sentinel.next;
        T X;
        if(renode!=null){
            sentinel.next=renode.next;
            X=renode.item;
            if(renode.next!=null) {
                renode.next.prev = sentinel;
            }
            else last=sentinel;
            size--;
        }
        else X=null;
        return X;
    }
    public T removeLast(){
        T X;
        if(last!=sentinel) {
            X = last.item;
            IntNode prelast = last.prev;
            prelast.next = null;
            last.prev = null;
            last = prelast;
            size--;
        }
        else X=null;
        return X;
    }
    public T get(int index){
        T X;
        if(size!=0) {
            IntNode p = sentinel;
            while (index != 0) {
                p = p.next;
                index--;
            }
            X=p.item;
        }
        else X=null;
        return X;
    }
}