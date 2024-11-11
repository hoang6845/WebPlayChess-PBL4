const boardSquares = document.getElementsByClassName("square");
const pieces = document.getElementsByClassName("piece");
const piecesImages = document.getElementsByTagName("img");

function setupBoardSquares() {
  for (let i = 0; i < boardSquares.length; i++) {
    let row = 8 - Math.floor(i / 8);
    let column = String.fromCharCode(97 + (i % 8));
    let square = boardSquares[i];
    square.id = column + row; // Đặt id cho ô
  }
}
function setupPieces() {
  for (let i = 0; i < pieces.length; i++) {
    pieces[i].id = pieces[i].className.split(" ")[1] + pieces[i].parentElement.id;
  }
}
setupBoardSquares();
setupPieces();