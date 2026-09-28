package vendor.beta.tracker;

/**
 * Узел 5 схемы 2 — I5 (интерфейс, задан).
 * Легенда: «приоритизируемый» — участие в расчёте приоритетов бэклога.
 * f5 — расчёт приоритета из серьёзности и срочности.
 */
public interface Prioritisable {

    int priority(int severity, int urgency);
}
