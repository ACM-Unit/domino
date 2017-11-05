package entity;

/**
 * Created by Admin on 03.11.2017.
 */
public class Domino {
    private int id;
    private int firstNum;
    private int secondNum;
    private boolean isJoin;


    public void rotate(){
        if(!isJoin){
            int buffer = firstNum;
            firstNum = secondNum;
            secondNum = buffer;
        }
    }
    public Domino(Integer firstNum, Integer secondNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
        this.isJoin = false;
    }

    public boolean join(Domino domino) {
        if(secondNum == domino.getFirstNum()){
            domino.isJoin = true;
            isJoin = true;
            return true;
        }
        if(secondNum == domino.getSecondNum()){
            domino.rotate();
            domino.isJoin = true;
            isJoin = true;
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }

    @Override
    public String toString() {
        return firstNum +  ":" + secondNum;
    }

    @Override
    protected Domino clone() throws CloneNotSupportedException {
        return new Domino(firstNum, secondNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Domino)) return false;

        Domino domino = (Domino) o;

        if (getId() != domino.getId()) return false;
        if (getFirstNum() != domino.getFirstNum()) return false;
        if (getSecondNum() != domino.getSecondNum()) return false;
        return isJoin == domino.isJoin;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFirstNum();
        result = 31 * result + getSecondNum();
        result = 31 * result + (isJoin ? 1 : 0);
        return result;
    }
}
