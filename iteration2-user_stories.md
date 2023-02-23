
# Implemented Functions - User stories and TDD


1.  As a sudoku game player I want an easy to understand color coordinated board that makes it easier to view the inner subdivided boxes of the grid.


- As soon as the game begins, a clear board is displayed to the user with words and a submit button. The teal color is used to outline the inner subdivided boxes of the
 grid which in this case is the 2x2 block.

    <p float="left">
        <img src="/img/main_submit.png" width="33%" />
    </p>
    
    <br>


2.  As a sudoku game player I want the Sudoku board to have a few words already added to the grid. These words should be distinct from the words I add to the board.

    - The game begins with a few words on the board to guide the user. These words are bolded and gray in color and cannot be clicked on to alter the board. The words the user adds are blue in color and can be changed at any time.

    <p float="left">
        <img src="/img/addedword.png" width="33%" />
    </p>
     <br>
3.   As a sudoku game player I want the ability to verify if my answers were correct after completing the board.

- Once all the cells on the board have been filled, the user can select the submit button which will validate their response. A message letting the user know if their status is visible as either Correct or incorrect.

    <p float="left">
        <img src="/img/correct_sol.png" width="33%" />
        <img src="/img/incorrectsol.png" width="33%" />
    </p>
    <br>
    


Iteration 3 - To be implemented features

# Additional Requirements - User Stories and TDD

# Different Devices


1. As someone who wants to learn new vocabulary, I prefer playing the language learning Sudoku game on a bigger screen to better view the contents such as longer words and enhance my gameplay experience.
    - Sudoku game users have the ability to play the game on devices with larger screens such as Ipad/Tablet. When the app is initially opened it detects the type of device being used and adjusts accordingly to fit the different screen sizes and displays the sudoku board and buttons in a responsive and organized manner.

    <p float="left">
        <img src="/img/9x9gridboard.PNG" width="33%" />
    </p>
    <br>

2. As someone who uses public transport regularly, I want to be able to play the Sudoku game in landscape mode to better read longer words without compromising on the functionally of the game
    -  When the user rotates the screen from portrait to landscape mode, the app detects the change internally and adjusts the app features accordingly. The landscape view allows for longer words to be visible in larger fonts and thus the Sudoku board is placed to one side of the screen to broaden the cells in the boards to
       showcase the longer words. The gameplay buttons are placed parallel to the sudoku board so that the user can easily click on a button and add words to the board. The core functionality of the game remains the same regardless of portrait or landscape mode.
    <p float="left">
    <img src="/img/landscape-potrait.PNG" width="33%" />
    </p>



# Different Size Sudoku Grids



1. As a language teacher, I want to personalize the game for my students based on their level of understanding and to meet their language learning goals without making the game unnecessarily difficult or easy.
    - As soon as the app is opened the user can pick the level of difficulty they would like to play with. Each level corresponds to a grid size, for example an Easy level would be of grid size 4x4, Medium is grid size 6x6 and Difficult is grid size 9x9 and Extra difficult to 12x12.

    <p float="left">
    <img src="/img/4x4gridboard.PNG" width="33%" />
    <img src="/img/6x6gridboard.PNG" width="33%" />
    </p>

2. As a language learner who is an expert in Sudoku, I would like to play in a challenging mode which has a grid size of 12x12 so I can learn many new words and enjoy the game.
    - On the menu page, the user can select the level of difficulty they would like to play with and there is an option to play on a 12x12 board for an extra challenge. Once the user selects that option, a 12x12 board is generated on the screen with 12 corresponding word pairs.
    <p float="left">
    <img src="/img/12x12gridboard.PNG" width="33%" />
    </p>





For the upcoming iteration, we hope to implement the above outlined features as well additional gameplay features to enhance the user's language learning experience.
Below are the features that have carried over from Iteration 1 which we we will be implementing for Iteration 3:

