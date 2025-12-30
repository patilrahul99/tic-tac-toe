const boardEl = document.getElementById("board");
const resultEl = document.getElementById("result");
const winLine = document.getElementById("win-line");

let gameOver = false;

function init() {
    boardEl.innerHTML = "";
    gameOver = false;
    resultEl.innerHTML = "";
    winLine.style.width = "0";

    for (let i = 0; i < 9; i++) {
        const c = document.createElement("div");
        c.className = "cell";
        c.onclick = () => move(i);
        boardEl.appendChild(c);
    }
}

function move(index) {
    if (gameOver) return;

    const difficulty = document.getElementById("difficulty").value;

    fetch(`/api/game/move?index=${index}&difficulty=${difficulty}`, {
        method: "POST"
    })
        .then(r => r.json())
        .then(updateBoard);
}

function updateBoard(state) {
    document.querySelectorAll(".cell").forEach((c, i) => {
        c.className = "cell";
        if (state.board[i] === 'X') c.classList.add("x");
        if (state.board[i] === 'O') c.classList.add("o");
    });

    if (state.gameOver) {
        gameOver = true;
        showResult(state.winner);
        setTimeout(resetGame, 2500);
    }
}

function showResult(w) {
    let msg = w === 'X' ? "😄 You Won!" :
        w === 'O' ? "😢 You Lost!" :
            "🤝 Draw";

    resultEl.innerHTML = msg;
}

function resetGame() {
    fetch("/api/game/reset").then(init);
}

init();
