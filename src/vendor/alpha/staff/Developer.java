package vendor.alpha.staff;

/**
 * Узел 12 схемы 1 — KL12 (полноценный класс, задан), лист иерархии.
 * Легенда: разработчик — конкретный тип сотрудника.
 */
public class Developer extends AbstractEmployee {

    public Developer(String name) {
        super(name, 2500);
    }

    /** f2 — рейтинг разработчика: полная выработка. */
    @Override
    public int rate(int hours, int ratePerHour) {
        return hours * ratePerHour;
    }

    /** f12 — специфичное действие: написать код по задаче. */
    public String writeCode(String task) {
        return getName() + " реализовал задачу «" + task + "»";
    }
}
