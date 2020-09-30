// General-purpose actions in the game.

Game.do = (function() {
  /**
   * A function for adding a disc to our Connect Four board state.
   *
   * @param number x_pos The x-position of the location chosen.
   * @param number y_pos The y-position of the location chosen.
   */
  function addDiscToBoard(x_pos, y_pos) {
    Game.board[y_pos][x_pos] = Game.currentPlayer;
  }

  /**
   * Print the contents of our Game.board state to the html page.
   */
  function printBoard() {
    var row, cell;
    for (var y = 0; y <= Game.config.boardHeight; y++) {
      for (var x = 0; x <= Game.config.boardLength; x++) {
        if (Game.check.isPositionTaken(x, y)) {
          row = document.querySelector('tr:nth-child(' + (1 + y) + ')');
          cell = row.querySelector('td:nth-child(' + (1 + x) + ')');
          cell.firstElementChild.classList.add(Game.board[y][x]);
        }
      }
    }
  }

  /**
   * A function for changing players both in state and on the screen.
   */
  function changePlayer() {
    var currentPlayerNameEl = document.querySelector('#current-player');
    var otherPlayerNameEl = document.querySelector('#other-player');

    // Switch players
    var otherPlayer = Game.currentPlayer
    var otherPlayerName = currentPlayerNameEl.textContent;
    var currentPlayerName = otherPlayerNameEl.textContent;
    Game.currentPlayer = (Game.currentPlayer === 'black') ? 'red' : 'black';


    // Update the players in the UI.
    currentPlayerNameEl.classList.remove(otherPlayer);
    currentPlayerNameEl.classList.add(Game.currentPlayer);
    currentPlayerNameEl.textContent = currentPlayerName;

    otherPlayerNameEl.classList.remove(Game.currentPlayer);
    otherPlayerNameEl.classList.add(otherPlayer);
    otherPlayerNameEl.textContent = otherPlayerName;

  }

  /**
   * If there are empty positions below the one chosen, return the new y-position
   * we should drop the piece to.
   *
   * @param number x_pos The x-position of the location chosen.
   * @param number y_pos The y-position of the location chosen.
   * @return number - The y-position the disc should fall into.
   */
  function dropToBottom(x_pos, y_pos) {
    // Start at the bottom of the column, and step up, checking to make sure
    // each position has been filled. If one hasn't, return the empty position.
    for (var y = Game.config.boardHeight; y > y_pos; y--) {
      if (!Game.check.isPositionTaken(x_pos, y)) {
        return y;
      }
    }
    return y_pos;
  }



  return {
    addDiscToBoard,
    printBoard,
    changePlayer,
    dropToBottom

  };
})();



// General-purpose status checks for the game.

Game.check = (function() {
  /**
   * Test to ensure the chosen location isn't taken.
   *
   * @param number x_pos The x-position of the location chosen.
   * @param number y_pos The y-position of the location chosen.
   * @return bool returns true or false for the question "Is this spot taken?".
   */
  function isPositionTaken(x_pos, y_pos) {
    return Game.board[y_pos][x_pos] != 0;
  }

  /**
   * Determine if the game is a draw (all peices on the board are filled).
   *
   * @return bool Returns true or false for the question "Is this a draw?".
   */
  function isGameADraw() {
    for (var y = 0; y <= Game.config.boardHeight; y++) {
      for (var x = 0; x <= Game.config.boardLength; x++) {
        if (!isPositionTaken(x, y)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Test to see if somebody got four consecutive horizontal pieces.
   *
   * @return bool Returns true if a win was found, and otherwise false.
   */
  function isHorizontalWin() {

    // No horizontal win was found.
    return false;
  }

  /**
   * Test to see if somebody got four consecutive vertical pieces.
   *
   * @return bool Returns true if a win was found, and otherwise false.
   */
  function isVerticalWin() {

    // No vertical win was found.
    return false;
  }

  /**
   * Test to see if somebody got four consecutive diagonel pieces.
   *
   * @return bool Returns true if a win was found, and otherwise false.
   */
  function isDiagonalWin() {

    return false;
  }

 return {
   isPositionTaken,
   isGameADraw

 }

})();
