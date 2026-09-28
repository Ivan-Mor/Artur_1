package vendor.alpha.staff;

/**
 * Узел 15 схемы 1 — белый узел, доопределён как KL15 (полноценный класс):
 * нижний элемент дерева обязан быть реальным классом для создания объектов.
 * Легенда: табельный отчёт (учёт рабочего времени за месяц).
 */
public class TimesheetReport extends AbstractReport {

    public TimesheetReport(String title) {
        super(title);
    }

    /** Содержательная часть шаблона отчёта (f3 через AbstractReport). */
    @Override
    protected String details(String period) {
        return "отработано 168 часов, переработок нет";
    }

    /** f15 — выгрузка табеля в CSV за месяц. */
    public String exportCsv(String month) {
        return "CSV(" + getTitle() + "," + month + "): Иванов,160;Петров,168;Сидоров,140";
    }
}
