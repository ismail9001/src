$(document).ready(function(){

    $("#registration_form").validate ({
        rules:{
            username: {
                required: true,
                minlength: 2,
                maxlength: 150
            },
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 150
            }
        },
        messages:{
            username: {
                required: "Username is required",
                minlength: "Your username must have at least 2 characters",
                maxlength: "Your username can contain a maximum of 150 characters"
            },
            email: {
                required: "E-mail is required",
                email: "Provide an email"
            },
            password: {
                required: "Password is required",
                minlength: "Your password must have at least 6 characters",
                maxlength: "Your password can contain a maximum of 150 characters"
            }
        }
    });

});