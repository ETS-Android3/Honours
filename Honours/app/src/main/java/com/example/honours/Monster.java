package com.example.honours;

public class Monster {

    private final int id;
    private final String monsterID;
    private final String filePath;
    private final String sound;


    public Monster(int id, String monsterID, String filePath, String sound) {
        this.id = id;
        this.monsterID = monsterID;
        this.filePath = filePath;
        this.sound = sound;


    }

    public int getID() {
        return this.id;
    }

    public String getMonsterID() {
        return this.monsterID;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getSound() {
        return this.sound;
    }


}
