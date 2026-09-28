package vendor.alpha.staff;

/**
 * Узел 6 схемы 1 — KL6 (полноценный класс, задан).
 * Легенда: руководитель группы (не в штате AbstractEmployee,
 * аттестуется по собственной схеме — реализует Rateable напрямую).
 */
public class Manager implements Rateable {

    private final String name;

    public Manager(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** f1 — выплата руководителю: оклад + бонус + надбавка за роль. */
    @Override
    public int pay(int base, int bonus) {
        return base + bonus + 500;
    }

    /** f2 — рейтинг руководителя: десятая часть выработки. */
    @Override
    public int rate(int hours, int ratePerHour) {
        return hours * ratePerHour / 10;
    }

    /** f6 — KPI группы: процент выполненных задач от запланированных. */
    public int teamKpi(int done, int planned) {
        return planned == 0 ? 0 : done * 100 / planned;
    }
}
