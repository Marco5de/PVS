public class Field {
    private int count;
    int[] arr;
    Field(int count){
        this.count=count;
	arr = new int[count];
    }

    void setField(int position,int value)throws MissingFiledException{
        if(position > count-1 ^ position < 0){
            throw new MissingFiledException();
        }
        else{
            arr[position] = value;
        }
    }

    int getFiled(int position) throws MissingFiledException {
        if(position > count-1 ^ position < 0){
            throw new MissingFiledException();
        }
        else
            return arr[position];
    }

    @Override
    public String toString(){
        String out="";
        for(int i=0; i< arr.length;i++){
            if(i < count-1)
                out += (arr[i] +" ");
            else
                out += arr[i];
        }
        return out;

    }
}
