var countOfFields = 1;
var maxFieldLimit = 12;
var number = 2;
function checkForm() {
    let validated = true
    let validNumber = document.getElementById("number").value < 1
    if(validNumber){
        validated = false
        document.getElementById("errorNumber").hidden = false
    }
    let placeArray = document.getElementsByClassName("place")
    for (let i = 0; i < placeArray.length; i++) {
        if (placeArray[i].value < 1){
            validated = false
            document.getElementById("errorPlaces").hidden = false
        }
    }
    if(validated) {
        document.getElementById("addPlayedGameAndScore").submit()
    }
}
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
    var form = document.forms[0]
    let div = document.getElementById(number)
    let new_div = div.cloneNode(true)
    new_div.setAttribute("id", number + 1)
    let oldPlace = parseInt(new_div.childNodes.item(6).value)
    new_div.childNodes.item(6).value = oldPlace + 1
    window.number = number + 1
    form.appendChild(new_div)
}
