package vendor.beta.tracker;

/**
 * Узел 9 схемы 2 — AKL9 (абстрактный класс, задан).
 * Легенда: карточка на доске (kanban/scrum board).
 * f3 export() реализован общим форматом «формат [статус] название»,
 * конкретная карточка задаёт свой label(); f9 validate() — проверка
 * допустимости перевода карточки по статусам.
 */
public abstract class AbstractBoardItem implements Exportable {

    private String state = "todo";

    public String getState() {
        return state;
    }

    /** Перевод карточки по доске (защита от недопустимых статусов). */
    public void setState(String newState) {
        if ("todo".equals(newState) || "doing".equals(newState) || "done".equals(newState)) {
            this.state = newState;
        }
    }

    /** f3 — общий шаблон выгрузки карточки. */
    @Override
    public String export(String format) {
        return format.toUpperCase() + " [" + state + "] " + label();
    }

    /** f9 — переход допустим, кроме «из done» и «сам в себя». */
    public boolean validate(String from, String to) {
        return !"done".equals(from) && !from.equals(to);
    }

    /** Название карточки — ответственность конкретного элемента доски. */
    protected abstract String label();
}
