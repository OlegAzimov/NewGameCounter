const sidebar = document.querySelector('.sidebar');
sidebar.querySelector('.blocker').onclick = hide;

function show() { // swipe right
    sidebar.classList.add('visible');
    document.body.style.overflow = 'hidden';
}

function hide() { // by blocker click, swipe left, or url change
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
