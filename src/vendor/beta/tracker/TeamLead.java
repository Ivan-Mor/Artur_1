package vendor.beta.tracker;

/**
 * Узел 14 схемы 2 — KL14 (полноценный класс, задан), лист иерархии.
 * Легенда: тимлид — участник команды с правами ревью и планирования.
 * Зависимость с Project (ребро 10–14 схемы): тимлид планирует
 * спринты в проекте.
 */
public class TeamLead extends TeamMember {

    public TeamLead(String name) {
        super(name);
    }

    /** f14 — специфичное действие: ревью merge-запроса. */
    public String review(String mergeRequest) {
        return "MR «" + mergeRequest + "» одобрен";
    }

    /** Использование Project (зависимость из схемы): план спринта. */
    public String planSprint(Project project) {
        return getName() + ": " + project.createSprint("взять задачи из бэклога");
    }
}
