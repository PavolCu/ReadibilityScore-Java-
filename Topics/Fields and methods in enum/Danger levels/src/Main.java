

enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private int level;

    DangerLevel(int levelRisk) {
        this.level = levelRisk;
    }

    public int getLevel() {
        return level;
    }

    public class TestDangerLevel {
        static {
            DangerLevel high = DangerLevel.HIGH;
            DangerLevel medium = DangerLevel.MEDIUM;

            System.out.println(high.getLevel() > medium.getLevel());
        }
    }
}