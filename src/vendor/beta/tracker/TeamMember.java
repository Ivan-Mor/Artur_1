package vendor.beta.tracker;

/**
 * Узел 11 схемы 2 — белый узел, доопределён как KL11 (полноценный класс):
 * конкретный участник команды (есть наследник, но абстракция не обязательна).
 * Легенда: участник команды — ему назначают задачи (Assignable)
 * и он влияет на приоритеты (Prioritisable).
 */
public class TeamMember implements Assignable, Prioritisable {

    private final String name;
    private String currentTask;

    public TeamMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** f4 — назначить участника на задачу. */
    @Override
    public void assign(String task) {
        this.currentTask = task;
        System.out.println("[трекер] " + name + " назначен на задачу «" + task + "»");
    }

    /** f5 — приоритет: серьёзность плюс половина срочности, максимум 5. */
    @Override
    public int priority(int severity, int urgency) {
        return Math.min(5, severity + urgency / 2);
    }

    /** f11 — списать часы на задачу, вернуть остаток очков. */
    public int logWork(int pointsLeft, int spentHours) {
        return Math.max(0, pointsLeft - spentHours);
    }
}
