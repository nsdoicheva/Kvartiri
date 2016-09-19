function validateForm() {
    var a = document.forms["login"]["username"].value;
    var b = document.forms["login"]["password"].value;
    var c = document.forms["login"]["email"].value;

    if (a === null || a === "" || b === null || b === "" || c === null || c === "" ) {
        alert("Попълни всички полета!");
        return false;
    } else {
        alert("OK!");
        confirm('Искаш ли да изпратиш информацията?')
    }
}

function refuse() {
    confirm('Are you sure you want to reset the page?');
}
