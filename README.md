# Chinese Chess

This is a project that creates a Xiangqi (Chinese chess) java desktop aplication. There are three game modes:
* Player vs Computer.
* Player vs Player locally on the same computer.
* Player vs Player online.

## Project structure

* [ChineseChessClient][ChineseChessClient_link]. Contains a module with java source files to build the client application. The client application is used to play the game.
* [ChineseChessServer][ChineseChessServer_link]. Contains a module with java source files to build the server application. The server application must also be running on one of the player's computer to play the game online.
* [ChineseChessShared][ChineseChessShared_link]. Contains a module with java source files that are shared both by the client application and the server application.

## Requirements

* [Windows 10][Windows10_link].
* [JDK 21][JDK_21_link].
* [IntelliJ IDEA][IntelliJ_IDEA_link].

## Workflow

### Open project

Open IntelliJ IDEA.

![1_Open_project_text][1_Open_project_link]

Click "Open" and select the path to the project folder.

![2_Open_project_text][2_Open_project_link]

Click "OK".

![3_Open_project_text][3_Open_project_link]

The project is opened successfully.

### Build project

Navigate to "Main Menu" -> "Build" and select "Rebuild Project".

![4_Build_project_text][4_Build_project_link]

Click on the "Build" icon to show the build output.

![5_Build_project_text][5_Build_project_link]

The project is built successfully.

### Build jars

Navigate to "Main Menu" -> "Build" and select "Build Artifacts...".

![6_Build_jars_text][6_Build_jars_link]

Navigate to "All Artifacts" and select "Rebuild".

![7_Build_jars_text][7_Build_jars_link]

The build output is shown in the "Build" panel.

![8_Build_jars_text][8_Build_jars_link]

Jars are built successfully.

## How to play

Navigate to out\artifacts\ChineseChessClient_jar and run "ChineseChessClient.jar".

![9_How_to_play_text][9_How_to_play_link]

### Settings

From Main Menu navigate to "SETTINGS". Here you can change language, music volume and sound effects volume.

![10_Settings_text][10_Settings_link]

### Rules

From Main Menu navigate to "RULES". Here you can read the rules of the game.

![11_Rules_text][11_Rules_link]

### Play Single Player

From Main Menu navigate to "PLAY" and select "SINGLE PLAYER". This is a game mode that allows a player to play versus a computer.

![12_Play_single_player_text][12_Play_single_player_link]
![13_Play_single_player_text][13_Play_single_player_link]

Click "OK" to start playing. You can see hints in the status bar at the bottom of the creen.

![14_Play_single_player_text][14_Play_single_player_link]

Click on a figure to choose it.

![15_Play_single_player_text][15_Play_single_player_link]

Click on a destination highlighted by a green circle to move a figure.

![16_Play_single_player_text][16_Play_single_player_link]

### Play Local Multiplayer

From Main Menu navigate to "PLAY" and select "LOCAL MULTIPLAYER". This is a game mode that allows a player to play versus another player locally on the same computer.

![17_Play_local_multiplayer_text][17_Play_local_multiplayer_link]
![18_Play_local_multiplayer_text][18_Play_local_multiplayer_link]

Players make their moves in turns. Red player makes his move first.

![19_Play_local_multiplayer_text][19_Play_local_multiplayer_link]
![20_Play_local_multiplayer_text][20_Play_local_multiplayer_link]

Black player makes his move second.

![21_Play_local_multiplayer_text][21_Play_local_multiplayer_link]
![22_Play_local_multiplayer_text][22_Play_local_multiplayer_link]

### Play Online Multiplayer

Online Multiplayer is a game mode that allows a player to play versus another player online. One of the players must also run the server application to play online. Navigate to out\artifacts\ChineseChessServer_jar and run "ChineseChessServer.jar".

![23_Play_online_multiplayer_text][23_Play_online_multiplayer_link]

Navigate to "START". Choose a port that will be used by the server. Choose maximum number of players that the server will be able to handle.

![24_Play_online_multiplayer_text][24_Play_online_multiplayer_link]

Click "START" to start the server.

![25_Play_online_multiplayer_text][25_Play_online_multiplayer_link]

In the client application from Main Menu navigate to "PLAY" and select "ONLINE MULTIPLAYER".

![26_Play_online_multiplayer_text][26_Play_online_multiplayer_link]
![27_Play_online_multiplayer_text][27_Play_online_multiplayer_link]

Navigate to "CONNECTION TO SERVER".

![28_Play_online_multiplayer_text][28_Play_online_multiplayer_link]

Enter IP address of the server, port number of the server and your nickname.

![29_Play_online_multiplayer_text][29_Play_online_multiplayer_link]

Click "CONNECT".

![30_Play_online_multiplayer_text][30_Play_online_multiplayer_link]

Navigate to "BACK" -> "LOBBY". Here you can see the list of other players that are connected to the same server. Right now the lobby is empty.

![31_Play_online_multiplayer_text][31_Play_online_multiplayer_link]
![32_Play_online_multiplayer_text][32_Play_online_multiplayer_link]

