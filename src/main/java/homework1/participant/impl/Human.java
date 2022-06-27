package homework1.participant.impl;

import homework1.participant.Participant;

public class Human implements Participant {

    double heightJump;
    double lengthRun;

    public Human(double heightJump, double lengthRun) {
        this.heightJump = heightJump;
        this.lengthRun = lengthRun;
    }

    @Override
    public boolean jump(double heightObstacle) {
        System.out.println("Прыгнул");
        return heightJump > heightObstacle;
    }

    @Override
    public boolean run(double lengthRoad) {
        System.out.println("Побежал");
        return lengthRun > lengthRoad;
    }

    @Override
    public String toString() {
        return "heightJump=" + heightJump + "\nlengthRun=" + lengthRun;
    }
}
