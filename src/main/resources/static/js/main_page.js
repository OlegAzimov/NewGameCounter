var countOfFields = 2
var number = 2
document.getElementById("1").childNodes.item(3).value = 1 // set first name of user to first section
document.getElementById("2").childNodes.item(1).value = 2 // set second name of user to second section 
const i = document.createElement('i')  // create icon for delete button
i.setAttribute('class', 'bi bi-x')
const a = document.createElement('a')  // create delete button for div
a.setAttribute('class', 'deleteModal')
a.setAttribute('onclick', 'deleteField(this)')
a.appendChild(i) // add icon to delete button
errorDate = document.getElementById("errorDate") // error div of date
errorPlaces = document.getElementById("errorPlaces") // error div of places
function checkPlaces() {
    let inputs = document.getElementsByClassName("place") // get all input of places
    let places = [] // create array for place values
    for (let i = 0; i < inputs.length; i++) {
        places.push(inputs[i].value)
        if (inputs[i].value < 1) { // show error places div if not valid
            errorPlaces.innerText = "Места не должны быть меньше 1"
            document.getElementById("errorPlaces").hidden = false
            return false
        }
    }
    places = places.map(place => Number(place))
    if (!places.includes(1)) { // check exists first place
        errorPlaces.innerText = "Добавьте хотя бы одно первое место"
        document.getElementById("errorPlaces").hidden = false
        return false
    }
    let maxValue = Math.max(...places)
    for (let i = 0; i < places.length; i++) { // check that (any place[2..] - previous place = 1)
        if (!places.includes(maxValue - 1) && (maxValue > 1)) {
            errorPlaces.innerText = "Разница между местами не должна превышать 1"
            document.getElementById("errorPlaces").hidden = false
            return false
        }
        maxValue--
    }
    document.getElementById("errorPlaces").hidden = true
    return true
}

function checkDate() {
    let date = document.getElementById("date").value
    var regEx = /^\d{4}-\d{2}-\d{2}$/ // check valid date format yyyy-mm-dd and not null
    if (!date.match(regEx)) {
        errorDate.innerText = "Укажите корректную дату"
        document.getElementById("errorDate").hidden = false
        return false // Invalid format
    }
    var d = new Date(date) // current date
    var from = new Date("01/01/2021").getTime()  // min date
    var to = new Date(document.getElementById("date").max).getTime() // max date
    if (d.getTime() < from) {
        errorDate.innerText = "Что за дед?"
        document.getElementById("errorDate").hidden = false
        return false // too old game
    }
    if (d.getTime() > to) {
        errorDate.innerText = "Пророк играл?"
        document.getElementById("errorDate").hidden = false
        return false // too new game
    }
    document.getElementById("errorDate").hidden = true
    return d.toISOString().slice(0, 10) === date
}

function checkNumber() {
    if (document.getElementById("number").value < 1) { // show or hide error number div if not valid
        document.getElementById("errorNumber").hidden = false
        return false
    } else {
        document.getElementById("errorNumber").hidden = true
        return true
    }
}

function checkForm() {
    if (checkNumber() && checkDate() && checkPlaces()) { // send form if valid
        document.getElementById("addPlayedGameAndScore").submit()
    }
}

function deleteField(a) { // method in use
    var contDiv = a.parentNode  // get parent of delete button
    let deleteId = (contDiv.getAttribute("id"))
    if (countOfFields > 1 && deleteId > 2) { // can't delete first and second div
        contDiv.parentNode.removeChild(contDiv)
        countOfFields--
        window.number = deleteId - 1
        return false
    }
}

function addField(number) {
    if (countOfFields >= maxFieldLimit) {
        alert("Число полей достигло своего максимума = " + maxFieldLimit)
        return false
    }
    var form = document.forms[0] //get addPlayedGameAndScore form
    let div = document.getElementById(number) //get previous div
    let new_div = div.cloneNode(true) //copy div
    new_div.setAttribute("id", number + 1) //set next id
    if (number === 2) { //check if number 2 -> add delete button to div
        let child = new_div.childNodes.item(1)
        new_div.insertBefore(a, child) // insert delete button in div
    }
    new_div.childNodes.item(2).value = number + 1 // set next name in section
    let placeInput = new_div.childNodes.item(3) // get input place
    let oldPlace = parseInt(placeInput.value) // get input place value
    placeInput.value = oldPlace + 1 // set new place value to input
    window.number = number + 1
    form.appendChild(new_div) // add new div to form
    countOfFields++
}
