package homework1;

/*
Java. Уровень 2. Урок 1. Объектно-ориентированное программирование Java.

1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Все они будут участниками соревнования. Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль). Важно: общего класса у них быть не должно. Их можно объеденить по классу participant. И создать массив на основании этого интерфейса. Этот интерфейс может наследовать другие интерфейсы необходимые для прохождения совместных испытаний участников в рамках соревнований - прыжки и бег.

2.Создайте два класса с методами: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).

3.Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.

4. У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки. Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идёт.
*/

import homework1.obstacle.Obstacle;
import homework1.obstacle.impl.RunningTrack;
import homework1.obstacle.impl.Wall;
import homework1.participant.impl.Cat;
import homework1.participant.impl.Human;
import homework1.participant.Participant;
import homework1.participant.impl.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static final int COUNT_PARTICIPANTS = 8;
    static final int COUNT_OBSTACLE = 10;
    static Random random = new Random();

    public static List<Participant> createParticipantList(int count) {
        List<Participant> participantList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int id = random.nextInt(3);
            if (id == 1) {
                participantList.add(new Cat(random.nextDouble() * 2.5, random.nextInt(200)));
            } else if (id == 2) {
                participantList.add(new Human(random.nextDouble() * 2, random.nextInt(300)));
            } else {
                participantList.add(new Robot(random.nextDouble() * 1, random.nextInt(1000)));
            }
        }
        return participantList;
    }

    public static List<Obstacle> createObstacleList(int count) {
        List<Obstacle> obstacleList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int id = random.nextInt(2);
            if (id == 1) {
                obstacleList.add(new RunningTrack(random.nextInt(100)));
            } else {
                obstacleList.add(new Wall(random.nextDouble() * 0.6));
            }
        }
        return obstacleList;
    }

    public static void main(String[] args) {
        List<Participant> participantList = createParticipantList(COUNT_PARTICIPANTS);
        List<Obstacle> obstacleList = createObstacleList(COUNT_OBSTACLE);

        for (int i = 0; i < participantList.size(); i++) {
            Participant participant = participantList.get(i);

            System.out.println("\n#" + i + " " + participant.getClass().getSimpleName());
            System.out.println(participant);

            System.out.println("\nСтарт: ");
            if (runRoadObstacle(obstacleList, participant)) {
                System.out.println("Финишировал!");
            } else {
                System.out.println("Выбыл!");
            }
        }
    }

    private static boolean runRoadObstacle(List<Obstacle> obstacleList, Participant participant) {
        for (int j = 0; j < obstacleList.size(); j++) {
            Obstacle obstacle = obstacleList.get(j);
            System.out.println(obstacle.getClass().getSimpleName() + " \n" + obstacle);
            if (!obstacle.run(participant)) {
                return false;
            }
        }
        return true;
    }
}
