﻿# language: ru
@all @blocker @users @6
Функция: Проверка сервиса пользователей
  Сценарий: Проверка успешного получения списка пользователей
    И 'универсальный шаг' добавляет к запросу заголовок "content-type" со значением "application/json"
    И 'универсальный шаг' добавляет к запросу параметр "page" со значением "2"
    И 'сервис авторизации' пользователь успешно авторизуется c параметрами по умолчанию и добавляет токен к списку заголовков
    И 'сервис пользователей' получает список пользователей с заданными параметрами
    И 'сервис пользователей' проверяет что список пользователей содержит пользователя с id равным 7
    И 'сервис пользователей' проверяет что пользователь имеет параметры
    |НАЗВАНИЕ ПАРАМЕТРА|УСЛОВИЕ СРАВНЕНИЯ|ОЖИДАЕМОЕ ЗНАЧЕНИЕ|ТИП ДАННЫХ|
    | id                 | больше                |    8              |  double        |

