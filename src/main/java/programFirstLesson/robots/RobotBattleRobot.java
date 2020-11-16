package programFirstLesson.robots;

public class RobotBattleRobot implements Robot{
    @Override
    public void sayTheName() {
        System.out.println("I'm terminator T-1000");
    }

    @Override
    public void doTheJob() {
        System.out.printf("Boom!!!");
    }
}
