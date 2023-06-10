package com.github.mydeardoctor.chinesechess.server;

class TextRussian extends Text
{
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
        return "Китайские шахматы. Сервер";
    }
    @Override
    public String getServerIsOff()
    {
        return "Сервер остановлен.";
    }
    @Override
    public String getServerIsOn()
    {
        return "Сервер запущен.";
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
    public String getStart()
    {
        return "Запустить";
    }
    @Override
    public String getLobby()
    {
        return "Лобби";
    }
    @Override
    public String getSettings()
    {
        return "Настройки";
    }

    //Frame Start Server.
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
    public String getMaximumNumberOfPlayers()
    {
        return String.format("Макс. кол-во\n%10s", "игроков");
    }
    @Override
    public String getPlayersTip()
    {
        return String.format("Должно быть\n%12s", "от 2 до 100");
    }
    @Override
    public String getStop()
    {
        return "Остановить";
    }
    @Override
    public String getServerStop()
    {
        return "Остановка сервера";
    }
    @Override
    public String getAreYouSure()
    {
        return "Все соединения будут разорваны!\nВы уверены?";
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
    public String getServerError()
    {
        return "Ошибка Server";
    }
    @Override
    public String getCouldNotStartServer()
    {
        return "Не удалось запустить сервер!";
    }
    @Override
    public String getCouldNotStopServer()
    {
        return "Не удалось остановить сервер!";
    }
    @Override
    public String getBack()
    {
        return "Назад";
    }

    //Frame Settings.
    @Override
    public String getLanguage()
    {
        return "Язык";
    }
}