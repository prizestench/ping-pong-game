public class CreativeCPU extends CPUChallenging {

    HumanPaddle other;

    // Constructor takes in the opponent paddle as an argument
    public CreativeCPU(HumanPaddle other) {
        super();
        this.other = other;
    }

    public static double SPEED;

    // override pointscored function
    public void pointScored(boolean didCPUScore) {
        if (didCPUScore) {
            this.score++;
        }

        if (other.getScore() >= getScore()) {
            SPEED = CPU_CHALLENGING_PADDLE_SPEED + (other.getScore() - getScore());
            setSpeed(SPEED);
        }
    }

}
