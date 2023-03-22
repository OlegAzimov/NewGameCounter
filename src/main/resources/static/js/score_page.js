const sidebar = document.querySelector('.sidebar');
const body = document.querySelector('body');
sidebar.querySelector('.blocker').onclick = hide;

function show() { // click on button, swipe left
    sidebar.classList.add('visible');
    document.body.style.overflow = 'hidden';
}

function hide() { // by blocker click, swipe right
    sidebar.classList.remove('visible');
    document.body.style.overflow = '';
}

function toggle() {
    sidebar.classList.contains('visible') ? hide() : show();
}

window.toggle = toggle;

var checkboxValues = JSON.parse(localStorage.getItem('checkboxValues')) || {};
var $checkboxes = $("#checkbox-container :checkbox");
var $barCheckboxes = $("#bar-checkbox-container :checkbox");
$checkboxes.on("change", function () {
    $checkboxes.each(function () {
        checkboxValues[this.id] = this.checked;
    });
    localStorage.setItem("checkboxValues", JSON.stringify(checkboxValues));
});
$barCheckboxes.on("change", function () {
    $barCheckboxes.each(function () {
        checkboxValues[this.id] = this.checked;
    });
    localStorage.setItem("checkboxValues", JSON.stringify(checkboxValues));
});
$.each(checkboxValues, function (key, value) {
    $("#" + key).prop('checked', value);
    $("#" + "bar" + key).prop('checked', value);
})

function allUncheck() {
    document.getElementsByName('gameList').forEach(el => {
        el.checked = false
    });
    $checkboxes.each(function () {
        checkboxValues[this.id] = false;
    });
    $barCheckboxes.each(function () {
        checkboxValues[this.id] = false;
    });
    localStorage.setItem("checkboxValues", JSON.stringify(checkboxValues));
}

function allCheck() {
    document.getElementsByName('gameList').forEach(el => {
        el.checked = true
    })
    $checkboxes.each(function () {
        checkboxValues[this.id] = true;
    });
    $barCheckboxes.each(function () {
        checkboxValues[this.id] = true;
    });
    localStorage.setItem("checkboxValues", JSON.stringify(checkboxValues));
}

let touchStart, touchEnd
body.addEventListener(
    'touchstart',
    e => (touchStart = e.targetTouches[0].clientX)
)
body.addEventListener(
    'touchmove',
    e => (touchEnd = e.targetTouches[0].clientX)
)
body.addEventListener('touchend', e => {
    if (touchStart - touchEnd < -100 && touchStart !== 0 && touchEnd !== 0) {
        hide()
    }
    if (touchStart - touchEnd > 100 && touchStart !== 0 && touchEnd !== 0) {
       show()
    }
    touchStart = 0
    touchEnd = 0
})