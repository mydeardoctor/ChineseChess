package com.github.mydeardoctor.chinesechess.client;

class TextRussian extends Text
{
    //Game.

    @Override
    public String getGame()
    {
        return "Игра";
    }
    @Override
    public String getYouPlay()
    {
        return "Вы играете ";
    }
    @Override
    public String getVersus()
    {
        return "против ";
    }
    @Override
    public String getRed()
    {
        return "Красными.";
    }
    @Override
    public String getBlack()
    {
        return "Чёрными.";
    }
    @Override
    public String getRedPlayer()
    {
        return "Красный игрок";
    }
    @Override
    public String getBlackPlayer()
    {
        return "Чёрный игрок";
    }
    @Override
    public String getChooseFigure()
    {
        return "выберите фигуру.";
    }
    @Override
    public String getChooseAnotherFigureOrDestination()
    {
        return "выберите другую фигуру или цель.";
    }
    @Override
    public String getGameOver()
    {
        return "Игра окончена.";
    }
    @Override
    public String getWon()
    {
        return "выиграл.";
    }

    //GUI.

    //Resources.
    @Override
    public String getGuiWarning()
    {
        return "Предупреждение GUI";
    }
    @Override
    public String getSomeResourcesAreMissing()
    {
        return "Не удалось обнаружить некоторые ресурсы!";
    }

    //Common frame features.
    @Override
    public String getChineseChessClient()
    {
        return "Китайские шахматы. Клиент";
    }
    @Override
    public String getNavigation()
    {
        return "Навигация";
    }
    @Override
    public String getMainMenu()
    {
        return "Главное меню";
    }
    @Override
    public String getExitToMainMenu()
    {
        return "Выход в главное меню";
    }
    @Override
    public String getAllProgressWillBeLost()
    {
        return "Весь прогресс будет потерян.\nВы уверены?";
    }
    @Override
    public String getYes()
    {
        return "Да";
    }
    @Override
    public String getNo()
    {
        return "Нет";
    }
    @Override
    public String getReplay()
    {
        return "Запись игры";
    }
    @Override
    public String getSaveReplay()
    {
        return "Сохранить запись игры";
    }
    @Override
    public String getReplayWarning()
    {
        return "Предупреждение Replay";
    }
    @Override
    public String getNothingToSave()
    {
        return "Сохранять нечего!";
    }
    @Override
    public String getReplayError()
    {
        return "Ошибка Replay";
    }
    @Override
    public String getCouldNotSaveReplay()
    {
        return "Не удалось сохранить запись игры!";
    }
    @Override
    public String getHelp()
    {
        return "Помощь";
    }
    @Override
    public String getAbout()
    {
        return "О программе";
    }
    @Override
    public String getAboutVerbose()
    {
        return String.format("Китайские шахматы\nПрограммист:%16s\nКомпозитор:%14s", "my_dear_doctor", "eskimolly");
    }

    //Frame Main Menu.
    @Override
    public String getPlay()
    {
        return "Играть";
    }
    @Override
    public String getLoad()
    {
        return "Загрузить";
    }
    @Override
    public String getRules()
    {
        return "Правила";
    }
    @Override
    public String getSettings()
    {
        return "Настройки";
    }
    @Override
    public String getLoadReplay()
    {
        return "Загрузить запись игры";
    }
    @Override
    public String getCouldNotLoadReplay()
    {
        return "Не удалось загрузить запись игры!";
    }

    //Frame Game Mode.
    @Override
    public String getSinglePlayer()
    {
        return "Одиночная игра";
    }
    @Override
    public String getLocalMultiplayer()
    {
        return "Локальный мультиплеер";
    }
    @Override
    public String getOnlineMultiplayer()
    {
        return "Онлайн мультиплеер";
    }
    @Override
    public String getBack()
    {
        return "Назад";
    }

    //Frame Online Multiplayer.
    @Override
    public String getConnectionToServer()
    {
        return "Подключение к серверу";
    }
    @Override
    public String getLobby()
    {
        return "Лобби";
    }
    @Override
    public String getDisconnectedFromServer()
    {
        return "Вы отключены от сервера.";
    }
    @Override
    public String getConnectedToServer()
    {
        return "Вы подключены к серверу.";
    }

