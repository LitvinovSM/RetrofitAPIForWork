﻿# language: ru
@all @blocker @auth @1
Функция: Проверка сервиса авторизации
  Сценарий: Проверка успешной авторизации
    И 'универсальный шаг' добавляет к запросу заголовок "content-type" со значением "application/json"
    И 'сервис авторизации' пользователь авторизуется под логином "eve.holt@reqres.in" и паролем "cityslicka"
    И 'сервис авторизации' проверяет что статус код ответа равен 200
    И 'сервис авторизации' добавляет токен авторизации к списку заголовков



