# Лабораторная работа 1. «Работа с иерархическими структурами классов UML и Java»

Тема: **система управления персоналом и таск-трекер**.
Задача 1 (схема 1, с корневым интерфейсом) → система управления персоналом.
Задача 2 (схема 2, без корневого узла) → таск-трекер.

## Структура проекта

```
Lab1/
├── src/
│   ├── vendor/alpha/staff/    — задача 1: иерархия «Персонал» (пакет-«библиотека» субподрядчика А)
│   ├── vendor/beta/tracker/   — задача 2: иерархия «Таск-трекер» (пакет-«библиотека» субподрядчика Б)
│   ├── company/hrapp/         — задача 1: My_class — класс управления бизнес-логикой (вне иерархии)
│   └── company/trackerapp/    — задача 2: My_class — класс управления бизнес-логикой (вне иерархии)
├── uml/
│   ├── task1_staff.puml / .png   — UML-диаграмма классов задачи 1
│   └── task2_tracker.puml / .png — UML-диаграмма классов задачи 2
├── build_and_run.sh           — сборка и запуск (javac/java)
└── README.md
```

## Сборка и запуск

```bash
./build_and_run.sh
# либо вручную:
javac -d out $(find src -name "*.java")
java -cp out company.hrapp.My_class      # задача 1
java -cp out company.trackerapp.My_class # задача 2
```

Для IntelliJ IDEA: открыть папку `Lab1` как проект, `src` пометить как Sources Root,
запустить `My_class#main` из нужного пакета.

## Задача 1. Система управления персоналом (схема 1)

### Таблица соответствия узлов схемы 1 и классов

| Узел | Тип по схеме | Сущность в коде | Роль в легенде | Своя функция |
|------|--------------|-----------------|----------------|--------------|
| 1 | I1 (задан) | `Payable` | «подлежащий оплате» | `pay(base, bonus)` |
| 2 | I2 (задан) | `Rateable extends Payable` | «подлежащий аттестации» | `rate(hours, ratePerHour)` |
| 3 | I3 (задан) | `Reportable extends Payable` | «формирующий отчёты» | `report(period)` |
| 4 | I4 (задан) | `Notifiable extends Payable` | «получающий уведомления» | `notify(message)` |
| 5 | I5 (задан) | `Premiumable extends Payable` | «премируемый» | `premium(salary, percent)` |
| 6 | KL6 (задан) | `Manager implements Rateable` | руководитель группы | `teamKpi(done, planned)` |
| 7 | AKL7 (задан) | `AbstractEmployee implements Rateable` | базовый сотрудник | `seniorityMonths(hireYear, currentYear)` |
| 8 | белый → **KL8** | `HrSpecialist implements Reportable` | HR-специалист | `interview(candidate)` |
| 9 | AKL9 (задан) | `AbstractReport implements Reportable` | базовый отчёт | `header()` |
| 10 | белый → **KL10** | `DepartmentHead implements Reportable, Notifiable` | руководитель отдела | `approveVacation(employee, days)` |
| 11 | белый → **KL11** | `OfficeWorker implements Notifiable, Premiumable` | офисный сотрудник | `orderSupplies(items)` |
| 12 | KL12 (задан) | `Developer extends AbstractEmployee` | разработчик | `writeCode(task)` |
| 13 | KL13 (задан) | `QaEngineer extends AbstractEmployee` | тестировщик | `testFeature(feature)` |
| 14 | KL14 (задан) | `OfficeManager extends OfficeWorker` | офис-менеджер | `planEvent(occasion, approver)` |
| 15 | белый → **KL15** | `TimesheetReport extends AbstractReport` | табельный отчёт | `exportCsv(month)` |

### Обоснование доопределения (этап «а»)

Правило: снизу вверх — сначала KL (везде, где допустимо), затем AKL, затем I;
нижние элементы дерева — обязательно реальные классы; уже заданные типы сохраняются;
абстракцию минимизируем.

* Узлы **8, 10, 11** не имеют наследников ниже либо наследники могут наследоваться от класса →
  становятся реальными классами (KL8, KL10, KL11).
* Узел **11** — родитель узла 14 (KL14, задан классом) → 11 может быть классом, а не абстракцией.
* Узел **15** — нижний элемент дерева → реальный класс KL15, наследуется от AKL9 (ребро 9–15).
* Множественные входящие рёбра (3+4→10, 4+5→11) реализованы множественной реализацией
  интерфейсов Java (`implements Reportable, Notifiable` и т.п.).
* Ребро 10–14 не может быть наследованием (у KL14 уже есть родитель KL11) и трактуется
  как **зависимость** («использует»): `planEvent(..., DepartmentHead approver)`.
* AKL7 реализует `Rateable` частично: `pay()` и `seniorityMonths()` реализованы, `rate()` — абстрактный.
* AKL9 реализует `Reportable` частично: `pay()` (через наследование от `Payable`), `report()`
  (шаблон «шапка+содержимое») и `header()` реализованы, `details()` — абстрактный.

### Легенда (реальный мир)

Компания ведёт кадровый учёт и расчёт заработной платы. Каждый участник расчётов
«подлежит оплате» (`Payable`). Сотрудники аттестуются (`Rateable`), отделы сдают отчётность
(`Reportable`) и получают корпоративные уведомления (`Notifiable`), часть персонала
премируется (`Premiumable`). Общие правила расчёта выплаты и стажа закреплены в базовом
классе сотрудника `AbstractEmployee`, а рейтинг каждый тип сотрудника (разработчик,
тестировщик) считает по-своему. Документы компании строятся по общему шаблону
`AbstractReport` (шапка + содержимое): например, табельный отчёт `TimesheetReport`
дополняет шаблон своей содержательной частью и выгрузкой в CSV.

