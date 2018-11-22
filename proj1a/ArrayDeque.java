public class ArrayDeque<ElementType> {
    private ElementType[] Array;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        Array= (ElementType[]) new Object[8];
        nextFirst=0;
        nextLast=1;
        size=0;
    }
    private int minusOne(int index){
        int indexOfMinusOne;
        if(index==0)    indexOfMinusOne=Array.length-1;
        else indexOfMinusOne=index-1;
        return indexOfMinusOne;
    }
    private int plusOne(int index){
        int indexOfPlusOne;
        if(index==(Array.length-1)) {
            indexOfPlusOne = 0;
        }
        else indexOfPlusOne=index+1;
        return indexOfPlusOne;
    }
    public void addFirst(ElementType item){
        if(isFull()==true){
            ElementType[] ResizeArray=(ElementType[]) new Object[Array.length*2];
            int copyIndex=1;
            for(int i=nextFirst+1;i!=nextLast;i=(i+1)%Array.length){
                ResizeArray[copyIndex++]=Array[i];
            }
            nextFirst=0;
            nextLast=Array.length;
            Array=ResizeArray;
        }
        Array[nextFirst]=item;
        nextFirst=minusOne(nextFirst);
        size=size+1;
    }

    public void addLast(ElementType item){
        if(isFull()==true){
            ElementType[] ResizeArray=(ElementType[]) new Object[Array.length*2];
            int copyIndex=1;
            for(int i=nextFirst+1;i!=nextLast;i=(i+1)%Array.length){
                ResizeArray[copyIndex++]=Array[i];
            }
            nextFirst=0;
            nextLast=Array.length-1;
            Array=ResizeArray;
        }
        Array[nextLast]=item;
        nextLast=plusOne(nextLast);
        size=size+1;
    }

    public boolean isEmpty(){
        return (nextFirst+1)%Array.length==nextLast;
    }
    public boolean isFull(){
        return (nextLast+1)%Array.length==nextFirst;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        if(isEmpty()==false) {
            for (int i = nextFirst + 1; i != nextLast; i = plusOne(i))
                System.out.println(Array[i]);
        }
    }
    public ElementType removeFirst(){
        ElementType removeItem=null;
        if(isEmpty()==false){
            removeItem=Array[plusOne(nextFirst)];
            Array[plusOne(nextFirst)]=null;
            nextFirst=plusOne(nextFirst);
            size=size-1;
        }
        return removeItem;
    }
    public ElementType removeLast(){
        ElementType removeItem=null;
        if(isEmpty()==false){
            removeItem=Array[minusOne(nextLast)];
            Array[minusOne(nextLast)]=null;
            nextLast=minusOne(nextLast);
            size=size-1;
        }
        return removeItem;
    }
    public ElementType get(int index){
        ElementType getItem=null;
        if(isEmpty()==false){
            getItem=Array[(nextFirst+index)%Array.length];
        }
        return getItem;
    }
}
