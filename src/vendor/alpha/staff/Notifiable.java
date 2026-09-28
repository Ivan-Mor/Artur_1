package vendor.alpha.staff;

/**
 * Узел 4 схемы 1 — I4 (интерфейс, задан).
 * Легенда: «получающий уведомления» (корпоративная рассылка).
 * f4 — принять и обработать уведомление.
 */
public interface Notifiable extends Payable {

    void notify(String message);
}
