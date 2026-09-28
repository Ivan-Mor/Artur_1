package vendor.beta.tracker;

/**
 * Узел 6 схемы 2 — KL6 (полноценный класс, задан).
 * Легенда: эпик — крупная работа, объединяющая задачи.
 */
public class Epic implements Estimable {

    private final String title;

    public Epic(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    /** f2 — оценка эпика: суммарная сложность на скорости команды. */
    @Override
    public int estimate(int complexity, int velocity) {
        return complexity * velocity / 10 + 1;
    }

    /** f6 — осталось очков до закрытия эпика. */
    public int totalPoints(int done, int remaining) {
        return done + remaining;
    }
}
