function ShowTasksGroup(input_str) {
         var inputText = input_str;

         $.ajax({
             url : '/dashboardProjectsTaskGroups',
             type: 'GET',
             dataType: 'json',
             contentType: 'application/json',
                 mimeType: 'application/json',
             data : ({
                projectId: inputText
             }),
             success: function (data) {
             if(typeof data.New === 'undefined')
             $("#newTasks").text(0);
             else var newTasks = data.New;
             $("#newTasks").text(newTasks);
             if(typeof data['In work'] === 'undefined')
             $("#progressTasks").text(0);
             else var inProgressTasks = data['In work'];
             $("#progressTasks").text(inProgressTasks);
             if(typeof data.Done === 'undefined')
             $("#doneTasks").text(0);
             else var doneTasks = data.Done;
             $("#doneTasks").text(doneTasks);
             }
         });
}