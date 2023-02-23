
#Implemented Functions - User stories


1.  As a sudoku game player I want an easy to understand color coordinated board that makes it easier to view the inner subdivided boxes of the grid.


- As soon as the game begins, a clear board is displayed to the user. The teal color is used to outline the inner subdivided boxes of the
 grid which in this case is the 3x3 block.

    <p float="left">
        <img src="/img/main.submit.png" width="33%" />
    </p>
    
    <br>


2.  As a sudoku game player I want the Sudoku board to have a few words already added to the grid. These words should be distinct from the words I add to the board.

    - The game begins with a few words on the board to guide the user. These words are bolded and gray in color and cannot be clicked on to alter the board. The words the user adds are blue in color and can be changed at any time.

    <p float="left">
        <img src="/img/main.addedword.png" width="33%" />
    </p>
     <br>
3.   As a sudoku game player I want the ability to verify if my answers were correct after completing the board.

- Once all the cells on the board have been filled, the user can select the submit button which will validate their response. A message letting the user know if their status is visible as either Correct or incorrect.

    <p float="left">
        <img src="/img/main.correctsol.png" width="33%" />
        <img src="/img/main.incorrectsol.png" width="33%" />
    </p>
    <br>
    




#Different devices




1. As someone who wants to learn new vocabulary, I prefer playing the language learning Sudoku game on a bigger screen to better view the contents such as longer words and enhance my gameplay experience.
    - Sudoku game users have the ability to play the game on devices with larger screens such as Ipad/Tablet. When the app is initially opened it detects the type of device being used and adjusts accordingly to fit the different screen sizes and displays the sudoku board and buttons in a responsive and organized manner.

    <p float="left">
        <img src="/img/main.9x9gridboard.png" width="33%" />
    </p>
    <br>

2. As someone who uses public transport regularly, I want to be able to play the Sudoku game in landscape mode to better read longer words without compromising on the functionally of the game
    -  When the user rotates the screen from portrait to landscape mode, the app detects the change internally and adjusts the app features accordingly. The landscape view allows for longer words to be visible in larger fonts and thus the Sudoku board is placed to one side of the screen to broaden the cells in the boards to
     showcase the longer words. The gameplay buttons are placed parallel to the sudoku board so that the user can easily click on a button and add words to the board. The core functionality of the game remains the same regardless of portrait or landscape mode.
    <p float="left">
    <img src="/img/main.landscape-potrait.png" width="33%" />
    </p>



# Different Size Sudoku Grids



1. As a language teacher, I want to personalize the game for my students based on their level of understanding and to meet their language learning goals without making the game unnecessarily difficult or easy.
    - As soon as the app is opened the user can pick the level of difficulty they would like to play with. Each level corresponds to a grid size, for example an Easy level would be of grid size 4x4, Medium is grid size 6x6 and Difficult is grid size 9x9 and Extra difficult to 12x12.

    <p float="left">
    <img src="/img/main.4x4gridboard.png" width="33%" />
    <img src="/img/main.6x6gridboard.png" width="33%" />
    </p>

2. As a language learner who is an expert in Sudoku, I would like to play in a challenging mode which has a grid size of 12x12 so I can learn many new words and enjoy the game.
    - On the menu page, the user can select the level of difficulty they would like to play with and there is an option to play on a 12x12 board for an extra challenge. Once the user selects that option, a 12x12 board is generated on the screen with 12 corresponding word pairs.
    <p float="left">
    <img src="/img/main.12x12gridboard.png" width="33%" />
    </p>
