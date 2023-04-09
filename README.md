# Foxtrot


# Iteration 4: Implemented user stories



## Listening Comprehension Mode -  Complete

(Users have the ability to practice their  understanding of spoken words in French by using the comprehension mode. In this mode, numbers will appear in the prefilled cells. When a user presses the number, the corresponding word in French will be read out to them. This game mode feature allows users to test their listening comprehension by selecting from the menu the correct English translation of the word.)

1. User Story: As a language learner,  I want to practice my understanding of spoken words in the language I am learning by hearing the words said out loud.

TDD: On the main menu, the user can select if they would like to play the sudoku game in listening comprehension mode or normal mode. If the listening comprehension mode is selected, then a board of size either 4x4, 6x6,9x9 or 12x12 is generated with prefilled numbers in the cells. By clicking on these numbers, I am able to hear the words corresponding to each number. For example if a block is labelled 1 and its corresponding word is Bonjour, then once I click on the cell with the number 1, I hear the word “Bonjour” read out. Then, I select the English translation of the French word I heard to fill the cell.


2. User Story: As a language learner,  I want the ability to verify whether I understand a word I hear right away

TDD: After the user has clicked on a numbered cell and heard the corresponding word in French. They must select what they think the english translation is to that word. If the wrong option is selected, the cell turns red to immediately let the player know that they have picked the wrong translation. In order to proceed in the game, the player must select the correct translation as the cell would turn green. At the end, if the player understood all the words read out to them then the entire grid must be green and the game is complete.

<p float="left">
<img src="/img/por_lc.png" width="35%" />
<img src="/img/land_lc.png" width="35%" />
</p>

<br>

## Instructions Page -  Complete

(Naive users who are new to the game have the ability to learn more about the game through the instruction page as it outline the rules of the game and explains how various game features such as Hint, Erase, Reset and Submit can be used throughout the game)

1. User Story: As a naive user, I want the ability to learn the game before I play

TDD: On the main menu, the user can select if they would like to learn how to play the sudoku game by clicking on the “Instructions” button. Once the button is clicked, the user is taken to a new page. On the instructions page the user can find details about the game and how it teaches players a new language, how to set up a game by choosing game preferences on the home page, the main goal of the game and explains the various game such as Hint, Erase, Reset and Submit buttons in detail.

<p float="left">
<img src="/img/instructionsFinal.png" width="35%" />
</p>

<br>



## Different Sudoku Grid Sizes: Complete

(Board sizes 4x4, 6x6, 9x9 and 12x12 are being supported in our current working game. These board sizes are separated by levels of difficulty, with 4x4 and 6x6 being level Easy, 9x9 being level Medium and 12x12 being level Hard)

1. User Story: As a language teacher, I want to personalize the game for my students based on their level of understanding and to meet their language learning goals without making the game unnecessarily difficult or easy.

TDD: As soon as the app is opened the user can pick the level of difficulty they would like to play with. Each level corresponds to a grid size, for example an Easy level would be of grid size 4x4, Medium is grid size 6x6 and Difficult is grid size 9x9 and Extra difficult to 12x12.

2. User Story:  As a language learner who is an expert in Sudoku, I would like to play in a challenging mode which has a grid size of 12x12 so I can learn many new words and enjoy the game.

TDD: On the menu page, the user can select the level of difficulty they would like to play with and there is an option to play on a 12x12 board for an extra challenge. Once the user selects that option, a 12x12 board is generated on the screen with 12 corresponding word pairs.

<p float="left">
<img src="/img/porFinal_six.png" width="33%" />
<img src="/img/porFinal_ninexnine.png" width="33%" />
<img src="/img/landFinal_twelvextwelve.png" width="33%" />
</p>

<br>

## Different Devices: Complete
(Portrait and Landscape orientations of a phone are being supported in our current working game. In addition, the game can be played on a Tablet as the landscape and portrait orientations of a tablet are also being supported)
1. User Story: As someone who wants to learn new vocabulary, I prefer playing the language learning Sudoku game on a bigger screen to better view the contents such as longer words and enhance my gameplay experience.

TDD: Sudoku game users have the ability to play the game on devices with larger screens such as Ipad/Tablet. When the app is initially opened it detects the type of device being used and adjusts accordingly to fit the different screen sizes and displays the sudoku board and buttons in a responsive and organized manner.


<p float="left">
<img src="/img/tablet_landFinal.png" width="33%" />
<img src="/img/tablet_porFinal.png" width="33%" />
</p>

<br>

2. User Story:  As someone who uses public transport regularly, I want to be able to play the Sudoku game in landscape mode to better read longer words without compromising on the functionally of the game

TDD: When the user rotates the screen from portrait to landscape mode, the app detects the change internally and adjusts the app features accordingly. The landscape view allows for longer words to be visible in larger fonts and thus the Sudoku board is placed to one side of the screen to broaden the cells in the boards to showcase the longer words. The gameplay buttons are placed parallel to the sudoku board so that the user can easily click on a button and add words to the board. The core functionality of the game remains the same regardless of portrait or landscape mode.

