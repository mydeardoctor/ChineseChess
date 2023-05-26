package com.github.mydeardoctor.chinesechess;

class TextRussian extends Text
{
    //Game.
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
    public String getTitle()
    {
        return "Китайские шахматы";
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
    public String getAreYouSure()
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

    //Frame Rules.
    @Override
    public String getGoal()
    {
        return "Цель";
    }
    @Override
    public String getGoalRule()
    {
        return "Цель игры – поставить шах и мат генералу противника.";
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
                 " Но чтобы захватить фигуру противника, пушка должна перепрыгнуть через одну другую фигуру.";
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
    public String getSfx()
    {
        return "Звуковые эффекты";
    }

    @Override
    public String getApply()
    {
        return "Применить";
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
        return "Некоторые функции недоступны!";
    }
}