## Задача 2. Таск-трекер (схема 2)

### Таблица соответствия узлов схемы 2 и классов

| Узел | Тип по схеме | Сущность в коде | Роль в легенде | Своя функция |
|------|--------------|-----------------|----------------|--------------|
| 2 | I2 (задан, верхний) | `Estimable` | «подлежащий оценке» | `estimate(complexity, velocity)` |
| 3 | I3 (задан, верхний) | `Exportable` | «выгружаемый в отчёты» | `export(format)` |
| 4 | I4 (задан, верхний) | `Assignable` | «подлежащий назначению» | `assign(to)` |
| 5 | I5 (задан, верхний) | `Prioritisable` | «приоритизируемый» | `priority(severity, urgency)` |
| 6 | KL6 (задан) | `Epic implements Estimable` | эпик | `totalPoints(done, remaining)` |
| 7 | AKL7 (задан) | `AbstractTask implements Estimable` | базовая задача | `ageDays(createdDay, today)` |
| 8 | белый → **KL8** | `Comment implements Exportable` | комментарий | `mention(user)` |
| 9 | AKL9 (задан) | `AbstractBoardItem implements Exportable` | карточка доски | `validate(from, to)` |
| 10 | белый → **KL10** | `Project implements Exportable, Assignable` | проект | `createSprint(goal)` |
| 11 | белый → **KL11** | `TeamMember implements Assignable, Prioritisable` | участник команды | `logWork(pointsLeft, spentHours)` |
| 12 | KL12 (задан) | `Bug extends AbstractTask` | дефект | `reproduce(environment)` |
| 13 | KL13 (задан) | `Feature extends AbstractTask` | фича | `acceptanceCriteria(scope)` |
| 14 | KL14 (задан) | `TeamLead extends TeamMember` | тимлид | `review(mergeRequest)` |
| 15 | белый → **KL15** | `Sprint extends AbstractBoardItem` | спринт | `burndown(totalPoints, donePoints)` |

### Обоснование доопределения (этап «а»)

Аналогично задаче 1, но у схемы 2 нет корневого узла I1 — интерфейсы I2…I5 верхнего уровня.

* Узлы **8, 10, 11, 15** доопределены реальными классами (минимум абстракции,
  нижние элементы — классы).
* Ребро 10–14 трактуется как зависимость: `TeamLead.planSprint(Project project)`.
* AKL7 реализует `Estimable` частично: `ageDays()` и геттеры реализованы, `estimate()` — абстрактный.
* AKL9 реализует `Exportable` частично: `export()`, `validate()`, `setState()` реализованы,
  `label()` — абстрактный.

### Легенда (реальный мир)

Командная работа над проектами в таск-трекере. Элементы бэклога оцениваются
в стори-поинтах (`Estimable`): эпик, баг и фича оцениваются по разным формулам,
общая база — `AbstractTask`. Любая сущность трекера может быть выгружена во внешний
формат (`Exportable`): комментарии, карточки доски, проекты. Назначение исполнителей
(`Assignable`) доступно и для проектов (ответственный), и для участников команды
(текущая задача). Карточка на доске (`AbstractBoardItem`) умеет проверять корректность
переходов по статусам; её конкретный вид — спринт (`Sprint`) со своим бёрндауном.

## Что демонстрирует `My_class` (для обеих задач)

1. **Массив объектов базового типа** — `Payable[]` / `Estimable[]`, в одном массиве лежат
   объекты разных классов иерархии.
2. **Полиморфизм** — одинаковый вызов через ссылку базового типа, у каждого объекта
   выполняется своя реализация.
3. **Приведение типов вниз** — `instanceof` + явное приведение `(Developer) p` и вызов
   специфичных методов (`writeCode`, `reproduce`, `exportCsv`…), в т.ч. приведение
   `Payable → Notifiable` (вниз к подынтерфейсу).
4. **Вызовы через интерфейсные ссылки** — `Rateable r = new Manager(...)`,
   `Exportable e = new Comment(...)` и т.д. (аналог `I1 i = new KL...`).
5. **Вызовы через ссылки на абстрактный класс** — `AbstractEmployee e = new Developer(...)`,
   `AbstractBoardItem b = new Sprint(...)`.
6. **Инкапсуляция** — все поля иерархий `private`, доступ только через публичные методы;
   клиентский код (`My_class`) находится в другом пакете и обращается только к публичному API.

## Примечания к защите

* `Notifiable.notify(String message)` — это перегрузка (overload) системного `Object.notify()`:
  сигнатуры разные, поэтому это легально; в демо вызывается именно наша перегрузка с параметром.
* Полиморфный прогон `pay(1000, 250)` даёт разные суммы (Manager 1750, Developer 1250,
  QaEngineer 1350, DepartmentHead 2250, OfficeWorker 1125, TimesheetReport 0) — виден
  динамический полиморфизм; `getClass().getSimpleName()` в выводе показывает фактический
  тип объекта, лежащего в переменной базового типа.
* Приведение `Payable → Notifiable` в демо (`(Notifiable) payable[3]`) — это приведение вниз
  к подынтерфейсу: `Reportable` и `Notifiable` — «сёстры» под `Payable`, объект типа
  `DepartmentHead` реализует оба.
* Множественные рёбра схемы в один узел (например, 3+4→10) в Java разрешены только
  для интерфейсов — множественная реализация; для классов ребро всегда одно (12→7, 13→7, 15→9, 14→11).
* Иерархии лежат в пакетах `vendor.alpha.staff` и `vendor.beta.tracker` — имитация
  «библиотек от разных субподрядчиков»; `My_class` использует их как API и расположен
  в `company.*`, демонстрируя работу с чужими классами из другого пакета.
