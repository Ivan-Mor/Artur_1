package vendor.alpha.staff;

/**
 * Узел 13 схемы 1 — KL13 (полноценный класс, задан), лист иерархии.
 * Легенда: инженер по тестированию — конкретный тип сотрудника.
 */
public class QaEngineer extends AbstractEmployee {

    public QaEngineer(String name) {
        super(name, 2000);
    }

    /** f1 — выплата тестировщику: оклад + бонус + надбавка за найденные дефекты. */
    @Override
    public int pay(int base, int bonus) {
        return base + bonus + 100;
    }

    /** f2 — рейтинг тестировщика: выработка с коэффициентом 0.8. */
    @Override
    public int rate(int hours, int ratePerHour) {
        return hours * ratePerHour * 8 / 10;
    }

    /** f13 — специфичное действие: протестировать функциональность. */
    public String testFeature(String feature) {
        return getName() + " протестировал функциональность «" + feature + "»: дефектов не найдено";
    }
}
