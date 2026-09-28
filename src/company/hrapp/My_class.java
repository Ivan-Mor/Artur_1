package company.hrapp;

import vendor.alpha.staff.AbstractEmployee;
import vendor.alpha.staff.AbstractReport;
import vendor.alpha.staff.DepartmentHead;
import vendor.alpha.staff.Developer;
import vendor.alpha.staff.HrSpecialist;
import vendor.alpha.staff.Manager;
import vendor.alpha.staff.Notifiable;
import vendor.alpha.staff.OfficeManager;
import vendor.alpha.staff.OfficeWorker;
import vendor.alpha.staff.Payable;
import vendor.alpha.staff.Premiumable;
import vendor.alpha.staff.QaEngineer;
import vendor.alpha.staff.Rateable;
import vendor.alpha.staff.Reportable;
import vendor.alpha.staff.TimesheetReport;

/**
 * Класс управления бизнес-логикой (ход работы, п. 3.1–3.2).
 * Находится в отдельном пакете company.hrapp, ВНЕ иерархии
 * vendor.alpha.staff — как клиентский код, использующий
 * библиотеку, полученную от субподрядчика.
 *
 * Демонстрирует:
 *  1) создание массива объектов базового типа;
 *  2) вызовы методов через ссылки базового типа (полиморфизм);
 *  3) приведение типов вниз и вызов специфичных методов;
 *  4) вызовы через интерфейсные ссылки;
 *  5) вызовы через ссылки на абстрактный класс.
 */
public class My_class {

    public static void main(String[] args) {
        System.out.println("=== Система управления персоналом ===");

        // 1) Массив объектов базового типа Payable (узел 1, I1):
        //    в массиве рядом лежат объекты разных классов иерархии.
        Payable[] payable = {
                new Manager("Анна"),
                new Developer("Борис"),
                new QaEngineer("Вера"),
                new DepartmentHead("Глеб"),
                new OfficeWorker("Дарья"),
                new TimesheetReport("Табель")
        };

        // 2) Полиморфизм: один и тот же вызов через ссылку базового типа,
        //    у каждого объекта работает своя реализация.
        //    getClass().getSimpleName() показывает фактический тип объекта.
        System.out.println("\n--- Выплата всем через Payable (полиморфизм) ---");
        for (Payable p : payable) {
            System.out.println(p.getClass().getSimpleName()
                    + ": pay(1000, 250) = " + p.pay(1000, 250));
        }

        // 3) Приведение типов ВНИЗ (явное) и вызов специфичных методов.
        System.out.println("\n--- Downcast и специфичные методы ---");
        for (Payable p : payable) {
            if (p instanceof Developer) {
                Developer dev = (Developer) p;              // вниз к классу
                System.out.println(dev.writeCode("Лаб.1: иерархия"));
                System.out.println(dev.getName() + ", стаж: "
                        + dev.seniorityMonths(2020, 2026) + " мес.");
            } else if (p instanceof QaEngineer) {
                QaEngineer qa = (QaEngineer) p;
                System.out.println(qa.testFeature("поиск сотрудников"));
            } else if (p instanceof TimesheetReport) {
                TimesheetReport ts = (TimesheetReport) p;
                System.out.println(ts.exportCsv("сентябрь"));
            }
        }
        // Приведение вниз к подынтерфейсу (Payable -> Notifiable).
        Notifiable n = (Notifiable) payable[3];             // DepartmentHead
        n.notify("общее собрание в 10:00");

        // 4) Вызовы любого метода иерархии через имя объекта класса
        //    (обычные ссылки на конкретные классы).
        System.out.println("\n--- Вызовы через ссылки конкретных классов ---");
        Manager manager = new Manager("Анна");
        System.out.println("KPI группы: " + manager.teamKpi(18, 20) + "%");
        HrSpecialist hr = new HrSpecialist("Елена");
        System.out.println(hr.report("III квартал"));
        System.out.println(hr.interview("Сидоров"));
        OfficeManager officeManager = new OfficeManager("Жанна");
        System.out.println(officeManager.orderSupplies("бумага, тонер"));
        System.out.println(officeManager.planEvent("День компании",
                new DepartmentHead("Глеб")));

        // 5) Вызовы через интерфейсные ссылки (приведение объекта
        //    к типу интерфейса) — узлы I2, I3, I4, I5.
        System.out.println("\n--- Вызовы через интерфейсные ссылки ---");
        Rateable rateable = new Manager("Анна");            // I2
        System.out.println("rate(160, 12) = " + rateable.rate(160, 12));
        Reportable reportable = new DepartmentHead("Глеб"); // I3
        System.out.println(reportable.report("III квартал"));
        Notifiable notifiable = new OfficeWorker("Дарья");  // I4
        notifiable.notify("завтра короткий день");
        // I5 (Premiumable) реализует OfficeWorker:
        Premiumable premiumable = new OfficeWorker("Дарья");
        System.out.println("premium(1000, 15) = " + premiumable.premium(1000, 15));

        // 6) Вызовы через ссылки на абстрактный класс
        //    (узлы AKL7 AbstractEmployee и AKL9 AbstractReport).
        System.out.println("\n--- Вызовы через ссылки на абстрактный класс ---");
        AbstractEmployee employee = new Developer("Борис");
        System.out.println("rate(160, 10) = " + employee.rate(160, 10)
                + ", стаж: " + employee.seniorityMonths(2021, 2026) + " мес.");
        AbstractReport report = new TimesheetReport("Табель");
        System.out.println(report.report("сентябрь"));
    }
}
