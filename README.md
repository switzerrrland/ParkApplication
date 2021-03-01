# ParkApplication

- БД написана с помощью **sqlite**
- Основная БД `Park.db` расположена локально на рабочем столе, путь к ней прописан в константе `PRODUCTION_DB_PATH` в `src/main/java/org/example/Constants.java`. При запуске на другом ПК путь стоит изменить
- Создание БД в sqlite зависит от способа запуска, я использовала:
  - **DB BROWSER** (кнопка `Новая база данных`)
  - Командную строку **sqlite tools** с [официального сайта](https://sqlite.org/download.html) (скачать архив `sqlite-tools-win32-x86...`, запустить sqlite3.exe, ввести команду `.open Park.db`)
- Скрипт для создания и заполнения таблицы `plants.sql`, лежит в корне проекта
