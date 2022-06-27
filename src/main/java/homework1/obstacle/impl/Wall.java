package homework1.obstacle.impl;

import homework1.obstacle.Obstacle;
import homework1.participant.Participant;

public class Wall implements Obstacle {

    double height;

    public Wall(double height) {
        this.height = height;
    }

    @Override
    public boolean run(Participant participant) {
        return participant.jump(height);
    }

    @Override
    public String toString() {
        return "height=" + String.valueOf(height).substring(0, 4);
    }
}
