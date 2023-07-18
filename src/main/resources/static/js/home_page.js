let name = localStorage.getItem('userName');
const phrases = [
    'Ты сосал, тебя ебали',
    'Готовь очко',
    'Ну ты дырявый!',
    'Качай волю',
    'Не будь подсосником',
    'Харе ныть, еби их',
    'БУМ ШАКАЛАКА',
    'Где бассейн?',
    'Играл в тюрьму?',
    name + ' лох, объелся блох',
];

function getRandomElementFromArray(arr) {
    const randomIndex = Math.floor(Math.random() * arr.length);
    return arr[randomIndex];
}


var AlertBox = function (id, option) {
    this.show = function (msg) {
        var alertArea = document.querySelector(id);
        var alertBox = document.createElement('div');
        var alertContent = document.createElement('DIV');
        var alertClass = this;
        alertContent.classList.add('alert-content');
        alertContent.innerText = msg;
        alertBox.classList.add('alert-box');
        alertBox.appendChild(alertContent);
        alertArea.appendChild(alertBox);
        if (!option.persistent) {
            var alertTimeout = setTimeout(function () {
                alertClass.hide(alertBox);
                clearTimeout(alertTimeout);
            }, option.closeTime);
        }
    };

    this.hide = function (alertBox) {
        alertBox.classList.add('hide');
        var disperseTimeout = setTimeout(function () {
            alertBox.parentNode.removeChild(alertBox);
            clearTimeout(disperseTimeout);
        }, 500);
    };
};

var alertNoClose = new AlertBox('#alert-area', {
    closeTime: 2500,
    persistent: false,
});


document.querySelector('#svg').addEventListener('click', function () {
    alertNoClose.show(getRandomElementFromArray(phrases));
});