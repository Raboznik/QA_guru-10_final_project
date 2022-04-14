# Автотесты для сайта <a target="_blank" href="https://store.steampowered.com/">Steam</a>!
<img src="src/test/img/icons/Steam.jpg" width="400" height="300"  alt="Steam"/>


## Почему Steam?  

- Во-первых он известный
- Во-вторых на нем много интерактивных деталей на которые можно потыкать
- В-третьих он выдержан в темном стиле, а черное все делает крутым, да? 😎


## ✔️ Реализованы четыре теста для сайта :

#### - 1️ **steamMainPageTest()** 

Smoke тест заглавной страницы магазина. Цель этого кейса убедиться, что основные элементы на главной странице магазина присутсвуют и выполняют бизнесс логику. Выполнен тест с помощью Page Object. ~~Все ведь любят PO?~~

✓ По href проверяются ссылки в верхней и нижней частях сайта. Они должны вести на нужные части сайта

✓ Проверяется окно поиска

✓ На баннерах с предложениями должны работать стрелки переключения. При этом нужно убедиться, что игры в баннерах действительно меняются при нажатии на стрелку

✓ В цикле проверяются четыре вкладки с предложениями. При наведении на них мыши всплывает окно с соответствующей игрой. 

#### Пример прохождения теста : 
![video](src/test/img/gif)


#### - 2️ **searchGameTest()**

Проверка окна поиска игры. На примере Dota 2 убеждаемся, что строка поика игр работает. Дотка на месте? За Стим можно не беспокоиться!

✓ Поиск по названию выдает желаемые результаты

✓ Href ведет именно на Dota 2

#### Пример прохождения теста : 
![video](src/test/img/gif)


#### - 3️ **loginTest()**

Еще один критичный сценарий - пользователь сайта должен иметь возможность залогиниться, иначе зачем все это?

✓ Страница логина открывается при переходе с главной страницы магазины

✓ Вводим логин и пароль 

✓ Убеждаемся, что при переходе на главную страницу мы залогинены


#### Пример прохождения теста : 
![video](src/test/img/gif)


#### - 4️ **steamChatTest()**

Проверяем функционал чата внутри Стима. Можно передать любую фразу в переменную *chatPhrase* и убедиться, что именно она появится в отправленных сообщениях. Если у тебя совсем нет друзей, то так можно коротать вечера. Поверь мне 🥲

✓ Логинимся на сайте магазина

✓ Переходим в раздел "чаты"

✓ Создаем чат с нашим логином

✓ Отправляем туда заданную фразу и проверяем, что она отображается в истории сообщений

✓ Удаляем чат

#### Пример прохождения теста : 
![video](src/test/img/gif)



##  ⚒️  Используемые технологии и инструменты :


![Java](src/img/icons/Java.png)![Gradle](src/img/icons/Gradle.png)![JUnit5](src/img/icons/JUnit5.png)![Intelij_IDEA](src/img/icons/Intelij_IDEA.png)![Selenoid](src/img/icons/Selenoid.png)![Selenide](src/img/icons/Selenide.png)![Allure Report](src/img/icons/Allure_Report.png)![Github](src/img/icons/Github.png)![Jenkins](src/img/icons/Jenkins.png)![Telegram](src/img/icons/Telegram.png)


## <img src="src/test/img/icons/Jenkins.png" width="25" height="25"  alt="Jenkins"/></a> Jenkins <a target="_blank" href="https://jenkins.autotests.cloud/job/10_HW_Khyuchkov_Test/"> job </a>
<p align="center">
<a href="https://jenkins.autotests.cloud/job/10_HW_Khyuchkov_Test/"><img src="src/test/img/jenkins main.png" alt="Jenkins"/></a>
</p>


![Jenkins](src/test/img/jenkins param.png)


## Для локального запуска :
```bash
gradle clean test
```

## Для удаленного запуска с параметрами :
```bash
clean
test
-Dsize=${BROWSER_SIZE}
```
___
## <img src="src/test/img/icons/Allure_Report.png" width="25" height="25"  alt="Allure"/></a> Отчет в <a target="_blank" href="https://jenkins.autotests.cloud/job/10_HW_Khyuchkov_Test/20/allure/">Allure report</a>

### Overview :
![Allure](src/test/img/allure main.png)

### Suites : 
![Allure](src/test/img/allure example.png)

### Graphs :
![Allure](src/test/img/allure stats 1.png)
![Allure](src/test/img/allure stats 2.png)

#### <img src="src/test/img/icons/Telegram.png" width="25" height="25"  alt="Telegram"/></a> Отчет в телеграмм о результатах тестов :

![Telegram](src/test/img/telegram.png)


## Остались вопросы? 
<a href="https://t.me/raboznik">
<img src="src/test/img/icons/Telegram.png" width="25" height="25"  alt="Telegram"/></a> 

<a href="mailto:raboznik@gmail.com">
<img src="src/test/img/icons/gmail.png" width="25" height="25"  alt="Gmail"/></a>  


