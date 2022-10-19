package model;
public class Game{
   private Player[] players;
   private Level[] levels;
   
   private static final int SIZE_OF_LEVELS=10;
   private static final int SIZE_OF_PlAYERS=20;
   private static final int SIZE_OF_TREASURES=20;
   private static final int SIZE_OF_ENEMIES=15;
   
   /**
    * MEthod Game:Create an object of class game
    */
   public Game(){
    levels = new Level[SIZE_OF_LEVELS];
    players = new Player[SIZE_OF_PlAYERS];
   for(int i=0;i<SIZE_OF_LEVELS;i++){
      levels[i]= new Level(i+1,(i+1)*100);
   }
   levels[9].getEnemies()[2] = new Enemy("1", 4, 200, 500,46 ,150);
   players[0] = new Player("a","b",levels[0]);
   players[1] = new Player("r","c",levels[0]);
   players[2] = new Player("t","d",levels[0]);
   players[3] = new Player("l","e",levels[0]);
   players[4] = new Player("d","f",levels[0]);
   players[0].setScore(2000);
   players[1].setScore(500);
   players[2].setScore(250);
   players[3].setScore(300);
   players[4].setScore(3000);
   }
   public String registerPlayer(String inName,String inNickName){
      String msj = "Capacidad maxima alcanzada";
      boolean isEmpty = false;
      if(verifyNickName(inNickName)!=true){
         Player newPlayer = new Player(inName,inNickName,levels[0]);
         for(int i = 0; i<SIZE_OF_PlAYERS && !isEmpty;i++){
            if(players[i] == null){
               players[i] = newPlayer;
               isEmpty = true;
               msj = "Se ha agregado al jugador";
            }
         }
      }else{
         msj = "nickname ya registrado";
      }
      return msj;
   }
   /**
    * Method verifyNickName:Verify that the nickname of the player is not in use.
    * @param nickPlayer nick name of player
    * @return return a boolean thatindicate if there was a coincidece with the nickname
    */
   public boolean verifyNickName(String nickPlayer){
      boolean macth = false;
      for(int i = 0; i<SIZE_OF_PlAYERS;i++){
         if(players[i]!=null){
            if(players[i].getNickName().equals(nickPlayer)){
               macth= true;
            }
         }
      }
      return macth;
   }
   /**
    * Method searchPlayerByNickName:This method search a player by it´s nickname and return it´s position 
    * @param playerNick String: The nickname of player to search 
    * @return pos int:position of player in array of players
    */
   public int searchPlayerByNickName(String playerNick){
      int pos = -1;
      boolean isFound = false;
      for(int i = 0;i<SIZE_OF_PlAYERS && !isFound;i++){
         if(players[i]!=null){
            if(players[i].getNickName().equalsIgnoreCase(playerNick)){
               pos = i;
               isFound = true;
            }
         }
      }
      return pos;
   }
   /**
    * Method modifyPlayerScore:This method search for a player by it´s nickname and changes it´s score for a value give by user
    * @param newScore double:The new value of the player´s score
    * @param nickPlayer String:Nickname of player
    * @return mj String:Message informing if the process was successfull
    */
   public String modifyPlayerScore(double newScore,String nickPlayer){
      int pos1 = searchPlayerByNickName(nickPlayer);
      String mj = "No se pudo cambiar el puntaje del jugador";
      if(pos1!= -1){
         players[pos1].setScore(newScore);
         mj = "Se ha cambiado el puntaje del jugador";
      }
      return mj;
   }
   /**Method:This method search a player by it´s nickname then if th user has the needed points increase player level
    * @param nickNamePlayer String:NickName of player 
    * @return mj1 String:Message informing if the process was successfull
    */
   public String increasePlayerLevel(String nickNamePlayer){
      int pos2 = searchPlayerByNickName(nickNamePlayer);
      String mj1 = "No se encontro al jugador";
      double missingPoints = 0;
      int levelpos = 0;
      if(pos2!=-1){
            levelpos = searchLevelById(players[pos2].getLevelId());
            if(levelpos+1<=9){
               if(players[pos2].getScore()>levels[levelpos+1].getPointsToLevelUp()){
                  players[pos2].setlevel(levels[levelpos+1]);
                  mj1 = "El jugador ha subido al nivel " + players[pos2].getLevelId();
               }else{
                  missingPoints = levels[levelpos+1].getPointsToLevelUp()-players[pos2].getScore();
                  mj1 = "Los puntos que faltan para pasar al siguiente nivel son "+missingPoints;
               } 
            }else{
               mj1 = "Nivel maximo alcanzado";
            }
            
         }
      return mj1;
   }
   
