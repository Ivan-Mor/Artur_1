package vendor.beta.tracker;

/**
 * Узел 15 схемы 2 — белый узел, доопределён как KL15 (полноценный класс):
 * нижний элемент дерева обязан быть реальным классом для создания объектов.
 * Легенда: спринт — карточка доски со своим бёрндауном.
 */
public class Sprint extends AbstractBoardItem {

    private final String goal;

    public Sprint(String goal) {
        this.goal = goal;
    }

    /** Название карточки для общего шаблона export() (f3). */
    @Override
    protected String label() {
        return "Спринт «" + goal + "»";
    }

    /** f15 — бёрндаун: сколько очков осталось до конца спринта. */
    public int burndown(int totalPoints, int donePoints) {
        return Math.max(0, totalPoints - donePoints);
    }
}
