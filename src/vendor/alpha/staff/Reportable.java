package vendor.alpha.staff;

/**
 * Узел 3 схемы 1 — I3 (интерфейс, задан).
 * Легенда: «формирующий отчётность».
 * f3 — сформировать текстовый отчёт за период.
 */
public interface Reportable extends Payable {

    String report(String period);
}
