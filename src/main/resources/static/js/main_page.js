var countOfFields = 1;
var maxFieldLimit = 12;

function deleteField(a) {
    var contDiv = a.parentNode;
    let deleteId = (contDiv.getAttribute("id"))
    if (countOfFields > 1 && deleteId > 2) {
        contDiv.parentNode.removeChild(contDiv);
        countOfFields--;
        window.number = deleteId - 1
        return false;
    }
}

function addField(number) {
    if (countOfFields >= maxFieldLimit) {
        alert("Число полей достигло своего максимума = " + maxFieldLimit);
        return false;
    }
    countOfFields++;
    var form = document.forms[1]
    let div = document.getElementById(number)
    let new_div = div.cloneNode(true)
    new_div.setAttribute("id", number + 1)
    let oldPlace = parseInt(new_div.childNodes.item(6).value)
    new_div.childNodes.item(6).value = oldPlace + 1
    window.number = number + 1
    form.appendChild(new_div)
}
