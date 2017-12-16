$(document).ready(function(){

    $("#forgot_password_form").validate ({
        rules:{
            email: {
                required: true,
                email: true
            }
        },
        messages:{
            email: {
                required: "E-mail is required",
                email: "Provide an email"
            }
        }
    });
});