1.  As a novice user, the user interface user interface an easy-to-use , so that I don't get frustrated while playing.

    - When a user plays the game, pressing on the grid will highlight it, and tapping on the word will place that word into the grid.

    <p float="left">
    <img src="/img/initialBoard.jpg" width="33%" />
    <img src="/img/onInsert_initial.jpg" width="33%" /> 
    <img src="/img/onInsert_done.jpg" width="33%" />
    </p>

    - When a user plays the game, pressing on the grid will highlight it, and then pressing the delete button will remove the word from that grid.

    <p float="left">
    <img src="/img/onDelete_initial.jpg" width="33%" />
    <img src="/img/onDelete_2.jpg" width="33%" /> 
    <img src="/img/onDelete_done.jpg" width="33%" />
    </p>

    - When the user clicks on the undo button, the most recent action is reverted

    <p float="left">
    <img src="/img/undoimg.jpg" width="33%" />
    </p>
    
    <br>

2.  As a novice user, I want hints for words that I don't know, so that I can still progress through the game.

    - When a user gets stuck, tapping on hint button will randomly fill in a grid with the correct answer.

    <p float="left">
    <img src="/img/onHint_initial.jpg" width="49%" />
    <img src="/img/onHint_done.jpg" width="49%" />
    </p>

    - When a user doesn't know a word, highlighting a word then tapping on the hint button will provide an explanation of the word.

    <br>

4.  As a language learner, I want to progressively learn new word sets, so that I can feel a sense of accomplishment as I learn the language.
    - When a user selects to play the progressive levels gamemode, they will be given levels that they must complete before moving onto the next. Each level will not necessarily get progressively harder, rather each level is a defined category of words the user will be focusing on and to beat the level the user must complete the sudoku within a time limit. Users who try to advance to the next level without completing the previous will be prompted with an error that tells them the level they must complete to unlock this.

    <img src="/img/progressive.png" width="70%" />
    <br>

5.  As a novice user, I want a help menu, to learn the rules of the game and to learn about app features 

    - When a user selects the 'Help' button, a dialog pops up on the screen to explain the rules of the game

    <p float="left">
    <img src="/img/help.jpg" width="49%">
    <img src="/img/help2.jpg" width="49%">
    </p>

    <br>

6.  As an expert user, I want to see where I rank in comparison to others and thus want a leaderboard so that I can showcase my learning accomplishments.

    - While a user is playing the game, there is a timer to keep track of the time. Once the game is completed, the user has the option to enter the score in the leaderboard. The leaderboard is categorized by difficulty, and displays the username and time taken, ranked in terms of time.

    <img src="/img/leaderboard.jpg" width="70%" />

    <br>

7.  As a language learner, I want an option to change to another language, so that there are more varieties of languages to learn.

    - When the user opens the app, they are shown the Home page which includes a dropdown from which the user can select the language they are interested in learning. Once the user clicks on one of the language options, they are able to add word pairs into the Dictionary. Once the desired words they wish to play with are added to the Dictionary, they can select the “Start game” button and a new Sudoku game with those words is created.

    <p float="left">
    <img src="/img/language.jpg" width="49%">
    <img src="/img/language2.jpg" width="49%">
    </p>

    <br>

8.  As a language teacher, I want to add new words, so that my students can learn specific words and enrich their vocabulary pool.

    - On the home page, there is a button that allows the user to add words to a Dictionary. The user will be able to add word pairings by filling in textboxes and clicking the “Add to Dictionary” button to add the pair of words to the Dictionary. Once at least nine words have been added to the dictionary, users have the ability to select which words they would like to generate a Sudoku board with. Lastly, by clicking on the start game button, a Sudoku board with words from the language the user knows appear on the board while words from the language the user is trying to learn are in the form of buttons.

    <p float="left">
    <img src="/img/dictionary.jpg" width="49%">
    <img src="/img/dictionary2.jpg" width="49%">
    </p>

    <img src="/img/dictionary3.jpg" width="49%">

