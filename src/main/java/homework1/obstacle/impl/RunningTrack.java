package homework1.obstacle.impl;

import homework1.obstacle.Obstacle;
import homework1.participant.Participant;

public class RunningTrack implements Obstacle {

    double length;

    public RunningTrack(double length) {
        this.length = length;
    }

    @Override
    public boolean run(Participant participant) {
        return participant.run(length);
    }

    @Override
    public String toString() {
        return "length=" + length;
    }
}
