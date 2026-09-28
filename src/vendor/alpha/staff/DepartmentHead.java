package vendor.alpha.staff;

/**
 * Узел 10 схемы 1 — белый узел, доопределён как KL10 (полноценный класс).
 * Легенда: руководитель отдела — сам формирует отчёты (Reportable)
 * и рассылает уведомления подчинённым (Notifiable).
 */
public class DepartmentHead implements Reportable, Notifiable {

    private final String name;

    public DepartmentHead(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** f1 — выплата руководителю отдела: двойной оклад + бонус. */
    @Override
    public int pay(int base, int bonus) {
        return base * 2 + bonus;
    }

    /** f3 — сводка отдела за период. */
    @Override
    public String report(String period) {
        return "Отчёт руководителя отдела " + name + " за " + period + ": план выполнен";
    }

    /** f4 — довести уведомление до отдела. */
    @Override
    public void notify(String message) {
        System.out.println("[рассылка отдела] " + name + " сообщает: " + message);
    }

    /** f10 — согласовать отпуск (не длиннее двух недель — без комиссии). */
    public boolean approveVacation(String employee, int days) {
        return days > 0 && days <= 14;
    }
}
