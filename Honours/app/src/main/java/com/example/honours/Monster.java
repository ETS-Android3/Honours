package com.example.honours;

public class Monster {


        private final int monsterID;
        private final String filePath;
        private final String sound;
        private final Boolean owns;



        public Monster(int monsterID, String filePath, String sound, Boolean owns) {

            this.monsterID = monsterID;
            this.filePath = filePath;
this.sound = sound;
this.owns =owns;


        }

    public int getMonsterID() {
        return this.monsterID;
    }
    public String getFilePath() {
        return this.filePath;
    }
    public String getSound() {
        return this.sound;
    }
    public Boolean getOwns() {
        return this.owns;
    }

}
