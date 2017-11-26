function SendGet(input_str) {
         var inputText = input_str;

         $.ajax({
         url : '/task/view',
         type: 'GET',
         dataType: 'json',
         contentType: 'application/json',
             mimeType: 'application/json',
         data : ({
           taskId: inputText
         }),
         success: function (data) {

         var result = data.task.title+', '+data.task.id;
         console.log(result);
        var id = data.task.id;
        $("#taskIdView").text(id);
		var title = document.getElementById("taskTitleView");
		title.value = data.task.title;
		var author = document.getElementById("taskAuthorView");
        author.value = data.user;
		var body = document.getElementById("taskBodyView");
		body.value = data.task.body;
		var dateCreated = document.getElementById("taskDateCreateView");
		dateCreated.value = new Date(),
            yyyy = date.getFullYear().toString(),
            mm = (date.getMonth()+1).toString(),
            dd  = date.getDate().toString(),
            mmChars = mm.split(''),
            ddChars = dd.split(''),
            datestring = yyyy + '-' + (mmChars[1]?mm:"0"+mmChars[0]) + '-' + (ddChars[1]?dd:"0"+ddChars[0]);
         }
         });
        }