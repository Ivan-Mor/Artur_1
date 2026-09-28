package company.trackerapp;

import vendor.beta.tracker.AbstractBoardItem;
import vendor.beta.tracker.AbstractTask;
import vendor.beta.tracker.Assignable;
import vendor.beta.tracker.Bug;
import vendor.beta.tracker.Comment;
import vendor.beta.tracker.Epic;
import vendor.beta.tracker.Estimable;
import vendor.beta.tracker.Exportable;
import vendor.beta.tracker.Feature;
import vendor.beta.tracker.Project;
import vendor.beta.tracker.Prioritisable;
import vendor.beta.tracker.Sprint;
import vendor.beta.tracker.TeamLead;
import vendor.beta.tracker.TeamMember;

/**
 * Класс управления бизнес-логикой (ход работы, п. 3.1–3.2) для задачи 2.
 * Пакет company.trackerapp вне иерархии vendor.beta.tracker.
 */
public class My_class {

    public static void main(String[] args) {
        System.out.println("=== Таск-трекер ===");

        // 1) Массив объектов базового типа Estimable (узел 2, I2):
        //    эпик, баг и фича в одном массиве.
        Estimable[] estimable = {
                new Epic("Редизайн личного кабинета"),
                new Bug("Падение при загрузке аватара"),
                new Feature("Тёмная тема")
        };

        // 2) Полиморфизм: оценка через ссылку базового типа.
        //    getClass().getSimpleName() показывает фактический тип объекта.
        System.out.println("\n--- Оценка задач через Estimable (полиморфизм) ---");
        for (Estimable e : estimable) {
            System.out.println(e.getClass().getSimpleName()
                    + ": estimate(8, 10) = " + e.estimate(8, 10));
        }

        // 3) Приведение типов вниз и специфичные методы.
        System.out.println("\n--- Downcast и специфичные методы ---");
        for (Estimable e : estimable) {
            if (e instanceof Bug) {
                Bug bug = (Bug) e;
                System.out.println(bug.reproduce("Linux, JDK 21"));
                System.out.println("Задаче «" + bug.getTitle() + "» "
                        + bug.ageDays(20, 28) + " дн.");
            } else if (e instanceof Feature) {
                Feature feature = (Feature) e;
                System.out.println(feature.acceptanceCriteria("переключатель темы"));
            }
        }

        // 4) Вызовы через ссылки конкретных классов.
        System.out.println("\n--- Вызовы через ссылки конкретных классов ---");
        Sprint sprint = new Sprint("успеть к релизу");
        sprint.setState("doing");
        System.out.println("Бёрндаун: " + sprint.burndown(40, 17) + " очков");
        System.out.println(sprint.validate("todo", "doing")
                ? "Переход todo->doing допустим" : "Переход запрещён");
        TeamLead lead = new TeamLead("Иван");
        System.out.println(lead.review("Добавить тёмную тему"));

        // 5) Вызовы через интерфейсные ссылки (узлы I3, I4, I5).
        System.out.println("\n--- Вызовы через интерфейсные ссылки ---");
        Exportable exportable = new Comment("Ольга", "воспроизводится только на стенде"); // I3
        System.out.println(exportable.export("markdown"));
        Exportable projectCard = new Project("Мобильный банк");                            // I3
        System.out.println(projectCard.export("csv"));
        Assignable assignable = new TeamMember("Пётр");                                    // I4
        assignable.assign("Тёмная тема");
        Prioritisable prioritisable = new TeamMember("Пётр");                              // I5
        System.out.println("priority(4, 3) = " + prioritisable.priority(4, 3));

        // 6) Вызовы через ссылки на абстрактный класс
        //    (узлы AKL7 AbstractTask и AKL9 AbstractBoardItem).
        System.out.println("\n--- Вызовы через ссылки на абстрактный класс ---");
        AbstractTask task = new Feature("Экспорт в PDF");
        System.out.println("Задаче «" + task.getTitle() + "» "
                + task.ageDays(1, 9) + " дн., оценка " + task.estimate(5, 10));
        AbstractBoardItem boardItem = new Sprint("спринт 24");
        System.out.println(boardItem.export("pdf"));
    }
}
