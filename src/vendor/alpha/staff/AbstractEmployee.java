package vendor.alpha.staff;

/**
 * Узел 7 схемы 1 — AKL7 (абстрактный класс, задан).
 * Легенда: базовый сотрудник компании.
 * Инкапсуляция: имя и оклад скрыты (private), доступ — через геттеры.
 * Реализует Rateable частично (поэтому класс abstract):
 * f1 pay() — общая реализация, f2 rate() — каждый тип сотрудника считает сам.
 */
public abstract class AbstractEmployee implements Rateable {

    private final String name;
    private final int baseSalary;

    protected AbstractEmployee(String name, int baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    /** f1 — общий для всех сотрудников расчёт выплаты (сложение оклада и бонуса). */
    @Override
    public int pay(int base, int bonus) {
        return base + bonus;
    }

    /** f7 — стаж в полных месяцах: разница лет, умноженная на 12. */
    public int seniorityMonths(int hireYear, int currentYear) {
        return Math.max(0, currentYear - hireYear) * 12;
    }

    /** f2 — рейтинг сотрудник каждого типа определяет по-своему. */
    @Override
    public abstract int rate(int hours, int ratePerHour);
}
