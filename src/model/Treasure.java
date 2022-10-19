package model;
public class Treasure{

    private String name;
    private String url;
    private double givenPoints;
    private double positionX;
    private double positionY;

    /**
     * Method Treasure:Create an object of class Treasure
     * @param inName String: Name of treasure
     * @param inUrl String: url of treasure
     * @param inGivenPoints double:Points that give to the player
     * @param newPositionX double: position in plane x
     * @param newPositionY double: position in plane y
     */
    public Treasure(String inName,String inUrl,double inGivenPoints,double newPositionX,double newPositionY){
        name = inName;
        url = inUrl;
        givenPoints = inGivenPoints;
        positionX = newPositionX;
        positionY = newPositionY;

    }
    /**
     * Method getGivenPoints: Return the removePoints of the treasure
     * @return double givenPoints of treasure
     */
    public double getGivenPoints(){
        return givenPoints;
    }
    /**
     * Method getName:Return the name of the treasure
     * @return String:name of treasure
     */
    public String getName(){
        return this.name;
    }
}