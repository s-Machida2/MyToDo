document.addEventListener("DOMContentLoaded", function(event) {
    var fadeImage = document.getElementById("fadeImage");
    fadeImage.style.opacity = 1; // 最初は透明にする

    window.onload = function() {
        fadeImage.style.opacity = 0; // ページの読み込みが完了したら不透明にする
    };
});