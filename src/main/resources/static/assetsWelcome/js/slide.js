window.onload = function () {
    const highlight =document.querySelector(".highlight");
    const offsets = highlight.getBoundingClientRect();
    const highlightButton = document.querySelector(".highlight-button");
    highlightButton.addEventListener("click",function () {
        window.scroll({top: offsets.bottom,
                behavior:'smooth'});
    },false);
};