package vendor.beta.tracker;

/**
 * Узел 3 схемы 2 — I3 (интерфейс, задан).
 * Легенда: «выгружаемый» — сущность, которую можно экспортировать
 * во внешний формат (markdown, csv, pdf...).
 * f3 — экспорт сущности в заданном формате.
 */
public interface Exportable {

    String export(String format);
}
