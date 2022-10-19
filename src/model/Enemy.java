package model;
public class Enemy{

    private String id;
    private TypeEnemy type;
    private double removePoints;
    private double givenPoints;
    private double positionX;
    private double positionY;

    /**
     * Method Enemy:Create an object of class enemy
     * @param inId String: id of enemy
     * @param inType int: number relationed to the type of enemy
     * @param inRemovePoints double:Points that the enemy remove from the player's score
     * @param inGivenPoints double:Points that give to the player
     * @param inPositionX double: position in plane x
     * @param inPositionY double: position in plane y
     */
    public Enemy(String inId,int inType,double inRemovePoints,double inGivenPoints,double inPositionX,double inPositionY){
         id = inId;
         switch(inType){
            case 1:
            type=TypeEnemy.OGRE;
                break;
            case 2:
            type=TypeEnemy.ABSTRAC;
                break;
            case 3:
            type=TypeEnemy.MAGICIAN;
                break;
            case 4:
            type=TypeEnemy.CHEF;
                break;
        }
         removePoints = inRemovePoints;
         givenPoints = inGivenPoints;
         positionX = inPositionX;
         positionY = inPositionY;
    }
    
    /**
     * Method getRemovePoints Return the removePoints of the enemy
     * @return double:removePoints of enemy
     */
    public double getRemovePoints(){
        return removePoints;
    }

    /**
     * Method getGivenPoints:Return the givenPoints of the enemy
     * @return double:givenPoints of enemy
     */
    public double getGivenPoints(){
        return givenPoints;
    }
    /**
     * MEthod getType:Return the type of the enmy as a string
     * @return string with the type of enemy
     */
    public String getType(){
        String type = "";
        if(this.type==TypeEnemy.OGRE){
            type = "Ogro";
        }else if(this.type==TypeEnemy.ABSTRAC){
            type = "Abstracto";
        }else if(this.type==TypeEnemy.MAGICIAN){
            type = "Magico";
        }else if(this.type==TypeEnemy.CHEF){
            type = "Jefe";
        }
        return type;
    }
    /**
     * Method getEnumType:Return the enumeration of the type of enemy
     * @return TypeEnemy: enumeration of the type of enemy
     */
    public TypeEnemy getEnumType(){
        return this.type;
    }
    
}