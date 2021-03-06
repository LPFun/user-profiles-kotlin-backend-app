### Описание

Серверное приложение предоставляющее работу с профилями. Представлено 3 вида профилей: личные данные, основное и
дополнительное образование, навыки и технологии.

### Модули

- **profile-be-app-ktor** - сервеная часть на ktor
- **profile-be-common** - общая часть для всего бэкэнда
- **profile-be-repo-db-base** - базовавая реализация для работы с БД на Kotlin Exposed
- **profile-be-repo-db** - модуль для работы с БД PostreSql
- **profile-be-repo-db-inmemory** - модуль для работы с H2 БД в памяти
- **profile-be-transport-kmp** - мапперы для преобразования мультиплатформенных транспортных моделей во внутренние
- **profile-transport-common** - мультиплатформенные вилидаторы и модели ошибок
- **profile-transport-kmp-models** - мультиплатформенные модели используемые для бэкэнда и фронтэнда
- **profile-domain** - модуль логики реализующий шаблон цепоки обязанностей. Используется **Kotlin DSL**.
- **log-lib** - модуль логирования

### Фреймворки

- **kotlin-coroutines** - асинхронная работа
- **kotlin-serialization** - сериализация моделей
- **kotlin-datetime** - работа с времененм
- **Kodein** - инжектор зависимостей
- **Kotlin Exposed** - фреймворк для работы с БД
- **Flyway** - для применения миграций к БД
- **Hikari** - для создания соединений к БД

### Тестирование

В основных модулях прописаны тесты успешных сценариев использования. Для тестирования базы данных используются
testcontainers.

### Дополнительно

**elk-stack** - содержит инструменты для для запуска ELK стэка, для визуализации логов и метрик приложения\
**resTests** - http запросы для тестирования
