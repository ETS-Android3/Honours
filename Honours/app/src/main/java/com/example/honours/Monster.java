package com.example.honours;

public class Monster {


        private final int monsterID;
        private final String filePath;



        public Monster(int monsterID, String filePath) {

            this.monsterID = monsterID;
            this.filePath = filePath;



        }

    public int getMonsterID() {
        return this.monsterID;
    }
    public String getFilePath() {
        return this.filePath;
    }


}
