package vendor.beta.tracker;

/**
 * Узел 13 схемы 2 — KL13 (полноценный класс, задан), лист иерархии.
 * Легенда: новая функциональность (фича) — конкретный тип задачи.
 */
public class Feature extends AbstractTask {

    public Feature(String title) {
        super(title);
    }

    /** f2 — фича оценивается по базовой формуле команды. */
    @Override
    public int estimate(int complexity, int velocity) {
        return complexity * velocity / 10 + 1;
    }

    /** f13 — специфичное действие: критерии приёмки фичи. */
    public String acceptanceCriteria(String scope) {
        return "Критерии приёмки «" + getTitle() + "»: " + scope + " работает по ТЗ";
    }
}
