package model;
import java.util.Random;
public class Level{
    private Enemy[] enemies;
    private Treasure[] treasures;
    private LevelDifficulty difficulty;
    private int id;
    private double pointsTolevelUp;
    private static final int SIZE_OF_TREASURES=20;
    private static final int SIZE_OF_ENEMIES=15;

    /**
     * @param idLevel int:id of level
     * @param inPointsTolevelUp double:the points neccesary to be in tne level
     */
    public Level(int idLevel,double inPointsTolevelUp) {

        id = idLevel;
        enemies = new Enemy[SIZE_OF_ENEMIES];
        treasures = new Treasure[SIZE_OF_ENEMIES];
        difficulty = null;
        pointsTolevelUp = inPointsTolevelUp;
        enemies = new Enemy[SIZE_OF_ENEMIES];
        treasures = new Treasure[SIZE_OF_TREASURES];
        for(int i=0;i<3;i++){
            treasures[i]= new Treasure("diamante","a",100,generateRamdomPosition(),generateRamdomPosition());
        }
        treasures[3] = new Treasure("roca","a",100,generateRamdomPosition(),generateRamdomPosition());
        treasures[4] = new Treasure("roca","a",100,generateRamdomPosition(),generateRamdomPosition());
        enemies[0]= new Enemy("1", 1, 200, 200,generateRamdomPosition() ,generateRamdomPosition());
        enemies[1]= new Enemy("1", 1, 200, 200,generateRamdomPosition() ,generateRamdomPosition());
    }

    /**
     * Method define the difficulty of level based in the points given by monsters and enemies
     * 
     */
    public void calculateDifficulty(){
        double enemiesPoints = 0;
        double treasuresPoints = 0;
        for(int i = 0;i<10;i++){
            enemiesPoints += enemies[i].getGivenPoints();
        }
        for(int i = 0;i<20;i++){
            treasuresPoints += treasures[i].getGivenPoints();
        }
        if(treasuresPoints>enemiesPoints){
            difficulty = LevelDifficulty.LOW;
        }else if(treasuresPoints==enemiesPoints){
            difficulty = LevelDifficulty.MEDIUM;
        }else if(treasuresPoints<enemiesPoints){
            difficulty = LevelDifficulty.HIGH;
        }

    } 
    public int getId() {
        return id;
    }
    public double getPointsToLevelUp(){
        return pointsTolevelUp;
    }
    public Enemy[] getEnemies() {
        return enemies;
    }
    public Treasure[] getTreasures(){
        return treasures;
    }
    /**
     * Method addEnemy:Create an object of class Enemy and add it to the level's array of enemies
     * @param id String:id of enemy
     * @param inType int:number relationed to a type of enemy
     * @param inRemovePoints double:Points that remove from player's score
     * @param inGivenPoints double:Points that give to the player
     * @return
     */
    public String addEnemy(String id,int inType,double inRemovePoints,double inGivenPoints){
        double posX = generateRamdomPosition();
        double posY = generateRamdomPosition();
        Enemy newEnemy = new Enemy(id,inType,inRemovePoints,inGivenPoints,posX,posY);
        Boolean isEmpty = false;
        String msj = "No se pudo agrgar al enemigo";
        for(int i=0;i<SIZE_OF_ENEMIES && !isEmpty;i++){
            if(enemies[i]==null){
                enemies[i] = newEnemy;
                isEmpty = true;
                msj = "Se ha agregado al enemigo";
            }
        }
        return msj;
    }
    /**
     * Method addTreasure;Create an object of class treasure with the parameters and add it to the arrayof treasures of level
     * @param name String:Name of the new treasure
     * @param url String: url of the new treasure
     * @param givenPoints double: Points that the treasure give to the player
     * @return
     */
    public String addTreasure(String name,String url,double givenPoints){
        String msj1 = "No se pudo agregar el tesoro al nivel";
        double posX = generateRamdomPosition();
        double posY = generateRamdomPosition();
        Treasure newTreasure = new Treasure(name,url,givenPoints, posX, posY);
        Boolean isEmpty2 = false;
        for(int i = 0;i<SIZE_OF_TREASURES && !isEmpty2;i++){
            if(treasures[i]==null){
                treasures[i] = newTreasure;
                isEmpty2 = true;
                msj1 = "Se ha agregado el tesoro : "+(i+1)+"\n";
            }
        }
        return msj1;
    }
    /**
     * Method Generate a ramdom double within the specified range
     * @return pos double:a double generated automatically
     */
    public double generateRamdomPosition(){
        double pos = (Math.random()*((300-100)+1))+100;
        return pos;
    }
    /**
     * Method  ShowEnemiesAndTreasures:Get the information of treasures and enemies of the level and return a string with it
     * @return msj String:Message with information of treasures and enemies
     */
    public String ShowEnemiesAndTreasures(){
        String msj = "";
        String first ="";
        int count=0;
        if(getTreasures()[0]!=null){
            first = getTreasures()[0].getName();
            for(int k = 0;k<SIZE_OF_TREASURES;k++){
                if(getTreasures()[k]!=null){
                    if(getTreasures()[k].getName()==first){
                        count += 1;
                    }
                }
            }
            msj = "Tesoro: "+first +" cantidad: "+ count +",";
        }
        for(int i = 1;i<SIZE_OF_TREASURES;i++){
            if(getTreasures()[i]!=null && getTreasures()[i].getName()!= first){
                count = 0;
                first = getTreasures()[i].getName();
                for(int l = 0;l<SIZE_OF_TREASURES;l++){
                    if(getTreasures()[l]!=null){
                        if(getTreasures()[l].getName()==first){
                            count += 1;
                        }
                    }
                }
                msj += "Tesoro: "+first +" cantidad: "+ count +",";
            }
        }
        for(int j = 0;j<SIZE_OF_ENEMIES;j++){
            if(getEnemies()[j]!=null){
                msj += "Enemigo: "+getEnemies()[j].getType()+",";
            }
        }
        
        return msj;
        
    }
   
    /**
     * Method searchTreasureByName:Search a treasure by it's name and return a boolean to confirm if it exist
     * @param treasureName Name of the treasure
     * @return return a boolean indicating if the treasure was found
     */
    public boolean searchTreasureByName(String treasureName){
        boolean isFound2 = false;
        for(int i = 0;i<SIZE_OF_TREASURES && !isFound2;i++){
            if(treasures[i]!=null && treasures[i].getName().equalsIgnoreCase(treasureName)){
                isFound2=true;
            }
        }
        return isFound2;
    }
   
}