    //Frame Connect to Server.
    @Override
    public String getIpAddress()
    {
        return "IP адрес";
    }
    @Override
    public String getIpTip()
    {
        return "Формат:\nXXX.XXX.XXX.XXX\nКаждый байт\nот 0 до 255";
    }
    @Override
    public String getPort()
    {
        return "Порт";
    }
    @Override
    public String getPortTip()
    {
        return String.format("%13s\nот 1024 до 65535", "Должен быть");
    }
    @Override
    public String getNickname()
    {
        return "Никнейм";
    }
    @Override
    public String getNicknameTip()
    {
        return String.format("Не может быть\n%10s", "пустым");
    }
    @Override
    public String getConnect()
    {
        return "Подключиться";
    }
    @Override
    public String getDisconnect()
    {
        return "Отключиться";
    }
    @Override
    public String getClientError()
    {
        return "Ошибка Client";
    }
    @Override
    public String getCouldNotConnectToServer()
    {
        return "Не удалось подключиться к серверу!";
    }
    @Override
    public String getDisconnectFromServer()
    {
        return "Отключение от сервера";
    }
    @Override
    public String getConnectionWillBeLost()
    {
        return "Соединение будет разорвано!\nВы уверены?";
    }
    @Override
    public String getClientInfo()
    {
        return "Информация Client";
    }

    //Frame Lobby.
    @Override
    public String getListOfClients()
    {
        return "Список клиентов";
    }
    @Override
    public String getInvite()
    {
        return "Пригласить";
    }
    @Override
    public String getOpponentIsUnavailable()
    {
        return "Соперник недоступен.";
    }
    @Override
    public String getOpponentQuit()
    {
        return "Соперник вышел из игры.";
    }
    @Override
    public String getOpponentDisconnected()
    {
        return "Соперник отключился.";
    }

    //Frame Rules.
    @Override
    public String getGoal()
    {
        return "Цель";
    }
    @Override
    public String getGoalRule()
    {
        return "Цель игры – поставить шах и мат генералу соперника.";
    }
    @Override
    public String getPalace()
    {
        return "Дворец";
    }
    @Override
    public String getPalaceRule()
    {
        return "Генерал не может покинуть Дворец. Советник не может покинуть Дворец.";
    }
    @Override
    public String getRiver()
    {
        return "Река";
    }
    @Override
    public String getRiverRule()
    {
        return "После пересечения Реки солдат получает возможность ходить горизонтально на один пункт." +
                " Слон не может пересечь Реку.";
    }
    @Override
    public String getGeneral()
    {
        return "Генерал";
    }
    @Override
    public String getGeneralRule()
    {
        return "Генерал ходит горизонтально или вертикально на один пункт. Генерал не может покинуть Дворец." +
                " Генералы не могут смотреть друг на друга.";
    }
    @Override
    public String getAdvisor()
    {
        return "Советник";
    }
    @Override
    public String getAdvisorRule()
    {
        return "Советник ходит по диагонали на один пункт. Советник не может покинуть Дворец.";
    }
    @Override
    public String getElephant()
    {
        return "Слон";
    }
    @Override
    public String getElephantRule()
    {
        return "Слон ходит по диагонали на два пункта. Слон не может перепрыгивать другие фигуры." +
                " Слон не может пересечь Реку.";
    }
    @Override
    public String getHorse()
    {
        return "Конь";
    }
    @Override
    public String getHorseRule()
    {
        return "Конь ходит горизонтально или вертикально на один пункт, а затем по диагонали на один пункт." +
                " Конь не может перепрыгивать другие фигуры.";
    }
    @Override
    public String getChariot()
    {
        return "Колесница";
    }
    @Override
    public String getChariotRule()
    {
        return "Колесница ходит горизонтально или вертикально на любое количество пунктов." +
                " Колесница не может перепрыгивать другие фигуры.";
    }
    @Override
    public String getCannon()
    {
        return "Пушка";
    }
    @Override
    public String getCannonRule()
    {
        return "Пушка ходит горизонтально или вертикально на любое количество пунктов." +
                 " Но чтобы захватить фигуру соперника, пушка должна перепрыгнуть через одну другую фигуру.";
    }
    @Override
    public String getSoldier()
    {
        return "Солдат";
    }
    @Override
    public String getSoldierRule()
    {
        return "Солдат ходит вертикально прямо на один пункт." +
                " После пересечения Реки солдат получает возможность ходить горизонтально на один пункт.";
    }

    //Frame Settings.
    @Override
    public String getLanguage()
    {
        return "Язык";
    }
    @Override
    public String getMusic()
    {
        return "Музыка";
    }
    @Override
    public String getSoundEffects()
    {
        return "Звуковые эффекты";
    }

    //Music player.

    @Override
    public String getMusicPlayerWarning()
    {
        return "Предупреждение Music player";
    }
    @Override
    public String getSomeFeaturesAreNotAvailable()
    {
        return "Некоторые функции Music player недоступны!";
    }
}