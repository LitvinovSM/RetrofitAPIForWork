﻿# language: ru
@all @blocker @auth @4 @failedCauseBug
Функция: Проверка сервиса авторизации
  Сценарий: Проверка авторизации с корректным логином и некорректным паролем
    И 'универсальный шаг' добавляет к запросу заголовок "content-type" со значением "application/json"
    И 'сервис авторизации' пользователь авторизуется под логином "eve.holt@reqres.in" и паролем "123123cityslicka"
    И 'универсальный шаг' проверяет что статус код ответа равен 400
    И 'сервис авторизации' проверяет что текст ошибки в теле сообщения равен "Incorrect password"

# Bug id ABC-1
