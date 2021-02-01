package programFirstLesson.robots;

public class RobotSecretary implements Robot{
    @Override
    public void sayTheName() {
        System.out.println("I'm the Secretary");
    }

    @Override
    public void doTheJob() {
        System.out.println("Please take this papers");
    }
}
