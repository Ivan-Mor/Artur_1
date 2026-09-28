package vendor.beta.tracker;

/**
 * Узел 7 схемы 2 — AKL7 (абстрактный класс, задан).
 * Легенда: базовая задача трекера.
 * Инкапсуляция: заголовок и исполнитель скрыты, доступ через геттеры.
 * f2 estimate() каждый тип задачи оценивает по-своему (abstract),
 * f7 ageDays() — общая реализация («часть функций реализована»).
 */
public abstract class AbstractTask implements Estimable {

    private final String title;
    private String assignee;

    protected AbstractTask(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAssignee() {
        return assignee;
    }

    /** f7 — возраст задачи в днях (не бывает отрицательным). */
    public int ageDays(int createdDay, int today) {
        return Math.max(0, today - createdDay);
    }

    /** f2 — формула оценки зависит от типа задачи. */
    @Override
    public abstract int estimate(int complexity, int velocity);
}
