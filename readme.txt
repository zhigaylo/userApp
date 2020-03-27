Для подключения к базе данных в resources/application.properties укажите данные
spring.datasource.url=jdbc:postgresql://localhost/ваша бд
spring.datasource.username=имя пользователя
spring.datasource.password=пароль

Описание API:

- POST /api/users/new - Добавляет нового пользователя

     Формат запроса:
     {
        "firstName": ... ,
        "lastName": ... ,
        "age": ...
     }

     Формат ответа:

     HttpStatus - CREATED
     {
        "id": ... ,
        "firstName": ... ,
        "lastName": ... ,
        "age": ... ,
        "documents": []
     }

- PUT /api/users/{id} - Редактирует данные пользователя с идентификатором {id}
    Формат запроса:
     {
        "firstName": ... ,
        "lastName": ... ,
        "age": ...
     }

     Формат ответа:

     HttpStatus - OK
     {
        "id": ... ,
        "firstName": ... ,
        "lastName": ... ,
        "age": ... ,
        "documents": []
     }

- DELETE /api/users/{id} - Удаляет данные пользователя с идентификатором {id}
    HttpStatus - OK

- POST /api/users/{id}/document - Загружает документ пользователя с идентификатором {id}
    Формат запроса:
     {
        "file": ...
     }

     Формат ответа:

     HttpStatus - OK
     {
        "id": ... ,
        "firstName": ... ,
        "lastName": ... ,
        "age": ... ,
        "documents": ...
     }

- GET /api/users/{id}/document - Возвращает документы, у которых срок действия еще не истек, для пользователя с идентификатором {id}

     Формат ответа:

     HttpStatus - OK
     [
         {
             "id": ... ,
             "name": ... ,
             "number": ... ,
             "expiryDate": ... ,
             "userId": ...
         },
         {
             "id": ... ,
             "name": ... ,
             "number": ... ,
             "expiryDate": ... ,
             "userId": ...
         }
         ...
     ]

- GET /api/users/all - Возвращает всех пользователей
     Формат ответа:

     HttpStatus - OK
     {
        {
            "id": ... ,
            "firstName": ... ,
            "lastName": ... ,
            "age": ... ,
            "documents": ...
        }
        {
                    "id": ... ,
                    "firstName": ... ,
                    "lastName": ... ,
                    "age": ... ,
                    "documents": ...
        }
        ...
     }
