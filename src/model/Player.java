package model;
public class Player{
    private String name;
    private String nickName;
    private double score;
    private int lifes;
    private Level playerLevel;

    /**
     * Method Player;Create an object of class player
     * @param inName String: Player's name
     * @param inNickName String: Player's nickname
     * @param playerLv Level:Player's level
     */
    public Player(String inName,String inNickName,Level playerLv){
        name = inName;
        nickName = inNickName;
        score = 10;
        lifes = 5;
        playerLevel = playerLv;

    }
    /**
     * Method getScore:Return player's score
     * @return Player's score
     */
    public double getScore(){
        return score;
    }

    /**
     * Method getNickName ;Return player's nickname
     * @return Player's nickname
     */
    public String getNickName(){
        return nickName;
    }
    /**
     * Method getLevel;Return the level of the player
     * @return Player's level
     */
    public Level getLevel(){
        return playerLevel;
    } 
    /**
     * Method setScore:Change the score of the player
     * @param newScore double:new score of player
     */
    public void setScore(double newScore){
        this.score = newScore;
    }
    /**
     * Method newLevel:Change the level of the player 
     * @param newLevel Level:New level of player
     */
    public void setlevel(Level newLevel){
        this.playerLevel = newLevel;
    }
    /**
     * Method getLevelId:return the id of the player's level 
     * @return id of level
     */
    public int getLevelId(){
        return playerLevel.getId();
    }
   
}