function validateRegistrationForm(form) {
    console.log("JUST HERE");
    var password = form.elements["password"].value;
    var passwordRepeatErrorElement = document.getElementById("password-repeat-error");
    if (password !== form.elements["password-repeat"].value){
        passwordRepeatErrorElement.style.display = 'block';
        console.log("HERE FALSE");
        return false;
    } else {
        console.log("HERE TRUE");
        passwordRepeatErrorElement.style.display = 'none';
    }
}