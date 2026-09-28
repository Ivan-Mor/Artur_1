package vendor.alpha.staff;

/**
 * Узел 14 схемы 1 — KL14 (полноценный класс, задан), лист иерархии.
 * Легенда: офис-менеджер — OfficeWorker с дополнительными обязанностями.
 * Зависимость с DepartmentHead (ребро 10–14 схемы): мероприятие
 * проводится по согласованию с руководителем отдела.
 */
public class OfficeManager extends OfficeWorker {

    public OfficeManager(String name) {
        super(name);
    }

    /** f14 — организовать мероприятие по согласованию с руководителем отдела. */
    public String planEvent(String occasion, DepartmentHead approver) {
        return getName() + " организует «" + occasion + "» (согласовано: "
                + approver.getName() + ")";
    }
}
