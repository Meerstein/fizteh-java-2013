## Telnet

TCP/IP-интерфейс для хранилища. Добавляется возможность работы с хранилищем удаленно с использованием текстового
протокола. Консольное приложение должно реализовывать режимы работы "сервер" и "клиент".

### Режим сервера

Каждое принятое подключение нужно обрабатывать в отдельном треде. Для подключения нужно создать шелл с командами,
аналогичными командам из предыдущих работ, и рассматривать incoming и outgoing потоки подключения как
входящий и исходящий потоки для шелла. Уточним, что по команде exit нужно лишь закрыть подключение, но не
останавливать весь сервер. Поскольку каждое подключение будет обрабатываться в отдельном треде, для подключений
будет имитироваться транзакционная модель (см. 7ое задание).

#### Команды

##### start

Выполняет запуск сервера, выполняя прослушивание указанного порта. Если порт не указан, прослушивается порт ```10001```:
```
start port
```

В случае успеха печатается:
```
started at port
```

В случае ошибки:
```
not started: текст ошибки

Например:
not started: wrong port number
not started: already started
```

##### stop

Останавливает работу сервера:
```
stop
```

В случае успеха печатается:
```
stopped at port
```

Если сервер не стартован, выводит:
```
not started
```

##### listusers

Выводит список все присоединённых пользователей:
```
listusers
```

Если сервер не стартован, выводит:
```
not started
```

Список пользователей выглядит следующим образом:
```
192.168.1.2:8080
192.168.1.3:37012
```

### Remote-клиент

Для работы с сервером хранилища можно будет использовать Telnet-клиент. Однако, будет полезным написать
Remote-клиент для сервера. Нужно написать реализацию
[RemoteTableProviderFactory](../src/ru/fizteh/fivt/storage/structured/RemoteTableProviderFactory.java).

Клиент должен открывать подключение к серверу по указанному порту и имени узла, после чего предоставлять API для
работы с хранилищем, по функциональности аналогичное обычному API для работы с локальным хранилищем.

### Режим клиента

Приложение в режиме клиента отправляет команды через RemoteTableProvider удалённому серверу.

#### Команды

##### connect

Выполняет соединение с сервером. Если соединение с сервером (этим или другим) уже установлено, необходимо выводить
сообщение об ошибке.
```
connect host port
```

В случае успеха:
```
connected
```

В случае ошибки:
```
not connected: текст ошибки
```

##### disconnect

Команда должна корректно закрывать удалённое соединение:
```
disconnect
```

Если соединение закрыто:
```
disconnected
```

Если соединения не было:
```
not connected
```

##### whereami

Команда выводит текущее состояние, либо ```local```, либо ```remote host port```.

### Реагирование на Ctrl+C

Приложение должно уметь прерывать работы при нажатии в консоли Ctrl+C (что эквивалентно сигналу SIGINT). При этом
нужно закрыть все подключения, в т.ч. выполнив для них rollback.

### Комментарии

* Нажатие Ctrl+C на клавиатуре вызывает сигнал [SIGINT](http://ru.wikipedia.org/wiki/SIGINT). Этот сигнал, по умолчанию,
производит "вежливую" остановку JVM. Вы можете использовать
[Runtime.addShutdownHook()](http://docs.oracle.com/javase/7/docs/api/java/lang/Runtime.html) для корректного завершения
работы.
* Фактически, можно стартовать сервер в приложении и к нему же подсоединиться. Таким образом можно проверять
работоспособность приложения.