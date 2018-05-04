function validateRegistrationForm(form) {
    var password = form.elements["password"].value;
    var passwordRepeatErrorElement = document.getElementById("password-repeat-error");
    if (password !== form.elements["password-repeat"].value){
        passwordRepeatErrorElement.style.display = 'block';
        return false;
    } else {
        passwordRepeatErrorElement.style.display = 'none';
    }
}