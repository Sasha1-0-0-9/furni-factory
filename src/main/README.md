Створення бд:
***
Команда в консоль:

docker run --name furnitor-db -e POSTGRES_USER=? -e POSTGRES_PASSWORD=? -p 5438:5432 -d postgres

знаки питання заміни на свого юзера psql і пароль. В моєму випадку логін admin, пароль postgres.
Якщо в тебе вони не такі, то коли піднімеш контейнер, то в application.properties заміниш логін і пароль на свої.
