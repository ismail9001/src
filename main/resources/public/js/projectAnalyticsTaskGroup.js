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
             var newTasks = data.newTasks;
             $("#newTasks").text(newTasks);
             var inProgressTasks = data.inProgressTasks;
             $("#progressTasks").text(inProgressTasks);
             var doneTasks = data.doneTasks;
             $("#doneTasks").text(doneTasks);
             }
         });
}