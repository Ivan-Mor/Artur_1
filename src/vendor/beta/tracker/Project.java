package vendor.beta.tracker;

/**
 * Узел 10 схемы 2 — белый узел, доопределён как KL10 (полноценный класс).
 * Легенда: проект — выгружается в отчёты (Exportable)
 * и требует назначения ответственного (Assignable).
 */
public class Project implements Exportable, Assignable {

    private final String name;
    private String lead;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** f3 — экспорт карточки проекта. */
    @Override
    public String export(String format) {
        String responsible = lead == null ? "не назначен" : lead;
        return format.toUpperCase() + ": проект «" + name + "», ответственный — " + responsible;
    }

    /** f4 — назначить ответственного за проект. */
    @Override
    public void assign(String to) {
        this.lead = to;
    }

    /** f10 — создать спринт с заданной целью. */
    public String createSprint(String goal) {
        return "В проекте «" + name + "» создан спринт с целью: " + goal;
    }
}
