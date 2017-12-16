$(document).ready(function(){

    $("#reset_password_form").validate ({
        rules:{
            password: {
                required: true,
                minlength: 6,
                maxlength: 150
            }
        },
        messages:{
            password: {
                required: "Password is required",
                minlength: "Your password must have at least 6 characters",
                maxlength: "Your password can contain a maximum of 150 characters"
            }
        }
    });
});