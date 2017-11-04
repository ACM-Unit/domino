package entity;

/**
 * Created by Admin on 03.11.2017.
 */
public class Domino {
    private int id;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return getName() != null ? getName().equals(domino.getName()) : domino.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getFirstNum();
        result = 31 * result + getSecondNum();
        return result;
    }
}
