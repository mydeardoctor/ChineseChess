package com.github.mydeardoctor.chinesechess;

class TextRussian extends Text
{
    @Override
    String getTitle()
    {
        return "Китайские шахматы";
    }

    //Menu Bar.
    @Override
    String getHelp()
    {
        return "Помощь";
    }
    @Override
    String getAbout()
    {
        return "О программе";
    }
    @Override
    String getAboutVerbose()
    {
        return String.format("Китайские шахматы\nПрограммист:%16s\nКомпозитор:%14s", "my_dear_doctor", "eskimolly");
    }

    //Frame Main Menu.
    @Override
    String getPlay()
    {
        return "Играть";
    }
    @Override
    String getLoad()
    {
        return "Загрузить";
    }
    @Override
    String getRules()
    {
        return "Правила";
    }
    @Override
    String getSettings()
    {
        return "Настройки";
    }

    //Frame Game Mode.
    @Override
    String getSinglePlayer()
    {
        return "Одиночная игра";
    }
    @Override
    String getLocalMultiplayer()
    {
        return "Локальный мультиплеер";
    }
    @Override
    String getOnlineMultiplayer()
    {
        return "Онлайн мультиплеер";
    }
    @Override
    String getBack()
    {
        return "Назад";
    }

    //Frame Settings.
    @Override
    String getLanguage()
    {
        return "Язык";
    }
    @Override
    String getApply()
    {
        return "Применить";
    }

    //Game.
    @Override
    String getPlayerRed()
    {
        return "Вы играете Красными.";
    }
    @Override
    String getPlayerBlack()
    {
        return "Вы играете Чёрными.";
    }

    //Errors.
    @Override
    String getError()
    {
        return "Ошибка";
    }
    @Override
    String getErrorIcon()
    {
        return "Не удалось загрузить \"icon.jpg\"";
    }
    @Override
    String getErrorFont()
    {
        return "Не удалось загрузить \"kashimarusbycop.otf\"";
    }
    @Override
    String getErrorBackground()
    {
        return "Не удалось загрузить \"background.jpg\"";
    }
    @Override
    String getErrorAdvisorBlack()
    {
        return "Не удалось загрузить \"advisorBlack.png\"";
    }
    @Override
    String getErrorAdvisorRed()
    {
        return "Не удалось загрузить \"advisorRed.png\"";
    }
    @Override
    String getErrorCannonBlack()
    {
        return "Не удалось загрузить \"cannonBlack.png\"";
    }
    @Override
    String getErrorCannonRed()
    {
        return "Не удалось загрузить \"cannonRed.png\"";
    }
    @Override
    String getErrorChariotBlack()
    {
        return "Не удалось загрузить \"chariotBlack.png\"";
    }
    @Override
    String getErrorChariotRed()
    {
        return "Не удалось загрузить \"chariotRed.png\"";
    }
    @Override
    String getErrorElephantBlack()
    {
        return "Не удалось загрузить \"elephantBlack.png\"";
    }
    @Override
    String getErrorElephantRed()
    {
        return "Не удалось загрузить \"elephantRed.png\"";
    }
    @Override
    String getErrorGeneralBlack()
    {
        return "Не удалось загрузить \"generalBlack.png\"";
    }
    @Override
    String getErrorGeneralRed()
    {
        return "Не удалось загрузить \"generalRed.png\"";
    }
    @Override
    String getErrorHorseBlack()
    {
        return "Не удалось загрузить \"horseBlack.png\"";
    }
    @Override
    String getErrorHorseRed()
    {
        return "Не удалось загрузить \"horseRed.png\"";
    }
    @Override
    String getErrorSoldierBlack()
    {
        return "Не удалось загрузить \"soldierBlack.png\"";
    }
    @Override
    String getErrorSoldierRed()
    {
        return "Не удалось загрузить \"soldierRed.png\"";
    }
    @Override
    String getErrorSelection()
    {
        return "Не удалось загрузить \"selection.png\"";
    }
}
