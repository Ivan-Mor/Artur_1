package vendor.beta.tracker;

/**
 * Узел 12 схемы 2 — KL12 (полноценный класс, задан), лист иерархии.
 * Легенда: дефект (баг) — конкретный тип задачи.
 */
public class Bug extends AbstractTask {

    public Bug(String title) {
        super(title);
    }

    /** f2 — баг оценивается дешевле: штраф 1 очко за риск регресса. */
    @Override
    public int estimate(int complexity, int velocity) {
        return Math.max(1, complexity * velocity / 10 - 1);
    }

    /** f12 — специфичное действие: шаги воспроизведения дефекта. */
    public String reproduce(String environment) {
        return "Воспроизведение «" + getTitle() + "» в среде " + environment
                + ": шаги записаны";
    }
}
