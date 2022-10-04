var countOfFields = 1;
var maxFieldLimit = 12;
var xhr = new XMLHttpRequest();
function deleteField(a) {
    var contDiv = a.parentNode;
    if(countOfFields > 1){
    contDiv.parentNode.removeChild(contDiv);
    countOfFields--;

    return false;}
}
function addField() {
    if (countOfFields >= maxFieldLimit) {
        alert("Число полей достигло своего максимума = " + maxFieldLimit);
        return false;
    }
    countOfFields++;
    var form = document.forms[0];
    var div = form.getElementsByTagName('div')[0];
    var new_div = div.cloneNode(true);
    form.appendChild(new_div);

}
