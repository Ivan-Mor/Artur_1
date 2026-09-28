package vendor.alpha.staff;

/**
 * Узел 5 схемы 1 — I5 (интерфейс, задан).
 * Легенда: «премируемый» — участвует в схеме премирования.
 * f5 — расчёт премии от оклада и процента.
 */
public interface Premiumable extends Payable {

    int premium(int salary, int percent);
}
