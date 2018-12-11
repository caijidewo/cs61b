public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        array = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }
    private int minusOne(int index){
        int indexOfMinusOne;
        if(index==0)    indexOfMinusOne=array.length-1;
        else indexOfMinusOne=index-1;
        return indexOfMinusOne;
    }
    private int plusOne(int index){
        int indexOfPlusOne;
        if(index==(array.length-1)) {
            indexOfPlusOne = 0;
        }
        else indexOfPlusOne=index+1;
        return indexOfPlusOne;
    }
    public void addFirst(T item){
        if(isFull()==true){
            T[] ResizeArray=(T[]) new Object[array.length*2];
            int copyIndex=1;
            for(int i=nextFirst+1;i!=nextLast;i=(i+1)%array.length){
                ResizeArray[copyIndex++]=array[i];
            }
            nextFirst=0;
            nextLast=array.length;
            array=ResizeArray;
        }
        array[nextFirst]=item;
        nextFirst=minusOne(nextFirst);
        size=size+1;
    }

    public void addLast(T item){
        if(isFull()==true){
            T[] ResizeArray=(T[]) new Object[array.length*2];
            int copyIndex=1;
            for(int i=nextFirst+1;i!=nextLast;i=(i+1)%array.length){
                ResizeArray[copyIndex++]=array[i];
            }
            nextFirst=0;
            nextLast=array.length-1;
            array=ResizeArray;
        }
        array[nextLast]=item;
        nextLast=plusOne(nextLast);
        size=size+1;
    }

    public boolean isEmpty(){
        return (nextFirst+1)%array.length==nextLast;
    }
    public boolean isFull(){
        return (nextLast+1)%array.length==nextFirst;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        if(isEmpty()==false) {
            for (int i = nextFirst + 1; i != nextLast; i = plusOne(i))
                System.out.println(array[i]);
        }
    }
    public T removeFirst(){
        T removeItem=null;
        if(isEmpty()==false){
            removeItem=array[plusOne(nextFirst)];
            array[plusOne(nextFirst)]=null;
            nextFirst=plusOne(nextFirst);
            size=size-1;
        }
        return removeItem;
    }
    public T removeLast(){
        T removeItem=null;
        if(isEmpty()==false){
            removeItem=array[minusOne(nextLast)];
            array[minusOne(nextLast)]=null;
            nextLast=minusOne(nextLast);
            size=size-1;
        }
        return removeItem;
    }
    public T get(int index){
        T getItem=null;
        if(isEmpty()==false){
            getItem=array[(nextFirst+index)%array.length];
        }
        return getItem;
    }
}