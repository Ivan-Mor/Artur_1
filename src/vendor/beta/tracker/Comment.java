package vendor.beta.tracker;

/**
 * Узел 8 схемы 2 — белый узел, доопределён как KL8 (полноценный класс):
 * комментарий — конкретная сущность, наследники не нужны.
 * Легенда: комментарий к задаче, участвует в выгрузках.
 */
public class Comment implements Exportable {

    private final String author;
    private final String text;

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }

    /** f3 — экспорт комментария в заданном формате. */
    @Override
    public String export(String format) {
        return format.toUpperCase() + ": «" + text + "» (" + author + ")";
    }

    /** f8 — упомянуть участника в комментарии. */
    public String mention(String user) {
        return "@" + user + ", прошу посмотреть комментарий";
    }
}
