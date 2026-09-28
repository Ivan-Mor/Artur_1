package vendor.alpha.staff;

/**
 * Узел 8 схемы 1 — белый узел, доопределён как KL8 (полноценный класс):
 * ниже него наследников нет, абстракция не нужна.
 * Легенда: HR-специалист (кадровик), готовит отчёты по персоналу.
 */
public class HrSpecialist implements Reportable {

    private final String name;

    public HrSpecialist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** f1 — выплата кадровику: оклад + бонус. */
    @Override
    public int pay(int base, int bonus) {
        return base + bonus;
    }

    /** f3 — кадровый отчёт за период. */
    @Override
    public String report(String period) {
        return "Кадровый отчёт " + name + " за " + period + ": 12 наймов, 3 увольнения";
    }

    /** f8 — итог собеседования с кандидатом. */
    public String interview(String candidate) {
        return "Кандидат " + candidate + " — рекомендован к найму";
    }
}