<p float="left">
<img src="/img/landscape12x12.png" width="33%" />
</p>


<p float="left">
<img src="/img/portraitPhoneHome.png" width="33%" />
<img src="/img/landscapePhoneHome.png" width="33%" />
</p>
<br>

## Hint, Erase, Stopwatch  and Reset  Functionality - Complete

(Users have the ability to get hints if they are stuck; Erase a word if they wish to remove a specific word from the board; Reset the entire board to its initial state by removing all their edits to board)
1. User Story:  As someone who is learning to play sudoku, I would like to have a feature that helps me when I am stuck.

TDD: If a player is stuck in the game and would like to get assistance, the hint button at the bottom of the board would tell the user where a specific word on the board should be placed. This would allow the player to continue playing the game and notice patterns to learn the game

2. User Story: As a sudoku game player, I want the ability to remove a word I placed on the board.

TDD: If a player places a word on the board by mistake or if they wish to remove a word from the board, they have the ability to select that specific cell and click on the Erase button at the bottom of the screen. Once they click the Erase button, the word is removed from the board and that cell becomes empty.

3. User Story:  As a player, I want the ability to clear the whole sudoku board so that I can start again.

TDD: If a player wishes to start the game again on the same board, they have the ability to remove all the words they previously added to the board by clicking the Restart button. Once a user clicks the Restart button, all their prior edits to the board are removed and the initially generated board is shown.

3. User Story:  As a sudoku player, I want the ability to track how long it takes me to finish a game of sudoku successfully

TDD: There is a timer built in the Sudoku application that starts as soon as a selected board is generated and is only stopped once the user submits their response.

<p float="left">
<img src="/img/allBoardButtons.png" width="33%" />
</p>
<br>


## Color Coordinate Board Organization - Complete

1. As a sudoku game player I want an easy to understand color coordinated board that makes it easier to view the inner subdivided boxes of the grid.

- As soon as the game begins, a clear board is displayed to the user with words and a submit button. The teal color is used to outline the inner subdivided boxes of the grid which in this case is the 2x2 block.

<p float="left">
<img src="/img/main_submit.png" width="33%" />
</p> 
<br>

2. As a sudoku game player I want the Sudoku board to have a few words already added to the grid. These words should be distinct from the words I add to the board.

- The game begins with a few words on the board to guide the user. These words are bolded and gray in color and cannot be clicked on to alter the board. The words the user adds are blue in color and can be changed at any time.

<p float="left">
<img src="/img/addedword.png" width="33%" />
</p>
<br>

## Sudoku Solution Verification - Complete 

3. As a sudoku game player I want the ability to verify if my answers were correct after completing the board.

- Once all the cells on the board have been filled, the user can select the submit button which will validate their response. A message letting the user know if their status is visible as either Correct or incorrect.

<p float="left">
<img src="/img/correct_sol.png" width="33%" />
<img src="/img/incorrectsol.png" width="33%" />
</p>
<br>

## Hint Functionality- Complete

2.  As a novice user, I want hints for words that I don't know, so that I can still progress through the game.

- When a user gets stuck, tapping on the hint button will randomly the correct answer to a random cell.

<p float="left">
<img src="/img/hintExample.jpg" width="38%" />
</p>

## Highlight Selected cell - Complete

1.  As a novice user, the user interface user interface an easy-to-use, so that I don't get frustrated while playing.

- When a user plays the game, pressing on the grid will highlight it, and tapping on the word will place that word into the grid.

<p float="left">
<img src="/img/initialBoard.jpg" width="33%" />
<img src="/img/onInsert_initial.jpg" width="33%" /> 
<img src="/img/onInsert_done.jpg" width="33%" />
</p>

- When a user plays the game, pressing on the grid will highlight it, and then pressing the erase button will remove the word from that grid.

<p float="left">
<img src="/img/highlightCell.png" width="38%"  />
</p>


## Different devices and various grid sizes - Complete

1. As someone who wants to learn new vocabulary, I prefer playing the language learning Sudoku game on a bigger screen to better view the contents such as longer words and enhance my gameplay experience.

- Sudoku game users have the ability to play the game on devices with larger screens such as Ipad/Tablet. When the app is initially opened it detects the type of device being used and adjusts accordingly to fit the different screen sizes and displays the sudoku board and buttons in a responsive and organized manner.

<p float="left">
<img src="/img/9x9gridboard.PNG" width="33%" />
</p>
<br>

2. As someone who uses public transport regularly, I want to be able to play the Sudoku game in landscape mode to better read longer words without compromising on the functionally of the game

- When the user rotates the screen from portrait to landscape mode, the app detects the change internally and adjusts the app features accordingly. The landscape view allows for longer words to be visible in larger fonts and thus the Sudoku board is placed to one side of the screen to broaden the cells in the boards to showcase the longer words. The gameplay buttons are placed parallel to the sudoku board so that the user can easily click on a button and add words to the board. The core functionality of the game remains the same regardless of portrait or landscape mode.
<p float="left">
<img src="/img/landscape-potrait.PNG" width="55%" />
</p>





