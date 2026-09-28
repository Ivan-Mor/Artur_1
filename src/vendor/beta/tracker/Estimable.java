package vendor.beta.tracker;

/**
 * Узел 2 схемы 2 — I2 (интерфейс, задан; верхний уровень, корневого I1 в схеме 2 нет).
 * Легенда (таск-трекер): «подлежащий оценке» — элемент бэклога.
 * f2 — оценка трудозатрат в стори-поинтах.
 */
public interface Estimable {

    int estimate(int complexity, int velocity);
}
