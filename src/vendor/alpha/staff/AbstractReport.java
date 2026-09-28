package vendor.alpha.staff;

/**
 * Узел 9 схемы 1 — AKL9 (абстрактный класс, задан).
 * Легенда: базовый формируемый документ (отчёт).
 * f3 report() реализован общим шаблоном «шапка + содержимое»,
 * конкретное содержимое (details()) каждый вид отчёта даёт сам,
 * f9 header() — готовая шапка отчёта.
 */
public abstract class AbstractReport implements Reportable {

    private final String title;

    protected AbstractReport(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    /** f1 — документ сам выплаты не получает: для отчёта выплата всегда нулевая. */
    @Override
    public int pay(int base, int bonus) {
        return 0;
    }

    /** f3 — общий шаблон формирования отчёта. */
    @Override
    public String report(String period) {
        return header() + " за " + period + ": " + details(period);
    }

    /** f9 — шапка отчёта из скрытого заголовка документа. */
    public String header() {
        return "[Отчёт «" + title + "»]";
    }

    /** Содержательная часть — ответственность конкретного отчёта. */
    protected abstract String details(String period);
}
