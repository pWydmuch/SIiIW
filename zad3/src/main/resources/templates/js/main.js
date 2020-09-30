// Setup the main game logic.

(function () {
  var prefixEl = document.querySelector('#prefix');
  var primaryTextEl = document.querySelector('.primary');
  var secondaryTextEl = document.querySelector('.secondary');
  var currentPlayerNameEl = document.querySelector('#current-player');
  var otherPlayerNameEl = document.querySelector('#other-player');
  var playAgainEl = document.querySelector('#play-again');
  var playAgainBtnEl = document.querySelector('#play-again-btn');
  var gameBoardEl = document.querySelector('#board');
  var resetBtn = document.querySelector('#reset-button');

  // playAgainBtnEl.addEventListener('click', () => location.reload());
  gameBoardEl.addEventListener('click', placeGamePiece);
  resetBtn.addEventListener('click',async ()=>{
      await fetch(`http://localhost:8080/reset`);
    location.reload();
  })
  // currentPlayerNameEl.addEventListener("keydown", Game.do.handleNameChange);
  // otherPlayerNameEl.addEventListener("keydown", Game.do.handleNameChange);

  async function placeGamePiece(e) {
    if (e.target.tagName !== 'BUTTON') return;

    var targetCell = e.target.parentElement;
    var targetRow = targetCell.parentElement;
    var targetRowCells = [...targetRow.children];
    var gameBoardRowsEls = [...document.querySelectorAll('#board tr')];

    // Detect the x and y position of the button clicked.
    var y_pos = gameBoardRowsEls.indexOf(targetRow);
    var x_pos = targetRowCells.indexOf(targetCell);

    // Ensure the piece falls to the bottom of the column.
    y_pos = Game.do.dropToBottom(x_pos, y_pos);

    if (Game.check.isPositionTaken(x_pos, y_pos)) {
      alert(Game.config.takenMsg);
      return;
    }
        // console.log(Game.board);
    // Add the piece to the board.
    // Game.do.addDiscToBoard(x_pos, y_pos);
    const respone = await fetch(`http://localhost:8080/X/${x_pos}`)
        var res= await respone.json();
    console.log(res);
    Game.board = res.grid;
    var checkIfWinner = res.checkIfWinner;
    let winner;
    if(checkIfWinner){
      winner = res.winner
    }
    Game.do.printBoard();

    // Check to see if we have a winner.
    if (checkIfWinner==1) {

      gameBoardEl.removeEventListener('click', placeGamePiece);
      prefixEl.textContent = Game.config.winMsg;
      currentPlayerNameEl.contentEditable = false;
      secondaryTextEl.remove();
      playAgainEl.classList.add('show');
      if(winner=="red")
          playAgainBtnEl.innerText="Wygraly czerwone";
      else
          playAgainBtnEl.innerText="Wygraly czarne";
      return;
    } else if (Game.check.isGameADraw()) {
      gameBoardEl.removeEventListener('click', placeGamePiece);
      primaryTextEl.textContent = Game.config.drawMsg;
      secondaryTextEl.remove();
      playAgainEl.classList.add('show');
        playAgainBtnEl.innerText="Remis";
      return;
    }

    // Change player.
    Game.do.changePlayer();
  };

})();