When other players connect to the same server, they become visible in the lobby.

![33_Play_online_multiplayer_text][33_Play_online_multiplayer_link]

Select a player that you want to play with and click "INVITE".

![34_Play_online_multiplayer_text][34_Play_online_multiplayer_link]

Click "OK" to start playing.

![35_Play_online_multiplayer_text][35_Play_online_multiplayer_link]

### Replay

When you play, you can save a replay of a game to rewatch later. In the game navigate to "Replay" and select "Save replay".

![36_Play_online_multiplayer_text][36_Play_online_multiplayer_link]

Choose the name of your replay and the directory to save your replay to. Click "Save".

![37_Play_online_multiplayer_text][37_Play_online_multiplayer_link]

From Main Menu navigate to "LOAD".

![38_Play_online_multiplayer_text][38_Play_online_multiplayer_link]
![39_Play_online_multiplayer_text][39_Play_online_multiplayer_link]

Select a replay that you want to rewatch. Click "Open".

![40_Play_online_multiplayer_text][40_Play_online_multiplayer_link]
![41_Play_online_multiplayer_text][41_Play_online_multiplayer_link]

Here you can play, stop, fast forward and rewind your replay. You can also play your replay move by move.

![42_Play_online_multiplayer_text][42_Play_online_multiplayer_link]




[ChineseChessShared_link]: ChineseChessShared
[ChineseChessClient_link]: ChineseChessClient
[ChineseChessServer_link]: ChineseChessServer
[Windows10_link]: https://www.microsoft.com/software-download/windows10
[JDK_21_link]: https://www.oracle.com/java/technologies/downloads/#jdk21-windows
[IntelliJ_IDEA_link]: https://www.jetbrains.com/idea/download/?section=windows

[1_Open_project_link]: images/1_Open_project.png
[2_Open_project_link]: images/2_Open_project.png
[3_Open_project_link]: images/3_Open_project.png
[4_Build_project_link]: images/4_Build_project.png
[5_Build_project_link]: images/5_Build_project.png
[6_Build_jars_link]: images/6_Build_jars.png
[7_Build_jars_link]: images/7_Build_jars.png
[8_Build_jars_link]: images/8_Build_jars.png
[9_How_to_play_link]: images/9_How_to_play.png
[10_Settings_link]: images/10_Settings.png
[11_Rules_link]: images/11_Rules.png
[12_Play_single_player_link]: images/12_Play_single_player.png
[13_Play_single_player_link]: images/13_Play_single_player.png
[14_Play_single_player_link]: images/14_Play_single_player.png
[15_Play_single_player_link]: images/15_Play_single_player.png
[16_Play_single_player_link]: images/16_Play_single_player.png
[17_Play_local_multiplayer_link]: images/17_Play_local_multiplayer.png
[18_Play_local_multiplayer_link]: images/18_Play_local_multiplayer.png
[19_Play_local_multiplayer_link]: images/19_Play_local_multiplayer.png
[20_Play_local_multiplayer_link]: images/20_Play_local_multiplayer.png
[21_Play_local_multiplayer_link]: images/21_Play_local_multiplayer.png
[22_Play_local_multiplayer_link]: images/22_Play_local_multiplayer.png
[23_Play_online_multiplayer_link]: images/23_Play_online_multiplayer.png
[24_Play_online_multiplayer_link]: images/24_Play_online_multiplayer.png
[25_Play_online_multiplayer_link]: images/25_Play_online_multiplayer.png
[26_Play_online_multiplayer_link]: images/26_Play_online_multiplayer.png
[27_Play_online_multiplayer_link]: images/27_Play_online_multiplayer.png
[28_Play_online_multiplayer_link]: images/28_Play_online_multiplayer.png
[29_Play_online_multiplayer_link]: images/29_Play_online_multiplayer.png
[30_Play_online_multiplayer_link]: images/30_Play_online_multiplayer.png
[31_Play_online_multiplayer_link]: images/31_Play_online_multiplayer.png
[32_Play_online_multiplayer_link]: images/32_Play_online_multiplayer.png
[33_Play_online_multiplayer_link]: images/33_Play_online_multiplayer.png
[34_Play_online_multiplayer_link]: images/34_Play_online_multiplayer.png
[35_Play_online_multiplayer_link]: images/35_Play_online_multiplayer.png
[36_Play_online_multiplayer_link]: images/36_Play_online_multiplayer.png
[37_Play_online_multiplayer_link]: images/37_Play_online_multiplayer.png
[38_Play_online_multiplayer_link]: images/38_Play_online_multiplayer.png
[39_Play_online_multiplayer_link]: images/39_Play_online_multiplayer.png
[40_Play_online_multiplayer_link]: images/40_Play_online_multiplayer.png
[41_Play_online_multiplayer_link]: images/41_Play_online_multiplayer.png
[42_Play_online_multiplayer_link]: images/42_Play_online_multiplayer.png