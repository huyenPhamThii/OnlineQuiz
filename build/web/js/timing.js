//let minutes, seconds;
//window.onload = function () {
//    var duration = document.getElementById("secs").value;
//    var timer = setInterval(countDown(duration), 1000);
//};
//function countDown(duration) {
//    minutes = parseInt(duration / 60, 10);
//    seconds = parseInt(duration % 60, 10);
//
//    minutes = minutes < 10 ? "0" + minutes : minutes;
//    seconds = seconds < 10 ? "0" + seconds : seconds;
//
//    document.getElementById("timer").innerHTML = minutes + ":" + seconds;
//
//    duration--;
//    if (duration < 0) {
//        clearInterval(timer);
//        document.getElementById("doQuiz").submit();
//    }
//}

function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
            clearInterval(timer);
            document.getElementById("doQuiz").submit();
        }
    }, 1000);
}

window.onload = function () {
    var fiveMinutes = document.getElementById("secs").value,
            display = document.querySelector('#timer');
    startTimer(fiveMinutes, display);
};