   /**Method searchLevelById:This method search a level by it´s id and return it´s position in array
    * @param levelId int:id of level
    * @return pos3 int;position of level in level's array
    */
   public int searchLevelById(int levelId){
      int pos3 = -1;
      for(int i = 0;i<SIZE_OF_LEVELS;i++){
         if(levels[i]!=null){
            if(levels[i].getId()==levelId){
               pos3 = i; 
            }
         }
      }
      return pos3;
   }
   /**
    * Method RegisterEnemyInLevel:Create and of object of class enemy and add it to the respective level of id
    * @param id enemy id
    * @param inType int: number relationed to the type of enemy
    * @param inRemovePoints double:Points that the enemy remove from the player's score
    * @param inGivenPoints double:Points that give to the player
    * @param levelId id of level where the enemy will be added
    * @return Message indicating  if the operation was successfull
    */
   public String RegisterEnemyInLevel(String id,int inType,double inRemovePoints,double inGivenPoints,int levelId){
      String msj = "No se pudo agregar al enemigo";
      int levelPos = searchLevelById(levelId);
      if(levelPos != -1){
         msj = levels[levelPos].addEnemy(id, inType, inRemovePoints, inGivenPoints);
      }
      return msj;
   }
   /**
    * Method registerTreasureInlevel:This method search a level by it's id and add a treasure with the parameters and quantity type by user
    * @param name String:Name of the treasure 
    * @param url String:Url of treasure
    * @param givenPoints double:Points that the treasure will give to a player
    * @param quantity int:The number of treasure of the same type that will be add to the level
    * @param levelId the id of the level where treasure will be add
    * @return Message indicating  if the operation was successfull
    */
   public String registerTreasureInlevel(String name,String url,double givenPoints,int quantity,int levelId){
      String msj2 = "";
      int levelPos1 = searchLevelById(levelId);
      for(int i = 0;i<quantity;i++){
         if(levelPos1 != -1){
            msj2 += levels[levelPos1].addTreasure(name, url, givenPoints);
         }
      }
      return msj2;
   } 
   /**
    * Method showEnemiesAndTreausresOfLevel;Search a level by it's id and return a message with the enemies and treasures of the level
    * @param levelId String:id of level
    * @return msj String: Message with the information of treasures and enemies of the level
    */
   public String showEnemiesAndTreausresOfLevel(int levelId){
      int levelPos = searchLevelById(levelId);
      String msj = "";
      if(levelPos != -1){
         msj = levels[levelPos].ShowEnemiesAndTreasures();
      }
      return msj;
   }
   /**Method MostRepeatedTreasureOfLevels:Search for the most repeated treasure inall the levels of the game and return it´s name and quantity
    * @return msj String;Message with the information of most repeated treasure
    */
   public String MostRepeatedTreasureOfLevels(){
      String msj = "";
      String treasureCount = "";
      int count = 0;
      int count2 = 0;
      for(int i = 0;i<SIZE_OF_LEVELS;i++){
         for(int l = 0;l<SIZE_OF_TREASURES;l++){
            if(levels[i].getTreasures()[l]!=null){
               treasureCount = levels[i].getTreasures()[l].getName();
               if(levels[i].getTreasures()[l]!=null && levels[i].getTreasures()[l].getName()==treasureCount){
                  count += 1;
               }else if(levels[i].getTreasures()[l]!=null && levels[i].getTreasures()[l].getName()!=treasureCount){
                  count2 += 1;
               }
               if(count<count2){
                  treasureCount = levels[l].getTreasures()[i].getName();
                  count = count2;
                  count2 = 0;
               }
           }
         }
      }
      msj = "El tesoro mas repetido es: "+ treasureCount +" Cantidad: " +count;
      return msj;
   }
   /**
    * Method quantityOfTreasureInGame:Search a treasure by name and return it´s quantity in all the level of the game
    * @param treasureName String:name of the treasure
    * @return mj;Message with the informtion of treasure
    */
   public String quantityOfTreasureInGame(String treasureName){
      boolean found = false;
      int count = 0;
      String mj = "Tesoro no encontrado";
      for(int i = 0;i<SIZE_OF_LEVELS;i++){
         found = levels[i].searchTreasureByName(treasureName);
      }
      if(found==true){
         for(int i = 0;i<SIZE_OF_LEVELS;i++){
            for(int l = 0;l<SIZE_OF_TREASURES;l++){
               if(levels[i].getTreasures()[l]!=null && levels[i].getTreasures()[l].getName().equalsIgnoreCase(treasureName)){
                  count += 1;
               }
            }
         }
         mj = "Hay "+count+" unidades del tesoro "+treasureName;
      }
      return mj;
   }
   /**
    * Method quantityOfEnemyInGame:Search for a enemy by it's type and return it´s quantity in all the levels of the game
    * @param inType int:The number that is relationated with the type of enemy
    * @return Message with the quantity of the enemy in all the game
    */
   public String quantityOfEnemyInGame(int inType){
      String mj2 = "No se encontro al enemigo"; 
      int count = 0;
      String enType = "";
      TypeEnemy type = null;
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
      for(int i = 0;i<SIZE_OF_LEVELS;i++){
         for(int l = 0;l<SIZE_OF_ENEMIES;l++){
            if(levels[i].getEnemies()[l]!=null && levels[i].getEnemies()[l].getEnumType()==type){
               count += 1;
               enType = levels[i].getEnemies()[l].getType();
            }
         }
      }
         mj2 = "Hay "+count+" unidades del enemigo "+ enType;
      
      return mj2;
   }
   /**
    * Method getEnemyWithMostGivenPoints;Search for the enemy that give the biggest among of points to the player in all the game
    * @return mj String:Message with the information of the enemy
    */
   public String getEnemyWithMostGivenPoints(){
      String mj = "No se encontraron enemigos";
      Enemy chooseEnemy = null;
      double givenPoints = 0;
      int levelId = 0;
      boolean isFound = false;
      for(int i = 0;i<SIZE_OF_LEVELS && !isFound;i++){
         for(int l = 0;l<SIZE_OF_ENEMIES && !isFound;l++){
            if(levels[i].getEnemies()[l]!=null){
               givenPoints = levels[i].getEnemies()[l].getGivenPoints();
               chooseEnemy = levels[i].getEnemies()[l];
               levelId = levels[i].getId();
               isFound = true;
            }
         }
      }
      for(int i = 0;i<SIZE_OF_LEVELS;i++){
         for(int l = 0;l<SIZE_OF_ENEMIES;l++){
            if(levels[i].getEnemies()[l]!=null){
               if(givenPoints<levels[i].getEnemies()[l].getGivenPoints()){
                  givenPoints =levels[i].getEnemies()[l].getGivenPoints();
                  chooseEnemy = levels[i].getEnemies()[l];
                  levelId = levels[i].getId();
               }
            }
         }
      }
      mj = "El enemigo que da mas puntos es: "+ chooseEnemy.getType()+" con :"+givenPoints+"puntos ,"+"en el nivel: " + levelId; 
      return mj;
   }
   /**
    * MEthod getTop5playersByScore;This method search the 5 players with the biggest and return their information
    * @return Message with the information of the 5 players with the biggest score
    */
   public String getTop5playersByScore(){
      String mj = "";
      double score = 0;
      boolean found = false;
      Player[] topPlayers = new Player[5];
      Player[] inPlayer = new Player[SIZE_OF_PlAYERS];
      inPlayer = players;
      int count = 0;

      for(int i = 0;i<SIZE_OF_PlAYERS && !found;i++){
            if(players[i]!=null){
               topPlayers[0] = players[i];
               found = true;
            }
         }
      
         for(int i = 0;i<SIZE_OF_PlAYERS;i++){
            for(int j = 0;j<SIZE_OF_PlAYERS;j++){
               for(int l = 0;l<5;l++){
                  if(inPlayer[i]!=null && inPlayer[j]!=null && inPlayer[i].getScore()>inPlayer[j].getScore()){
                     topPlayers[l] = players[i];
                     players[i]= null;
                  }
               }
            }
         }
      
      for(int i = 0;i<5;i++){
         if(topPlayers[i]!=null){
            mj += "Jugador: "+topPlayers[i].getNickName()+" puntos: "+topPlayers[i].getScore()+"\n";
         }
      }
      return mj;
   }
   
}