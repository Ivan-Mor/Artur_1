package vendor.alpha.staff;

/**
 * Узел 2 схемы 1 — I2 (интерфейс, задан).
 * Легенда: «подлежащий аттестации» — участник ежеквартальной оценки.
 * f2 — расчёт рейтинга за период.
 */
public interface Rateable extends Payable {

    int rate(int hours, int ratePerHour);
}
