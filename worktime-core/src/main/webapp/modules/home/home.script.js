
function menu_open() {
    document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
    document.getElementsByClassName("w3-overlay")[0].style.display = "block";
}
function menu_close() {
    document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
    document.getElementsByClassName("w3-overlay")[0].style.display = "none";
}

// Change style of top container on scroll
window.onscroll = function() {myFunction()};
function myFunction() {
    if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
        document.getElementById("smallHeader").classList.add("w3-card-4");
        document.getElementById("headerSpan").classList.add("w3-show-inline-block");
    } else {
        document.getElementById("headerSpan").classList.remove("w3-show-inline-block");
        document.getElementById("smallHeader").classList.remove("w3-card-4");
    }
}