package vendor.alpha.staff;

/**
 * Узел 11 схемы 1 — белый узел, доопределён как KL11 (полноценный класс):
 * имеет наследника (узел 14), но сам может быть конкретным сотрудником,
 * абстракция здесь не требуется — минимизируем уровень абстракции.
 * Легенда: офисный сотрудник — получает рассылки и премии.
 */
public class OfficeWorker implements Notifiable, Premiumable {

    private final String name;

    public OfficeWorker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** f1 — выплата офисному сотруднику: оклад + половина бонуса. */
    @Override
    public int pay(int base, int bonus) {
        return base + bonus / 2;
    }

    /** f4 — принять корпоративное уведомление. */
    @Override
    public void notify(String message) {
        System.out.println("[уведомление] " + name + " получил: " + message);
    }

    /** f5 — премия: процент от оклада. */
    @Override
    public int premium(int salary, int percent) {
        return salary * percent / 100;
    }

    /** f11 — заказать канцелярию/расходники. */
    public String orderSupplies(String items) {
        return "Заказ оформлен (" + name + "): " + items;
    }
}
